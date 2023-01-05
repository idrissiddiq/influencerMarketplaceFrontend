/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketplace.clientapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 *
 * @author ASUS
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/influencer/getAll");
//        web.ignoring().antMatchers("/campaign/getOnlyOpen");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/images/**").permitAll()
//                .antMatchers("/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/login/brand").permitAll()
//                .antMatchers("/**").permitAll()
//                .antMatchers("/campaign").permitAll()
//                .antMatchers("/influencer/getAll").permitAll()
//                .antMatchers("/campaign/getOnlyOpen").permitAll()
//                .antMatchers(HttpMethod.POST,"/campaign/create").permitAll()
//                .and()
//                .csrf().ignoringAntMatchers("/influencer/getAll")
//                .and()
//                .csrf().ignoringAntMatchers("/campaign/getOnlyOpen")
                .and()
                .csrf().ignoringAntMatchers("/campaign/create")
                .and()
                .csrf().ignoringAntMatchers("/register/influencer/forgot")
                .and()
                .csrf().ignoringAntMatchers("/register/brand/forgot")
                .and()
                .csrf().ignoringAntMatchers("/campaign/edit/**")
                .and()
                .csrf().ignoringAntMatchers("/register/influencer")
                .and()
                .csrf().ignoringAntMatchers("/register/brand")
                .and()
                .csrf().ignoringAntMatchers("/influencer/upload")
                .and()
                .csrf().ignoringAntMatchers("/brand/upload")
                .and()
                .csrf().ignoringAntMatchers("/influencer/profile")
                .and()
                .csrf().ignoringAntMatchers("/brand/profile")
                .and()
                .csrf().ignoringAntMatchers("/contract/**")
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("login")
//                .loginPage("/login/brand")
//                .loginProcessingUrl("login/brand")
//                .failureForwardUrl("/login?error=true")
//                .successForwardUrl("/dashboard")
//                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true").permitAll();
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("*"));
//        configuration.setAllowedHeaders(Arrays.asList("*"));
//        configuration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
}
