package com.example.hranalytics.domain.enums;

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
}
