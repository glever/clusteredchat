package be.glever.poc.clusteredchat.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.session.ConcurrentSessionFilter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterAfter(new NaiveAuthenticationFilter(), ConcurrentSessionFilter.class);
        http.requestMatchers().antMatchers("/**");
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> new User(username, null, null);
    }
}
