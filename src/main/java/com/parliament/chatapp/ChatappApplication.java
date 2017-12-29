package com.parliament.chatapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.parliament.chatapp.repository.AccountRepository;
import com.parliament.chatapp.model.Account;

@SpringBootApplication
@EnableJpaAuditing
public class ChatappApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatappApplication.class, args);
	}


}

@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void init( AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService( userDetailsService() );
    }

    @Bean
    UserDetailsService userDetailsService() {

        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {

                Account account = accountRepository.findByUsername( username );
                if( account != null ) {
                    return new User( account.getUsername(), account.getPassword(), true, true, true, true, AuthorityUtils.createAuthorityList("USER") );
                } else {
                    throw new UsernameNotFoundException( "could not find user " + username );
                }
            }
        };
    }
}


@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure( HttpSecurity http ) throws Exception {

        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/user").permitAll().anyRequest().fullyAuthenticated().and().httpBasic().and().csrf().disable();

    }

}
