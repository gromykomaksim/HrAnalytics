package com.example.hranalytics.domain.model;

import com.example.hranalytics.domain.enums.EmsApproveStatusEnum;
import com.example.hranalytics.domain.enums.EventTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Setter
@Getter
@Table(name = "ems_event")
public class EmsEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    @Enumerated(value = EnumType.ORDINAL)
    private EventTypeEnum eventType;

    @Column(name = "from")
    private ZonedDateTime from;

    @Column(name = "minuts")
    private int minutes;

    @Column(name = "deleted")
    private int deleted;

    @Column(name = "deleted_time")
    private ZonedDateTime deletedTime;

    @JoinColumn(name = "project_came_to_id")
    @ManyToOne
    private ProjectCameTo projectCameTo;

    @Column(name = "comment")
    @Lob
    private String comment;

    @Column(name = "approve", columnDefinition = "int default 0")
    @Enumerated(value = EnumType.ORDINAL)
    private EmsApproveStatusEnum approve;

    @JoinColumn(name = "vacation_request_id")
    @ManyToOne
    private VacationRequest vacationRequest;
}
