package com.lambdaschool.schools.models;

import org.springframework.boot.context.properties.bind.validation.ValidationErrors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ErrorDetail {
    private String title;
    private int status;
    private String detail;
    private Date timestamp;
    private String developerMessage;

    private List<ValidationErrors> errors = new ArrayList<>();
}
// Will not be creating a constructor, will let the JDk handle that