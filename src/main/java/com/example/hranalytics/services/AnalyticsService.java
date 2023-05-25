package com.example.hranalytics.services;

import com.example.hranalytics.domain.dtos.UserProjectAnalyticsDto;

import java.time.LocalDate;
import java.util.List;

public interface AnalyticsService {
    List<UserProjectAnalyticsDto> getProjectAnalytics(Integer projectId, String userId,
                                                      LocalDate from, LocalDate to);
}
