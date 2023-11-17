package tijo.sportEventApp.sportEvent.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface SportEventRepository extends JpaRepository<SportEvent, Long> {
}
