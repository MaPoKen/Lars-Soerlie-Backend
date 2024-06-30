package no.larssorlie.models.dto;

import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import no.larssorlie.models.domain.Skill;

@Serdeable
@Introspected
public class NewSkillDTO {
  private String name;
  private String iconUrl;

  @Creator
  public NewSkillDTO(String name, String iconUrl) {
    this.name = name;
    this.iconUrl = iconUrl;
  }

  public Skill toModel() {
    return new Skill(this.name, this.iconUrl);
  }

  public Skill toModel(Long id) {
    return new Skill(id, this.name, this.iconUrl);
  }
}
