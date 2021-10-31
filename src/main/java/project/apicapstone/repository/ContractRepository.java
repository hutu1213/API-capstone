package project.apicapstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.apicapstone.entity.Contract;
@Repository
public interface ContractRepository extends JpaRepository<Contract,String> {
}
