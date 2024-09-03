package no.larssorlie.models.dto;

import io.micronaut.serde.annotation.Serdeable;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.larssorlie.models.domain.Project;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Serdeable
public class ProjectDTO {
  private Long id;
  private String title;
  private String description;
  private Set<String> urls;
  private Set<SkillDTO> skills;
}
