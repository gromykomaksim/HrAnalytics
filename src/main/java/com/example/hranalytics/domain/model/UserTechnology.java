package com.example.hranalytics.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "user_technologies")
public class UserTechnology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "level", nullable = false)
    private int level;

    @Column(name = "importance", nullable = false)
    private int importance;

    @Column(name = "begin_use", nullable = false)
    private ZonedDateTime beginUse;

    @Column(name = "deleted", nullable = false)
    private int deleted;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "technology_id", nullable = false)
    @ManyToOne
    private Technology technology;

    @Column(name = "deleted_time")
    private ZonedDateTime deletedTime;

    @ManyToMany(mappedBy = "userTechnologies")
    private Set<ProjectCameTo> projectsCameTo = new HashSet<>();
}
