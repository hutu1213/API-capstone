package project.apicapstone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import project.apicapstone.common.entity.BaseEntity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"roles"})
@EqualsAndHashCode(exclude = { "roles" }, callSuper = false)
@Entity
@Table(name = "table_account")
public class Account extends BaseEntity {
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String status;

    //relation acc-role : N-N
    @JsonIgnore
    @Builder.Default
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "account_role", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    // relation: employee -account 1-1
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JsonIgnore
    private Employee employee;
}
