package project.apicapstone.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.apicapstone.dto.contract.CreateContractDto;
import project.apicapstone.dto.contract.PagingFormatContractDto;
import project.apicapstone.entity.Contract;

import java.util.List;

public interface ContractService {
    List<Contract> findAll();

    Contract addNewContract(CreateContractDto dto);

    boolean isExisted(String s);

    Page<Contract> findAllContract(Pageable pageable);

    PagingFormatContractDto pagingFormat(Page<Contract> contractPage);
}
