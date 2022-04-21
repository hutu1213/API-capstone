package project.apicapstone.sercurity.config;


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

    private static final String MANAGE_ALLOWANCE = "/v1/api/allowance/**";
    private static final String MANAGE_REQUEST = "/v1/api/recruitmentRequest/**";
    private static final String MANAGE_APPLICANT = "/v1/api/applicant/**";
    private static final String MANAGE_AREA = "/v1/api/area/**";
    private static final String MANAGE_ACCOUNT = "/v1/api/account/**";
    private static final String MANAGE_CONTRACT = "/v1/api/contract/**";
    private static final String MANAGE_TRAINING_COURSE = "/v1/api/training-course/**";
    private static final String MANAGE_CRITERIA = "/v1/api/criteria/**";
    private static final String MANAGE_DEPARTMENT = "/v1/api/department/**";
    private static final String MANAGE_DEPENDANT = "/v1/api/dependant/**";
    private static final String MANAGE_EXCEL = "/v1/api/excel/**";
    private static final String MANAGE_EMPLOYEE = "/v1/api/employees/**";
    private static final String MANAGE_JOB_POSTING = "/v1/api/jobPosting/**";
    private static final String MANAGE_POSITION = "/v1/api/position/**";
    private static final String MANAGE_ROLE = "/v1/api/role/**";
    private static final String MANAGE_SUB_AREA = "/v1/api/subarea/**";
    private static final String MANAGE_TASK = "/v1/api/task/**";
    private static final String MANAGE_TITLE = "/v1/api/title/**";
    private static final String MANAGE_WORKPLACE = "/v1/api/workplace/**";
    private static final String MANAGE_EVALUATION = "/v1/api/evaluation/**";
    private static final String MANAGE_MAIL = "/v1/api/mail/**";
    private static final String MANAGE_SKILL = "/v1/api/skill/**";
    private static final String MANAGE_PASSWORD = "/v1/api/password/**";
    private static final String MANAGE_TIME_KEEPING = "/v1/api/time-keeping/**";
    private static final String MANAGE_RECRUITMENT_REQUEST = "/v1/api/recruitmentRequest/**";
    private static final String ROLE_TRUONGPHONG = "ROLE_TRUONGPHONG";
    private static final String ROLE_QL_NHANVIEN = "ROLE_QL_NHANVIEN";
    private static final String ROLE_QL_HOPDONG = "ROLE_QL_HOPDONG";
    private static final String ROLE_QL_TUYENDUNG = "ROLE_QL_TUYENDUNG";
    private static final String ROLE_NHANVIEN = "ROLE_NHANVIEN";
    private static final String ROLE_TRUONGPHONGBAN_KHAC = "ROLE_TRUONGPHONGBAN_KHAC";

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
                .antMatchers("/v1/api/jobPosting/search-paging/{paramSearch}/{position}",
                        "/v1/api/auth/login", "/swagger-ui.html#/**", "/v1/api/applicant/create-applicant",
                        "/v1/api/jobPosting/get-by-id/{id}", "/v1/api/jobPosting", "/dashboard/**", "/api/**", "/sse/**",
                        "/v1/api/title/get-all",
                        "/v1/api/department/get-all",
                        "/v1/api/position/get-all",
                        "/v1/api/area/get-all",
                        "/v1/api/subarea/get-all",
                        "/v1/api/workplace/get-all",
                        "/v1/api/applicant/get-all",
                        "/v1/api/title/get-by").permitAll()
                .antMatchers(MANAGE_APPLICANT, MANAGE_EVALUATION)
                .hasAnyAuthority(ROLE_TRUONGPHONG, ROLE_QL_TUYENDUNG, ROLE_TRUONGPHONGBAN_KHAC)

                .antMatchers("/v1/api/employees/get-by-task-id/{id}", MANAGE_REQUEST, MANAGE_PASSWORD).
                hasAnyAuthority(ROLE_TRUONGPHONG, ROLE_QL_NHANVIEN, ROLE_QL_HOPDONG, ROLE_QL_TUYENDUNG, ROLE_TRUONGPHONGBAN_KHAC, ROLE_NHANVIEN)

                .antMatchers(MANAGE_EMPLOYEE, MANAGE_DEPENDANT).hasAnyAuthority(ROLE_QL_NHANVIEN, ROLE_TRUONGPHONG)
                .antMatchers(MANAGE_CONTRACT, MANAGE_ALLOWANCE).hasAnyAuthority(ROLE_QL_HOPDONG, ROLE_TRUONGPHONG)
                .antMatchers(
                        MANAGE_SKILL,
                        MANAGE_TIME_KEEPING
                ).hasAnyAuthority(ROLE_QL_NHANVIEN)

                .antMatchers(MANAGE_TASK).hasAnyAuthority(ROLE_NHANVIEN, ROLE_TRUONGPHONG)

                .antMatchers(MANAGE_AREA, MANAGE_ACCOUNT, MANAGE_TRAINING_COURSE,
                        MANAGE_CRITERIA, MANAGE_DEPARTMENT,
                        MANAGE_EXCEL, MANAGE_JOB_POSTING,
                        MANAGE_POSITION, MANAGE_ROLE, MANAGE_SUB_AREA,
                        MANAGE_TITLE, MANAGE_WORKPLACE, MANAGE_RECRUITMENT_REQUEST,
                        MANAGE_MAIL)
                .hasAnyAuthority(ROLE_TRUONGPHONG)
                .anyRequest().authenticated();


// .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
    }
}
