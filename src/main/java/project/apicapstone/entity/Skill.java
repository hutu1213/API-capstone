package project.apicapstone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import project.apicapstone.common.entity.BaseEntity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "table_skill")
public class Skill extends BaseEntity {
    @Column
    private String skillName;
    @Column
    private String description;

    //relation empl- skill : N-N
    @JsonIgnore
    @Builder.Default
    @ManyToMany(mappedBy = "skills",fetch = FetchType.LAZY)
    private Set<Employee> employees = new HashSet<>();
}
