package project.apicapstone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.apicapstone.entity.Account;
import project.apicapstone.entity.JobPosting;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting,String> {
    @Transactional(readOnly = true)
    @Query("SELECT e FROM JobPosting e")
    Page<JobPosting> findAllJobPosting(Pageable pageable);
}
