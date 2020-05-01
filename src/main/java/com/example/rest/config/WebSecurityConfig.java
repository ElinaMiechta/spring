package com.example.rest.config;

import com.example.rest.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    private final AdminRepository adminRepository;

    @Autowired
    public WebSecurityConfig(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                // SQL dla logowania użytkownika po adresie email i haśle
                .usersByUsernameQuery("SELECT c.email, c.password FROM clients c WHERE c.email = ?")
                // SQL dla przypisania uprawnień dla zalogowanego użytkownika
                .authoritiesByUsernameQuery("SELECT c.email, c.password FROM clients c  WHERE c.email = ?")
                // wynik logowania
                .dataSource(dataSource)
                // szyfrowanie hasła
                .passwordEncoder(passwordEncoder());


    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.antMatcher("/admin")
                .authorizeRequests()
                .anyRequest()
                .hasRole("ADMIN")

                .and()
                .formLogin()
                .loginPage("/loginAdmin");



    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
