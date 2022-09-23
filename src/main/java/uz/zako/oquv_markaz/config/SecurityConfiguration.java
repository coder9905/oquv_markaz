package uz.zako.oquv_markaz.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import uz.zako.oquv_markaz.security.JwtAuthEntryPoint;
import uz.zako.oquv_markaz.security.JwtConfigurer;
import uz.zako.oquv_markaz.security.JwtTokenProvider;

@Configuration
@EnableWebSecurity// hammasiga zakrit qiladi
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthEntryPoint jwtAuthEntryPoint;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthEntryPoint)
                .and()
                .cors()
                .and()
                .headers()
                .frameOptions()
                .and().and()
                .authorizeRequests()
                .antMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/superAdmin/**").hasRole("SUPERADMIN")
                .antMatchers("/api/admin/**", "/api/file/**").hasAnyRole("ADMIN","SUPERADMIN")
                // Rest api documentition ni chiqazib beradi
                .antMatchers("/swagger-ui.html", "/webjars/**", "/swagger-resources/**", "/v2/**", "/csrf").permitAll()
                //qolganlari royhatdan otiwini talab qiladi
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .disable()
                .apply(new JwtConfigurer(jwtTokenProvider))
        ;
    }

}
