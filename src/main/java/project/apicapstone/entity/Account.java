package project.apicapstone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;



import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
@EqualsAndHashCode(exclude = {"roles","employee"}, callSuper = false)
@Entity
@Table(name = "table_account")
public class Account {
    @Id
    @Column
    private String accountId;
    @Column
    private String username;
    @Column
    @JsonIgnore
    private String password;
    @Column
    private String status;

    //relation acc-role : N-N
    @JsonIgnore
    @Builder.Default
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "account_role", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    // relation: employee -account 1-1
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    //@JsonIgnore
    private Employee employee;

//    @OneToOne(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private Employee employee;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "employee_id", referencedColumnName = "employeeId")
//    private Employee employee;

// relationship account - notification: 1 - N
    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private Set<Notification> notifications = new HashSet<>();
    //helper
    public void addRole(Role role) {
        roles.add(role);
        role.getAccounts().add(this);
    }
}
