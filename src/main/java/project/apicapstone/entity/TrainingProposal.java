package project.apicapstone.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {})
@EqualsAndHashCode(exclude = {}, callSuper = false)
@Entity
@Table(name = "table_trainingProposal")
public class TrainingProposal {
    @Id
    @Column
    private String proposalId;
    @Column
    private String proposalName;
    @Column
    private String proposalDescription;
    @Column
    private String status;
    // relationship employ - trainingProposal 1-N
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
