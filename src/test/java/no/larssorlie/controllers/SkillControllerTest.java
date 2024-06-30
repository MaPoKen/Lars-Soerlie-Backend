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
import no.larssorlie.services.ExperienceService;
import no.larssorlie.services.SkillService;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

@MicronautTest
public class SkillControllerTest {
  @Inject
  SkillService skillService;

  @Test
  public void getAllSkills(RequestSpecification spec) {
    var skill1 = new SkillDTO(1L, "asdfsa", "asdf");
    var skill2 = new SkillDTO(2L, "asdfsa", "asdf");
    var skill3 = new SkillDTO(3L, "asdfsa", "asdf");

    when(skillService.getAllSkills())
      .thenReturn(Mono.just(List.of(skill1, skill2, skill3)));

    spec.when().get("/skill").then().statusCode(200).body("size()", is(3));
  }

  @Test
  public void getSkill(RequestSpecification spec) {
    var skill1 = new SkillDTO(1L, "asdfsa", "asdf");

    when(skillService.getSkill(1L)).thenReturn(Mono.just(skill1));

    spec
      .when()
      .get("/skill/{id}", 1L)
      .then()
      .statusCode(200)
      .body("name", is(skill1.getName()))
      .body("iconUrl", is(skill1.getIconUrl()))
      .body("id", is(1));
  }

  @Test
  public void createSkill(RequestSpecification spec) {
    var skill1 = new SkillDTO(1L, "asdfsa", "asdf");
    var newSkill1 = new NewSkillDTO("asdfsa", "asdf");

    when(skillService.createSkill(any())).thenReturn(Mono.just(skill1));

    spec
      .when()
      .contentType(ContentType.JSON)
      .body(newSkill1)
      .post("/skill")
      .then()
      .statusCode(201)
      .body("name", is(skill1.getName()))
      .body("iconUrl", is(skill1.getIconUrl()))
      .body("id", is(1));
  }

  @Test
  public void updateSkill(RequestSpecification spec) {
    var skill1 = new SkillDTO(1L, "asdfsa", "asdf");
    var newSkill1 = new NewSkillDTO("asdfsa", "asdf");

    when(skillService.updateSkill(any(), any())).thenReturn(Mono.just(skill1));

    spec
      .when()
      .contentType(ContentType.JSON)
      .body(newSkill1)
      .put("/skill/{id}", 1L)
      .then()
      .statusCode(200)
      .body("name", is(skill1.getName()))
      .body("iconUrl", is(skill1.getIconUrl()))
      .body("id", is(1));
  }

  @Test
  public void deleteSkill(RequestSpecification spec) {
    when(skillService.deleteSkill(1L)).thenReturn(Mono.just(1L));

    spec.when().delete("/skill/{id}", 1L).then().statusCode(204);
  }

  @MockBean(SkillService.class)
  public SkillService skillService() {
    return mock(SkillService.class);
  }
}
