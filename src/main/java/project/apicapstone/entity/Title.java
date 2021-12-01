package project.apicapstone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @OneToMany(mappedBy="title")
    //@JsonManagedReference
    @JsonIgnore
    private Set<Employee> employees ;

    // relationship position - title: 1-N
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    @JsonIgnore
    private Position position;

    // relationship department - title 1-N
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "department_id")
    private Department department;

    // relationship title - job posting: 1-N
    @OneToMany(mappedBy="title")
    @JsonIgnore
    private Set<JobPosting> jobPostings = new HashSet<>();

}
