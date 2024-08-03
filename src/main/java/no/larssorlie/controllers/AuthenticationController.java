package no.larssorlie.controllers;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.token.render.BearerAccessRefreshToken;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import no.larssorlie.models.dto.NewUserDTO;
import no.larssorlie.models.dto.UserDTO;
import no.larssorlie.providers.BasicAuthenticationProvider;
import no.larssorlie.services.AuthenticationService;
import reactor.core.publisher.Mono;

@Controller
@Validated
public class AuthenticationController {
  @Inject
  private AuthenticationService authenticationService;

  @Secured(SecurityRule.IS_ANONYMOUS)
  @Post("/register")
  @Status(HttpStatus.CREATED)
  public Mono<UserDTO> registerUser(@Body @NotNull NewUserDTO user) {
    return authenticationService
      .checkIfUserExists(user.getUsername())
      .flatMap(
        userExist ->
          userExist ? Mono.empty() : authenticationService.createUser(user)
      );
  }
}
