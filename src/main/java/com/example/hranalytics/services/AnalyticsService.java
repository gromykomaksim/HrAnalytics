package com.example.hranalytics.services;

import com.example.hranalytics.domain.dtos.ProjectAnalyticsDto;

import java.time.LocalDate;
import java.util.List;

public interface AnalyticsService {
    List<ProjectAnalyticsDto> getProjectAnalytics(Integer projectId, String userId,
                                                  LocalDate from, LocalDate to,
                                                  Integer deleted, Integer approved);
}
