package no.larssorlie.models.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Entity
@Table(name = "skills")
public class Skill {
  @Id
  @GeneratedValue
  private Long id;

  @NotNull
  private String name;

  @NotNull
  private String iconUrl;

  @Override
  public boolean equals(Object o) {
    Skill object = (Skill) o;
    if (object.getId() != null && this.id != null) {
      return object.getId().equals(this.id);
    }

    return (
      object.getIconUrl().equals(this.iconUrl) &&
      object.getName().equals(this.name)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.id);
  }
}
