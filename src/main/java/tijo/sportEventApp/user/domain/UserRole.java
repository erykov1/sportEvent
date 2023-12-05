package tijo.sportEventApp.user.domain;

import tijo.sportEventApp.user.dto.UserRoleDto;

enum UserRole {
  ADMIN,
  USER;

  public UserRoleDto dto() {
    return UserRoleDto.valueOf(name());
  }
}
