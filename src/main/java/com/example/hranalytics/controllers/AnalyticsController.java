package com.example.hranalytics.controllers;

import com.example.hranalytics.domain.dtos.ProjectAnalyticsDto;
import com.example.hranalytics.domain.dtos.ResponseModelDto;
import com.example.hranalytics.services.AnalyticsService;
import com.example.hranalytics.util.CommonUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/analytics")
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    @Operation(summary = "Get projects analytics by project and user")
    @GetMapping("/projects")
    public ResponseEntity<ResponseModelDto<List<ProjectAnalyticsDto>>> getProjectAnalytics(@RequestParam(required = false) Integer projectId,
                                                                                           @RequestParam(required = false) String userId,
                                                                                           @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                                                                           @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
                                                                                           @RequestParam(required = false) @Parameter(description = "0 - not deleted, 1 - deleted, empty - any") Integer deleted,
                                                                                           @RequestParam(required = false) @Parameter(description = "0 - in review, 1 - declined, 2 - approved, empty - any") Integer approved) {
        return CommonUtils.toResponseModel(
                ResponseEntity.ok(analyticsService.getProjectAnalytics(projectId, userId, from, to, deleted, approved)),
                new ArrayList<>(),
                List.of("Ok"));
    }
}
