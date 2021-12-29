package project.apicapstone.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.dto.contract.CreateContractDto;
import project.apicapstone.dto.contract.UpdateContractDto;

import project.apicapstone.entity.Contract;

import project.apicapstone.service.ContractService;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/contract")
public class ContractController {
    private ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("/get-all")
    public Object findAll() {
        List<Contract> contracts = contractService.findAll();
        return ResponseHandler.getResponse(contracts, HttpStatus.OK);
    }

    @GetMapping
    public Object findAllContract(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Contract> contractPage = contractService.findAllContract(pageable);
        return ResponseHandler.getResponse(contractService.pagingFormat(contractPage), HttpStatus.OK);
    }

    @PostMapping
    public Object createContract(@Valid @RequestBody CreateContractDto dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
        }
        Contract createContract = contractService.addNewContract(dto);
        return ResponseHandler.getResponse(createContract, HttpStatus.CREATED);
    }

    @GetMapping("/get-by-id/{id}")
    public Object findContractById(@PathVariable String id) {
        Contract contract = contractService.getById(id);
        return ResponseHandler.getResponse(contract, HttpStatus.OK);
    }

    @PutMapping
    public Object updateContract(@Valid @RequestBody UpdateContractDto dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
        }
        contractService.update(dto, dto.getContractId());
        return ResponseHandler.getResponse(HttpStatus.OK);
    }

    @DeleteMapping()
    public Object deleteContract(@RequestParam(name = "id") String id) {
        contractService.deleteById(id);
        return ResponseHandler.getResponse(HttpStatus.OK);
    }

    @GetMapping("/search/{paramSearch}")
    public Object findContractByNameOrId(@PathVariable String paramSearch) {
        List<Contract> contractList = contractService.findContractByNameOrId(paramSearch);
        if (contractList.isEmpty()) {
            return ResponseHandler.getErrors("Not found ",HttpStatus.NOT_FOUND);
        }
        return ResponseHandler.getResponse(contractList, HttpStatus.OK);
    }
}
