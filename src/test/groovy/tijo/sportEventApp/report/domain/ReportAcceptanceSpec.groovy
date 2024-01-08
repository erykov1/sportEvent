package tijo.sportEventApp.report.domain

import tijo.sportEventApp.integration.IntegrationSpec
import tijo.sportEventApp.report.dto.CreateReportDto
import tijo.sportEventApp.report.dto.ReportStatusDto
import tijo.sportEventApp.sportEvent.domain.SportEventSample
import tijo.sportEventApp.sportEvent.dto.SportEventTypeDto

class ReportAcceptanceSpec extends IntegrationSpec implements ReportSample, SportEventSample {

  def "Should create new report"() {
    given: "there is sport event"
      def handballEdition = api.sportEvent().createSportEvent(createNewSportEvent(eventName: "event",
          eventTime: "2024-08-08 12:00", registrationDeadline: "2024-07-08 12:00",
          description: "event handball desc", eventAddress: 1L, maxParticipants: 200L, sportEventType: SportEventTypeDto.HANDBALL))
    when: "creates new report for sport event"
      def result = api.report().createReport(new CreateReportDto("janedoe", handballEdition.sportEventId))
    then: "report is created"
    equalsReport(result, createReport(reportId: result.reportId, username: "janedoe", reportStatus: ReportStatusDto.PENDING,
        reportedAt: result.reportedAt, eventId: handballEdition.sportEventId
    ))
  }

  def "Should change status from pending to accepted if report is accepted"() {
    given: "there is sport event"
    def handballEdition = api.sportEvent().createSportEvent(createNewSportEvent(eventName: "event",
        eventTime: "2024-08-08 12:00", registrationDeadline: "2024-07-08 12:00",
        description: "event handball desc", eventAddress: 1L, maxParticipants: 200L, sportEventType: SportEventTypeDto.HANDBALL))
    and: "there is new report for sport event"
      def report = api.report().createReport(new CreateReportDto("janedoe", handballEdition.sportEventId))
    when: "accept report"
      def result = api.report().acceptReport(report.reportId)
    then: "report is accepted"
      result.reportId == report.reportId
      result.status == ReportStatusDto.ACCEPTED
  }

  def "Should change status from pending to declined if report is declined"() {
    given: "there is sport event"
      def handballEdition = api.sportEvent().createSportEvent(createNewSportEvent(eventName: "event",
          eventTime: "2024-08-08 12:00", registrationDeadline: "2024-07-08 12:00",
          description: "event handball desc", eventAddress: 1L, maxParticipants: 200L, sportEventType: SportEventTypeDto.HANDBALL))
    and: "there is new report for sport event"
      def report = api.report().createReport(new CreateReportDto("janedoe", handballEdition.sportEventId))
    when: "decline report"
      def result = api.report().declineReport(report.reportId)
    then: "report is declined"
      result.reportId == report.reportId
      result.status == ReportStatusDto.DECLINED
  }

  def "Should delete report if it has pending status"() {
    given: "there is sport event"
    def handballEdition = api.sportEvent().createSportEvent(createNewSportEvent(eventName: "event",
        eventTime: "2024-08-08 12:00", registrationDeadline: "2024-07-08 12:00",
        description: "event handball desc", eventAddress: 1L, maxParticipants: 200L, sportEventType: SportEventTypeDto.HANDBALL))
    and: "there is new report for sport event with pending status"
      def report = api.report().createReport(new CreateReportDto("janedoe", handballEdition.sportEventId))
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
