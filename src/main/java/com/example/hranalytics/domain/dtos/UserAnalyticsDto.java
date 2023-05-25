package com.example.hranalytics.domain.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class UserAnalyticsDto {
    private String userId;
    private String firstName;
    private String lastName;
    private List<String> technologies;
    List<EvenTypeInfoDto> evenTypeInfoList;
}
