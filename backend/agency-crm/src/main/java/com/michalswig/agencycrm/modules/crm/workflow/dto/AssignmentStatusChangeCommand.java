package com.michalswig.agencycrm.modules.crm.workflow.dto;

import com.michalswig.agencycrm.modules.crm.domain.AssignmentCloseReason;

public record AssignmentStatusChangeCommand(
        Long actorUserId,
        String note,
        AssignmentCloseReason closeReason
) {}
