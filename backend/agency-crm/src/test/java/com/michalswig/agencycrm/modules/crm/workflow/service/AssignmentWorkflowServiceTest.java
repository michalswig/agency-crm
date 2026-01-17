package com.michalswig.agencycrm.modules.crm.workflow.service;

import com.michalswig.agencycrm.modules.crm.domain.*;
import com.michalswig.agencycrm.modules.crm.repository.AssignmentRepository;
import com.michalswig.agencycrm.modules.crm.repository.WorkflowStatusChangeAuditRepository;
import com.michalswig.agencycrm.modules.crm.workflow.dto.AssignmentStatusChangeCommand;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AssignmentWorkflowServiceTest {

    @Test
    void should_close_assignment_and_close_audit() {
        var assignmentRepo = mock(AssignmentRepository.class);
        var workflowStatusChangeAuditRepository = mock(WorkflowStatusChangeAuditRepository.class);
        var service = new AssignmentWorkflowService(assignmentRepo, workflowStatusChangeAuditRepository);

        Assignment assignment = new Assignment(
                null, null, null,
                null, null, null,
                LanguageLevel.GOOD, null,
                AssignmentStatus.OPEN,
                null, null,
                null,
                OffsetDateTime.now(), null
        );

        when(assignmentRepo.findById(10L)).thenReturn(Optional.of(assignment));
        when(assignmentRepo.save(assignment)).thenAnswer(i -> i.getArgument(0));

        service.changeStatus(10L,
                AssignmentStatus.CLOSED,
                new AssignmentStatusChangeCommand(99L, null, AssignmentCloseReason.FAMILY_CANCELLED)
        );

        assertEquals(AssignmentStatus.CLOSED, assignment.getStatus());
        verify(workflowStatusChangeAuditRepository, times(1)).save(any(WorkflowStatusChangeAudit.class));

    }

}