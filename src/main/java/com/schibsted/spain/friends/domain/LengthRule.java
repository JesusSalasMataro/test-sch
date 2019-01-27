package com.schibsted.spain.friends.domain;

public class LengthRule extends BaseValidationRule {

    private int minimumLength;
    private int maximumLength;

    public LengthRule(int minimumLength, int maximumLength) {
        super();
        this.minimumLength = minimumLength;
        this.maximumLength = maximumLength;
    }

    @Override
    public String getDescription() {
        return "Must be between " + minimumLength + " and " + maximumLength + " characters length.";
    }

    @Override
    public boolean isValid(String field) {
        return field.length() >= minimumLength &&
                field.length() <= maximumLength;
    }
}
