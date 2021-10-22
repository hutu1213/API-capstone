package project.apicapstone.entity;



import project.apicapstone.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "table_department")
public class Department extends BaseEntity {
    @Column
    private String departmentName;

    // relationship department - title 1-N
    @OneToMany(mappedBy="department")
    private Set<Title> titles = new HashSet<>();
}
