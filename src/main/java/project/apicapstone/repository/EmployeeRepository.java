package project.apicapstone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.apicapstone.entity.Employee;
import project.apicapstone.entity.Title;
import project.apicapstone.entity.util.WorkingStatus;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    //List<Employee> findEmployeesByEmployeeName(String employeeName);
    List<Employee> findEmployeesByEmployeeNameContains(String employeeName);


    @Query("SELECT e FROM Employee e WHERE lower(e.employeeName)  LIKE lower(concat('%', ?1,'%'))  OR e.employeeId LIKE %?1%")
    List<Employee> findEmployeesByNameOrId(String paramSearch);

    @Transactional(readOnly = true)
    @Query("SELECT e FROM Employee e")
    Page<Employee> findAllEmp(Pageable pageable);

    //@Query("select u from User u where lower(u.name) like lower(concat('%', ?1,'%'))")
    @Query("SELECT e FROM Employee e WHERE lower(e.employeeName)  LIKE lower(concat('%', ?1,'%'))  OR e.employeeId LIKE %?1%")
    Page<Employee> search(String paramSearch, Pageable pageable);

    int countByCreateDateBetweenAndWorkingStatusNotContains(LocalDate start, LocalDate end, String workingStatus);

    int countByCreateDateBetween(LocalDate start, LocalDate end);

    int countByUpdateDateBetweenAndWorkingStatus(LocalDate start, LocalDate end, String workingStatus);

    @Query("SELECT COUNT(e.employeeId) FROM Employee e")
    int countAll();

    int countEmployeeByWorkingStatus(String workingStatus);

//    @Query("SELECT e.dateBirth FROM Employee e")
//    List<LocalDate> getAllBirth();

    List<Employee> getAllByDayOfBirthAndMonthOfBirth(int day, int month);

    @Query("SELECT COUNT(a.areaId) FROM Employee e JOIN e.workplace w JOIN w.subarea s JOIN s.area a WHERE a.name = ?1")
    int countByArea(String area);

    int countEmployeesByEmployeeId(String id);

    List<Employee> findByEmployeeNameIgnoreCase(String name);

    @Query("SELECT e FROM Employee e join e.trainingCourses tc WHERE tc.courseId = ?1")
    List<Employee> findAllByTrainingCourseId(String id);

    @Query("SELECT COUNT(e.employeeId) FROM Employee e join e.trainingCourses tc WHERE tc.courseId = ?1 AND e.employeeId =?2")
    int findEmployeeByCourseIdAndEmployeeId(String courseId, String employeeId);

    @Query("SELECT COUNT(e.employeeId) FROM Employee e join e.tasks t WHERE t.taskId = ?1 AND e.employeeId =?2")
    int findEmployeeByTaskIdAndEmployeeId(Long taskId, String employeeId);

    @Query("SELECT e FROM Employee e join e.tasks t WHERE t.taskId = ?1")
    List<Employee> findAllByTaskId(Long id);

    List<Employee> findByDayOfBirthAndMonthOfBirth(int dayOfMonth, int monthValue);

    @Query("SELECT COUNT(e.employeeId) FROM Employee e join e.recruitmentRequests rr WHERE e.employeeId=?1 AND rr.status=?2 ")
    int getEmployeeByRequestIdAndStatus(String s, String status);


}
