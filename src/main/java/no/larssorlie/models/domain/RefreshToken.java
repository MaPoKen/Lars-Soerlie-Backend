package no.larssorlie.models.domain;

import io.micronaut.data.annotation.DateCreated;
import jakarta.persistence.*;
import java.time.Instant;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  private String username;

  @NonNull
  private String refreshToken;

  @NonNull
  private Boolean revoked;

  @NonNull
  @DateCreated
  private Instant dateCreated;
}
