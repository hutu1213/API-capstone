package project.apicapstone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.apicapstone.dto.applicant.ProcessApplicantDto;
import project.apicapstone.entity.Account;
import project.apicapstone.entity.Applicant;
import project.apicapstone.entity.Contract;

import java.util.List;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant,String> {
    @Transactional(readOnly = true)
    @Query("SELECT e FROM Applicant e")
    Page<Applicant> findAllApplicant(Pageable pageable);

    @Query("SELECT c FROM Applicant c WHERE lower(c.applicantName)  LIKE lower(concat('%', ?1,'%')) OR c.applicantId LIKE %?1%")
    List<Applicant> findApplicantsByNameOrId(String paramSearch);

    @Query("SELECT c FROM Applicant c WHERE lower(c.applicantName)  LIKE lower(concat('%', ?1,'%')) OR c.applicantId LIKE %?1%")
    Page<Applicant> search(String paramSearch, Pageable pageable);

    @Query("SELECT c.applicantId AS applicantId," +
                    "c.status AS status, " +
                    "c.resumeFile AS resumeFile, " +
                    "c.scanData AS scanData, " +
                    "c.score AS score, " +
                    "c.jobPosting AS jobPosting " +
            "FROM Applicant c JOIN c.jobPosting j WHERE j.jobPostingId = ?1")
    List<ProcessApplicantDto> getAllProcessApplicantsByJobPostingId(String jobPostingId);

    @Transactional
    @Modifying
    @Query("UPDATE Applicant a SET a.scanData = ?1 WHERE a.applicantId = ?2")
    int updateApplicantScanData(String scanData, String id);

    @Transactional
    @Modifying
    @Query("UPDATE Applicant a SET a.score = ?1 WHERE a.applicantId = ?2")
    int updateApplicantScore(Double score, String id);
  
    List<Applicant> findAllByStatusAndCheckSendMail(String status,int check);


}
