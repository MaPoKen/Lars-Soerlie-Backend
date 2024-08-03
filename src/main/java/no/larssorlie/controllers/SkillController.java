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
import no.larssorlie.models.dto.NewSkillDTO;
import no.larssorlie.models.dto.SkillDTO;
import no.larssorlie.services.SkillService;
import reactor.core.publisher.Mono;

@Controller("/skill")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class SkillController {
  @Inject
  private SkillService skillService;

  @Get
  @PermitAll
  public Mono<List<SkillDTO>> getSkills() {
    return skillService.getAllSkills();
  }

  @Get("/{id}")
  @PermitAll
  public Mono<SkillDTO> getSkill(@PathVariable Long id) {
    return skillService.getSkill(id);
  }

  @Delete("/{id}")
  @Status(HttpStatus.NO_CONTENT)
  @Secured("VIEW")
  public Mono<Long> deleteSkill(@PathVariable Long id) {
    return skillService.deleteSkill(id);
  }

  @Put("/{id}")
  @Secured("VIEW")
  public Mono<SkillDTO> updateProject(
    @NotNull @PathVariable Long id,
    @NotNull @Valid @Body NewSkillDTO skill
  ) {
    return skillService.updateSkill(id, skill);
  }

  @Post
  @Status(HttpStatus.CREATED)
  @Secured("VIEW")
  public Mono<SkillDTO> createSkill(@NotNull @Valid @Body NewSkillDTO skill) {
    return skillService.createSkill(skill);
  }
}
