package com.example.hranalytics.domain.enums;

import com.example.hranalytics.domain.exceptions.BadRequestException;


import static com.example.hranalytics.domain.consts.Exceptions.INCORRECT_EVENT_TYPE;

public enum EventTypeEnum {
    OVERTIME("Overtime"), SICK("Sick"),
    SOCIAL_VACATION("Social vacation"),
    PAID_VACATION("Paid vacation"), WORK("Work");

    private final String name;

    EventTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static EventTypeEnum getEventType(int order) {
        if (order < 0 || order > EventTypeEnum.values().length)
            throw new BadRequestException(INCORRECT_EVENT_TYPE);

        return EventTypeEnum.values()[order];
    }
}
