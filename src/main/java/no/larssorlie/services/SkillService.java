package no.larssorlie.services;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.List;
import no.larssorlie.models.dto.NewSkillDTO;
import no.larssorlie.models.dto.SkillDTO;
import no.larssorlie.models.mappers.SkillMapper;
import no.larssorlie.repositories.SkillRepository;
import reactor.core.publisher.Mono;

@Singleton
public class SkillService {
  @Inject
  private SkillRepository skillRepository;

  public Mono<List<SkillDTO>> getAllSkills() {
    return skillRepository.findAll().map(SkillMapper::toDTO).collectList();
  }

  public Mono<SkillDTO> getSkill(Long id) {
    return skillRepository.findById(id).map(SkillMapper::toDTO);
  }

  public Mono<SkillDTO> createSkill(NewSkillDTO newSkill) {
    return skillRepository
      .save(SkillMapper.toModel(newSkill))
      .map(SkillMapper::toDTO);
  }

  public Mono<Long> deleteSkill(Long id) {
    return skillRepository.deleteById(id);
  }

  public Mono<SkillDTO> updateSkill(Long id, NewSkillDTO skill) {
    return skillRepository
      .update(SkillMapper.toModel(skill, id))
      .map(SkillMapper::toDTO);
  }
}
