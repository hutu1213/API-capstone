package project.apicapstone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.apicapstone.entity.Account;
import project.apicapstone.entity.Contract;
import project.apicapstone.entity.Employee;
import project.apicapstone.entity.JobPosting;

import java.util.List;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, String> {
    @Transactional(readOnly = true)
    @Query("SELECT e FROM JobPosting e")
    Page<JobPosting> findAllJobPosting(Pageable pageable);

    //    @Query("SELECT j FROM JobPosting j WHERE j.jobDescription LIKE %?1% OR j.jobPostingId LIKE %?1% OR j.vacancies LIKE %?1% ")
//    List<JobPosting> findJobPostingsByIdAndDescriptionAndVacancies(String paramSearch);
    @Query("SELECT c FROM JobPosting c WHERE lower(c.postTitle)  LIKE lower(concat('%', ?1,'%')) OR c.jobPostingId LIKE %?1%")
    Page<JobPosting> search(String paramSearch, Pageable pageable);


}
