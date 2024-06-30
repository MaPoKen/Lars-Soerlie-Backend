package no.larssorlie.models.dto;

import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import java.util.Set;
import java.util.stream.Collectors;
import no.larssorlie.models.domain.Project;

@Serdeable
@Introspected
public class NewProjectDTO {
  private String title;
  private String description;
  private Set<String> urls;
  private Set<SkillDTO> skills;

  @Creator
  public NewProjectDTO(
    String title,
    String description,
    Set<String> urls,
    Set<SkillDTO> skills
  ) {
    this.title = title;
    this.description = description;
    this.urls = urls;
    this.skills = skills;
  }

  public Project toModel() {
    return new Project(
      this.title,
      this.description,
      this.urls,
      this.skills.stream().map(SkillDTO::toModel).collect(Collectors.toSet())
    );
  }

  public Project toModel(Long id) {
    return new Project(
      id,
      this.title,
      this.description,
      this.urls,
      this.skills.stream().map(SkillDTO::toModel).collect(Collectors.toSet())
    );
  }
}
