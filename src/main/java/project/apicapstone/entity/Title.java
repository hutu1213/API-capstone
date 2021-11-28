package project.apicapstone.entity;

import lombok.Data;
import project.apicapstone.common.entity.BaseEntity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Data
@Entity
@Table(name = "table_title")
public class Title  {
    @Id
    private String titleId;
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
