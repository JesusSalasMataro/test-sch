package com.schibsted.spain.friends.exceptions;

import com.schibsted.spain.friends.domain.BaseValidationRule;

import java.util.List;

public class InvalidPasswordException extends Exception {
    private List<BaseValidationRule> failedRules;

    public InvalidPasswordException(List<BaseValidationRule> failedRules) {
        super("Invalid password");
        this.failedRules = failedRules;
    }

    public List<BaseValidationRule> getFailedRules() {
        return failedRules;
    }
}
