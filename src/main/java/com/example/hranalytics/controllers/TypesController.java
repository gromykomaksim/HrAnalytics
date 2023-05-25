package com.example.hranalytics.controllers;

import com.example.hranalytics.domain.dtos.IdNameDto;
import com.example.hranalytics.domain.enums.EventTypeEnum;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/types")
public class TypesController {

    @Operation(summary = "Get event types")
    @GetMapping("/events")
    public ResponseEntity<List<IdNameDto>> getEventTypes() {
        var response = Arrays.stream(EventTypeEnum.values())
                .map(x -> new IdNameDto(x.ordinal(), x.getName())).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}
