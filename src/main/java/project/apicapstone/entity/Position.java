package project.apicapstone.entity;

import lombok.Data;
import project.apicapstone.common.entity.BaseEntity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Data
@Entity
@Table(name = "table_position")
public class Position{
    @Id
    private String positionId;
    @Column
    private String positionName;

    // relationship position - title: 1-N
    @OneToMany(mappedBy="position")
    private Set<Title> titles = new HashSet<>();
}
