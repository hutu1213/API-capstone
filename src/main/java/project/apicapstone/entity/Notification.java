package project.apicapstone.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import project.apicapstone.common.util.DateUtils;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_notification")
public class Notification {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column
//    private String fcmToken;
    @Column
    private String content;
    @Column
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate createDate;

    // relationship account - notification: 1 - N
    @ManyToOne(fetch = FetchType.LAZY)
    //@JsonIgnore
    @JoinColumn(name = "account_id")
    private Account account;
}
