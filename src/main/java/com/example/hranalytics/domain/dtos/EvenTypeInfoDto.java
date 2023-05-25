package com.example.hranalytics.domain.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class EvenTypeInfoDto {
    private int eventTypeId;
    private int hours;
    private int minutes;
}
