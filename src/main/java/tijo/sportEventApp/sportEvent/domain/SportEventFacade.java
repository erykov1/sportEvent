package tijo.sportEventApp.sportEvent.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import tijo.sportEventApp.sportEvent.dto.CreateSportEventAddressDto;
import tijo.sportEventApp.sportEvent.dto.CreateSportEventDto;
import tijo.sportEventApp.sportEvent.dto.SportEventAddressDto;
import tijo.sportEventApp.sportEvent.dto.SportEventDto;
import tijo.sportEventApp.utils.InstantProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SportEventFacade {
  SportEventRepository sportEventRepository;
  SportEventAddressRepository sportEventAddressRepository;
  InstantProvider instantProvider;

  public SportEventAddressDto createEventAddress(CreateSportEventAddressDto createSportEventAddress) {
    if (sportEventAddressRepository.findSportEventAddressByDetails(createSportEventAddress.getPostalCode(),
            createSportEventAddress.getCity(), createSportEventAddress.getStreet(), createSportEventAddress.getStreetNumber()
    ).isPresent()) {
      return SportEventAddressDto.builder().build();
    }
    SportEventAddress sportEventAddressSave = SportEventAddress.builder()
            .postalCode(createSportEventAddress.getPostalCode())
            .city(createSportEventAddress.getCity())
            .street(createSportEventAddress.getStreet())
            .streetNumber(createSportEventAddress.getStreetNumber())
            .build();
    return sportEventAddressRepository.save(sportEventAddressSave).dto();
  }

  public List<SportEventAddressDto> findAllEventAddresses() {
    return sportEventAddressRepository.findAll().stream()
            .map(SportEventAddress::dto)
            .collect(Collectors.toList());
  }

  public SportEventDto createSportEvent(CreateSportEventDto createSportEventDto) {
    return SportEventDto.builder().build();
  }

  public List<SportEventDto> findAllSportEvents() {
    return new ArrayList<>();
  }
}
