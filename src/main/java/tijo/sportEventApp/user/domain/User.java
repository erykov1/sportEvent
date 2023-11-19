package tijo.sportEventApp.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "users")
@Builder()
@FieldDefaults(level = AccessLevel.PRIVATE)
class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long userId;
  String username;
  String password;
  UserRole userRole;
}
