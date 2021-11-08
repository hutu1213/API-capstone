package project.apicapstone.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "table_department")
public class Department  {
    @Id
    @Column
    private String departmentId;

    @Column
    private String departmentName;

    // relationship department - title 1-N
    @OneToMany(mappedBy="department")
    private Set<Title> titles = new HashSet<>();


}
