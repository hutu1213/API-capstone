package project.apicapstone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.apicapstone.entity.Contract;
import project.apicapstone.entity.Department;
import project.apicapstone.entity.Employee;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

    @Transactional(readOnly = true)
    @Query("SELECT d FROM Department d")
    Page<Department> findALlDepartment(Pageable pageable);

    @Query("SELECT c FROM Department c WHERE lower(c.departmentName)  LIKE lower(concat('%', ?1,'%')) OR c.departmentId LIKE %?1%")
    List<Department> findDepartmentsByNameOrId(String paramSearch);


    @Query("SELECT c FROM Department c WHERE lower(c.departmentName)  LIKE lower(concat('%', ?1,'%')) OR c.departmentId LIKE %?1%")
    Page<Department> search(String paramSearch, Pageable pageable);
}
