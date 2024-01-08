package tijo.sportEventApp.report.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.UUID;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReportDto {
  UUID reportId;
  String username;
  ReportStatusDto reportStatus;
  Instant reportedAt;
  Long sportEventId;
}
