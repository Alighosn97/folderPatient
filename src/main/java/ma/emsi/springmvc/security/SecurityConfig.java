package ma.emsi.springmvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private DataSource datasource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();
        auth.jdbcAuthentication()
                .dataSource(datasource)
                .usersByUsernameQuery("SELECT username as principal,password as credentials,active from users where username=?")
        .authoritiesByUsernameQuery("select username as principal , role as role from users_roles where username=?")
        .passwordEncoder(passwordEncoder).rolePrefix("ROLE_");
        System.out.println(passwordEncoder.encode("1234"));
        //auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder.encode("1234")).roles("user");
        //auth.inMemoryAuthentication().withUser("User2").password(passwordEncoder.encode("1234")).roles("user","admin");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login");

        http.authorizeRequests().antMatchers("/save**/**","/delete**/**","/form**/**").hasRole("admin");
        http.authorizeRequests().antMatchers("/patients**/**").hasRole("user");
        http.authorizeRequests().antMatchers("/login","/webjars/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.csrf();
        http.exceptionHandling().accessDeniedPage("/notAuthorized");
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
