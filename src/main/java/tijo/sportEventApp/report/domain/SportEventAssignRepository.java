package tijo.sportEventApp.report.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

interface SportEventAssignRepository extends JpaRepository<SportEventAssign, Long> {
  Optional<SportEventAssign> findSportEventBySportEventId(Long sportEventId);
}
