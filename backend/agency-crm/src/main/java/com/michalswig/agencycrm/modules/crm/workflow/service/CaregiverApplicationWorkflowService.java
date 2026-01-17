package com.michalswig.agencycrm.modules.crm.workflow.service;

import com.michalswig.agencycrm.modules.crm.domain.ApplicationStatus;
import com.michalswig.agencycrm.modules.crm.domain.CaregiverApplication;
import com.michalswig.agencycrm.modules.crm.domain.WorkflowEntityType;
import com.michalswig.agencycrm.modules.crm.domain.WorkflowStatusChangeAudit;
import com.michalswig.agencycrm.modules.crm.repository.CaregiverApplicationRepository;
import com.michalswig.agencycrm.modules.crm.repository.WorkflowStatusChangeAuditRepository;
import com.michalswig.agencycrm.modules.crm.workflow.dto.ApplicationStatusChangeCommand;
import com.michalswig.agencycrm.modules.crm.workflow.exception.WorkflowEntityNotFoundException;
import com.michalswig.agencycrm.modules.crm.workflow.exception.WorkflowValidationException;
import com.michalswig.agencycrm.modules.crm.workflow.validator.ApplicationStatusTransitionValidator;

import java.time.OffsetDateTime;

public class CaregiverApplicationWorkflowService {

    private final CaregiverApplicationRepository caregiverApplicationRepository;
    private final WorkflowStatusChangeAuditRepository workflowStatusChangeAuditRepository;

    private final ApplicationStatusTransitionValidator validator = new ApplicationStatusTransitionValidator();

    public CaregiverApplicationWorkflowService(CaregiverApplicationRepository caregiverApplicationRepository, WorkflowStatusChangeAuditRepository workflowStatusChangeAuditRepository) {
        this.caregiverApplicationRepository = caregiverApplicationRepository;
        this.workflowStatusChangeAuditRepository = workflowStatusChangeAuditRepository;
    }

    public void changeStatus(Long applicationId, ApplicationStatus newStatus, ApplicationStatusChangeCommand command) {

        if(command.actorUserId() == null){
            throw new WorkflowValidationException("Actor Id is required");
        }

        CaregiverApplication app = caregiverApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new WorkflowEntityNotFoundException("Application with id " + applicationId + " not found"));

        ApplicationStatus from = app.getStatus();
        validator.validate(from, newStatus);

        if (newStatus == ApplicationStatus.CLOSED && command.closeReason() == null) {
            throw new WorkflowEntityNotFoundException("close reason is required");
        }

        if (newStatus == ApplicationStatus.CLOSED) {
            app.close(command.closeReason());
        } else {
            app.changeStatus(newStatus);
        }

        caregiverApplicationRepository.save(app);

        workflowStatusChangeAuditRepository.save(new WorkflowStatusChangeAudit(
                WorkflowEntityType.CAREGIVER_APPLICATION,
                applicationId,
                from.name(),
                newStatus.name(),
                command.actorUserId(),
                OffsetDateTime.now(),
                command.closeReason() != null ? command.closeReason().name() : null,
                command.note()
        ));
    }
}
