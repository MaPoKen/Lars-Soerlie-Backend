package no.larssorlie.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Set;
import no.larssorlie.models.dto.*;
import no.larssorlie.services.ProjectService;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

@MicronautTest
public class ProjectControllerTest {
  @Inject
  ProjectService projectService;

  @Test
  public void getAllProjects(RequestSpecification spec) {
    var skill1 = new SkillDTO(1L, "asdfsa", "asdf");
    var skill2 = new SkillDTO(2L, "asdfsa", "asdf");
    var skill3 = new SkillDTO(3L, "asdfsa", "asdf");

    var project1 = new ProjectDTO(
      1L,
      "asdf",
      "asdfa",
      Set.of("asdf", "sert"),
      Set.of(skill1, skill2, skill3)
    );
    var project2 = new ProjectDTO(
      2L,
      "asdf",
      "asdfa",
      Set.of("asdf", "sert"),
      Set.of(skill1, skill2, skill3)
    );

    when(projectService.getAllProjects())
      .thenReturn(Mono.just(List.of(project1, project2)));

    spec.when().get("/project").then().statusCode(200).body("size()", is(2));
  }

  @Test
  public void getProject(RequestSpecification spec) {
    var skill1 = new SkillDTO(1L, "asdfsa", "asdf");
    var skill2 = new SkillDTO(2L, "asdfsa", "asdf");
    var skill3 = new SkillDTO(3L, "asdfsa", "asdf");

    var project1 = new ProjectDTO(
      1L,
      "asdf",
      "asdfa",
      Set.of("asdf", "sert"),
      Set.of(skill1, skill2, skill3)
    );

    when(projectService.getProject(1L)).thenReturn(Mono.just(project1));

    spec
      .when()
      .get("/project/{id}", 1L)
      .then()
      .statusCode(200)
      .body("title", is(project1.getTitle()))
      .body("description", is(project1.getDescription()))
      .body("id", is(1));
  }

  @Test
  public void createExperience(RequestSpecification spec) {
    var skill1 = new SkillDTO(1L, "asdfsa", "asdf");
    var skill2 = new SkillDTO(2L, "asdfsa", "asdf");
    var skill3 = new SkillDTO(3L, "asdfsa", "asdf");

    var project1 = new ProjectDTO(
      1L,
      "asdf",
      "asdfa",
      Set.of("asdf", "sert"),
      Set.of(skill1, skill2, skill3)
    );
    var newProject1 = new NewProjectDTO(
      "asdf",
      "asdfa",
      Set.of("asdf", "sert"),
      Set.of(skill1, skill2, skill3)
    );

    when(projectService.createProject(any())).thenReturn(Mono.just(project1));

    spec
      .when()
      .contentType(ContentType.JSON)
      .body(newProject1)
      .post("/project")
      .then()
      .statusCode(201)
      .body("title", is(project1.getTitle()))
      .body("description", is(project1.getDescription()))
      .body("id", is(1))
      .body("skills.size()", is(3));
  }

  @Test
  public void updateExperience(RequestSpecification spec) {
    var skill1 = new SkillDTO(1L, "asdfsa", "asdf");
    var skill2 = new SkillDTO(2L, "asdfsa", "asdf");
    var skill3 = new SkillDTO(3L, "asdfsa", "asdf");
    var project1 = new ProjectDTO(
      1L,
      "asdf",
      "asdfa",
      Set.of("asdf", "sert"),
      Set.of(skill1, skill2, skill3)
    );
    var newProject1 = new NewProjectDTO(
      "asdf",
      "asdfa",
      Set.of("asdf", "sert"),
      Set.of(skill1, skill2, skill3)
    );

    when(projectService.updateProject(any(), any()))
      .thenReturn(Mono.just(project1));

    spec
      .when()
      .contentType(ContentType.JSON)
      .body(newProject1)
      .put("/project/{id}", 1L)
      .then()
      .statusCode(200)
      .body("title", is(project1.getTitle()))
      .body("description", is(project1.getDescription()))
      .body("id", is(1))
      .body("skills.size()", is(3));
  }

  @Test
  public void deleteExperience(RequestSpecification spec) {
    when(projectService.deleteProject(1L)).thenReturn(Mono.just(1L));

    spec.when().delete("/project/{id}", 1L).then().statusCode(204);
  }

  @MockBean(ProjectService.class)
  public ProjectService projectService() {
    return mock(ProjectService.class);
  }
}
