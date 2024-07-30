package no.larssorlie.providers;

import static io.micronaut.security.authentication.AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.provider.HttpRequestReactiveAuthenticationProvider;
import jakarta.inject.Inject;
import java.util.Collections;
import no.larssorlie.services.AuthenticationService;
import reactor.core.publisher.Mono;

public class BasicAuthenticationProvider<B>
  implements HttpRequestReactiveAuthenticationProvider<B> {
  @Inject
  private AuthenticationService authenticationService;

  @Override
  public @NonNull Mono<AuthenticationResponse> authenticate(
    @Nullable HttpRequest<B> requestContext,
    @NonNull AuthenticationRequest<String, String> authRequest
  ) {
    final String username = authRequest.getIdentity();
    final String password = authRequest.getSecret();

    return authenticationService
      .findUserByUserNameAndPassword(username, password)
      .map(
        a ->
          AuthenticationResponse.success(
            a.getUsername(),
            Collections.singletonList(a.getRole())
          )
      )
      .defaultIfEmpty(
        () ->
          new AuthenticationFailed(CREDENTIALS_DO_NOT_MATCH).getAuthentication()
      );
  }
}
