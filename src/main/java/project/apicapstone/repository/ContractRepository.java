package project.apicapstone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.apicapstone.entity.Area;
import project.apicapstone.entity.Contract;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, String> {
    @Transactional(readOnly = true)
    @Query("SELECT d FROM Contract d")
    Page<Contract> findAllContract(Pageable pageable);

    @Query("SELECT c FROM Contract c WHERE lower(c.contractName)  LIKE lower(concat('%', ?1,'%')) OR c.contractId LIKE %?1%")
    List<Contract> findContractByNameOrId(String paramSearch);

    @Query("SELECT c FROM Contract c join c.employee e WHERE e.employeeId = ?1")
    List<Contract> findEmployeeIdInContract(String id);

    @Query("SELECT c.contractId FROM Contract c join c.employee e WHERE e.employeeId = ?1")
    String findEmployeeIdWithContract(String id);

    @Query("SELECT c FROM Contract c WHERE lower(c.contractName)  LIKE lower(concat('%', ?1,'%')) OR c.contractId LIKE %?1%")
    Page<Contract> search(String paramSearch, Pageable pageable);

    @Query("SELECT COUNT(c.contractId) FROM Contract c join c.employee e WHERE e.employeeId=?1 AND c.status LIKE %?2%")
    int countContractByEmployeeIdAndStatus(String s, String status);

    @Query("SELECT c FROM Contract c where c.endDate =?1")
    List<Contract> getContractsByEndDate(LocalDate date);

    @Query("SELECT c FROM Contract c join c.employee e WHERE e.employeeId = ?1")
    Contract findEmployeeById(String id);

    @Query("SELECT c FROM Contract c WHERE c.status = ?1")
    List<Contract> findContractByStatus(String status);

    @Query("SELECT c.endDate FROM Contract c ")
    List<LocalDate> getAllEndDate();

    @Query("SELECT c FROM Contract c join c.employee e WHERE e.employeeId=?1 AND c.status LIKE %?2%")
    Contract getContractByEmployeeIdAndStatus(String id, String status);
}
