package no.larssorlie.services;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.List;
import no.larssorlie.models.dto.NewSkillDTO;
import no.larssorlie.models.dto.SkillDTO;
import no.larssorlie.repositories.SkillRepository;
import reactor.core.publisher.Mono;

@Singleton
public class SkillService {
  @Inject
  private SkillRepository skillRepository;

  public Mono<List<SkillDTO>> getAllSkills() {
    return skillRepository.findAll().map(SkillDTO::toDto).collectList();
  }

  public Mono<SkillDTO> getSkill(Long id) {
    return skillRepository.findById(id).map(SkillDTO::toDto);
  }

  public Mono<SkillDTO> createSkill(NewSkillDTO newSkill) {
    return skillRepository.save(newSkill.toModel()).map(SkillDTO::toDto);
  }

  public Mono<Long> deleteSkill(Long id) {
    return skillRepository.deleteById(id);
  }

  public Mono<SkillDTO> updateSkill(Long id, NewSkillDTO skill) {
    return skillRepository.update(skill.toModel(id)).map(SkillDTO::toDto);
  }
}
