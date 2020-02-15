package com.springcourse.resource.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ApiErrorList extends ApiError {

    private List<String> errors;
    public ApiErrorList(int code, String msg, Date date, List<String> errors) {
        super(code, msg, date);
        this.errors = errors;
    }
}
