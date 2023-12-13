package tijo.sportEventApp.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import tijo.sportEventApp.user.dto.UserDto;

@Entity
@Table(name = "users")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long userId;
  String username;
  String password;
  UserRole userRole;

  UserDto dto() {
    return UserDto.builder()
        .userId(userId)
        .username(username)
        .password(password)
        .userRole(userRole.dto())
        .build();
  }
}
