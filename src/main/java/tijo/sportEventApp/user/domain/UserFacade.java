package tijo.sportEventApp.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import tijo.sportEventApp.user.dto.CreateUserDto;
import tijo.sportEventApp.user.dto.UserDto;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserFacade {
  UserRepository userRepository;

  public UserDto createUser(CreateUserDto user) {
    return UserDto.builder().build();
  }

  public UserDto findByUsername(String username) {
    return UserDto.builder().build();
  }
}
