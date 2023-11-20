package tijo.sportEventApp.sportEvent.domain

import tijo.sportEventApp.sportEvent.dto.SportEventDto
import tijo.sportEventApp.sportEvent.exception.AlreadyReservedAddressException
import tijo.sportEventApp.sportEvent.dto.SportEventTypeDto

class SportEventSpec extends SportEventBase implements SportEventAddressSample, SportEventSample {
  def "Should create new sport event"() {
    when: "Creates new sport event"
    SportEventDto result = sportEventFacade.createSportEvent(createNewSportEvent(eventName: "event",
        eventTime: instantProvider.fromFormatted("2024-08-08 12:00"), registrationDeadline: instantProvider.fromFormatted("2024-07-08 12:00"),
        description: "event desc", eventAddress: 1L, maxParticipants: 200L, sportEventType: SportEventTypeDto.HANDBALL))
    then: "new sport event is created"
    equalsSportEvents([result], [createSportEvent(sportEventId: result.sportEventId, eventName: "event",
        eventTime: instantProvider.fromFormatted("2024-08-08 12:00"), registrationDeadline: instantProvider.fromFormatted("2024-07-08 12:00"), description: "event desc", eventAddress: EVENT_ADDRESS,
        maxParticipants: MAX_PARTICIPANTS, sportEventType: SportEventTypeDto.HANDBALL
    )])
  }

  def "Should not create new sport event if there is already sport event in the same place nad time"() {
    given: "there is sport event"
    sportEventFacade.createSportEvent(createNewSportEvent(eventName: "event",
        eventTime: instantProvider.fromFormatted("2024-08-08 12:00"), registrationDeadline: instantProvider.fromFormatted("2024-07-08 12:00"),
        description: "event handball desc", eventAddress: 1L, maxParticipants: 200L, sportEventType: SportEventTypeDto.HANDBALL))
    when: "creates sport event in the same place and time"
    sportEventFacade.createSportEvent(createNewSportEvent(eventName: "event",
        eventTime: instantProvider.fromFormatted("2024-08-08 12:00"), registrationDeadline: instantProvider.fromFormatted("2024-07-09 12:00"),
        description: "event volleyball desc", eventAddress: 1L, maxParticipants: 100L, sportEventType: SportEventTypeDto.VOLLEYBALL))
    then: "gets error of already reserved address"
    thrown(AlreadyReservedAddressException)
  }

  def "Should not create new sport event if it already exists"() {
    given: "there is sport event"
    sportEventFacade.createSportEvent(createNewSportEvent(eventName: "event",
        eventTime: instantProvider.fromFormatted("2024-08-08 12:00"), registrationDeadline: instantProvider.fromFormatted("2024-07-08 12:00"),
        description: "event handball desc", eventAddress: 1L, maxParticipants: 200L, sportEventType: SportEventTypeDto.HANDBALL))
    when: "creates the same sport event"
    sportEventFacade.createSportEvent(createNewSportEvent(eventName: "event",
        eventTime: instantProvider.fromFormatted("2024-08-08 12:00"), registrationDeadline: instantProvider.fromFormatted("2024-07-08 12:00"),
        description: "event handball desc", eventAddress: 1L, maxParticipants: 200L, sportEventType: SportEventTypeDto.HANDBALL))
    then: "gets error of sport event already exists"
    thrown(AlreadyReservedAddressException)
  }

  def "Should create sport event if event has the same data but different event time"() {
    given: "there is sport event"
    def firstHandballEdition = sportEventFacade.createSportEvent(createNewSportEvent(eventName: "event",
        eventTime: instantProvider.fromFormatted("2024-08-08 12:00"), registrationDeadline: instantProvider.fromFormatted("2024-07-08 12:00"),
        description: "event handball desc", eventAddress: 1L, maxParticipants: 200L, sportEventType: SportEventTypeDto.HANDBALL))
    when: "creates the same event but with different event time"
    def secondHandballEdition = sportEventFacade.createSportEvent(createNewSportEvent(eventName: "event",
        eventTime: instantProvider.fromFormatted("2025-08-08 12:00"), registrationDeadline: instantProvider.fromFormatted("2025-07-08 12:00"),
        description: "event handball desc", eventAddress: 1L, maxParticipants: 200L, sportEventType: SportEventTypeDto.HANDBALL))
    then: "there are two sport events"
    equalsSportEvents(sportEventFacade.findAllSportEvents(), [createSportEvent(sportEventId: firstHandballEdition.sportEventId, eventName: "event",
        eventTime: instantProvider.fromFormatted("2024-08-08 12:00"), registrationDeadline: instantProvider.fromFormatted("2024-07-08 12:00"),
        description: "event handball desc", eventAddress: EVENT_ADDRESS,
        maxParticipants: MAX_PARTICIPANTS, sportEventType: SportEventTypeDto.HANDBALL
    ), createSportEvent(sportEventId: secondHandballEdition.sportEventId, eventName: "event",
        eventTime: instantProvider.fromFormatted("2025-08-08 12:00"), registrationDeadline: instantProvider.fromFormatted("2025-07-08 12:00"),
        description: "event handball desc", eventAddress: EVENT_ADDRESS,
        maxParticipants: MAX_PARTICIPANTS, sportEventType: SportEventTypeDto.HANDBALL,
    )])
  }
}
