package no.larssorlie.services;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.List;
import no.larssorlie.models.dto.ExperienceDTO;
import no.larssorlie.models.dto.NewExperienceDTO;
import no.larssorlie.repositories.ExperienceRepository;
import reactor.core.publisher.Mono;

@Singleton
public class ExperienceService {
  @Inject
  private ExperienceRepository experienceRepository;

  public Mono<List<ExperienceDTO>> getAllExperiences() {
    return experienceRepository
      .findAll()
      .map(ExperienceDTO::toDTO)
      .collectList();
  }

  public Mono<ExperienceDTO> getExperience(Long id) {
    return experienceRepository.findById(id).map(ExperienceDTO::toDTO);
  }

  public Mono<ExperienceDTO> createExperience(NewExperienceDTO experience) {
    return experienceRepository
      .save(experience.toModel())
      .map(ExperienceDTO::toDTO);
  }

  public Mono<Long> deleteExperience(Long id) {
    return experienceRepository.deleteById(id);
  }

  public Mono<ExperienceDTO> updateExperience(
    Long id,
    NewExperienceDTO experience
  ) {
    return experienceRepository
      .update(experience.toModel(id))
      .map(ExperienceDTO::toDTO);
  }
}
