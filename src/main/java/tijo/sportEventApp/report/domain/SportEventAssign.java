package tijo.sportEventApp.report.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import tijo.sportEventApp.report.dto.SportEventAssignDto;

import java.time.Instant;

@Entity
@Table(name = "sport_events_assign")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class SportEventAssign {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long sportEventId;
  Long maxParticipants;
  Instant registrationDeadline;
  Instant eventTime;

  SportEventAssignDto dto() {
    return SportEventAssignDto.builder()
        .sportEventId(sportEventId)
        .maxParticipants(maxParticipants)
        .registrationDeadline(registrationDeadline)
        .eventTime(eventTime)
        .build();
  }
}
