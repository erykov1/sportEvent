package tijo.sportEventApp.user.domain

import tijo.sportEventApp.integration.IntegrationSpec
import tijo.sportEventApp.user.dto.CreateUserDto
import tijo.sportEventApp.user.dto.UserRoleDto

class UserAcceptanceSpec extends IntegrationSpec implements UserSample {
  def "Should create new user"() {
    when: "creates new user"
      def result = api.user().registerUser(new CreateUserDto("jane", "doe"))
    then: "user is created"
      equalsUser(result, createUser(userId: result.userId, username: "jane", password: "jane123", userRole: UserRoleDto.USER))
  }

  def "Should get user by username"() {
    given: "there is user 'jane'"
      api.user().registerUser(new CreateUserDto("jane", "doe"))
    when: "ask for user 'jane'"
      def result = api.user().getUserByUsername("jane")
    then: "finds user by username"
      equalsUser(result, createUser(userId: result.userId, username: "jane", password: "jane123", userRole: UserRoleDto.USER))
  }

  def cleanup() {
    api.report().cleanup()
    api.sportEvent().cleanup()
    api.user().cleanup()
  }
}
