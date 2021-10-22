package project.apicapstone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import project.apicapstone.common.entity.BaseEntity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "table_role")
public class Role extends BaseEntity {
    @Column
    private String roleName;
    @Column
    private String description;

    //relation acc-role : N-N
    @JsonIgnore
    @Builder.Default
    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private Set<Account> accounts = new HashSet<>();
}
