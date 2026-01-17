package com.michalswig.agencycrm.modules.crm.workflow.validator;

import com.michalswig.agencycrm.modules.crm.domain.ApplicationStatus;
import com.michalswig.agencycrm.modules.crm.workflow.exception.InvalidStatusTransitionException;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public class ApplicationStatusTransitionValidator {

    private static final Map<ApplicationStatus, Set<ApplicationStatus>> ALLOWED = Map.of(
            ApplicationStatus.ACTIVE, EnumSet.of(ApplicationStatus.CLOSED),
            ApplicationStatus.CLOSED, EnumSet.noneOf(ApplicationStatus.class)
    );

    public void validate(ApplicationStatus from, ApplicationStatus to) {
        if (from == to) return;

        if (!ALLOWED.getOrDefault(from, Set.of()).contains(to)) {
            throw new InvalidStatusTransitionException("Application transition not allowed: " + from + " -> " + to);
        }
    }
}

