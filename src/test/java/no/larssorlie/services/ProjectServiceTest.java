package no.larssorlie.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import no.larssorlie.models.domain.Project;
import no.larssorlie.models.domain.Skill;
import no.larssorlie.models.dto.*;
import no.larssorlie.repositories.ProjectRepository;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@MicronautTest
public class ProjectServiceTest {
  @Inject
  ProjectRepository projectRepository;

  @Inject
  ProjectService projectService;

  @Test
  public void getAllProjects() throws ExecutionException, InterruptedException {
    Long id = 1L;
    Skill skill1 = new Skill(4L, "hei", "as");
    Skill skill2 = new Skill(3L, "hei", "as");
    Skill skill3 = new Skill(2L, "hei", "as");

    Set<Skill> skills = Set.of(skill1, skill2, skill3);

    Project project1 = new Project(
      id,
      "as",
      "asd",
      Set.of("asd", "asda", "asdas"),
      skills
    );
    Project project2 = new Project(
      id,
      "as",
      "asd",
      Set.of("asd", "asda", "asdas"),
      skills
    );

    when(this.projectRepository.findAll())
      .thenReturn(Flux.just(project1, project2));

    Mono<List<ProjectDTO>> t = this.projectService.getAllProjects();

    assertEquals(t.toFuture().get().get(0).getId(), id);

    verify(projectRepository).findAll();
  }

  @Test
  public void deleteExperience() {
    Long id = 1L;
    when(this.projectRepository.deleteById(id)).thenReturn(Mono.just(id));

    Mono<Long> t = this.projectService.deleteProject(id);

    assertEquals(t.block(), id);

    verify(projectRepository).deleteById(id);
  }

  @Test
  public void getSpecificProject()
    throws ExecutionException, InterruptedException {
    Long id = 1L;
    Skill skill1 = new Skill(4L, "hei", "as");
    Skill skill2 = new Skill(3L, "hei", "as");
    Skill skill3 = new Skill(2L, "hei", "as");

    Set<Skill> skills = Set.of(skill1, skill2, skill3);

    Project project1 = new Project(
      id,
      "as",
      "asd",
      Set.of("asd", "asda", "asdas"),
      skills
    );

    when(this.projectRepository.findById(id)).thenReturn(Mono.just(project1));

    Mono<ProjectDTO> t = this.projectService.getProject(id);

    assertEquals(t.toFuture().get().getId(), id);

    verify(projectRepository).findById(id);
  }

  @Test
  public void createProject() {
    Long id = 1L;
    Set<Skill> skills = Set.of(
      new Skill(1L, "heia", "asa"),
      new Skill(2L, "heib", "asb"),
      new Skill(3L, "heic", "asc"),
      new Skill(4L, "heid", "asd")
    );

    NewProjectDTO p = new NewProjectDTO(
      "asa",
      "asa",
      Set.of("asdas"),
      skills.stream().map(SkillDTO::toDto).collect(Collectors.toSet())
    );

    when(this.projectRepository.save(p.toModel()))
      .thenReturn(Mono.just(p.toModel(id)));

    Mono<ProjectDTO> t = this.projectService.createProject(p);

    assertEquals(t.block().getId(), id);

    verify(projectRepository).save(p.toModel(id));
  }

  @Test
  public void updateProject() {
    Long id = 1L;
    Set<Skill> skills = Set.of(
      new Skill(1L, "hei", "as"),
      new Skill(2L, "hei", "as"),
      new Skill(3L, "hei", "as"),
      new Skill(4L, "hei", "as")
    );

    NewProjectDTO p = new NewProjectDTO(
      "as",
      "asd",
      Set.of("asd", "asda", "asdas"),
      skills.stream().map(SkillDTO::toDto).collect(Collectors.toSet())
    );

    Project pUpdated = new Project(
      id,
      "as",
      "asd",
      Set.of("asd", "asda", "asdas"),
      skills
    );

    when(this.projectRepository.update(p.toModel(id)))
      .thenReturn(Mono.just(pUpdated));

    Mono<ProjectDTO> t = this.projectService.updateProject(id, p);
    assertEquals(t.block().getId(), id);
    verify(projectRepository).update(p.toModel(id));
  }

  @MockBean(ProjectRepository.class)
  public ProjectRepository projectRepository() {
    return mock(ProjectRepository.class);
  }
}
