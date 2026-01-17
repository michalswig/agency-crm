package com.michalswig.agencycrm.modules.crm.workflow.validator;

import com.michalswig.agencycrm.modules.crm.domain.AssignmentStatus;
import com.michalswig.agencycrm.modules.crm.workflow.exception.InvalidStatusTransitionException;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public class AssignmentStatusTransitionValidator {

    private static final Map<AssignmentStatus, Set<AssignmentStatus>> ALLOWED = Map.of(
            AssignmentStatus.OPEN, EnumSet.of(AssignmentStatus.CLOSED),
            AssignmentStatus.CLOSED, EnumSet.noneOf(AssignmentStatus.class)
    );

    public void validate(AssignmentStatus from, AssignmentStatus to) {
        if (from == to) return;

        if (!ALLOWED.getOrDefault(from, Set.of()).contains(to)) {
            throw new InvalidStatusTransitionException("Assignment transition not allowed: " + from + " -> " + to);
        }
    }
}


