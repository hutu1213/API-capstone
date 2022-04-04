package project.apicapstone.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import project.apicapstone.common.util.DateUtils;


import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Entity
@Table(name = "table_evaluation")
public class Evaluation {
    @Id
    private String evaluationId;
    @Column
    private String content;
    @Column
    private String type;
    @Column
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate createDate;
    @Column
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate updateDate;
    @Column
    private String createTime;
    @Column
    private int rating;
    @Column
    private Boolean result;
    //relationship application - evaluation 1-N
    @ManyToOne(fetch = FetchType.LAZY)
    //@JsonIgnore
    @JoinColumn(name = "application_id")
    private Applicant applicant;

    // relationship employ - evaluation 1-N
    @ManyToOne(fetch = FetchType.LAZY)
    //@JsonIgnore
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
