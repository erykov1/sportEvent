package tijo.sportEventApp.report.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "sport_events_assign")
@Builder()
@FieldDefaults(level = AccessLevel.PRIVATE)
class SportEventAssign {
  @Id
  Long sportEventId;
  Long maxParticipants;
  Instant registrationDeadline;
  Instant eventTime;
  @OneToMany(mappedBy = "sportEventAssign", cascade = CascadeType.ALL, orphanRemoval = true)
  Set<Report> reports;
}
