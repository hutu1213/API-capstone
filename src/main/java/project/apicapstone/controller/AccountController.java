package project.apicapstone.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.dto.account.CreateAccountDto;
import project.apicapstone.dto.role.AddRoleDto;
import project.apicapstone.entity.Account;
import project.apicapstone.service.AccountService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping()
    public Object findAllUser() {
        List<Account> accounts = accountService.findAll();
        return ResponseHandler.getResponse(accounts, HttpStatus.OK);
    }

    @PostMapping("/create-account")
    public Object createUser(@Valid @RequestBody CreateAccountDto dto,
                             BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Account newAcc = accountService.createAccount(dto);

        return ResponseHandler.getResponse(newAcc, HttpStatus.OK);
    }

    @PostMapping("/add-role")
    public Object addRoleToAccount(@Valid @RequestBody AddRoleDto dto, BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Account updatedAccount = accountService.addRole(dto);

        return ResponseHandler.getResponse("Add Successful !", HttpStatus.OK);
    }
}
