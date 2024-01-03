package tijo.sportEventApp.report.domain

import tijo.sportEventApp.report.dto.CreateReportDto
import tijo.sportEventApp.report.dto.ReportStatusDto
import tijo.sportEventApp.report.dto.SportEventAssignDto
import tijo.sportEventApp.sportEvent.domain.SportEventBase
import tijo.sportEventApp.sportEvent.domain.SportEventSample
import tijo.sportEventApp.sportEvent.dto.SportEventTypeDto
import tijo.sportEventApp.sportEvent.exception.FullParticipantsException
import tijo.sportEventApp.sportEvent.exception.NotExistingSportEventException

class ReportSpec extends SportEventBase implements ReportSample, SportEventSample {
  ReportFacade reportFacade = new ReportConfiguration().reportFacade()

  def "Should create report"() {
    given: "there is sport event"
    def firstHandballEdition = sportEventFacade.createSportEvent(createNewSportEvent(eventName: "event",
        eventTime: "2024-08-08 12:00", registrationDeadline: "2024-07-08 12:00",
        description: "event handball desc", eventAddress: 1L, maxParticipants: 200L, sportEventType: SportEventTypeDto.HANDBALL))
    reportFacade.onSportEventCreate(new SportEventAssignDto(firstHandballEdition.sportEventId, firstHandballEdition.maxParticipants,
      firstHandballEdition.registrationDeadline, firstHandballEdition.eventTime
    ))
    when: "creates report"
      def result = reportFacade.createReport(new CreateReportDto("janedoe", firstHandballEdition.sportEventId))
    then: "report is created"
      equalsReport(result, createReport(reportId: result.reportId, username: "janedoe", reportStatus: ReportStatusDto.PENDING,
        reportedAt: result.reportedAt, eventId: firstHandballEdition.sportEventId
      ))
  }

  def "Should not create report if try to report to not existing sport event"() {
    when: "creates report to not existing sport event"
      reportFacade.createReport(new CreateReportDto("janedoe", new Random().nextLong()))
    then: "gets error of not existing sport event"
      thrown(NotExistingSportEventException)
  }

  def "Should not create report to sport event if there is already full participants"() {
    given: "there is sport event"
      def firstHandballEdition = sportEventFacade.createSportEvent(createNewSportEvent(eventName: "event",
          eventTime: "2024-08-08 12:00", registrationDeadline: "2024-07-08 12:00",
          description: "event handball desc", eventAddress: 1L, maxParticipants: 0L, sportEventType: SportEventTypeDto.HANDBALL))
      reportFacade.onSportEventCreate(new SportEventAssignDto(firstHandballEdition.sportEventId, firstHandballEdition.maxParticipants,
          firstHandballEdition.registrationDeadline, firstHandballEdition.eventTime
      ))
    when: "creates report to sport event that has already full participants"
      reportFacade.createReport(new CreateReportDto("janedoe", firstHandballEdition.sportEventId))
    then: "gets error of full filled participants in given sport event"
      thrown(FullParticipantsException)
  }

  def "Should create report for the same user if try to assign to two sport events that are in different time"() {
    given: "there is sport event"
      def firstHandballEdition = sportEventFacade.createSportEvent(createNewSportEvent(eventName: "event",
          eventTime: "2024-08-08 12:00", registrationDeadline: "2024-07-08 12:00",
          description: "event handball desc", eventAddress: 1L, maxParticipants: 200L, sportEventType: SportEventTypeDto.HANDBALL))
      reportFacade.onSportEventCreate(new SportEventAssignDto(firstHandballEdition.sportEventId, firstHandballEdition.maxParticipants,
          firstHandballEdition.registrationDeadline, firstHandballEdition.eventTime
      ))
    and: "there is another sport event"
      def secondHandballEdition = sportEventFacade.createSportEvent(createNewSportEvent(eventName: "event",
          eventTime: "2025-08-08 12:00", registrationDeadline: "2025-07-08 12:00",
          description: "event handball desc", eventAddress: 1L, maxParticipants: 200L, sportEventType: SportEventTypeDto.HANDBALL))
      reportFacade.onSportEventCreate(new SportEventAssignDto(secondHandballEdition.sportEventId, secondHandballEdition.maxParticipants,
          secondHandballEdition.registrationDeadline, secondHandballEdition.eventTime
      ))
    and: "user assign to first event"
      def handballReport = reportFacade.createReport(new CreateReportDto("janedoe", firstHandballEdition.sportEventId))
    when: "user assigns to other event"
      instantProvider.useFixedClock(instantProvider.now().plusMillis(3l))
      def handballSecondEditionReport = reportFacade.createReport(new CreateReportDto("janedoe", secondHandballEdition.sportEventId))
    then: "user is assigned to two sport events"
      equalsReports(reportFacade.getAllUserReports("janedoe"), [createReport(reportId: handballReport.reportId, username: "janedoe", reportStatus: ReportStatusDto.PENDING,
          reportedAt: handballReport.reportedAt, eventId: firstHandballEdition.sportEventId
      ), createReport(reportId: handballSecondEditionReport.reportId, username: "janedoe", reportStatus: ReportStatusDto.PENDING,
          reportedAt: handballSecondEditionReport.reportedAt, eventId: secondHandballEdition.sportEventId)])
  }

  def "Should change status from pending to accepted"() {
    given: "there is sport event"
    def firstHandballEdition = sportEventFacade.createSportEvent(createNewSportEvent(eventName: "event",
        eventTime: "2024-08-08 12:00", registrationDeadline: "2024-07-08 12:00",
        description: "event handball desc", eventAddress: 1L, maxParticipants: 200L, sportEventType: SportEventTypeDto.HANDBALL))
    reportFacade.onSportEventCreate(new SportEventAssignDto(firstHandballEdition.sportEventId, firstHandballEdition.maxParticipants,
        firstHandballEdition.registrationDeadline, firstHandballEdition.eventTime
    ))
    and: "user report for the sport event"
      def handballEvent = reportFacade.createReport(new CreateReportDto("janedoe", firstHandballEdition.sportEventId))
    when: "gets accepted"
      def result = reportFacade.acceptReport(handballEvent.reportId)
    then: "status of his report is accepted"
      result.reportId == handballEvent.reportId
      result.status == ReportStatusDto.ACCEPTED
  }

  def "Should change status from pending to declined"() {
    given: "there is sport event"
    def firstHandballEdition = sportEventFacade.createSportEvent(createNewSportEvent(eventName: "event",
        eventTime: "2024-08-08 12:00", registrationDeadline: "2024-07-08 12:00",
        description: "event handball desc", eventAddress: 1L, maxParticipants: 200L, sportEventType: SportEventTypeDto.HANDBALL))
    reportFacade.onSportEventCreate(new SportEventAssignDto(firstHandballEdition.sportEventId, firstHandballEdition.maxParticipants,
        firstHandballEdition.registrationDeadline, firstHandballEdition.eventTime
    ))
    and: "user report for the sport event"
      def handballEvent = reportFacade.createReport(new CreateReportDto("janedoe", firstHandballEdition.sportEventId))
    when: "gets accepted"
      def result = reportFacade.declineReport(handballEvent.reportId)
    then: "status of his report is declined"
      result.reportId == handballEvent.reportId
      result.status == ReportStatusDto.DECLINED
  }

  def "Should get all reports with pending status"() {
    given: "there is sport event"
    def firstHandballEdition = sportEventFacade.createSportEvent(createNewSportEvent(eventName: "event",
        eventTime: "2024-08-08 12:00", registrationDeadline: "2024-07-08 12:00",
        description: "event handball desc", eventAddress: 1L, maxParticipants: 200L, sportEventType: SportEventTypeDto.HANDBALL))
    reportFacade.onSportEventCreate(new SportEventAssignDto(firstHandballEdition.sportEventId, firstHandballEdition.maxParticipants,
        firstHandballEdition.registrationDeadline, firstHandballEdition.eventTime
    ))
    and: "user janedoe creates report"
      def janeDoeReport = reportFacade.createReport(new CreateReportDto("janedoe", firstHandballEdition.sportEventId))
    and: "user mikejones creates report"
      instantProvider.useFixedClock(instantProvider.now().plusMillis(3l))
      def mikeJonesReport = reportFacade.createReport(new CreateReportDto("mikejones", firstHandballEdition.sportEventId))
    when: "asks for all pending status"
      def result = reportFacade.getAllReportsByStatus(ReportStatusDto.PENDING.name())
    then: "gets all pending status"
    equalsReports(result, [createReport(reportId: janeDoeReport.reportId, username: "janedoe", reportStatus: ReportStatusDto.PENDING,
        reportedAt: janeDoeReport.reportedAt, eventId: firstHandballEdition.sportEventId
    ), createReport(reportId: mikeJonesReport.reportId, username: "mikejones", reportStatus: ReportStatusDto.PENDING,
        reportedAt: mikeJonesReport.reportedAt, eventId: firstHandballEdition.sportEventId)])
  }

  def "Should get all reports with declined status"() {
    given: "there is sport event"
    def firstHandballEdition = sportEventFacade.createSportEvent(createNewSportEvent(eventName: "event",
        eventTime: "2024-08-08 12:00", registrationDeadline: "2024-07-08 12:00",
        description: "event handball desc", eventAddress: 1L, maxParticipants: 200L, sportEventType: SportEventTypeDto.HANDBALL))
    reportFacade.onSportEventCreate(new SportEventAssignDto(firstHandballEdition.sportEventId, firstHandballEdition.maxParticipants,
        firstHandballEdition.registrationDeadline, firstHandballEdition.eventTime
    ))
    and: "user janedoe creates report"
      def janeReport = reportFacade.createReport(new CreateReportDto("janedoe", firstHandballEdition.sportEventId))
    and: "user mikejones creates report"
      instantProvider.useFixedClock(instantProvider.now().plusMillis(3l))
      def mikeReport = reportFacade.createReport(new CreateReportDto("mikejones", firstHandballEdition.sportEventId))
    and: "janedoe report change status to declined"
      reportFacade.declineReport(janeReport.reportId)
    and: "mikejonse report change status to declined"
      reportFacade.declineReport(mikeReport.reportId)
    when: "asks for all reports with declined status"
      def result = reportFacade.getAllReportsByStatus(ReportStatusDto.DECLINED.name())
    then: "gets all pending status"
    equalsReports(result, [createReport(reportId: janeReport.reportId, username: "janedoe", reportStatus: ReportStatusDto.DECLINED,
        reportedAt: janeReport.reportedAt, eventId: firstHandballEdition.sportEventId
    ), createReport(reportId: mikeReport.reportId, username: "mikejones", reportStatus: ReportStatusDto.DECLINED,
        reportedAt: mikeReport.reportedAt, eventId: firstHandballEdition.sportEventId)])
  }
}
