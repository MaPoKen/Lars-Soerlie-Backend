package no.larssorlie.models.mappers;

import no.larssorlie.models.domain.User;
import no.larssorlie.models.dto.NewUserDTO;
import no.larssorlie.models.dto.UserDTO;

public class UserMapper {

  public static UserDTO toDTO(User user) {
    return new UserDTO()
      .toBuilder()
      .id(user.getId())
      .role(user.getRole())
      .username(user.getUsername())
      .build();
  }

  public static User toModel(UserDTO user) {
    return new User()
      .toBuilder()
      .id(user.getId())
      .role(user.getRole())
      .username(user.getUsername())
      .build();
  }

  public static User toModel(NewUserDTO user) {
    return new User()
      .toBuilder()
      .role(user.getRole())
      .password(user.getPassword())
      .username(user.getUsername())
      .build();
  }
}
