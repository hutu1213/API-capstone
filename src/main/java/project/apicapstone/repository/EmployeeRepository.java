package project.apicapstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.apicapstone.entity.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    //List<Employee> findEmployeesByEmployeeName(String employeeName);
   List<Employee> findEmployeesByEmployeeNameContains(String employeeName);

}
