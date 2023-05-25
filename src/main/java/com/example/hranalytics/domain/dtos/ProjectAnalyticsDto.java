package com.example.hranalytics.domain.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class ProjectAnalyticsDto {
    private int id;
    private String name;
    private List<UserAnalyticsDto> userProjectAnalyticsList;
}
