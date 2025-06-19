package com.react05.fcinema_spring.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.react05.fcinema_spring.entity.User;
import com.react05.fcinema_spring.exception.AppException;
import com.react05.fcinema_spring.exception.ErrorCode;
import com.react05.fcinema_spring.model.request.Authentication.AuthenticationRequest;
import com.react05.fcinema_spring.model.request.Authentication.IntrospectRequest;
import com.react05.fcinema_spring.model.response.Authentication.AuthenticationResponse;
import com.react05.fcinema_spring.model.response.Authentication.IntrospectResponse;
import com.react05.fcinema_spring.repository.UserRepository;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {

  UserRepository userRepository;

  @Value("${jwt.signerKey}")
  @NonFinal
  String SIGNER_KEY;

  @Value("${jwt.valid-duration}")
  @NonFinal
  long VALID_DURATION;

  @Value("${jwt.refreshable-duration}")
  @NonFinal
  long REFRESHABLE_DURATION;

  public IntrospectResponse introspect(IntrospectRequest request) {
    String token = request.getToken();
    try {
      verifyToken(token);
    } catch (Exception e) {
      return IntrospectResponse.builder().valid(false).build();
    }
    return IntrospectResponse.builder().valid(true).build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    var user =
        userRepository
            .findByEmail(request.getEmail())
            .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

    if (!authenticated) {
      throw new AppException(ErrorCode.UNAUTHENTICATED);
    }

    var token = generateToken(user);
    return AuthenticationResponse.builder().token(token).build();
  }

  private String generateToken(User user) {
    try {
      // Create JWS Header
      JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
      // Create JWT Claims
      JWTClaimsSet jwtClaimsSet =
          new JWTClaimsSet.Builder()
              .subject(user.getEmail())
              .issuer("swpsp25.com")
              .issueTime(Date.from(Instant.now()))
              .expirationTime(Date.from(Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS)))
              .jwtID(UUID.randomUUID().toString())
              .claim("scope", buildScope(user))
              .claim("userId", user.getId())
              .build();

      // Create Payload
      Payload payload = new Payload(jwtClaimsSet.toJSONObject());

      // Create JWS Object
      JWSObject jwsObject = new JWSObject(header, payload);

      // Sign the JWS object using HMAC-SHA512
      jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));

      // Serialize to compact form
      return jwsObject.serialize();
    } catch (JOSEException ex) {
      throw new RuntimeException(ex);
    }
  }

  private String buildScope(User user) {
    StringJoiner stringJoiner = new StringJoiner(" ");
    log.info(user.getRole().name());
    stringJoiner.add("ROLE_" + (user.getRole().name()));

    return stringJoiner.toString();
  }

  private SignedJWT verifyToken(String token) throws JOSEException, ParseException {
    JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

    SignedJWT signedJWT = SignedJWT.parse(token);

    Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

    var verified = signedJWT.verify(verifier);

    if (!(verified && expiryTime.after(new Date())))
      throw new AppException(ErrorCode.UNAUTHENTICATED);

    return signedJWT;
  }
}
