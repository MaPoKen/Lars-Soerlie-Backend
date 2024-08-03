package no.larssorlie.models.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Introspected
@Serdeable
public class NewUserDTO {
  @NotBlank
  private String username;

  @NotBlank
  private String password;

  @NotNull
  @Builder.Default
  private String role = "VIEW";
}
