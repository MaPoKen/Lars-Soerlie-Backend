package no.larssorlie.services;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import no.larssorlie.models.dto.NewUserDTO;
import no.larssorlie.models.dto.UserDTO;
import no.larssorlie.models.mappers.UserMapper;
import no.larssorlie.repositories.UserRepository;
import reactor.core.publisher.Mono;

@Singleton
public class AuthenticationService {
  @Inject
  private UserRepository userRepository;

  public Mono<UserDTO> createUser(NewUserDTO user) {
    return userRepository.save(UserMapper.toModel(user)).map(UserMapper::toDTO);
  }

  public Mono<UserDTO> findUserByUserNameAndPassword(
    String username,
    String password
  ) {
    return userRepository
      .findByUsernameAndPassword(username, password)
      .map(UserMapper::toDTO);
  }

  public Mono<Boolean> checkIfUserExists(String username) {
    return userRepository
      .findByUsername(username)
      .map(a -> true)
      .defaultIfEmpty(false);
  }
}
