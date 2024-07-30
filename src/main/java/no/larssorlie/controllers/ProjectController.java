package no.larssorlie.controllers;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.validation.Validated;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import no.larssorlie.models.dto.NewProjectDTO;
import no.larssorlie.models.dto.ProjectDTO;
import no.larssorlie.services.ProjectService;
import reactor.core.publisher.Mono;

@Controller("/project")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class ProjectController {
  @Inject
  private ProjectService projectService;

  @Get
  @PermitAll
  public Mono<List<ProjectDTO>> getProjects() {
    return projectService.getAllProjects();
  }

  @Get("/{id}")
  @PermitAll
  public Mono<ProjectDTO> getProjects(@PathVariable Long id) {
    return projectService.getProject(id);
  }

  @Delete("/{id}")
  @Status(HttpStatus.NO_CONTENT)
  @Secured("VIEW")
  public Mono<Long> deleteProject(@PathVariable Long id) {
    return projectService.deleteProject(id);
  }

  @Put("/{id}")
  @Secured("VIEW")
  public Mono<ProjectDTO> updateProject(
    @NotNull @PathVariable Long id,
    @NotNull @Valid @Body NewProjectDTO project
  ) {
    return projectService.updateProject(id, project);
  }

  @Post
  @Status(HttpStatus.CREATED)
  @Secured("VIEW")
  public Mono<ProjectDTO> createProject(
    @NotNull @Valid @Body NewProjectDTO project
  ) {
    return projectService.createProject(project);
  }
}
