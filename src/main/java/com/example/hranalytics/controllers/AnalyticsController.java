package com.example.hranalytics.controllers;

import com.example.hranalytics.domain.dtos.UserProjectAnalyticsDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/analytics")
public class AnalyticsController {
    @Operation(summary = "Get projects analytics by project and user(optional)")
    @GetMapping("/projects")
    public ResponseEntity<List<UserProjectAnalyticsDto>> getProjectAnalytics(@RequestParam @NotNull Integer projectId,
                                                                             @RequestParam String userId,
                                                                             @RequestParam LocalDate from,
                                                                             @RequestParam LocalDate to) {

    }
}
