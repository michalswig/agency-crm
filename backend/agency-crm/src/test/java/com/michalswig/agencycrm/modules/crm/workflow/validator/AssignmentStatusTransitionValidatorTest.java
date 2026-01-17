package com.michalswig.agencycrm.modules.crm.workflow.validator;

import com.michalswig.agencycrm.modules.crm.domain.AssignmentStatus;
import com.michalswig.agencycrm.modules.crm.workflow.exception.InvalidStatusTransitionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AssignmentStatusTransitionValidatorTest {
    @Test
    void should_allow_open_to_closed() {
        var v = new AssignmentStatusTransitionValidator();
        assertDoesNotThrow(() -> v.validate(AssignmentStatus.OPEN, AssignmentStatus.CLOSED));
    }

    @Test
    void should_not_allow_close_to_opened() {
        var v = new AssignmentStatusTransitionValidator();
        assertThrows(InvalidStatusTransitionException.class,
                (() -> v.validate(AssignmentStatus.CLOSED, AssignmentStatus.OPEN)));
    }

    @Test
    void should_not_allow_open_to_opened() {
        var v = new AssignmentStatusTransitionValidator();
        assertDoesNotThrow(() -> v.validate(AssignmentStatus.OPEN, AssignmentStatus.OPEN));
    }

}