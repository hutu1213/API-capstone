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

}