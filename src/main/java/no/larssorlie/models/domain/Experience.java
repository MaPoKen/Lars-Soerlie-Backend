package no.larssorlie.models.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Entity
@Table(name = "experiences")
public class Experience {
  @Id
  @GeneratedValue
  private Long id;

  @ManyToMany
  private Set<Skill> skills;

  @ManyToMany
  private Set<Project> projects;

  @NotNull
  private String description;

  @NotNull
  private String title;

  @Override
  public boolean equals(Object o) {
    Experience object = (Experience) o;

    if (object.getId() != null && this.id != null) {
      return object.getId().equals(this.id);
    }

    return (
      object.getTitle().equals(this.title) &&
      object.getDescription().equals(this.description) &&
      object.getSkills().containsAll(this.skills) &&
      object.getProjects().containsAll(this.projects)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.id);
  }
}
