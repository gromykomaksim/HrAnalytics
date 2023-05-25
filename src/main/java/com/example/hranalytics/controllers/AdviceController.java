package com.example.hranalytics.controllers;

import com.example.hranalytics.domain.dtos.ResponseModelDto;
import com.example.hranalytics.domain.exceptions.BadRequestException;
import com.example.hranalytics.util.CommonUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RestControllerAdvice
public class AdviceController {
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ResponseModelDto<Object>> responseModelDto(BadRequestException e) {
        var message = e.getMessage();

        return CommonUtils.toResponseModel(ResponseEntity.badRequest().body(null), List.of(message), new ArrayList<>());
    }
}
