package tijo.sportEventApp.report.domain

import tijo.sportEventApp.report.dto.ReportDto
import tijo.sportEventApp.report.dto.ReportStatusDto
import tijo.sportEventApp.report.dto.SportEventAssignDto
import tijo.sportEventApp.utils.InstantProvider

import java.time.Instant

trait ReportSample {
  static final Long HANDBALL_EVENT = 1L
  static final Long FOOTBALL_EVENT = 2L
  static final Long FULL_PARTICIPANTS_EVENT = 3L

  private Map<String, Object> DEFAULT_REPORT_DATA = [
      reportId: "",
      username: "",
      reportStatus: ReportStatusDto.PENDING,
      reportedAt: InstantProvider.fromFormatted("2023-11-14 14:00"),
      eventId: HANDBALL_EVENT
  ] as Map<String, Object>

  ReportDto createReport(Map<String, Object> changes = [:]) {
    def changesWithDefaults = DEFAULT_REPORT_DATA + changes
    ReportDto.builder()
        .reportId(changesWithDefaults.reportId as UUID)
        .username(changesWithDefaults.username as String)
        .reportStatus(changesWithDefaults.reportStatus as ReportStatusDto)
        .reportedAt(changesWithDefaults.reportedAt as Instant)
        .sportEventId(changesWithDefaults.eventId as Long)
        .build()
  }

  private Map<String, Object> DEFAULT_SPORT_EVENT_ASSIGN = [
      sportEventId: HANDBALL_EVENT,
      maxParticipants: 200L,
      registrationDeadLine: InstantProvider.fromFormatted("2023-11-14 14:00"),
      eventTime: InstantProvider.fromFormatted("2023-11-16 14:00")
  ] as Map<String, Object>

  SportEventAssignDto createSportEventAssign(Map<String, Object> changes = [:]) {
    def changesWithDefaults = DEFAULT_SPORT_EVENT_ASSIGN + changes
    SportEventAssignDto.builder()
      .sportEventId(changesWithDefaults.sportEventId as Long)
      .maxParticipants(changesWithDefaults.maxParticipants as Long)
      .registrationDeadline(changesWithDefaults.registrationDeadline as Instant)
      .eventTime(changesWithDefaults.eventTime as Instant)
      .build()
  }

  void equalsReport(ReportDto result, ReportDto expected) {
    assert result.reportId == expected.reportId
    assert result.username == expected.username
    assert result.reportStatus == expected.reportStatus
    assert result.reportedAt == expected.reportedAt
    assert result.sportEventId == expected.sportEventId
  }

  void equalsReports(List<ReportDto> result, List<ReportDto> expected) {
    def comparator = Comparator.comparing(ReportDto::getUsername)
    result.sort(comparator)
    assert result.reportId == expected.reportId
    assert result.username == expected.username
    assert result.reportStatus == expected.reportStatus
    assert result.reportedAt == expected.reportedAt
    assert result.sportEventId == expected.sportEventId
  }
}