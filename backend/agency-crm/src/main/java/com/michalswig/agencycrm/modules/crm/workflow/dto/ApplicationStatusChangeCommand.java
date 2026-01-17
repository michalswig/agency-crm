package com.michalswig.agencycrm.modules.crm.workflow.dto;

import com.michalswig.agencycrm.modules.crm.domain.ApplicationCloseReason;

public record ApplicationStatusChangeCommand(
        Long actorUserId,
        String note,
        ApplicationCloseReason closeReason
) {}
