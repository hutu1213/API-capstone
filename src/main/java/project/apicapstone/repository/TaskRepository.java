package project.apicapstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.apicapstone.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,String> {
}
