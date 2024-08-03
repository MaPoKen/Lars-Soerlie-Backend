package no.larssorlie.repositories;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import io.micronaut.transaction.annotation.Transactional;
import java.util.Optional;
import no.larssorlie.models.domain.RefreshToken;
import reactor.core.publisher.Mono;

@Repository
public interface RefreshRepository
  extends ReactorCrudRepository<RefreshToken, Long> {
  @Transactional
  Mono<RefreshToken> save(
    @NonNull String username,
    @NonNull String refreshToken,
    @NonNull Boolean revoked
  );

  Mono<RefreshToken> findByRefreshToken(@NonNull String refreshToken);

  long updateByUsername(@NonNull String username, boolean revoked);
}
