package no.larssorlie.models.dto;

import io.micronaut.core.annotation.Creator;
import io.micronaut.serde.annotation.Serdeable;
import java.util.Set;
import java.util.stream.Collectors;
import no.larssorlie.models.domain.Experience;
import no.larssorlie.models.domain.Project;
import no.larssorlie.models.domain.Skill;

@Serdeable
public class ExperienceDTO {
  private Long id;
  private Set<SkillDTO> skills;
  private Set<ProjectDTO> projects;
  private String description;
  private String title;

  @Creator
  public ExperienceDTO(
    Long id,
    String title,
    String description,
    Set<SkillDTO> skills,
    Set<ProjectDTO> projects
  ) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.skills = skills;
    this.projects = projects;
  }

  public String getDescription() {
    return description;
  }

  public Set<SkillDTO> getSkills() {
    return skills;
  }

  public Set<ProjectDTO> getProjects() {
    return projects;
  }

  public String getTitle() {
    return title;
  }

  public Long getId() {
    return id;
  }

  public Experience toModel() {
    return new Experience(
      this.id,
      this.title,
      this.description,
      this.skills.stream().map(SkillDTO::toModel).collect(Collectors.toSet()),
      this.projects.stream()
        .map(ProjectDTO::toModel)
        .collect(Collectors.toSet())
    );
  }

  public static ExperienceDTO toDTO(Experience experience) {
    return new ExperienceDTO(
      experience.getId(),
      experience.getTitle(),
      experience.getDescription(),
      experience
        .getSkills()
        .stream()
        .map(SkillDTO::toDto)
        .collect(Collectors.toSet()),
      experience
        .getProjects()
        .stream()
        .map(ProjectDTO::toDTO)
        .collect(Collectors.toSet())
    );
  }
}
