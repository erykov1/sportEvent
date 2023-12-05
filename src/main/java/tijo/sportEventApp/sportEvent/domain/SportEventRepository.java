package tijo.sportEventApp.sportEvent.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Optional;

interface SportEventRepository extends JpaRepository<SportEvent, Long> {
    Optional<SportEvent> findBySportEventId(Long sportEventId);
    Optional<SportEvent> findBySportEventAddressAndEventTime(Long sportEventAddress, Instant eventTime);
}
