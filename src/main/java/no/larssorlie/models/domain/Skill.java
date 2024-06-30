package no.larssorlie.models.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

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

  public String getIconUrl() {
    return iconUrl;
  }

  public String getName() {
    return name;
  }

  public Long getId() {
    return id;
  }

  public Skill(Long id, String name, String iconUrl) {
    this.id = id;
    this.name = name;
    this.iconUrl = iconUrl;
  }

  public Skill(String name, String iconUrl) {
    this.name = name;
    this.iconUrl = iconUrl;
  }

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
