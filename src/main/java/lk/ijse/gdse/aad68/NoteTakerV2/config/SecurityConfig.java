package lk.ijse.gdse.aad68.NoteTakerV2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionCreationEvent;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(req ->
                        req.requestMatchers("api/v1/auth/**").permitAll()
                                .anyRequest()
                                .authenticated()
                ).sessionManagement(SessionCreationPolicy.STATELESS)
                .authenticationProvider()
                .addFilterBefore();
        return httpSecurity.build();
    }
}
