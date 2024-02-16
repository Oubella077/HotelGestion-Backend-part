package com.example.reservation.Web;

import com.example.reservation.Security.Entity.AppUser;
import com.example.reservation.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class ClientApi {
@Autowired
    AccountService accountService;
@Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtEncoder jwtEncoder;
    @PostMapping("/login")
    public Map<String,String> login(String fullname, String password){
        AppUser appUser=accountService.loadUserByUsername(fullname);
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(fullname,password));
        Instant instant=Instant.now();
        String scope=authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
        JwtClaimsSet jwtClaimsSet= JwtClaimsSet.builder()
                .issuedAt(instant)
                .expiresAt(instant.plus(5, ChronoUnit.MINUTES))
                .subject(fullname)
                .claim("roles",scope)
                .claim("userId",appUser.getIduser())
                .build();
 JwtEncoderParameters jwtEncoderParameters=JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(),jwtClaimsSet);
String jwt=jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
return Map.of("access-token",jwt);
    }

}
