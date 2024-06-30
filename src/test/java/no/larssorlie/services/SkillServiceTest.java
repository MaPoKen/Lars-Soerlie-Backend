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
import no.larssorlie.repositories.SkillRepository;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@MicronautTest
public class SkillServiceTest {
  @Inject
  SkillRepository skillRepository;

  @Inject
  SkillService skillService;

  @Test
  public void getAllSkills() throws ExecutionException, InterruptedException {
    Long id = 1L;
    Skill skill1 = new Skill(id, "hei", "as");
    Skill skill2 = new Skill(3L, "hei", "as");
    Skill skill3 = new Skill(2L, "hei", "as");

    when(this.skillRepository.findAll())
      .thenReturn(Flux.just(skill1, skill2, skill3));

    Mono<List<SkillDTO>> t = this.skillService.getAllSkills();

    assertEquals(t.toFuture().get().getFirst().getId(), id);

    verify(skillRepository).findAll();
  }

  @Test
  public void deleteSkill() {
    Long id = 1L;
    when(this.skillRepository.deleteById(id)).thenReturn(Mono.just(id));

    Mono<Long> t = this.skillService.deleteSkill(id);

    assertEquals(t.block(), id);

    verify(skillRepository).deleteById(id);
  }

  @Test
  public void getSpecificSkill()
    throws ExecutionException, InterruptedException {
    Long id = 1L;
    Skill skill1 = new Skill(id, "hei", "as");

    when(this.skillRepository.findById(id)).thenReturn(Mono.just(skill1));

    Mono<SkillDTO> t = this.skillService.getSkill(id);

    assertEquals(t.block().getId(), id);

    verify(skillRepository).findById(id);
  }

  @Test
  public void createSkill() {
    Long id = 1L;

    NewSkillDTO s = new NewSkillDTO("asd", "asda");

    when(this.skillRepository.save(s.toModel()))
      .thenReturn(Mono.just(s.toModel(id)));

    Mono<SkillDTO> t = this.skillService.createSkill(s);

    assertEquals(t.block().getId(), id);

    verify(skillRepository).save(s.toModel(id));
  }

  @Test
  public void updateSkill() {
    Long id = 1L;
    NewSkillDTO s = new NewSkillDTO("asd", "asda");
    Skill sUpdated = new Skill(id, "asda", "asda");

    when(this.skillRepository.update(s.toModel(id)))
      .thenReturn(Mono.just(sUpdated));

    Mono<SkillDTO> t = this.skillService.updateSkill(id, s);
    assertEquals(t.block().getId(), id);
    verify(skillRepository).update(s.toModel(id));
  }

  @MockBean(SkillRepository.class)
  public SkillRepository skillRepository() {
    return mock(SkillRepository.class);
  }
}
