package com.react05.fcinema_spring.controller;

import com.nimbusds.jose.JOSEException;
import com.react05.fcinema_spring.model.request.Authentication.AuthenticationRequest;
import com.react05.fcinema_spring.model.request.Authentication.IntrospectRequest;
import com.react05.fcinema_spring.model.response.ApiResponse;
import com.react05.fcinema_spring.model.response.Authentication.AuthenticationResponse;
import com.react05.fcinema_spring.model.response.Authentication.IntrospectResponse;
import com.react05.fcinema_spring.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> authenticate(@RequestBody AuthenticationRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.authenticate(request);

        return ResponseEntity.ok(
                ApiResponse.<AuthenticationResponse>builder()
                        .result(result)
                        .build()
        );
    }

    @PostMapping("/introspect")
    public ResponseEntity<ApiResponse<IntrospectResponse>> introspect(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ResponseEntity.ok().body(
                ApiResponse.<IntrospectResponse>builder()
                        .result(result)
                        .build()
        );
    }
}
