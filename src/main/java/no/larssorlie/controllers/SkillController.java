package no.larssorlie.controllers;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import no.larssorlie.models.dto.NewSkillDTO;
import no.larssorlie.models.dto.SkillDTO;
import no.larssorlie.services.SkillService;
import reactor.core.publisher.Mono;

@Validated
@Controller("/skill")
public class SkillController {
  @Inject
  private SkillService skillService;

  @Get
  public Mono<List<SkillDTO>> getSkills() {
    return skillService.getAllSkills();
  }

  @Get("/{id}")
  public Mono<SkillDTO> getSkill(@PathVariable Long id) {
    return skillService.getSkill(id);
  }

  @Delete("/{id}")
  @Status(HttpStatus.NO_CONTENT)
  public Mono<Long> deleteSkill(@PathVariable Long id) {
    return skillService.deleteSkill(id);
  }

  @Put("/{id}")
  public Mono<SkillDTO> updateProject(
    @NotNull @PathVariable Long id,
    @NotNull @Valid @Body NewSkillDTO skill
  ) {
    return skillService.updateSkill(id, skill);
  }

  @Post
  @Status(HttpStatus.CREATED)
  public Mono<SkillDTO> createSkill(@NotNull @Valid @Body NewSkillDTO skill) {
    return skillService.createSkill(skill);
  }
}
