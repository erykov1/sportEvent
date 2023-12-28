package tijo.sportEventApp.sportEvent.domain

import tijo.sportEventApp.integration.IntegrationSpec
import tijo.sportEventApp.sportEvent.dto.CreateSportEventAddressDto
import tijo.sportEventApp.sportEvent.dto.SportEventAddressDto
import tijo.sportEventApp.sportEvent.dto.SportEventDto
import tijo.sportEventApp.sportEvent.dto.SportEventTypeDto
import tijo.sportEventApp.utils.InstantProvider

class SportEventAcceptanceSpec extends IntegrationSpec implements SportEventSample, SportEventAddressSample {
  def "Should create new sport event"() {
    when: "Creates new sport event"
    SportEventDto result = api.sportEvent().createSportEvent(createNewSportEvent(eventName: "event",
        eventTime: "2024-08-08 12:00", registrationDeadline: "2024-07-08 12:00",
        description: "event desc", eventAddress: 1L, maxParticipants: 200L, sportEventType: SportEventTypeDto.HANDBALL))
    then: "sport event is created"
      equalsSportEvents([result], [createSportEvent(sportEventId: result.sportEventId, eventName: "event",
          eventTime: InstantProvider.fromFormatted("2024-08-08 12:00"), registrationDeadline: InstantProvider.fromFormatted("2024-07-08 12:00"), description: "event desc", eventAddress: EVENT_ADDRESS,
          maxParticipants: MAX_PARTICIPANTS, sportEventType: SportEventTypeDto.HANDBALL
      )])
  }

  def "Should create new sport address"() {
    when: "Creates new sport event address"
      SportEventAddressDto result = api.sportEvent().createSportEventAddress(CreateSportEventAddressDto.builder()
          .postalCode("30-100").city("Tarnow").street("Mickiewicza").streetNumber("34A").build()
      )
    then: "sport event address is created"
      equalsEventAddresses([result], [createEventAddress(eventAddressId: result.eventAddressId,
          postalCode: "30-100", city: "Tarnow", street: "Mickiewicza", streetNumber: "34A"
      )])
  }

  def "Should get all sport events"() {
    given: "there are some sport events"
    SportEventDto handball = api.sportEvent().createSportEvent(createNewSportEvent(eventName: "event",
        eventTime: "2024-08-08 12:00", registrationDeadline: "2024-07-08 12:00",
        description: "event desc", eventAddress: 1L, maxParticipants: 200L, sportEventType: SportEventTypeDto.HANDBALL))
    SportEventDto football = api.sportEvent().createSportEvent(createNewSportEvent(eventName: "event",
        eventTime: "2025-08-08 12:00", registrationDeadline: "2025-07-08 12:00",
        description: "event desc", eventAddress: 1L, maxParticipants: 200L, sportEventType: SportEventTypeDto.FOOTBALL))
    when: "ask for all sport events"
      def result = api.sportEvent().getAllSportEvents()
    then: "gets all sport events"
    equalsSportEvents(result, [createSportEvent(sportEventId: handball.sportEventId, eventName: "event",
        eventTime: InstantProvider.fromFormatted("2024-08-08 12:00"), registrationDeadline: InstantProvider.fromFormatted("2024-07-08 12:00"),
        description: "event desc", eventAddress: EVENT_ADDRESS,
        maxParticipants: MAX_PARTICIPANTS, sportEventType: SportEventTypeDto.HANDBALL
    ), createSportEvent(sportEventId: football.sportEventId, eventName: "event",
        eventTime: InstantProvider.fromFormatted("2025-08-08 12:00"), registrationDeadline: InstantProvider.fromFormatted("2025-07-08 12:00"),
        description: "event desc", eventAddress: EVENT_ADDRESS,
        maxParticipants: MAX_PARTICIPANTS, sportEventType: SportEventTypeDto.FOOTBALL,
    )])
  }

  def "Should get all sport events addresses"() {
    given: "there are some sport events addresses"
      SportEventAddressDto tarnow = api.sportEvent().createSportEventAddress(CreateSportEventAddressDto.builder()
          .postalCode("30-100").city("Tarnow").street("Mickiewicza").streetNumber("34A").build())
      SportEventAddressDto cracow = api.sportEvent().createSportEventAddress(CreateSportEventAddressDto.builder()
        .postalCode("30-100").city("Krakow").street("Mickiewicza").streetNumber("34A").build())
    when: "ask for all addresses"
      def result = api.sportEvent().getAllSportEventsAddresses()
    then: "gets all sport event addresses"
      equalsEventAddresses(result, [createEventAddress(eventAddressId: cracow.eventAddressId, postalCode: "30-100",
        city: "Krakow", street: "Mickiewicza", streetNumber: "34A"
      ), createEventAddress(eventAddressId: tarnow.eventAddressId, postalCode: "30-100",
          city: "Tarnow", street: "Mickiewicza", streetNumber: "34A")])
  }

  def cleanup() {
    api.user().cleanup()
    api.sportEvent().cleanup()
    api.report().cleanup()
  }
}
