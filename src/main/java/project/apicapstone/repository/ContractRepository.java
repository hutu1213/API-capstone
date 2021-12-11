package project.apicapstone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.apicapstone.entity.Contract;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract,String> {
    @Transactional(readOnly = true)
    @Query("SELECT d FROM Contract d")
    Page<Contract> findAllContract(Pageable pageable);

    @Query("SELECT c FROM Contract c WHERE c.contractName LIKE %?1% OR c.contractId LIKE %?1%")
    List<Contract> findContractByNameOrId(String paramSearch);
}
