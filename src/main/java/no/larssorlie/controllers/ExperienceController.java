package no.larssorlie.controllers;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import no.larssorlie.models.dto.ExperienceDTO;
import no.larssorlie.models.dto.NewExperienceDTO;
import no.larssorlie.services.ExperienceService;
import reactor.core.publisher.Mono;

@Validated
@Controller("/experience")
public class ExperienceController {
  @Inject
  private ExperienceService experienceService;

  @Get
  public Mono<List<ExperienceDTO>> getExperiences() {
    return experienceService.getAllExperiences();
  }

  @Get("/{id}")
  public Mono<ExperienceDTO> getExperience(@PathVariable Long id) {
    return experienceService.getExperience(id);
  }

  @Delete("/{id}")
  @Status(HttpStatus.NO_CONTENT)
  public Mono<Long> deleteExperience(@PathVariable Long id) {
    return experienceService.deleteExperience(id);
  }

  @Put("/{id}")
  public Mono<ExperienceDTO> updateExperience(
    @NotNull @PathVariable Long id,
    @NotNull @Valid @Body NewExperienceDTO experience
  ) {
    return experienceService.updateExperience(id, experience);
  }

  @Post
  @Status(HttpStatus.CREATED)
  public Mono<ExperienceDTO> createExperience(
    @NotNull @Valid @Body NewExperienceDTO experience
  ) {
    return experienceService.createExperience(experience);
  }
}
