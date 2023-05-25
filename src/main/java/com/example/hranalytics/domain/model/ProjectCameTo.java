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

    @Column(name = "deleted")
    private int deleted;

    @Column(name = "from")
    private ZonedDateTime from;

    @Column(name = "to")
    private ZonedDateTime to;

    @Column(name = "rate", columnDefinition = "int default 0")
    private int rate;

    @Column(name = "deleted_time")
    private ZonedDateTime deletedTime;

    @JoinColumn(name = "project_id")
    @ManyToOne
    private Project project;

    @ManyToMany
    @JoinTable(
            name = "project_came_to_user_technology",
            joinColumns = {@JoinColumn(name = "project_came_tos_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_technologies_id")},
            uniqueConstraints = @UniqueConstraint(columnNames = {"project_came_tos_id", "user_technologies_id"}))
    private Set<UserTechnology> userTechnologies = new HashSet<>();

    @OneToMany(mappedBy = "projectCameTo", cascade = CascadeType.MERGE)
    private List<EmsEvent> emsEvents;
}
