package no.larssorlie.providers;

import static io.micronaut.http.HttpStatus.BAD_REQUEST;
import static io.micronaut.http.HttpStatus.NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.endpoints.TokenRefreshRequest;
import io.micronaut.security.token.generator.RefreshTokenGenerator;
import io.micronaut.security.token.render.BearerAccessRefreshToken;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import java.util.Map;
import java.util.Optional;
import no.larssorlie.repositories.RefreshRepository;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

@MicronautTest
public class RefreshTokenProviderTest {
  @Inject
  @Client("/")
  HttpClient client;

  @Inject
  RefreshTokenGenerator refreshTokenGenerator;

  @Inject
  RefreshRepository refreshRepository;

  @Test
  void accessingSecuredURLWithoutValidRefreshToken() {
    String unsignedRefreshedToken = "foo";

    Argument<BearerAccessRefreshToken> bodyArgument = Argument.of(
      BearerAccessRefreshToken.class
    );
    Argument<Map> errorArgument = Argument.of(Map.class);

    HttpClientResponseException e = assertThrows(
      HttpClientResponseException.class,
      () -> {
        client
          .toBlocking()
          .exchange(
            HttpRequest.POST(
              "/oauth/access_token",
              new TokenRefreshRequest(
                TokenRefreshRequest.GRANT_TYPE_REFRESH_TOKEN,
                unsignedRefreshedToken
              )
            ),
            bodyArgument,
            errorArgument
          );
      }
    );
    assertEquals(BAD_REQUEST, e.getStatus());

    Optional<Map> mapOptional = e.getResponse().getBody(Map.class);
    assertTrue(mapOptional.isPresent());

    Map m = mapOptional.get();
    assertEquals("invalid_grant", m.get("error"));
    assertEquals("Refresh token is invalid", m.get("error_description"));
  }

  @Test
  void accessingSecuredURLWithoutValidRefreshTokenAuthenticatingReturnsUnauthorized() {
    when(this.refreshRepository.findByRefreshToken(any()))
      .thenReturn(Mono.empty());

    Authentication user = Authentication.build("sherlock");

    String refreshToken = this.refreshTokenGenerator.createKey(user);
    Optional<String> refreshTokenOptional =
      this.refreshTokenGenerator.generate(user, refreshToken);
    assertTrue(refreshTokenOptional.isPresent());

    String signedRefreshToken = refreshTokenOptional.get();

    Argument<BearerAccessRefreshToken> bodyArgument = Argument.of(
      BearerAccessRefreshToken.class
    );

    Argument<Map> errorArgument = Argument.of(Map.class);

    HttpRequest<?> req = HttpRequest.POST(
      "/oauth/access_token",
      new TokenRefreshRequest(
        TokenRefreshRequest.GRANT_TYPE_REFRESH_TOKEN,
        signedRefreshToken
      )
    );

    HttpClientResponseException e = assertThrows(
      HttpClientResponseException.class,
      () -> {
        client.toBlocking().exchange(req, bodyArgument, errorArgument);
      }
    );
    assertEquals(BAD_REQUEST, e.getStatus());

    Optional<Map> mapOptional = e.getResponse().getBody(Map.class);
    assertTrue(mapOptional.isPresent());

    Map m = mapOptional.get();
    assertEquals("invalid_grant", m.get("error"));
    assertEquals("refresh token not found", m.get("error_description"));
  }

  @Test
  void accessingSecuredURLWithoutAuthenticatingReturnsUnauthorized() {
    String unsignedRefreshedToken = "foo";

    Argument<BearerAccessRefreshToken> bodyArgument = Argument.of(
      BearerAccessRefreshToken.class
    );
    Argument<Map> errorArgument = Argument.of(Map.class);

    HttpClientResponseException e = assertThrows(
      HttpClientResponseException.class,
      () -> {
        client
          .toBlocking()
          .exchange(
            HttpRequest.POST(
              "/oauth/access_token",
              new TokenRefreshRequest(
                TokenRefreshRequest.GRANT_TYPE_REFRESH_TOKEN,
                unsignedRefreshedToken
              )
            ),
            bodyArgument,
            errorArgument
          );
      }
    );
    assertEquals(BAD_REQUEST, e.getStatus());

    Optional<Map> mapOptional = e.getResponse().getBody(Map.class);
    assertTrue(mapOptional.isPresent());

    Map m = mapOptional.get();
    assertEquals("invalid_grant", m.get("error"));
    assertEquals("Refresh token is invalid", m.get("error_description"));
  }

  @MockBean(RefreshRepository.class)
  public RefreshRepository refreshRepository() {
    return mock(RefreshRepository.class);
  }
}
