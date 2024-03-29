package tijo.sportEventApp.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tijo.sportEventApp.user.dto.CreateUserDto;
import tijo.sportEventApp.user.dto.UserDto;
import tijo.sportEventApp.user.exception.InvalidFieldException;
import tijo.sportEventApp.user.exception.UserNotFoundException;
import tijo.sportEventApp.user.exception.UsernameAlreadyTakenException;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserFacade {
  UserRepository userRepository;
  BCryptPasswordEncoder bCryptPasswordEncoder;

  public UserDto createUser(CreateUserDto user) {
    checkIfUserIsAlreadyCreated(user.getUsername());
    checkIfFieldsAreValid(user);
    User saveUser = User.builder()
        .username(user.getUsername())
        .password(bCryptPasswordEncoder.encode(user.getPassword()))
        .userRole(UserRole.USER)
        .build();
    return userRepository.save(saveUser).dto();
  }

  public UserDto createAdmin(CreateUserDto user) {
    checkIfUserIsAlreadyCreated(user.getUsername());
    checkIfFieldsAreValid(user);
    User saveUser = User.builder()
        .username(user.getUsername())
        .password(bCryptPasswordEncoder.encode(user.getPassword()))
        .userRole(UserRole.ADMIN)
        .build();
    return userRepository.save(saveUser).dto();
  }

  public UserDto findByUsername(String username) {
    return userRepository.findByUsername(username)
        .map(User::dto)
        .orElseThrow(() -> new UserNotFoundException("User with given username not found"));
  }

  public void cleanup() {
    userRepository.deleteAll();
  }

  private void checkIfUserIsAlreadyCreated(String username) {
    if (userRepository.findByUsername(username).isPresent()) {
      throw new UsernameAlreadyTakenException("There is already created user with such username");
    }
  }

  private void checkIfFieldsAreValid(CreateUserDto createUser) {
    if(createUser.getUsername() == null || createUser.getPassword() == null) {
      throw new InvalidFieldException("some of the field is not valid");
    }
  }
}
