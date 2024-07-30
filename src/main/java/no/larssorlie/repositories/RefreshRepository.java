package no.larssorlie.repositories;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import io.micronaut.transaction.annotation.Transactional;
import no.larssorlie.models.domain.RefreshToken;
import reactor.core.publisher.Mono;

@Repository
public interface RefreshRepository
  extends ReactorCrudRepository<RefreshToken, Long> {
  @NonNull
  @Transactional
  Mono<RefreshToken> save(
    @NonNull String username,
    @NonNull String refreshToken,
    @NonNull Boolean revoked
  );

  @NonNull
  Mono<RefreshToken> findByRefreshToken(@NonNull String refreshToken);

  @NonNull
  long updateByUsername(@NonNull String username, boolean revoked);
}
