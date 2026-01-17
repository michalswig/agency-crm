package com.michalswig.agencycrm.modules.crm.workflow.exception;

public class InvalidStatusTransitionException extends RuntimeException {
    public InvalidStatusTransitionException(String message) { super(message); }
}

