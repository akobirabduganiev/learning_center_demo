package com.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "course")
public class CourseEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @Column
    private Double price;
    @Column
    private Integer duration;
}