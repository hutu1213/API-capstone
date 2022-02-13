package project.apicapstone.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import project.apicapstone.common.util.DateUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Data
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
//@EqualsAndHashCode(exclude = {"employees"}, callSuper = false)
@Entity
@Table(name = "table_training_course")
public class TrainingCourse {
    @Id
    @Column
    private String courseId;
    @Column
    private String courseName;
    @Column
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate startDate;
    @Column
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate endDate;
    @Column
    private String description;
    @Column
    private String trainer;

    //relation employee - training : N-N
    @JsonIgnore
    @Builder.Default
    @ManyToMany(mappedBy = "trainingCourses",fetch = FetchType.LAZY)
    private Set<Employee> employees = new HashSet<>();


    //helper
    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.getTrainingCourses().add(this);
    }
}
