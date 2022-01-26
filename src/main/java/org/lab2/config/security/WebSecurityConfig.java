package org.lab2.config.security;

import org.lab2.dao.DAOConnection;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@ComponentScan("org.lab2")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static Logger logger = Logger.getLogger(WebSecurityConfig.class);

    private DAOConnection daoConnection;

    @Autowired
    public void setDaoConnection(DAOConnection daoConnection) {
        this.daoConnection = daoConnection;
    }

    @Override
    protected void configure(HttpSecurity http) {
        try {
            http.authorizeRequests().
                    antMatchers("/index", "/", "/registrationPage").permitAll()
                    .antMatchers("/list").authenticated()
                    .and()
                    .formLogin()
                    .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/")
                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/access");
        } catch (Exception e) {
            logger.error("error in configure. WebSecurityConfig.class ", e);
        }
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationMgr) {
        try {
            authenticationMgr.jdbcAuthentication().dataSource(daoConnection.getDataSource()).passwordEncoder(NoOpPasswordEncoder.getInstance())
                    .usersByUsernameQuery(
                            "select USERNAME, PASSWORD, ENABLE " +
                                    "from LAB2_USERS " +
                                    "where USERNAME = ?")
                    .authoritiesByUsernameQuery(
                            "SELECT U.USERNAME, R.ROLE_NAME " +
                                    "FROM LAB2_USERS U " +
                                    "LEFT OUTER JOIN LAB2_ROLES R " +
                                    "ON U.ROLE_ID = R.ROLE_ID " +
                                    "WHERE U.USERNAME = ?");

        } catch (Exception e) {
            logger.error("error in configure. WebSecurityConfig.class ", e);
        }
    }
}