package com.schibsted.spain.friends.domain;

public abstract class BaseValidationRule {
    public abstract String getDescription();
    public abstract boolean isValid(String field);
}
