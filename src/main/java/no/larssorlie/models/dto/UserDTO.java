package no.larssorlie.models.dto;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Serdeable
public class UserDTO {
  @NotBlank
  private Long id;

  @NotBlank
  private String username;

  private String role;
}
