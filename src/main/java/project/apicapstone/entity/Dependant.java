package project.apicapstone.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import project.apicapstone.common.entity.BaseEntity;
import project.apicapstone.common.util.DateUtils;

import javax.persistence.*;
import java.time.LocalDate;

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
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate dateBirth;
    @Column
    private String phone;
    @Column
    private String nationality;


    // relationship employee - dependant 1-N
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employees;
}
