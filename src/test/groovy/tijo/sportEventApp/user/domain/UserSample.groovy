package tijo.sportEventApp.user.domain

import tijo.sportEventApp.user.dto.CreateUserDto
import tijo.sportEventApp.user.dto.UserDto
import tijo.sportEventApp.user.dto.UserRoleDto

trait UserSample {
  private Map<String, Object> DEFAULT_USER_DATA = [
      userId: 1L,
      username: '',
      password: '',
      userRole: ''
  ] as Map<String, Object>

  UserDto createUser(Map<String, Object> changes = [:]) {
    def changesWithDefaults = DEFAULT_USER_DATA + changes
    UserDto.builder()
        .userId(changesWithDefaults.userId as Long)
        .username(changesWithDefaults.username as String)
        .password(changesWithDefaults.password as String)
        .userRole(changesWithDefaults.userRole as UserRoleDto)
        .build()
  }

  CreateUserDto createNewUser(Map<String, Object> changes = [:]) {
    def changesWithDefaults = DEFAULT_USER_DATA + changes
    CreateUserDto.builder()
        .username(changesWithDefaults.username as String)
        .password(changesWithDefaults.password as String)
        .build()
  }

  void equalsUser(UserDto result, UserDto expected) {
    assert result.userId == expected.userId
    assert result.username == expected.username
    assert result.password == expected.password
    assert result.userRole == expected.userRole
  }
}