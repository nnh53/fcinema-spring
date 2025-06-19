package com.react05.fcinema_spring.service;

import com.react05.fcinema_spring.model.request.Authentication.AuthenticationRequest;
import com.react05.fcinema_spring.model.request.Authentication.IntrospectRequest;
import com.react05.fcinema_spring.model.response.Authentication.AuthenticationResponse;
import com.react05.fcinema_spring.model.response.Authentication.IntrospectResponse;

public interface AuthenticationService {
  public IntrospectResponse introspect(IntrospectRequest request);

  public AuthenticationResponse authenticate(AuthenticationRequest request);
}
