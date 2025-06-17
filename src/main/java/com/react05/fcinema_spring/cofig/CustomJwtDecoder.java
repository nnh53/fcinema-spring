package com.react05.fcinema_spring.cofig;

import com.react05.fcinema_spring.model.request.Authentication.IntrospectRequest;
import com.react05.fcinema_spring.service.AuthenticationService;
import com.react05.fcinema_spring.service.AuthenticationServiceImpl;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.util.Objects;
//@Component
// implements JwtDecoder
public class CustomJwtDecoder {
//    @Value("${jwt.signerKey}")
//    private String signerKey;
//
//    @Autowired
//    private AuthenticationServiceImpl authenticationServiceImpl;
//    private NimbusJwtDecoder nimbusJwtDecoder = null;
//    @Override
//    public Jwt decode(String token) throws JwtException {
//        try{
//
//            var response =  authenticationServiceImpl.introspect(IntrospectRequest.builder()
//                    .token(token)
//                    .build());
//
//            if(!response.isValid()){
//                throw new JwtException("Token invalid");
//            }
//        }catch (JOSEException | ParseException e){
//            throw new JwtException(e.getMessage());
//        }
//
//        if(Objects.isNull(nimbusJwtDecoder)){
//            SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(),"HS512");
//            nimbusJwtDecoder = NimbusJwtDecoder
//                    .withSecretKey(secretKeySpec)
//                    .macAlgorithm(MacAlgorithm.HS512)
//                    .build();
//        }
//
//        return nimbusJwtDecoder.decode(token);
//    }
}
