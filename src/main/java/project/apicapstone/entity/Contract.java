package project.apicapstone.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import project.apicapstone.common.util.DateUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@ToString(exclude = {})
//@EqualsAndHashCode(exclude = {}, callSuper = false)
@Entity
@Table(name = "table_contract")
public class Contract {
    @Id
    @Column
    private String contractId;
    @Column
    private String contractName;
    @Column
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate startDate;
    @Column
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate endDate;
    @Column
    private String status;
    @Column
    private String content;
    @Column
    private double salary;
    @Column
    private String type;

    // relationship emply - contract 1-N
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employees;


    // relationship contract - dependant 1-N
    @OneToMany(mappedBy = "contracts")
    private Set<Allowance> allowance = new HashSet<>();


}
