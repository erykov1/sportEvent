package tijo.sportEventApp.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "sport_event")
@Builder()
@FieldDefaults(level = AccessLevel.PRIVATE)
class User {
  Long userId;
  String username;
  String password;
  UserRole userRole;
}
