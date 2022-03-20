package project.apicapstone.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
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
@Table(name = "tbl_notification")
@Transactional
public class Notification {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate createDate;

    // relationship account - notification: 1 - N
//    @ManyToOne(fetch = FetchType.LAZY)
//    //@JsonIgnore
//    @JoinColumn(name = "account_id")
//    private Account account;
    @JsonIgnore
    @Builder.Default
    @ManyToMany(mappedBy = "notifications", fetch = FetchType.LAZY)
    private Set<Account> accounts = new HashSet<>();
//    @JsonIgnore
//    @Builder.Default
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(name = "notification_account", joinColumns = @JoinColumn(name = "notification_id"), inverseJoinColumns = @JoinColumn(name = "account_id"))
//    private Set<Account> accounts = new HashSet<>();

    //helper
    public void addAccount(Account account) {
        accounts.add(account);
        account.getNotifications().add(this);
    }



}
