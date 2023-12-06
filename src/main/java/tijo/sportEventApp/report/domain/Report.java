package tijo.sportEventApp.report.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import tijo.sportEventApp.report.dto.ReportDto;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "reports")
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
class Report {
  @Id
  UUID reportId;
  String username;
  @Enumerated(EnumType.STRING)
  ReportStatus reportStatus;
  Instant reportedAt;
  Long sportEventId;

  ReportDto dto() {
    return ReportDto.builder()
        .reportId(reportId)
        .username(username)
        .reportStatus(reportStatus.dto())
        .reportedAt(reportedAt)
        .sportEventId(sportEventId)
        .build();
  }
}
