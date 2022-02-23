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
    private final UserDetailsService userDetailsService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfig(UserDetailsService userDetailsService, JwtAuthorizationFilter jwtAuthorizationFilter) {
        this.userDetailsService = userDetailsService;
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

        //test1
        //http.csrf().disable().cors().and().authorizeRequests().anyRequest().permitAll();

        //cấu hình xác thực cho các api
        http.authorizeRequests()
                .antMatchers("/api/jobPosting/search-paging/{paramSearch}/{position}", "/api/auth/login","/swagger-ui.html/**","/api/applicant/create-applicant","/api/jobPosting/get-by-id/{id}","/api/jobPosting").permitAll()
                .antMatchers("/api/allowance/**", "/api/applicant/**", "/api/area/**", "/api/account/**", "/api/contract/**", "/api/training-course/**", "/api/criteria/**", "/api/department/**", "/api/dependant/**", "/api/excel/**", "/api/employees/**", "/api/jobPosting/**", "/api/position/**", "/api/role/**", "/api/subarea/**", "/api/task/**", "/api/title/**", "/api/workplace/**","/api/evaluation/**","/api/mail/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_TRUONGPHONG")
                .antMatchers("/api/employees/**", "/api/skill/**", "/api/dependant/**", "/api/time-keeping/**").hasAnyAuthority("ROLE_QL_NHANVIEN", "ROLE_TRUONGPHONG")
                .antMatchers("/api/contract/**", "/api/allowance/**").hasAnyAuthority("ROLE_QL_HOPDONG", "ROLE_TRUONGPHONG")
                .antMatchers("/api/jobPosting/**", "/api/applicant/search/{paramSearch}", "/api/applicant/**").hasAnyAuthority("ROLE_QL_TUYENDUNG", "ROLE_TRUONGPHONG")
//                .antMatchers("").hasAnyAuthority("TRƯỞNG PHÒNG BAN KHÁC")
                .antMatchers("/api/task/**").hasAnyAuthority("ROLE_NHANVIEN", "ROLE_TRUONGPHONG")
                .anyRequest().authenticated();//.and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);


    }
}
