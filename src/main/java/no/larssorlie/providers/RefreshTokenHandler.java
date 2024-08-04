package no.larssorlie.providers;

import static io.micronaut.security.errors.IssuingAnAccessTokenErrorCode.*;

import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.errors.OauthErrorResponseException;
import io.micronaut.security.token.event.RefreshTokenGeneratedEvent;
import io.micronaut.security.token.refresh.RefreshTokenPersistence;
import jakarta.inject.Inject;
import no.larssorlie.repositories.RefreshRepository;
import reactor.core.publisher.Mono;

public class RefreshTokenHandler implements RefreshTokenPersistence {
  @Inject
  private RefreshRepository refreshRepository;

  @Override
  public void persistToken(RefreshTokenGeneratedEvent event) {
    if (
      event != null &&
      event.getRefreshToken() != null &&
      event.getAuthentication() != null &&
      event.getAuthentication().getName() != null
    ) {
      String payload = event.getRefreshToken();
      refreshRepository.save(
        event.getAuthentication().getName(),
        payload,
        false
      );
    }
  }

  @Override
  public Mono<Authentication> getAuthentication(String refreshToken) {
    return refreshRepository
      .findByRefreshToken(refreshToken)
      .map(
        token -> {
          if (token.getRevoked()) {
            throw new OauthErrorResponseException(
              INVALID_GRANT,
              "refresh token revoked",
              null
            );
          } else {
            return Authentication.build(token.getUsername());
          }
        }
      )
      .switchIfEmpty(
        Mono.defer(
          () ->
            Mono.error(
              new OauthErrorResponseException(
                INVALID_GRANT,
                "refresh token not found",
                null
              )
            )
        )
      );
  }
}
