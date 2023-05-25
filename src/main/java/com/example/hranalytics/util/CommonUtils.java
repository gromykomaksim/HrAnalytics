package com.example.hranalytics.util;

import com.example.hranalytics.domain.dtos.ResponseModelDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CommonUtils {
    public static <T> ResponseEntity<ResponseModelDto<T>> toResponseModel(ResponseEntity<T> responseEntity, List<String> errors, List<String> messages) {
        var responseModelDto = new ResponseModelDto<T>();

        responseModelDto.setCode(responseEntity.getStatusCode().value());
        responseModelDto.setDidError(responseEntity.getStatusCode().value() != 200);
        responseModelDto.setErrors(errors);
        responseModelDto.setMessages(messages);
        responseModelDto.setModel(responseEntity.getBody());

        return ResponseEntity.ok(responseModelDto);
    }
}
