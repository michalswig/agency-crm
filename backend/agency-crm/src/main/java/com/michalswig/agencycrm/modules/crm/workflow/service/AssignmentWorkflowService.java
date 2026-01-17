package com.michalswig.agencycrm.modules.crm.workflow.service;

import com.michalswig.agencycrm.modules.crm.domain.Assignment;
import com.michalswig.agencycrm.modules.crm.domain.AssignmentStatus;
import com.michalswig.agencycrm.modules.crm.domain.WorkflowEntityType;
import com.michalswig.agencycrm.modules.crm.domain.WorkflowStatusChangeAudit;
import com.michalswig.agencycrm.modules.crm.repository.AssignmentRepository;
import com.michalswig.agencycrm.modules.crm.repository.WorkflowStatusChangeAuditRepository;
import com.michalswig.agencycrm.modules.crm.workflow.dto.AssignmentStatusChangeCommand;
import com.michalswig.agencycrm.modules.crm.workflow.exception.WorkflowEntityNotFoundException;
import com.michalswig.agencycrm.modules.crm.workflow.exception.WorkflowValidationException;
import com.michalswig.agencycrm.modules.crm.workflow.validator.AssignmentStatusTransitionValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class AssignmentWorkflowService {

    private final AssignmentRepository assignmentRepository;
    private final WorkflowStatusChangeAuditRepository workflowStatusChangeAuditRepository;

    private final AssignmentStatusTransitionValidator validator = new AssignmentStatusTransitionValidator();

    public AssignmentWorkflowService(AssignmentRepository assignmentRepository, WorkflowStatusChangeAuditRepository workflowStatusChangeAuditRepository) {
        this.assignmentRepository = assignmentRepository;
        this.workflowStatusChangeAuditRepository = workflowStatusChangeAuditRepository;
    }

    @Transactional
    public void changeStatus(Long assigmentId, AssignmentStatus newStatus, AssignmentStatusChangeCommand command) {

        if(command.actorUserId() == null){
            throw new WorkflowValidationException("Actor Id is required");
        }

        Assignment assignment = assignmentRepository.findById(assigmentId)
                .orElseThrow(() -> new WorkflowEntityNotFoundException("Assignment with id " + assigmentId + " not found"));

        AssignmentStatus from = assignment.getStatus();
        validator.validate(from, newStatus);

        if (newStatus == AssignmentStatus.CLOSED && command.closeReason() == null) {
            throw new WorkflowValidationException("Close reason required");
        }

        if (newStatus == AssignmentStatus.CLOSED) {
            assignment.close(command.closeReason(), command.note());
        } else {
            assignment.changeStatus(newStatus);
        }

        assignmentRepository.save(assignment);

        workflowStatusChangeAuditRepository.save(new WorkflowStatusChangeAudit(
                WorkflowEntityType.ASSIGNMENT,
                assigmentId,
                from.name(),
                newStatus.name(),
                command.actorUserId(),
                OffsetDateTime.now(),
                command.closeReason() != null ? command.closeReason().name() : null,
                command.note()
        ));
    }
}