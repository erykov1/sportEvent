package tijo.sportEventApp.integration

import tijo.sportEventApp.report.domain.ReportSample
import tijo.sportEventApp.report.dto.CreateReportDto
import tijo.sportEventApp.report.dto.ReportStatusDto

class ReportAcceptanceSpec extends IntegrationSpec implements ReportSample {
  def "Should create new report"() {
    when: "creates new report for sport event"
      def result = api.report().createReport(new CreateReportDto("janedoe", HANDBALL_EVENT))
    then: "report is created"
    equalsReport(result, createReport(reportId: result.reportId, username: "janedoe", reportStatus: ReportStatusDto.PENDING,
        reportedAt: result.reportedAt, eventId: HANDBALL_EVENT
    ))
  }

  def "Should change status from pending to accepted if report is accepted"() {
    given: "there is new report for sport event"
      def report = api.report().createReport(new CreateReportDto("janedoe", HANDBALL_EVENT))
    when: "accept report"
      def result = api.report().acceptReport(report.reportId)
    then: "report is accepted"
      result.reportId == report.reportId
      result.status == ReportStatusDto.ACCEPTED
  }

  def "Should change status from pending to declined if report is declined"() {
    given: "there is new report for sport event"
      def report = api.report().createReport(new CreateReportDto("janedoe", HANDBALL_EVENT))
    when: "accept report"
      def result = api.report().declineReport(report.reportId)
    then: "report is accepted"
      result.reportId == report.reportId
      result.status == ReportStatusDto.DECLINED
  }

  def "Should delete report if it has pending status"() {
    given: "there is new report for sport event with pending status"
      def report = api.report().createReport(new CreateReportDto("janedoe", HANDBALL_EVENT))
    when: "deletes report"
      api.report().deleteReport(report.reportId)
    then: "report is deleted"
      !api.report().findReports().contains(report)
  }

  def cleanup() {
    api.sportEvent().cleanup()
    api.report().cleanup()
    api.user().cleanup()
  }
}
