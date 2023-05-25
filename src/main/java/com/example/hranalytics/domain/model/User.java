package com.example.hranalytics.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "asp_net_users")
public class User {
    @Id
    @GeneratedValue
    private String id;

    @Column(name = "first_name")
    @Lob
    private String firstName;

    @Column(name = "last_name")
    @Lob
    private String lastName;
}
