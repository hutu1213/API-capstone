package project.apicapstone.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import project.apicapstone.common.util.DateUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "table_task")
public class Task {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    @Column
    private String taskName;
    @Column
    private String owner;// bỏ
    @Column
    private String status;
    @Column
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate startDate;
    @Column
    private String workPlan;
    @Column
    private String duration;
    @Column
    private String priority;
    @Column(columnDefinition = "TEXT")
    private String taskDetail;

    //relation employee - task : N-N
    @JsonIgnore
    @Builder.Default
    @ManyToMany(mappedBy = "tasks",fetch = FetchType.LAZY)
    private Set<Employee> employees = new HashSet<>();

    //helper
    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.getTasks().add(this);
    }
    public void deleteEmployee(Employee employee) {
        employees.remove(employee);
        employee.getTasks().add(this);
    }
}
