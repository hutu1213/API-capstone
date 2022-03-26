package project.apicapstone.sercurity.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import project.apicapstone.sercurity.jwt.JwtAuthenticationEntryPoint;
import project.apicapstone.sercurity.jwt.JwtAuthorizationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String MANAGE_ALLOWANCE = "/api/allowance/**";
    private static final String MANAGE_APPLICANT = "/api/applicant/**";
    private static final String MANAGE_AREA = "/api/area/**";
    private static final String MANAGE_ACCOUNT = "/api/account/**";
    private static final String MANAGE_CONTRACT = "/api/contract/**";
    private static final String MANAGE_TRAINING_COURSE = "/api/training-course/**";
    private static final String MANAGE_CRITERIA = "/api/criteria/**";
    private static final String MANAGE_DEPARTMENT = "/api/department/**";
    private static final String MANAGE_DEPENDANT = "/api/dependant/**";
    private static final String MANAGE_EXCEL = "/api/excel/**";
    private static final String MANAGE_EMPLOYEE = "/api/employees/**";
    private static final String MANAGE_JOB_POSTING = "/api/jobPosting/**";
    private static final String MANAGE_POSITION = "/api/position/**";
    private static final String MANAGE_ROLE = "/api/role/**";
    private static final String MANAGE_SUB_AREA = "/api/subarea/**";
    private static final String MANAGE_TASK = "/api/task/**";
    private static final String MANAGE_TITLE = "/api/title/**";
    private static final String MANAGE_WORKPLACE = "/api/workplace/**";
    private static final String MANAGE_EVALUATION = "/api/evaluation/**";
    private static final String MANAGE_MAIL = "/api/mail/**";
    private static final String MANAGE_SKILL = "/api/skill/**";
    private static final String MANAGE_TIME_KEEPING = "/api/time-keeping/**";

    private static final String ROLE_TRUONGPHONG = "ROLE_TRUONGPHONG";
    private static final String ROLE_QL_NHANVIEN = "ROLE_QL_NHANVIEN";
    private static final String ROLE_QL_HOPDONG = "ROLE_QL_HOPDONG";
    private static final String ROLE_QL_TUYENDUNG = "ROLE_QL_TUYENDUNG";
    private static final String ROLE_NHANVIEN = "ROLE_NHANVIEN";

    private final UserDetailsService userDetailsService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, UserDetailsService userDetailsService, JwtAuthorizationFilter jwtAuthorizationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        http.cors().configurationSource(request -> configuration.applyPermitDefaultValues());

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        http.csrf().disable();

        //cấu hình xác thực cho các api
        http.authorizeRequests()
                .antMatchers("/api/jobPosting/search-paging/{paramSearch}/{position}", "/api/auth/login", "/swagger-ui.html#/**", "/api/applicant/create-applicant", "/api/jobPosting/get-by-id/{id}", "/api/jobPosting", "/dashboard/**", "/api/title/get-all").permitAll()
                .antMatchers(MANAGE_ALLOWANCE, MANAGE_APPLICANT,
                        MANAGE_AREA, MANAGE_ACCOUNT, MANAGE_CONTRACT, MANAGE_TRAINING_COURSE,
                        MANAGE_CRITERIA, MANAGE_DEPARTMENT, MANAGE_DEPENDANT,
                        MANAGE_EXCEL, MANAGE_EMPLOYEE, MANAGE_JOB_POSTING,
                        MANAGE_POSITION, MANAGE_ROLE, MANAGE_SUB_AREA,
                        MANAGE_TASK, MANAGE_TITLE, MANAGE_WORKPLACE,
                        MANAGE_EVALUATION, MANAGE_MAIL).hasAnyAuthority("ROLE_ADMIN", ROLE_TRUONGPHONG)
                .antMatchers(MANAGE_EMPLOYEE,
                        MANAGE_SKILL,
                        MANAGE_DEPENDANT,
                        MANAGE_TIME_KEEPING).hasAnyAuthority(ROLE_QL_NHANVIEN, ROLE_TRUONGPHONG)
                .antMatchers(MANAGE_CONTRACT,
                        MANAGE_ALLOWANCE).hasAnyAuthority(ROLE_QL_HOPDONG, ROLE_TRUONGPHONG)
                .antMatchers(MANAGE_JOB_POSTING,
                        "/api/applicant/search/{paramSearch}",
                        MANAGE_APPLICANT).hasAnyAuthority(ROLE_QL_TUYENDUNG, ROLE_TRUONGPHONG)
//              .antMatchers("").hasAnyAuthority("TRƯỞNG PHÒNG BAN KHÁC")

                .antMatchers(MANAGE_TASK).hasAnyAuthority(ROLE_NHANVIEN, ROLE_TRUONGPHONG)
                .anyRequest().authenticated();


// .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
    }
}
