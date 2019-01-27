package com.schibsted.spain.friends.domain;

public class CharactersRule extends BaseValidationRule {
    @Override
    public String getDescription() {
        return "All characters must be alphanumeric characters.";
    }

    @Override
    public boolean isValid(String field) {
        return field.matches("[a-zA-Z0-9]+");
    }
}
