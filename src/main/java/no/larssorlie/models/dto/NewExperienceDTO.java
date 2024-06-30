package no.larssorlie.models.dto;

import io.micronaut.core.annotation.Creator;
import io.micronaut.serde.annotation.Serdeable;
import java.util.Set;
import java.util.stream.Collectors;
import no.larssorlie.models.domain.Experience;

@Serdeable
public class NewExperienceDTO {
  private Set<SkillDTO> skills;
  private Set<ProjectDTO> projects;
  private String description;
  private String title;

  @Creator
  public NewExperienceDTO(
    String title,
    String description,
    Set<SkillDTO> skills,
    Set<ProjectDTO> projects
  ) {
    this.title = title;
    this.description = description;
    this.skills = skills;
    this.projects = projects;
  }

  public Experience toModel() {
    return new Experience(
      this.title,
      this.description,
      this.skills.stream().map(SkillDTO::toModel).collect(Collectors.toSet()),
      this.projects.stream()
        .map(ProjectDTO::toModel)
        .collect(Collectors.toSet())
    );
  }

  public Experience toModel(Long id) {
    return new Experience(
      id,
      this.title,
      this.description,
      this.skills.stream().map(SkillDTO::toModel).collect(Collectors.toSet()),
      this.projects.stream()
        .map(ProjectDTO::toModel)
        .collect(Collectors.toSet())
    );
  }
}
