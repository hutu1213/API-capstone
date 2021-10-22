package project.apicapstone.entity;



import project.apicapstone.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "table_allowance")
public class Allowance extends BaseEntity {
    @Column
    private String allowanceName;
    @Column
    private String type;

    // relationship emply - dependant 1-N
    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contracts;
}
