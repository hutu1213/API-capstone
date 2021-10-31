package project.apicapstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.apicapstone.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

}
