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
import no.larssorlie.models.domain.Experience;
import no.larssorlie.models.domain.Project;
import no.larssorlie.models.domain.Skill;
import no.larssorlie.models.dto.ExperienceDTO;
import no.larssorlie.models.dto.NewExperienceDTO;
import no.larssorlie.models.dto.ProjectDTO;
import no.larssorlie.models.dto.SkillDTO;
import no.larssorlie.repositories.ExperienceRepository;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@MicronautTest
public class ExperienceServiceTest {
  @Inject
  ExperienceRepository experienceRepository;

  @Inject
  ExperienceService experienceService;

  @Test
  public void getAllExperience()
    throws ExecutionException, InterruptedException {
    Long id = Long.getLong("1");
    Set<Skill> skills = Set.of(
      new Skill(4L, "heia", "as"),
      new Skill(3L, "heib", "as"),
      new Skill(2L, "heic", "as"),
      new Skill(1L, "heis", "as")
    );
    Set<Project> experiences = Set.of(
      new Project(
        id,
        "as",
        "asd",
        Set.of("asd", "asda", "asdas"),
        Set.of(new Skill(4L, "heia", "as"), new Skill(2L, "hei", "as"))
      )
    );

    when(this.experienceRepository.findAll())
      .thenReturn(
        Flux.just(
          new Experience(id, "halla", "hei", skills, experiences),
          new Experience(1L, "halla", "hei", skills, experiences)
        )
      );

    Mono<List<ExperienceDTO>> t = this.experienceService.getAllExperiences();

    assertEquals(t.toFuture().get().getFirst().getId(), id);

    verify(experienceRepository).findAll();
  }

  @Test
  public void deleteExperience() {
    Long id = 1L;
    when(this.experienceRepository.deleteById(id)).thenReturn(Mono.just(id));

    Mono<Long> t = this.experienceService.deleteExperience(id);

    assertEquals(t.block(), id);

    verify(experienceRepository).deleteById(id);
  }

  @Test
  public void getSpecificExperience()
    throws ExecutionException, InterruptedException {
    Long id = 1L;
    Set<Skill> skills = Set.of(
      new Skill(1L, "hei", "as"),
      new Skill(2L, "hei", "as"),
      new Skill(3L, "hei", "as"),
      new Skill(4L, "hei", "as")
    );
    Set<Project> experiences = Set.of(
      new Project(
        1L,
        "as",
        "asd",
        Set.of("asd", "asda", "asdas"),
        Set.of(new Skill(4L, "hei", "as"), new Skill(3L, "hei", "as"))
      )
    );

    when(this.experienceRepository.findById(id))
      .thenReturn(
        Mono.just(new Experience(id, "halla", "hei", skills, experiences))
      );

    Mono<ExperienceDTO> t = this.experienceService.getExperience(id);

    assertEquals(t.toFuture().get().getId(), id);

    verify(experienceRepository).findById(id);
  }

  @Test
  public void createExperience() {
    Long id = 1L;

    Set<Skill> skills = Set.of(
      new Skill(1L, "hei", "as"),
      new Skill(2L, "hei", "as"),
      new Skill(3L, "hei", "as"),
      new Skill(4L, "hei", "as")
    );

    Set<Project> projects = Set.of(
      new Project(
        1L,
        "as",
        "asd",
        Set.of("asd", "asda", "asdas"),
        Set.of(new Skill(4L, "hei", "as"), new Skill(3L, "hei", "as"))
      )
    );

    NewExperienceDTO exp = new NewExperienceDTO(
      "asdj",
      "asda",
      skills.stream().map(SkillDTO::toDto).collect(Collectors.toSet()),
      projects.stream().map(ProjectDTO::toDTO).collect(Collectors.toSet())
    );

    Experience done = new Experience(id, "asdj", "asda", skills, projects);

    when(this.experienceRepository.save(exp.toModel()))
      .thenReturn(Mono.just(done));

    Mono<ExperienceDTO> t = this.experienceService.createExperience(exp);

    assertEquals(t.block().getId(), id);

    verify(experienceRepository).save(exp.toModel());
  }

  @Test
  public void updateExperience() {
    Long id = 1L;

    Set<Skill> skills = Set.of(
      new Skill(1L, "hei", "as"),
      new Skill(2L, "hei", "as"),
      new Skill(3L, "hei", "as"),
      new Skill(4L, "hei", "as")
    );

    Set<Project> projects = Set.of(
      new Project(
        1L,
        "as",
        "asd",
        Set.of("asd", "asda", "asdas"),
        Set.of(new Skill(4L, "hei", "as"), new Skill(3L, "hei", "as"))
      )
    );

    NewExperienceDTO exp = new NewExperienceDTO(
      "asdj",
      "asda",
      skills.stream().map(SkillDTO::toDto).collect(Collectors.toSet()),
      projects.stream().map(ProjectDTO::toDTO).collect(Collectors.toSet())
    );

    when(this.experienceRepository.update(exp.toModel(id)))
      .thenReturn(
        Mono.just(new Experience(id, "halla", "hei", skills, projects))
      );

    Mono<ExperienceDTO> t = this.experienceService.updateExperience(id, exp);

    assertEquals(t.block().getId(), id);

    verify(experienceRepository).update(exp.toModel(id));
  }

  @MockBean(ExperienceRepository.class)
  public ExperienceRepository experienceRepository() {
    return mock(ExperienceRepository.class);
  }
}
