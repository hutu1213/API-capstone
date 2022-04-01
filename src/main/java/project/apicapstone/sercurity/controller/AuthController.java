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
import project.apicapstone.entity.Role;
import project.apicapstone.entity.Title;
import project.apicapstone.repository.AccountRepository;
import project.apicapstone.repository.EmployeeRepository;
import project.apicapstone.repository.RoleRepository;
import project.apicapstone.sercurity.dto.LoginDto;
import project.apicapstone.sercurity.jwt.JwtUtils;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(Jwts.class);
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private AccountRepository accountRepository;
    private RoleRepository roleRepository;
    private static final String STATUS = "ACTIVE";


    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, AccountRepository accountRepository, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/login")
    public Object login(@Valid @RequestBody LoginDto dto, BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Authentication auth = null;
        Account account = accountRepository.findByUsername(dto.getUsername());
        if (!account.getStatus().equals(STATUS)) {
            return ResponseHandler.getErrors("Tài khoản không hoạt động", HttpStatus.BAD_REQUEST);
        }
        try {
            auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = jwtUtils.generateJwtToken(auth);
            List<Role> roleList = roleRepository.findByUsername(dto.getUsername());
            return ResponseHandler.getResponseLogin(roleList, token, account, HttpStatus.OK);
        } catch (Exception e) {
            logger.debug("{} has been logged in with wrong password: {}", dto.getUsername(), e.getMessage());
        }
        return ResponseHandler.getErrors("Tên đăng nhập hoặc mật khẩu không đúng", HttpStatus.BAD_REQUEST);
    }
}