package project.apicapstone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.apicapstone.entity.Position;
import project.apicapstone.entity.RecruitmentRequest;

@Repository
public interface RecruitmentRequestRepository extends JpaRepository<RecruitmentRequest, Long> {
    @Transactional(readOnly = true)
    @Query("SELECT e FROM RecruitmentRequest e")
    Page<RecruitmentRequest> findAllRecruitmentRequest(Pageable pageable);

    @Query("SELECT r FROM RecruitmentRequest r join r.employee e where e.employeeId=?1")
    Page<RecruitmentRequest> getByEmployeeId(String id, Pageable pageable);

//    @Query("SELECT r FROM RecruitmentRequest r join r.employee e where =?1")
    Page<RecruitmentRequest> getAllByStatus(String status, Pageable pageable);
}
