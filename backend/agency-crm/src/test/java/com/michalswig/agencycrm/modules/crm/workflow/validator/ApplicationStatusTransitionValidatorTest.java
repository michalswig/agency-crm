package com.michalswig.agencycrm.modules.crm.workflow.validator;

import com.michalswig.agencycrm.modules.crm.domain.ApplicationStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationStatusTransitionValidatorTest {
    @Test
    void should_allow_active_to_closed(){
        var v= new ApplicationStatusTransitionValidator();
        assertDoesNotThrow(()-> v.validate(
                ApplicationStatus.ACTIVE, ApplicationStatus.CLOSED
        ));
    }

}