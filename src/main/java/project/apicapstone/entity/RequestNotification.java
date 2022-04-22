package project.apicapstone.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import project.apicapstone.common.util.DateUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
//@EqualsAndHashCode(exclude = {"roles", "employee","notifications"}, callSuper = false)
@Entity
@Table(name = "tbl_request_notification")
public class RequestNotification {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long RecruitmentRequestId;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate createDate;
    @JsonIgnore
    @Builder.Default
    @ManyToMany(mappedBy = "requestNotifications", fetch = FetchType.LAZY)
    private Set<Account> accounts = new HashSet<>();

    public void addAccountToRequestNotifi(Account account) {
        accounts.add(account);
        account.getRequestNotifications().add(this);
    }
}
