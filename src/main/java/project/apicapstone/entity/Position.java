package project.apicapstone.entity;

import project.apicapstone.common.entity.BaseEntity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "table_position")
public class Position extends BaseEntity {
    @Column
    private String positionName;

    // relationship position - title: 1-N
    @OneToMany(mappedBy="position")
    private Set<Title> titles = new HashSet<>();
}
