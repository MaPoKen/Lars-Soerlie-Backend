package no.larssorlie.models.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.larssorlie.models.domain.Skill;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Serdeable
public class SkillDTO {
  private Long id;
  private String name;
  private String iconUrl;
}
