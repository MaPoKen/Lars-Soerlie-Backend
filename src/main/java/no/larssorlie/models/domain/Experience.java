package no.larssorlie.models.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

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

  public Experience(
    Long id,
    String title,
    String description,
    Set<Skill> skills,
    Set<Project> projects
  ) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.skills = skills;
    this.projects = projects;
  }

  public Experience(
    String title,
    String description,
    Set<Skill> skills,
    Set<Project> projects
  ) {
    this.title = title;
    this.description = description;
    this.skills = skills;
    this.projects = projects;
  }

  public String getDescription() {
    return description;
  }

  public Set<Skill> getSkills() {
    return skills;
  }

  public Set<Project> getProjects() {
    return projects;
  }

  public String getTitle() {
    return title;
  }

  public Long getId() {
    return id;
  }

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
