package project.apicapstone.entity;



import project.apicapstone.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "table_criteria")
public class Criteria extends BaseEntity {
    @Column
    private String description;
    @Column
    private String weight;

    // relationship job posting - criteria 1-N
    @ManyToOne
    @JoinColumn(name = "job_posting_id")
    private JobPosting jobPosting;
}
