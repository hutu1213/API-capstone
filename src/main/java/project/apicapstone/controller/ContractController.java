package project.apicapstone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.dto.contract.CreateContractDto;
import project.apicapstone.dto.department.CreateDepartmentDto;
import project.apicapstone.entity.Contract;
import project.apicapstone.entity.Department;
import project.apicapstone.service.ContractService;
import project.apicapstone.service.DepartmentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/contract")
public class ContractController {
    private ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping
    public Object findAllContract() {
        List<Contract> contracts = contractService.findAll();
        return ResponseHandler.getResponse(contracts, HttpStatus.OK);
    }

    @PostMapping
    public Object addContract(@Valid @RequestBody CreateContractDto dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
        }
        Contract addedContract = contractService.addNewContract(dto);
        return ResponseHandler.getResponse(addedContract, HttpStatus.CREATED);
    }
}
