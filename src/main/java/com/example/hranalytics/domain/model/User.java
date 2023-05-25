package com.example.hranalytics.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Table(name = "asp_net_users")
public class User {
    @Id
    @GeneratedValue
    private String id;
}
