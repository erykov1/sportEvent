package tijo.sportEventApp.user.domain

import spock.lang.Specification
import spock.lang.Unroll
import tijo.sportEventApp.user.dto.CreateUserDto
import tijo.sportEventApp.user.dto.UserRoleDto
import tijo.sportEventApp.user.exception.InvalidFieldException
import tijo.sportEventApp.user.exception.UsernameAlreadyTakenException
import tijo.sportEventApp.user.exception.UserNotFoundException

class UserSpec extends Specification implements UserSample {
  UserFacade userFacade = new UserConfiguration().userFacade()

  def "Should create new user"() {
    when: "creates new user"
      def result = userFacade.createUser(createNewUser(username: "jane", password: "jane123"))
    then: "user is created"
      equalsUser(result, createUser(userId: result.userId, username: "jane", password: "jane123", userRole: UserRoleDto.USER))
  }

  def "Should not create user if there is already user with such username"() {
    given: "there is user 'jane'"
      userFacade.createUser(createNewUser(username: "jane", password: "jane123"))
    when: "creates user with the same username"
      userFacade.createUser(createNewUser(username: "jane", password: "jane1234"))
    then: "gets error of already taken username"
      thrown(UsernameAlreadyTakenException)
  }

  @Unroll
  def "Should not create user if #USERNAME or #PASSWORD is not passed"() {
    when: "creates new user with invalid field"
      userFacade.createUser(new CreateUserDto(USERNAME, PASSWORD))
    then: "gets error of invalid field"
      thrown(InvalidFieldException)
    where:
      USERNAME | PASSWORD
      "jane"   | null
      ""       | null
      null     | "password"
      null     | null
      null     | ""
  }

  def "Should find user by username"() {
    given: "there is user 'jane'"
      userFacade.createUser(createNewUser(username: "jane", password: "jane123"))
    when: "asks for user with username 'jane'"
      def result = userFacade.findByUsername("jane")
    then: "finds user with username 'jane'"
      equalsUser(result, createUser(userId: result.userId, username: "jane", password: "jane123", userRole: UserRoleDto.USER))
  }

  def "Should gets error if try to find user that does not exist"() {
    when: "asks for user with username 'jane'"
      userFacade.findByUsername("jane")
    then: "gets error of not found user"
      thrown(UserNotFoundException)
  }
}
