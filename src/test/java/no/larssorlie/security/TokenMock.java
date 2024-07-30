package no.larssorlie.security;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.event.ApplicationEventPublisher;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.server.util.HttpHostResolver;
import io.micronaut.http.server.util.locale.HttpLocaleResolver;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.event.TokenValidatedEvent;
import io.micronaut.security.token.TokenAuthenticationFetcher;
import io.micronaut.security.token.reader.TokenResolver;
import io.micronaut.security.token.validator.TokenValidator;
import jakarta.inject.Singleton;
import java.util.List;
import reactor.core.publisher.Mono;

@Replaces(TokenAuthenticationFetcher.class)
@Singleton
public class TokenMock extends TokenAuthenticationFetcher {

  public TokenMock(
    List<TokenValidator<HttpRequest<?>>> tokenValidators,
    TokenResolver<HttpRequest<?>> tokenResolver,
    ApplicationEventPublisher<TokenValidatedEvent> tokenValidatedEventPublisher,
    HttpHostResolver httpHostResolver,
    HttpLocaleResolver httpLocaleResolver
  ) {
    super(
      tokenValidators,
      tokenResolver,
      tokenValidatedEventPublisher,
      httpHostResolver,
      httpLocaleResolver
    );
  }

  @Override
  public Mono<Authentication> fetchAuthentication(HttpRequest<?> request) {
    return Mono.just(Authentication.build("test", List.of("VIEW")));
  }
}
