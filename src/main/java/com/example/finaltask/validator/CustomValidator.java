package com.example.finaltask.validator;

public interface CustomValidator {
    boolean validateEmail(String email);
    boolean validatePassword(String password);
    boolean validateName(String firstName, String lastName);


}
