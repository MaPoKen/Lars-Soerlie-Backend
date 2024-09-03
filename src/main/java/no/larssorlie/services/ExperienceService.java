package no.larssorlie.services;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.List;
import no.larssorlie.models.dto.ExperienceDTO;
import no.larssorlie.models.dto.NewExperienceDTO;
import no.larssorlie.models.mappers.ExperienceMapper;
import no.larssorlie.repositories.ExperienceRepository;
import reactor.core.publisher.Mono;

@Singleton
public class ExperienceService {
  @Inject
  private ExperienceRepository experienceRepository;

  public Mono<List<ExperienceDTO>> getAllExperiences() {
    return experienceRepository
      .findAll()
      .map(ExperienceMapper::toDTO)
      .collectList();
  }

  public Mono<ExperienceDTO> getExperience(Long id) {
    return experienceRepository.findById(id).map(ExperienceMapper::toDTO);
  }

  public Mono<ExperienceDTO> createExperience(NewExperienceDTO experience) {
    return experienceRepository
      .save(ExperienceMapper.toModel(experience))
      .map(ExperienceMapper::toDTO);
  }

  public Mono<Long> deleteExperience(Long id) {
    return experienceRepository.deleteById(id);
  }

  public Mono<ExperienceDTO> updateExperience(
    Long id,
    NewExperienceDTO experience
  ) {
    return experienceRepository
      .update(ExperienceMapper.toModel(experience, id))
      .map(ExperienceMapper::toDTO);
  }
}
