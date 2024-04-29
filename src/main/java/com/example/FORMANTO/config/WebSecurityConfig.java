//package com.example.FORMANTO.config;
//
//import com.example.FORMANTO.service.CustomerDetailService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
//
//@Configuration
//@RequiredArgsConstructor
//public class WebSecurityConfig {
//
//    private final CustomerDetailService customerDetailService;
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer(){
//        return web -> web.ignoring()
//                .requestMatchers(toH2Console())
//                .requestMatchers("/static/**");
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        return http.authorizeHttpRequests(auth -> auth
//                .requestMatchers("/login", "/signup", "/user").permitAll()
//                .anyRequest().permitAll()
//        ).formLogin(in -> in
//                .loginPage("/login")
//                .defaultSuccessUrl("/main")
//        ).logout(out -> out
//                .logoutSuccessUrl("/login")
//                .invalidateHttpSession(true)
//        ).csrf(csrf -> csrf
//                .disable()
//        ).build();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(customerDetailService);
//        provider.setPasswordEncoder(bCryptPasswordEncoder());
//        return provider;
//    }
//}
