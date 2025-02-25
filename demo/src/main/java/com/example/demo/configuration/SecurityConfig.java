package com.example.demo.configuration;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
@EnableMethodSecurity
@Configuration
    @EnableWebSecurity
    public class SecurityConfig {
        private final String[] POST_PUBLIC_ENDPOINT = {"/add","/auth/login","/auth/introspect","/address"};
    private final String[] PUBLIC = {"/address/get_address_ui","university/getUniversities"};



        @Value("${jwt.SECRET_KEY}")
        String SECRET_KEY;
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.authorizeHttpRequests(request ->
                    request.requestMatchers(HttpMethod.POST,POST_PUBLIC_ENDPOINT).permitAll()
                            .requestMatchers(HttpMethod.GET,PUBLIC).permitAll()
                            .anyRequest().authenticated()

            );
            http.csrf(AbstractHttpConfigurer::disable);
            http.httpBasic(AbstractHttpConfigurer::disable);
            http.oauth2ResourceServer(httpOauth2 ->
                    httpOauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(decoderFactory())
                            .jwtAuthenticationConverter(jwtAuthenticationConverter()))
                            .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
            );

            return http.build();
        }

        @Bean
        JwtAuthenticationConverter jwtAuthenticationConverter(){
            JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
            jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
            JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
            jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
            return jwtAuthenticationConverter;
        }

        public JwtDecoder decoderFactory(){
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "HS512");
            return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS512).build();
        };

        @Bean
        public PasswordEncoder passwordEncoder (){
            return  new BCryptPasswordEncoder(10);
        }
    }



