package com.lambdaschool.schools.services;

import com.lambdaschool.schools.models.ValidationError;

import java.util.List;

public interface HelperFunctions {
    List<ValidationError> getConstraintViolation(Throwable cause);
}

// This is where helper function implementation gets the cause from