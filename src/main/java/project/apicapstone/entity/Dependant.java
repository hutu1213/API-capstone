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
@Table(name = "table_dependant")
public class Dependant  {
    @Id
    @Column
    private String dependantId;
    @Column
    private String dependantName;
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


    // relationship employee - dependant 1-N
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employees;
}
