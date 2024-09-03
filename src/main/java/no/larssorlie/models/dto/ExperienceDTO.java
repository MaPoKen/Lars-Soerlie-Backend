package no.larssorlie.models.dto;

import io.micronaut.serde.annotation.Serdeable;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Serdeable
public class ExperienceDTO {
  private Long id;
  private Set<SkillDTO> skills;
  private Set<ProjectDTO> projects;
  private String description;
  private String title;
}
