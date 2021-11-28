package project.apicapstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.apicapstone.entity.JobPosting;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting,String> {
}
