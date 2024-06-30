package no.larssorlie.repositories;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import jakarta.inject.Singleton;
import no.larssorlie.models.domain.Skill;

@Repository
public interface SkillRepository extends ReactorCrudRepository<Skill, Long> {}
