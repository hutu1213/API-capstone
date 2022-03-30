package project.apicapstone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.dto.account.ChangePasswordDto;
import project.apicapstone.entity.Account;
import project.apicapstone.service.AccountService;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/password")
public class PasswordController {
    private final AccountService accountService;

    public PasswordController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/change-password")
    public Object changPassword(@Valid @RequestBody ChangePasswordDto passwordDto, BindingResult bindingResult) {
        // xac dinh user sau khi dang nhap
        //Account account = accountService.findByUsername(((Account) SecurityContextHolder.getContext().getAuthentication().get).getUsername());
        if (bindingResult.hasErrors())
            return ResponseHandler.getResponse(bindingResult, HttpStatus.BAD_REQUEST);

        Account account = accountService.findByUsername(passwordDto.getUsername());

        if (!accountService.checkIfValidOldPassword(account, passwordDto.getOldPassword())) {
            return ResponseHandler.getErrors("Sai mật khẩu cũ", HttpStatus.BAD_REQUEST);
        }
        if (!passwordDto.getNewPassword().equals(passwordDto.getConfirmNewPassword())) {
            return ResponseHandler.getErrors("Xác nhận mật khẩu mới không trùng khớp", HttpStatus.BAD_REQUEST);
        }
        accountService.changePassword(account, passwordDto.getNewPassword());
        return ResponseHandler.getResponse("Successful", HttpStatus.OK);
    }
}
