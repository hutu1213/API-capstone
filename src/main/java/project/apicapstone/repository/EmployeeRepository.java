package project.apicapstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.apicapstone.entity.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    //List<Employee> findEmployeesByEmployeeName(String employeeName);
    List<Employee> findEmployeesByEmployeeNameContains(String employeeName);

    //
    @Query("SELECT e FROM Employee e WHERE e.employeeName LIKE %?1% OR e.employeeId LIKE %?1%")
    List<Employee> findEmployeesByNameOrId(String paramSearch);

    Employee findEmployeeByEmployeeId(String id);

}
