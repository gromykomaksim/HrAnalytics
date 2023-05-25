package com.example.hranalytics.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Setter
@Getter
@Table(name = "holidays")
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Lob
    private String  name;

    @Column(name = "day")
    private int day;

    @Column(name = "month")
    private int month;

    @Column(name = "deleted")
    private int deleted;

    @Column(name = "deleted_time")
    private ZonedDateTime deletedTime;
}
