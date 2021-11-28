package project.apicapstone.entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.apicapstone.common.entity.BaseEntity;

import javax.persistence.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "table_evaluation")
public class Evaluation  {
    @Id
    private String evaluationId;
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
