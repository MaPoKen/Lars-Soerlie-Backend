package no.larssorlie.repositories;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import no.larssorlie.models.domain.Experience;

@Repository
public interface ExperienceRepository
  extends ReactorCrudRepository<Experience, Long> {}
