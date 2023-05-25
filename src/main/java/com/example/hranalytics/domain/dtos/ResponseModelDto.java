package com.example.hranalytics.domain.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class ResponseModelDto<T> {
    private int code;
    private boolean didError;
    private List<String> errors;
    private List<String> messages;
    private T model;
}
