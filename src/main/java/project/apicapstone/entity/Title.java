package project.apicapstone.entity;

import project.apicapstone.common.entity.BaseEntity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "table_title")
public class Title extends BaseEntity {
    @Column
    private String jobTitle;

    // relationship title - employee 1-N
    @OneToMany(mappedBy="titles")
    private Set<Employee> employees = new HashSet<>();

    // relationship position - title: 1-N
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    // relationship department - title 1-N
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // relationship title - job posting: 1-N
    @OneToMany(mappedBy="title")
    private Set<JobPosting> jobPostings = new HashSet<>();

}
