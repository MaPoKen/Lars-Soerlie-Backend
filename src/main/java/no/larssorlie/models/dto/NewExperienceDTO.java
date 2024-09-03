package no.larssorlie.models.dto;

import io.micronaut.core.annotation.Creator;
import io.micronaut.serde.annotation.Serdeable;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.larssorlie.models.domain.Experience;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Serdeable
public class NewExperienceDTO {
  private Set<SkillDTO> skills;
  private Set<ProjectDTO> projects;
  private String description;
  private String title;
}
