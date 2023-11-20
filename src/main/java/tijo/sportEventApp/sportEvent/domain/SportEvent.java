package tijo.sportEventApp.sportEvent.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@Table(name = "sport_events")
@Builder()
@FieldDefaults(level = AccessLevel.PRIVATE)
class SportEvent {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long sportEventId;
  String eventName;
  Instant eventTime;
  Instant registrationDeadline;
  String description;
  Long maxParticipants;
  @Enumerated(EnumType.STRING)
  SportEventType sportEventType;
  Long sportEventAddress;
}
