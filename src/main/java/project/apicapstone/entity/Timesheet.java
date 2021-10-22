package project.apicapstone.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import project.apicapstone.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    @ManyToMany(mappedBy = "timesheets",fetch = FetchType.LAZY)
    private Set<Employee> employees = new HashSet<>();
}
