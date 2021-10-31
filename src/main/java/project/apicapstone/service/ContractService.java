package project.apicapstone.service;

import project.apicapstone.dto.contract.CreateContractDto;
import project.apicapstone.entity.Contract;

import java.util.List;

public interface ContractService {
    List<Contract> findAll();

    Contract addNewContract(CreateContractDto dto);

    boolean isExisted(String s);
}
