package be.zlz.budgetor.api.config;

import be.zlz.budgetor.api.controller.filter.JWTFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    //todo use property value
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(new JWTFilter("secret"), BasicAuthenticationFilter.class)
                .antMatcher("/api/**")
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
