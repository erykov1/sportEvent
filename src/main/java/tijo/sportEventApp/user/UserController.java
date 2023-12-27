package tijo.sportEventApp.user;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tijo.sportEventApp.user.domain.UserFacade;
import tijo.sportEventApp.user.dto.CreateUserDto;
import tijo.sportEventApp.user.dto.UserDto;

@RestController
@RequestMapping("/api/user")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class UserController {
  UserFacade userFacade;

  @Autowired
  UserController(UserFacade userFacade) {
    this.userFacade = userFacade;
  }

  @PostMapping("/register")
  ResponseEntity<UserDto> registerUser(@RequestBody CreateUserDto createUser) {
    return ResponseEntity.ok(userFacade.createUser(createUser));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/{username}")
  ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
    return ResponseEntity.ok(userFacade.findByUsername(username));
  }

  @GetMapping("/cleanup")
  @Hidden
  ResponseEntity<String> cleanup() {
    userFacade.cleanup();
    return ResponseEntity.ok("user cleanup");
  }
}
