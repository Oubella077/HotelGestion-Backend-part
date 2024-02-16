package com.example.reservation.Security;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.crypto.spec.SecretKeySpec;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
@Autowired
UserDaitailServiceImp userDaitailServiceImp;
@Value("${jwt.secret}")
    private String secret;
    @Bean
    AuthenticationManager authenticationManager(UserDetailsService userDetailsService){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
         daoAuthenticationProvider.setUserDetailsService(userDetailsService);
         daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
         return new ProviderManager(daoAuthenticationProvider);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity ,
                                                   HandlerMappingIntrospector introspector)
            throws Exception {
        MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(introspector);
        httpSecurity.sessionManagement(se->se.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
     httpSecurity.authorizeHttpRequests(re->re
             .requestMatchers(mvc.pattern("/**"),
                     mvc.pattern("/login/**")).permitAll()
             .requestMatchers(toH2Console()).permitAll()
             .anyRequest().authenticated());
     httpSecurity.csrf(c->c.disable());
     httpSecurity.userDetailsService(userDaitailServiceImp);
     httpSecurity.headers(head->head.frameOptions(f->f.disable()));
     httpSecurity.cors(c->c.configurationSource(corsConfigurationSource()));
     httpSecurity.oauth2ResourceServer(ou->ou.jwt(Customizer.withDefaults()));
     return httpSecurity.build();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    JwtEncoder jwtEncoder(){
        return new NimbusJwtEncoder(new ImmutableSecret<>(this.secret.getBytes()));
    }
    @Bean
    JwtDecoder jwtDecoder(){
        SecretKeySpec secretKeySpec=new SecretKeySpec(this.secret.getBytes(),"RSA");
return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512).build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addAllowedOrigin("http://localhost:4200");
        // config.setExposedHeaders((List.of("x-auth-token")));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;}

}


