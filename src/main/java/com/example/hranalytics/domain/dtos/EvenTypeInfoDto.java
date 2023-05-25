package com.example.hranalytics.domain.dtos;

import com.example.hranalytics.domain.enums.EventTypeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class EvenTypeInfoDto {
    private int eventTypeId;
    private String eventTypeName;
    private int hours;
    private int minutes;

    public EvenTypeInfoDto(int eventTypeId) {
        this.eventTypeId = eventTypeId;
        this.eventTypeName = EventTypeEnum.getEventType(eventTypeId).getName();
        this.hours = 0;
        this.minutes = 0;
    }
}
