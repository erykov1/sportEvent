package tijo.sportEventApp.report.domain

import tijo.sportEventApp.report.dto.ReportDto
import tijo.sportEventApp.report.dto.ReportStatusDto
import tijo.sportEventApp.utils.InstantProvider

import java.time.Instant

trait ReportSample {
  static final Long HANDBALL_EVENT = 1L
  static final Long FOOTBALL_EVENT = 2L
  static final Long FULL_PARTICIPANTS_EVENT = 3L

  private Map<String, Object> DEFAULT_REPORT_DATA= [
      reportId: "",
      name: "",
      surname: "",
      email: "",
      reportStatus: ReportStatusDto.PENDING,
      reportedAt: InstantProvider.fromFormatted("2023-11-14 14:00"),
      eventId: HANDBALL_EVENT
  ] as Map<String, Object>

  ReportDto createReport(Map<String, Object> changes = [:]) {
    def changesWithDefaults = DEFAULT_REPORT_DATA + changes
    ReportDto.builder()
        .reportId(changesWithDefaults.reportId as UUID)
        .name(changesWithDefaults.name as String)
        .surname(changesWithDefaults.surname as String)
        .reportStatus(changesWithDefaults.reportStatus as ReportStatusDto)
        .reportedAt(changesWithDefaults.reportedAt as Instant)
        .eventId(changesWithDefaults.eventId as Long)
        .build()
  }

  void equalsReport(ReportDto result, ReportDto expected) {
    assert result.reportId == expected.reportId
    assert result.name == expected.name
    assert result.surname == expected.surname
    assert result.reportStatus == expected.reportStatus
    assert result.reportedAt == expected.reportedAt
    assert result.eventId == expected.eventId
  }

  void equalsReports(List<ReportDto> result, List<ReportDto> expected) {
    def comparator = Comparator.comparing(ReportDto::getReportedAt)
    result.sort(comparator)
    assert result.reportId == expected.reportId
    assert result.name == expected.name
    assert result.surname == expected.surname
    assert result.reportStatus == expected.reportStatus
    assert result.reportedAt == expected.reportedAt
    assert result.eventId == expected.eventId
  }
}