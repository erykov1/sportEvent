package tijo.sportEventApp.report.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SportEventAssignDto {
  Long sportEventId;
  Long maxParticipants;
  Instant registrationDeadline;
  Instant eventTime;
}
