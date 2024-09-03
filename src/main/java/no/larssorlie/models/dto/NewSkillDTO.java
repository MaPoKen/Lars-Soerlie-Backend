package no.larssorlie.models.dto;

import io.micronaut.core.annotation.Introspected;
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
@Introspected
public class NewSkillDTO {
  private String name;
  private String iconUrl;
}
