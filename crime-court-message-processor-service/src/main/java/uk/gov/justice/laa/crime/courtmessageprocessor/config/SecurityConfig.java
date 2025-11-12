package uk.gov.justice.laa.crime.courtmessageprocessor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;


@Configuration(proxyBeanMethods = false)
public class SecurityConfig {
    @Bean
    public WebSecurityCustomizer ignoringCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/actuator/prometheus");
    }

}