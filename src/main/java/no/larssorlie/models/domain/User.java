package no.larssorlie.models.domain;

import io.micronaut.core.annotation.NonNull;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  private String username;

  @NonNull
  private String password;

  @NonNull
  @Builder.Default
  private String role = "VIEW";
}
