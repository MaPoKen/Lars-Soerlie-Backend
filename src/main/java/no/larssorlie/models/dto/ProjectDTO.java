package no.larssorlie.models.dto;

import io.micronaut.core.annotation.Creator;
import io.micronaut.serde.annotation.Serdeable;
import java.util.Set;
import java.util.stream.Collectors;
import no.larssorlie.models.domain.Project;

@Serdeable
public class ProjectDTO {
  private Long id;
  private String title;
  private String description;
  private Set<String> urls;
  private Set<SkillDTO> skills;

  @Creator
  public ProjectDTO(
    Long id,
    String title,
    String description,
    Set<String> urls,
    Set<SkillDTO> skills
  ) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.urls = urls;
    this.skills = skills;
  }

  public Set<SkillDTO> getSkills() {
    return skills;
  }

  public String getDescription() {
    return description;
  }

  public String getTitle() {
    return title;
  }

  public Set<String> getUrls() {
    return urls;
  }

  public Long getId() {
    return id;
  }

  public static ProjectDTO toDTO(Project project) {
    return new ProjectDTO(
      project.getId(),
      project.getTitle(),
      project.getDescription(),
      project.getUrls(),
      project
        .getSkills()
        .stream()
        .map(SkillDTO::toDto)
        .collect(Collectors.toSet())
    );
  }

  public Project toModel() {
    return new Project(
      this.id,
      this.title,
      this.description,
      this.urls,
      this.skills.stream().map(SkillDTO::toModel).collect(Collectors.toSet())
    );
  }
}
