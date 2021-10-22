package project.apicapstone.entity;



import project.apicapstone.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "table_evaluation")
public class Evaluation extends BaseEntity {
    @Column
    private String content;
    @Column
    private String type;

    // relationship application - evaluation 1-N
    @ManyToOne
    @JoinColumn(name = "application_id")
    private Applicant applicant;

  // relationship employ - evaluation 1-N
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
