package com.michalswig.agencycrm.modules.crm.workflow.service;

import com.michalswig.agencycrm.modules.crm.domain.*;
import com.michalswig.agencycrm.modules.crm.repository.CaregiverApplicationRepository;
import com.michalswig.agencycrm.modules.crm.repository.WorkflowStatusChangeAuditRepository;
import com.michalswig.agencycrm.modules.crm.workflow.dto.ApplicationStatusChangeCommand;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CaregiverApplicationWorkflowServiceTest {

    @Test
    void should_open_assignment_to_closed(){
        var caregiverApplicationRepository = mock(CaregiverApplicationRepository.class);
        var workflowStatusChangeAuditRepository = mock(WorkflowStatusChangeAuditRepository.class);

        var service = new CaregiverApplicationWorkflowService(caregiverApplicationRepository, workflowStatusChangeAuditRepository);

        Assignment assignment = new Assignment(
                null, null, null,
                null, null, null,
                LanguageLevel.GOOD, null,
                AssignmentStatus.OPEN,
                null, null,
                null,
                OffsetDateTime.now(), null
        );

        CaregiverApplication app = new CaregiverApplication(
                assignment,
                null,
                ApplicationStatus.ACTIVE,
                null,
                null,
                null,
                OffsetDateTime.now(),
                null
        );

        when(caregiverApplicationRepository.findById(10L)).thenReturn(Optional.of(app));
        when(caregiverApplicationRepository.save(app)).thenAnswer(inv->inv.getArgument(0));

        service.changeStatus(
                10L,
                ApplicationStatus.CLOSED,
                new ApplicationStatusChangeCommand(99L, null, ApplicationCloseReason.REJECTED)
        );

        assertEquals(ApplicationStatus.CLOSED, app.getStatus());
        verify(workflowStatusChangeAuditRepository, times(1)).save(any(WorkflowStatusChangeAudit.class));

    }

}