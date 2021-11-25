package project.apicapstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.apicapstone.entity.Applicant;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant,String> {
}
