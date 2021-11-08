package project.apicapstone.entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.apicapstone.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "table_application")
public class Applicant  {
    @Id
    @Column
    private String applicantId;
    @Column
    private String applicantName;
    @Column
    private String dateBirth;
    @Column
    private String address;
    @Column
    private String phone;
    @Column
    private String gender;
    @Column
    private String email;
    @Column
    private String certification;
    @Column
    private String status;
    @Column
    private String resumeFile;

    // relationship jobPosting - application 1-N
    @ManyToOne
    @JoinColumn(name = "job_posting_id")
    private JobPosting jobPosting;

    // relationship application - evaluation 1-N
    @OneToMany(mappedBy="applicant")
    private Set<Evaluation> evaluations = new HashSet<>();
}
