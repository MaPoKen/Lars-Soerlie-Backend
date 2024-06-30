package no.larssorlie.models.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

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

  public Project(
    Long id,
    String title,
    String description,
    Set<String> urls,
    Set<Skill> skills
  ) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.urls = urls;
    this.skills = skills;
  }

  public Project(
    String title,
    String description,
    Set<String> urls,
    Set<Skill> skills
  ) {
    this.title = title;
    this.description = description;
    this.urls = urls;
    this.skills = skills;
  }

  public Set<Skill> getSkills() {
    return skills;
  }

  public String getDescription() {
    return description;
  }

  public String getTitle() {
    return title;
  }

  public Set<String> getUrls() {
    return urls;
  }

  public Long getId() {
    return id;
  }

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
