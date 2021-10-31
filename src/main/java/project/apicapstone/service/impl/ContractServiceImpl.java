package project.apicapstone.service.impl;

import org.springframework.stereotype.Service;
import project.apicapstone.dto.contract.CreateContractDto;
import project.apicapstone.entity.Contract;
import project.apicapstone.repository.ContractRepository;
import project.apicapstone.service.ContractService;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    private ContractRepository contractRepository;

    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    @Override
    public Contract addNewContract(CreateContractDto dto) {
        Contract addContract = new Contract();
        addContract.setContractId(dto.getContractId());
        addContract.setContractName(dto.getContractName());
        addContract.setStartDate(dto.getStartDate());
        addContract.setEndDate(dto.getEndDate());
        addContract.setStatus(dto.getStatus());
        addContract.setContent(dto.getContent());
        addContract.setSalary(dto.getSalary());
        addContract.setWorkingTime(dto.getWorkingTime());
        return contractRepository.save(addContract);
    }

    @Override
    public boolean isExisted(String s) {
        return contractRepository.existsById(s);
    }

}
