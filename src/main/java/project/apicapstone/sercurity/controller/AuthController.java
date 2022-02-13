package project.apicapstone.sercurity.controller;

import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.entity.Account;
import project.apicapstone.entity.Title;
import project.apicapstone.repository.AccountRepository;
import project.apicapstone.repository.EmployeeRepository;
import project.apicapstone.sercurity.dto.LoginDto;
import project.apicapstone.sercurity.jwt.JwtUtils;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(Jwts.class);
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private AccountRepository accountRepository;


    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, AccountRepository accountRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.accountRepository = accountRepository;
    }

    @PostMapping("/login")
    public Object login(@Valid @RequestBody LoginDto dto, BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Authentication auth = null;

        try {
            auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = jwtUtils.generateJwtToken(auth);
            Account account = accountRepository.findByUsername(dto.getUsername());
            // String title = accountRepository.findTitleByUsername(dto.getUsername());
            return ResponseHandler.getResponseLogin(token, account, HttpStatus.OK);
            //return ResponseHandler.getResponse(token, HttpStatus.OK);

        } catch (Exception e) {
            logger.debug("{} has been logged in with wrong password: {}", dto.getUsername(), e.getMessage());
        }
        return ResponseHandler.getErrors("Username or password is invalid.", HttpStatus.BAD_REQUEST);
    }
}