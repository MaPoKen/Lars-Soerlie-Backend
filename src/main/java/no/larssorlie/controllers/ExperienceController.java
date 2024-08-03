package no.larssorlie.controllers;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import no.larssorlie.models.dto.ExperienceDTO;
import no.larssorlie.models.dto.NewExperienceDTO;
import no.larssorlie.services.ExperienceService;
import reactor.core.publisher.Mono;

@Controller("/experience")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class ExperienceController {
  @Inject
  private ExperienceService experienceService;

  @Get
  @PermitAll
  public Mono<List<ExperienceDTO>> getExperiences() {
    return experienceService.getAllExperiences();
  }

  @Get("/{id}")
  @PermitAll
  public Mono<ExperienceDTO> getExperience(@PathVariable Long id) {
    return experienceService.getExperience(id);
  }

  @Delete("/{id}")
  @Status(HttpStatus.NO_CONTENT)
  @Secured("VIEW")
  public Mono<Long> deleteExperience(@PathVariable Long id) {
    return experienceService.deleteExperience(id);
  }

  @Put("/{id}")
  @Secured("VIEW")
  public Mono<ExperienceDTO> updateExperience(
    @NotNull @PathVariable Long id,
    @NotNull @Valid @Body NewExperienceDTO experience
  ) {
    return experienceService.updateExperience(id, experience);
  }

  @Post
  @Status(HttpStatus.CREATED)
  @Secured("VIEW")
  public Mono<ExperienceDTO> createExperience(
    @NotNull @Valid @Body NewExperienceDTO experience
  ) {
    return experienceService.createExperience(experience);
  }
}
