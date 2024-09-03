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
import no.larssorlie.models.dto.ExperienceDTO;
import no.larssorlie.models.dto.NewExperienceDTO;
import no.larssorlie.models.dto.ProjectDTO;
import no.larssorlie.models.dto.SkillDTO;
import no.larssorlie.services.ExperienceService;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

@MicronautTest
public class ExperienceControllerTest {
  @Inject
  ExperienceService experienceService;

  @Test
  public void getAllExperiences(RequestSpecification spec) {
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

    var experience1 = new ExperienceDTO(
      1L,
      Set.of(skill1, skill2, skill3),
      Set.of(project1, project2),
      "asdfas",
      "asdf"
    );
    var experience2 = new ExperienceDTO(
      2L,
      Set.of(skill1, skill2, skill3),
      Set.of(project1, project2),
      "asdfas",
      "asdf"
    );

    when(experienceService.getAllExperiences())
      .thenReturn(Mono.just(List.of(experience1, experience2)));

    spec.when().get("/experience").then().statusCode(200).body("size()", is(2));
  }

  @Test
  public void getExperience(RequestSpecification spec) {
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

    var experience1 = new ExperienceDTO(
      1L,
      Set.of(skill1, skill2, skill3),
      Set.of(project1, project2),
      "asdfas",
      "asdf"
    );

    when(experienceService.getExperience(1L))
      .thenReturn(Mono.just(experience1));

    spec
      .when()
      .get("/experience/{id}", 1L)
      .then()
      .statusCode(200)
      .body("title", is(experience1.getTitle()))
      .body("description", is(experience1.getDescription()))
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
    var project2 = new ProjectDTO(
      2L,
      "asdf",
      "asdfa",
      Set.of("asdf", "sert"),
      Set.of(skill1, skill2, skill3)
    );

    var experience1 = new ExperienceDTO(
      1L,
      Set.of(skill1, skill2, skill3),
      Set.of(project1, project2),
      "asdfas",
      "asdf"
    );

    var newExperience1 = new NewExperienceDTO(
      Set.of(skill1, skill2, skill3),
      Set.of(project1, project2),
      "asdfas",
      "asdf"
    );

    when(experienceService.createExperience(any()))
      .thenReturn(Mono.just(experience1));

    spec
      .when()
      .contentType(ContentType.JSON)
      .body(newExperience1)
      .post("/experience")
      .then()
      .statusCode(201)
      .body("title", is(experience1.getTitle()))
      .body("description", is(experience1.getDescription()))
      .body("id", is(1))
      .body("skills.size()", is(3))
      .body("projects.size()", is(2));
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
    var project2 = new ProjectDTO(
      2L,
      "asdf",
      "asdfa",
      Set.of("asdf", "sert"),
      Set.of(skill1, skill2, skill3)
    );

    var experience1 = new ExperienceDTO(
      1L,
      Set.of(skill1, skill2, skill3),
      Set.of(project1, project2),
      "asdfas",
      "asdf"
    );

    var newExperience1 = new NewExperienceDTO(
      Set.of(skill1, skill2, skill3),
      Set.of(project1, project2),
      "asdfas",
      "asdf"
    );

    when(experienceService.updateExperience(any(), any()))
      .thenReturn(Mono.just(experience1));

    spec
      .when()
      .contentType(ContentType.JSON)
      .body(newExperience1)
      .put("/experience/{id}", 1L)
      .then()
      .statusCode(200)
      .body("title", is(experience1.getTitle()))
      .body("description", is(experience1.getDescription()))
      .body("id", is(1))
      .body("skills.size()", is(3))
      .body("projects.size()", is(2));
  }

  @Test
  public void deleteExperience(RequestSpecification spec) {
    when(experienceService.deleteExperience(1L)).thenReturn(Mono.just(1L));

    spec.when().delete("/experience/{id}", 1L).then().statusCode(204);
  }

  @MockBean(ExperienceService.class)
  public ExperienceService experienceService() {
    return mock(ExperienceService.class);
  }
}
