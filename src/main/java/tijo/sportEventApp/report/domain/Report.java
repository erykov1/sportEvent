package tijo.sportEventApp.report.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "reports")
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
class Report {
  @Id
  UUID reportId;
  String name;
  String surname;
  @Enumerated(EnumType.STRING)
  ReportStatus reportStatus;
  Instant reportedAt;
  Long eventId;
  @ManyToOne
  @JoinColumn(name = "sportEventId")
  SportEventAssign sportEventAssign;



}
