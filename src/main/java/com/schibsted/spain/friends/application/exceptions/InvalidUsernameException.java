package com.schibsted.spain.friends.application.exceptions;

import com.schibsted.spain.friends.domain.BaseValidationRule;

import java.util.List;

public class InvalidUsernameException extends Exception {

    private List<BaseValidationRule> failedRules;

    public InvalidUsernameException(List<BaseValidationRule> failedRules) {
        super("Invalid username");
        this.failedRules = failedRules;
    }

    public List<BaseValidationRule> getFailedRules() {
        return failedRules;
    }
}

