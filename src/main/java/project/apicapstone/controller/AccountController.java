package project.apicapstone.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.dto.account.CreateAccountDto;
import project.apicapstone.entity.Account;
import project.apicapstone.service.AccountService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;
    public AccountController(AccountService service) {
        this.accountService = accountService;
    }

    //    @GetMapping("")
//    public ResponseEntity<List<User>> findAllUser() {
//        List<User> users = service.findAll();
//
//        //return ResponseHandler.getResponse(users, HttpStatus.OK);
//        return ResponseEntity.ok().body(users);
//    }
    @GetMapping("")
    public Object findAllUser() {
        List<Account> accounts = accountService.findAll();
        //return ResponseHandler.getResponse(users, HttpStatus.OK);
        return ResponseEntity.ok().body(accounts);
    }
    @PostMapping("")
    public Object createUser(@Valid @RequestBody CreateAccountDto dto,
                             BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Account newAcc = accountService.createAccount(dto);

        return ResponseHandler.getResponse(newAcc, HttpStatus.OK);
    }
}
