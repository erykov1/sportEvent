package tijo.sportEventApp.sportEvent.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import tijo.sportEventApp.sportEvent.dto.CreateSportEventAddressDto;
import tijo.sportEventApp.sportEvent.dto.CreateSportEventDto;
import tijo.sportEventApp.sportEvent.dto.SportEventAddressDto;
import tijo.sportEventApp.sportEvent.dto.SportEventDto;
import tijo.sportEventApp.utils.InstantProvider;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SportEventFacade {
  SportEventRepository sportEventRepository;
  SportEventAddressRepository sportEventAddressRepository;
  InstantProvider instantProvider;

  public SportEventAddressDto createEventAddressDto(CreateSportEventAddressDto createSportEventAddressDto) {
    return SportEventAddressDto.builder().build();
  }

  public List<SportEventAddressDto> findAllEventAddresses() {
    return new ArrayList<>();
  }

  public SportEventDto createSportEvent(CreateSportEventDto createSportEventDto) {
    return SportEventDto.builder().build();
  }

  public List<SportEventDto> findAllSportEvents() {
    return new ArrayList<>();
  }
}
