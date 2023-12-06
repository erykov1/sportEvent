package tijo.sportEventApp.report.domain

import spock.lang.Specification
import tijo.sportEventApp.report.dto.CreateReportDto
import tijo.sportEventApp.report.dto.ReportStatusDto
import tijo.sportEventApp.report.exception.AlreadyAssignedException
import tijo.sportEventApp.sportEvent.exception.FullParticipantsException
import tijo.sportEventApp.sportEvent.exception.NotExistingSportEventException

class ReportSpec extends Specification implements ReportSample {
  ReportFacade reportFacade = new ReportConfiguration().reportFacade()

  def "Should create report"() {
    when: "creates report"
      def result = reportFacade.createReport(new CreateReportDto("janedoe", HANDBALL_EVENT))
    then: "report is created"
      equalsReport(result, createReport(reportId: result.reportId, username: "janedoe", reportStatus: ReportStatusDto.PENDING,
        reportedAt: result.reportedAt, eventId: HANDBALL_EVENT
      ))
  }

  def "Should not create report if try to report to not existing sport event"() {
    when: "creates report to not existing sport event"
      reportFacade.createReport(new CreateReportDto("janedoe", new Random().nextLong()))
    then: "gets error of not existing sport event"
      thrown(NotExistingSportEventException)
  }

  def "Should not create report to sport event if there is already full participants"() {
    when: "creates report to sport event that has already full participants"
      reportFacade.createReport(new CreateReportDto("janedoe", new Random().nextLong()))
    then: "gets error of full filled participants in given sport event"
      thrown(FullParticipantsException)
  }

  def "Should create report for the same user if try to assign to two sport events that are in different time"() {
    given: "user assign to first event"
      def handballEvent = reportFacade.createReport(new CreateReportDto("janedoe", HANDBALL_EVENT))
    when: "user assigns to other event"
      def footballEvent = reportFacade.createReport(new CreateReportDto("janedoe", FOOTBALL_EVENT))
    then: "user is assigned to two sport events"
      equalsReports(reportFacade.getAllUserReports("jane123"), [createReport(reportId: handballEvent.reportId, username: "janedoe", reportStatus: ReportStatusDto.PENDING,
          reportedAt: footballEvent.reportedAt, eventId: HANDBALL_EVENT
      ), createReport(reportId: handballEvent.reportId, username: "janedoe", reportStatus: ReportStatusDto.PENDING,
          reportedAt: footballEvent.reportedAt, eventId: FOOTBALL_EVENT)])
  }

  def "Should not create report for the sport event if user is already reported to other sport event"() {
    given: "user report for the first event"
      def report = reportFacade.createReport(new CreateReportDto("janedoe", HANDBALL_EVENT))
    and: "gets accepted"
      reportFacade.acceptReport(report.reportId)
    when: "user reports for other sport event that is in the same time"
      reportFacade.createReport(new CreateReportDto("janedoe", HANDBALL_EVENT))
    then: "gets error of already assigned user to sport event"
      thrown(AlreadyAssignedException)
  }

  def "Should change status from pending to accepted"() {
    given: "user report for the sport event"
      def handballEvent = reportFacade.createReport(new CreateReportDto("janedoe", HANDBALL_EVENT))
    when: "gets accepted"
      def result = reportFacade.acceptReport(handballEvent.reportId)
    then: "status of his report is accepted"
      result.reportId == handballEvent.reportId
      result.status == ReportStatusDto.ACCEPTED
  }

  def "Should change status from pending to declined"() {
    given: "user report for the sport event"
      def handballEvent = reportFacade.createReport(new CreateReportDto("janedoe", HANDBALL_EVENT))
    when: "gets accepted"
      def result = reportFacade.declineReport(handballEvent.reportId)
    then: "status of his report is declined"
      result.reportId == handballEvent.reportId
      result.status == ReportStatusDto.DECLINED
  }

  def "Should get all reports with pending status"() {
    given: "there are some reports with pending status"
      def handballEvent = reportFacade.createReport(new CreateReportDto("janedoe", HANDBALL_EVENT))
      def footballEvent = reportFacade.createReport(new CreateReportDto("mikejones", FOOTBALL_EVENT))
    when: "asks for all pending status"
      def result = reportFacade.getAllReportsByStatus(ReportStatusDto.PENDING)
    then: "gets all pending status"
    equalsReports(result, [createReport(reportId: handballEvent.reportId, username: "janedoe", reportStatus: ReportStatusDto.PENDING,
        reportedAt: footballEvent.reportedAt, eventId: HANDBALL_EVENT
    ), createReport(reportId: handballEvent.reportId, username: "mikejones", reportStatus: ReportStatusDto.PENDING,
        reportedAt: footballEvent.reportedAt, eventId: FOOTBALL_EVENT)])
  }

  def "Should get all reports with declined status"() {
    given: "there are some reports with pending status"
      def handballEvent = reportFacade.createReport(new CreateReportDto("janedoe", HANDBALL_EVENT))
      def footballEvent = reportFacade.createReport(new CreateReportDto("mikejones", FOOTBALL_EVENT))
    and: "both reports change status to declined"
      reportFacade.declineReport(handballEvent.reportId)
      reportFacade.declineReport(footballEvent.reportId)
    when: "asks for all reports with declined status"
      def result = reportFacade.getAllReportsByStatus(ReportStatusDto.DECLINED)
    then: "gets all pending status"
    equalsReports(result, [createReport(reportId: handballEvent.reportId, username: "janedoe", reportStatus: ReportStatusDto.DECLINED,
        reportedAt: footballEvent.reportedAt, eventId: HANDBALL_EVENT
    ), createReport(reportId: handballEvent.reportId, username: "mikejones", reportStatus: ReportStatusDto.DECLINED,
        reportedAt: footballEvent.reportedAt, eventId: FOOTBALL_EVENT)])
  }
}
