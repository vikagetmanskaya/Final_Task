package com.example.finaltask.validator.impl;

import com.example.finaltask.validator.CustomValidator;

public class CustomValidatorImpl implements CustomValidator {
    private static final String EMAIL_PATTERN = "^[A-Za-z\\d._\\-]+[@][A-Za-z]+\\.[A-Za-z]{2,4}$";
    private static final String PASSWORD_PATTERN = "^(?=\\w*\\d)(?=\\w*[a-z])(?=\\w*[A-Z])\\w{8,45}$";
    private static final String FIRST_AND_LAST_NAME_PATTERN = "^[A-ZА-ЯЁ][a-zа-яё]{2,45}$|^[A-ZА-ЯЁ][a-zа-яё]{2,21}[-][A-ZА-ЯЁ][a-zа-яё]{2,21}$";
    private static CustomValidatorImpl instance;

    private CustomValidatorImpl() {
    }

    public static CustomValidatorImpl getInstance() {
        if(instance == null){
            instance = new CustomValidatorImpl();
        }
        return instance;
    }

    @Override
    public boolean validateEmail(String email) {
        return email.matches(EMAIL_PATTERN);
    }

    @Override
    public boolean validatePassword(String password) {
        return password.matches(PASSWORD_PATTERN);
    }

    @Override
    public boolean validateName(String firstName, String lastName) {
        return firstName.matches(FIRST_AND_LAST_NAME_PATTERN) && lastName.matches(FIRST_AND_LAST_NAME_PATTERN);
    }
}
