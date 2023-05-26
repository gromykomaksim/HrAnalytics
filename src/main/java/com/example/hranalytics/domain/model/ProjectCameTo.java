package com.example.hranalytics.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "project_came_to")
public class ProjectCameTo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "deleted", nullable = false)
    private int deleted;

    @Column(name = "\"from\"", nullable = false)
    private ZonedDateTime from;

    @Column(name = "\"to\"")
    private ZonedDateTime to;

    @Column(name = "rate", columnDefinition = "int default 0", nullable = false)
    private int rate;

    @Column(name = "deleted_time")
    private ZonedDateTime deletedTime;

    @JoinColumn(name = "project_id", columnDefinition = "int default 0", nullable = false)
    @ManyToOne
    private Project project;

    @OneToMany(mappedBy = "projectCameTo", cascade = CascadeType.MERGE)
    private List<EmsEvent> emsEvents;

    @ManyToMany
    @JoinTable(
            name = "project_came_to_user_technology",
            joinColumns = {@JoinColumn(name = "project_came_tos_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_technologies_id")},
            uniqueConstraints = @UniqueConstraint(columnNames = {"project_came_tos_id", "user_technologies_id"}))
    private Set<UserTechnology> userTechnologies = new HashSet<>();
}
