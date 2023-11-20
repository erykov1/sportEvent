package tijo.sportEventApp.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tijo.sportEventApp.user.domain.UserFacade;
import tijo.sportEventApp.user.dto.CreateUserDto;
import tijo.sportEventApp.user.dto.UserDto;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class UserController {
  UserFacade userFacade;

  @PostMapping("/register")
  ResponseEntity<UserDto> registerUser(@RequestBody CreateUserDto createUser) {
    return ResponseEntity.ok(userFacade.createUser(createUser));
  }

  @GetMapping("/{username}")
  ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
    return ResponseEntity.ok(userFacade.findByUsername(username));
  }
}
