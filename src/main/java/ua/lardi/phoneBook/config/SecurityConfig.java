package ua.lardi.phoneBook.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/resources/**", "/registration/**").permitAll()
//                .anyRequest().authenticated();
//        http.formLogin()
//                .loginPage("/signin")
//                .loginProcessingUrl("/spring_security_check")
//                .failureUrl("/signin?error")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .permitAll();
//        http.logout()
//                .permitAll()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/signin?logout")
//                .invalidateHttpSession(true);
    }
}
