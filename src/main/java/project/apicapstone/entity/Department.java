package project.apicapstone.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "table_department")
public class Department  {
    @Id
    @Column()
    private String departmentId;

    @Column
    private String departmentName;

    // relationship department - title 1-N
    @OneToMany(mappedBy="department")
    private Set<Title> titles = new HashSet<>();

    public Department(String departmentId, String departmentName, Set<Title> titles) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.titles = titles;
    }

    public Department() {

    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Set<Title> getTitles() {
        return titles;
    }

    public void setTitles(Set<Title> titles) {
        this.titles = titles;
    }
}
