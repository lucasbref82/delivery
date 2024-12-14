package br.com.delivery.configs;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    // Desabilitando para deixar todos publicos, inicialmente.
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
//                .csrf(AbstractHttpConfigurer::disable);
//
//        return http.build();
//    }

}
