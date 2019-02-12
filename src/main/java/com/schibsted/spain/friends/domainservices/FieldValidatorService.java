package com.schibsted.spain.friends.domainservices;

import com.schibsted.spain.friends.domain.BaseValidationRule;
import com.schibsted.spain.friends.domain.CharactersRule;
import com.schibsted.spain.friends.domain.LengthRule;
import com.schibsted.spain.friends.application.exceptions.InvalidPasswordException;
import com.schibsted.spain.friends.application.exceptions.InvalidUsernameException;

import java.util.ArrayList;
import java.util.List;

public class FieldValidatorService {

    private static final int USERNAME_MINIMUM_LENGTH = 5;
    private static final int USERNAME_MAXIMUM_LENGTH = 10;
    private static final int PASSWORD_MINIMUM_LENGTH = 8;
    private static final int PASSWORD_MAXIMUM_LENGTH = 12;

    public void validateUsername(String username) throws InvalidUsernameException {
        List<BaseValidationRule> rules = getUsernameRules();
        List<BaseValidationRule> failedRules = checkRules(username, rules);

        if (failedRules.size() > 0) {
            throw new InvalidUsernameException(failedRules);
        }
    }

    public void validatePassword(String password) throws InvalidPasswordException {
        List<BaseValidationRule> rules = getPasswordRules();
        List<BaseValidationRule> failedRules = checkRules(password, rules);

        if (failedRules.size() > 0) {
            throw new InvalidPasswordException(failedRules);
        }
    }

    private List<BaseValidationRule> checkRules(String username, List<BaseValidationRule> rules) {
        List<BaseValidationRule> failedRules = new ArrayList<>();

        for (BaseValidationRule rule : rules) {
            if (!rule.isValid(username)) {
                failedRules.add(rule);
            }
        }
        return failedRules;
    }

    private List<BaseValidationRule> getUsernameRules() {
        ArrayList<BaseValidationRule> rules = new ArrayList<>();
        rules.add(new CharactersRule());
        rules.add(new LengthRule(USERNAME_MINIMUM_LENGTH, USERNAME_MAXIMUM_LENGTH));
        return rules;
    }

    private List<BaseValidationRule> getPasswordRules() {
        ArrayList<BaseValidationRule> rules = new ArrayList<>();
        rules.add(new CharactersRule());
        rules.add(new LengthRule(PASSWORD_MINIMUM_LENGTH, PASSWORD_MAXIMUM_LENGTH));
        return rules;
    }
}
