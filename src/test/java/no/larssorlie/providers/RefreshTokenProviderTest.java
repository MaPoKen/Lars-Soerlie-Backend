package no.larssorlie.providers;

import static io.micronaut.http.HttpStatus.BAD_REQUEST;
import static org.junit.jupiter.api.Assertions.*;

import io.micronaut.context.ApplicationContext;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.endpoints.TokenRefreshRequest;
import io.micronaut.security.token.generator.RefreshTokenGenerator;
import io.micronaut.security.token.render.BearerAccessRefreshToken;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import no.larssorlie.repositories.RefreshRepository;
import org.junit.jupiter.api.Test;

/* TODO: Mock repository to make tests work */
@MicronautTest
public class RefreshTokenProviderTest {
  /**

    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer.class, Collections.emptyMap());
    HttpClient client = embeddedServer.getApplicationContext().createBean(HttpClient.class, embeddedServer.getURL());
    RefreshTokenGenerator refreshTokenGenerator = embeddedServer.getApplicationContext().getBean(RefreshTokenGenerator.class);
    RefreshRepository refreshTokenRepository = embeddedServer.getApplicationContext().getBean(RefreshRepository.class);


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
  void accessingSecuredURLWithoutAuthenticatingReturnsUnAuthorized() {
    Authentication user = Authentication.build("sherlock");
    String refreshToken = refreshTokenGenerator.createKey(user);
    Optional<String> refreshTokenOptional = refreshTokenGenerator.generate(
      user,
      refreshToken
    );
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
        Authentication user = Authentication.build("sherlock");

        String refreshToken = refreshTokenGenerator.createKey(user);
        Optional<String> refreshTokenOptional = refreshTokenGenerator.generate(user, refreshToken);
        assertTrue(refreshTokenOptional.isPresent());

        long oldTokenCount = refreshTokenRepository.count().block();
        String signedRefreshToken = refreshTokenOptional.get();
        refreshTokenRepository.save(user.getName(), refreshToken, true);
        assertEquals(oldTokenCount + 1, refreshTokenRepository.count().block());

        Argument<BearerAccessRefreshToken> bodyArgument = Argument.of(BearerAccessRefreshToken.class);
        Argument<Map> errorArgument = Argument.of(Map.class);
        HttpClientResponseException e = assertThrows(HttpClientResponseException.class, () -> {
            client.toBlocking().exchange(
                    HttpRequest.POST("/oauth/access_token", new TokenRefreshRequest(TokenRefreshRequest.GRANT_TYPE_REFRESH_TOKEN, signedRefreshToken)),
                    bodyArgument,
                    errorArgument);
        });
        assertEquals(BAD_REQUEST, e.getStatus());

        Optional<Map> mapOptional = e.getResponse().getBody(Map.class);
        assertTrue(mapOptional.isPresent());

        Map m = mapOptional.get();
        assertEquals("invalid_grant", m.get("error"));
        assertEquals("refresh token revoked", m.get("error_description"));

        refreshTokenRepository.deleteAll();
    } */
}
