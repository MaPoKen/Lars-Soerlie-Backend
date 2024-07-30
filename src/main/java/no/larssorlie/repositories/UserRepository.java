package no.larssorlie.repositories;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import no.larssorlie.models.domain.User;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactorCrudRepository<User, Long> {
  @NonNull
  Mono<User> findByUsernameAndPassword(String username, String password);

  @NonNull
  Mono<User> findByUsername(String username);
}
