package project.apicapstone.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import project.apicapstone.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {""})
@EqualsAndHashCode(exclude = { "" }, callSuper = false)
@Entity
@Table(name = "table_timesheet")
public class Timesheet extends BaseEntity {
    @Column
    private String startDate;
    @Column
    private String endDate;
    @Column
    private String workTime;

    //relation empl- timesheet : N-N
    @JsonIgnore
    @Builder.Default
    @ManyToMany(mappedBy = "timeSheets",fetch = FetchType.LAZY)
    private Set<Employee> employees = new HashSet<>();
}
