package no.larssorlie.models.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Entity
@Table(name = "projects")
public class Project {
  @Id
  @GeneratedValue
  private Long id;

  @NotNull
  private String title;

  @NotNull
  private String description;

  @NotNull
  private Set<String> urls;

  @ManyToMany
  private Set<Skill> skills;

  @Override
  public boolean equals(Object o) {
    Project object = (Project) o;
    if (object.getId() != null && this.id != null) {
      return object.getId().equals(this.id);
    }

    return (
      object.getTitle().equals(this.title) &&
      object.getDescription().equals(this.description) &&
      object.getSkills().containsAll(this.skills) &&
      object.getUrls().containsAll(this.urls)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.id);
  }
}
