package project.apicapstone.entity;



import project.apicapstone.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "table_contract")
public class Contract extends BaseEntity {
    @Column
    private String contractName;
    @Column
    private String startDate;
    @Column
    private String endDate;
    @Column
    private String status;
    @Column
    private String content;
    @Column
    private String salary;
    @Column
    private String workingTime;

    // relationship emply - contract 1-N
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employees;

    // relationship contract - dependant 1-N
    @OneToMany(mappedBy="contracts")
    private Set<Allowance> allowance = new HashSet<>();

}
