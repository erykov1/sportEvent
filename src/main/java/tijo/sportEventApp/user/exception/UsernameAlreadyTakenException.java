package tijo.sportEventApp.user.exception;

public class UsernameAlreadyTakenException extends RuntimeException {
  public UsernameAlreadyTakenException(String message) {
    super(message);
  }
}
