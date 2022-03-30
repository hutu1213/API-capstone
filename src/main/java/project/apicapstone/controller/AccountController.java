package project.apicapstone.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.dto.account.CreateAccountDto;
import project.apicapstone.dto.account.AddRoleDto;
import project.apicapstone.dto.account.UpdateAccountDto;
import project.apicapstone.entity.Account;
import project.apicapstone.service.AccountService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/api/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public Object findAllAccount(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Account> accountPage = accountService.findAllAccount(pageable);
        return ResponseHandler.getResponse(accountService.pagingFormat(accountPage), HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public Object findAll() {
        List<Account> accounts = accountService.findAll();
        return ResponseHandler.getResponse(accounts, HttpStatus.OK);
    }

    @PostMapping("/create-account")
    public Object createAccount(@Valid @RequestBody CreateAccountDto dto, BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Account createAcc = accountService.createAccount(dto);

        return ResponseHandler.getResponse(createAcc, HttpStatus.CREATED);
    }

    @PutMapping
    public Object update(@Valid @RequestBody UpdateAccountDto dto, BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        accountService.update(dto);
        return ResponseHandler.getResponse("Successful", HttpStatus.OK);
    }

    @PostMapping("/add-role")
    public Object addRoleToAccount(@Valid @RequestBody AddRoleDto dto, BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Account updatedAccount = accountService.addRole(dto);

        return ResponseHandler.getResponse("Add Successful !", HttpStatus.OK);
    }

    @GetMapping("/get-by-role-id/{roleId}")
    public Object getByRoleId(@PathVariable String roleId) {
        List<Account> accountList = accountService.getByRoleId(roleId);

        return ResponseHandler.getResponse(accountList, HttpStatus.OK);
    }

    @GetMapping("/get-by-employee-id/{id}")
    public Object getByEmployeeId(@PathVariable String id) {
        Account account = accountService.getByEmployeeId(id);
        return ResponseHandler.getResponse(account, HttpStatus.OK);
    }

}
