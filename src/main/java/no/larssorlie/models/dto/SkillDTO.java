package no.larssorlie.models.dto;

import io.micronaut.core.annotation.Creator;
import io.micronaut.serde.annotation.Serdeable;
import no.larssorlie.models.domain.Skill;

@Serdeable
public class SkillDTO {
  private Long id;
  private String name;
  private String iconUrl;

  @Creator
  public SkillDTO(Long id, String name, String iconUrl) {
    this.id = id;
    this.name = name;
    this.iconUrl = iconUrl;
  }

  public String getIconUrl() {
    return iconUrl;
  }

  public String getName() {
    return name;
  }

  public Long getId() {
    return id;
  }

  public static SkillDTO toDto(Skill skill) {
    return new SkillDTO(skill.getId(), skill.getIconUrl(), skill.getName());
  }

  public Skill toModel() {
    return new Skill(this.id, this.name, this.iconUrl);
  }
}
