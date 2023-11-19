package tijo.sportEventApp.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserDto {
  Long userId;
  String username;
  String password;
  UserRoleDto userRole;
}
