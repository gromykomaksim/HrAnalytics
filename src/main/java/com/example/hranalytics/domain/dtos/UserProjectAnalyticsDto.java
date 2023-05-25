package com.example.hranalytics.domain.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class UserProjectAnalyticsDto {
    private String userId;
    private String technologyName;
    List<EvenTypeInfoDto> evenTypeInfoList;
}
