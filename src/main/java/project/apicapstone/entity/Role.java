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
@EqualsAndHashCode(exclude = {"accounts"}, callSuper = false)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Entity
@Table(name = "table_role")
public class Role {
    @Id
    @Column
    private String roleId;
    @Column
    private String roleName;
    @Column
    private String roleDescription;

    //relation account-role : N-N
//    @JsonIgnore
//    @Builder.Default
//    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
//    private Set<Account> accounts = new HashSet<>();

    //***** Relationship role - account: 1 - n
    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private Set<Account> accounts = new HashSet<>();
}
