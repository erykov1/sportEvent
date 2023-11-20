package tijo.sportEventApp.sportEvent.domain

import tijo.sportEventApp.sportEvent.dto.SportEventAddressDto

trait SportEventAddressSample {
  private Map<String, Object> DEFAULT_EVENT_ADDRESS_DATA = [
      eventAddressId: 1L,
      postalCode: '',
      city: '',
      street: '',
      streetNumber: ''
  ] as Map<String, Object>

  SportEventAddressDto createEventAddress(Map<String, Object> changes = [:]) {
    def changesWithDefaults = DEFAULT_EVENT_ADDRESS_DATA + changes
    SportEventAddressDto.builder()
        .eventAddressId(changesWithDefaults.eventAddressId as Long)
        .postalCode(changesWithDefaults.postalCode as String)
        .city(changesWithDefaults.city as String)
        .street(changesWithDefaults.street as String)
        .streetNumber(changesWithDefaults.streetNumber as String)
        .build()
  }

  void equalsEventAddresses(List<SportEventAddressDto> result, List<SportEventAddressDto> expected) {
    def comparator = Comparator.comparing(SportEventAddressDto::getCity)
    result.sort(comparator)
    assert result.eventAddressId == expected.eventAddressId
    assert result.postalCode == expected.postalCode
    assert result.city == expected.city
    assert result.street == expected.street
    assert result.streetNumber == expected.streetNumber
  }

  void equalsEventAddress(SportEventAddressDto result, SportEventAddressDto expected) {
    assert result.eventAddressId == expected.eventAddressId
    assert result.postalCode == expected.postalCode
    assert result.city == expected.city
    assert result.street == expected.street
    assert result.streetNumber == expected.streetNumber
  }
}