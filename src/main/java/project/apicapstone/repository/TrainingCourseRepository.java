package project.apicapstone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import project.apicapstone.entity.TrainingCourse;
@Repository
public interface TrainingCourseRepository extends JpaRepository<TrainingCourse,String> {
    @Transactional(readOnly = true)
    @Query("SELECT e FROM TrainingCourse e")
    Page<TrainingCourse> findAllCourse(Pageable pageable);


}
