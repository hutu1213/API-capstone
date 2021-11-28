package project.apicapstone.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import project.apicapstone.common.entity.BaseEntity;
import project.apicapstone.common.util.DateUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "table_timesheet")
public class Timesheet  {
    @Id
    private String TimesheetId;
    @Column
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private String startDate;
    @Column
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private String endDate;
    @Column
    private String workTime;

    //relation empl- timesheet : N-N
    @JsonIgnore
    @Builder.Default
    @ManyToMany(mappedBy = "timeSheets",fetch = FetchType.LAZY)
    private Set<Employee> employees = new HashSet<>();
}
