package project.apicapstone.entity;



import project.apicapstone.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "table_dependant")
public class Dependant extends BaseEntity {
    @Column
    private String depentdantName;
    @Column
    private String gender;
    @Column
    private String address;
    @Column
    private String dateBirth;
    @Column
    private String phone;
    @Column
    private String nationality;


    // relationship emply - dependant 1-N
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employees;
}
