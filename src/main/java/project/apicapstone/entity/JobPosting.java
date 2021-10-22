package project.apicapstone.entity;



import project.apicapstone.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "table_jobPosting")
public class JobPosting extends BaseEntity {
    @Column
    private String vacancies;
    @Column
    private String datePost;
    @Column
    private String employmentInfor;
    @Column
    private String jobDescription;
    @Column
    private String jobRequirements;

    // relationship  title - job posting: 1-N
    @ManyToOne
    @JoinColumn(name = "title_id")
    private Title title;

    // relationship job posting - criteria 1-N
    @OneToMany(mappedBy="jobPosting")
    private Set<Criteria> criteria = new HashSet<>();

    // relationship job posting - application 1-N
    @OneToMany(mappedBy="jobPosting")
    private Set<Applicant> applicants = new HashSet<>();

}
