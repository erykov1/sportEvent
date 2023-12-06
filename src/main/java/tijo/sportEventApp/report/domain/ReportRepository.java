package tijo.sportEventApp.report.domain;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

interface ReportRepository extends JpaRepository<Report, UUID> {
  List<Report> findAllReportsByUsername(String username);
  Optional<Report> findByReportId(UUID reportId);
  List<Report> findAllReportsBySportEventId(Long sportEventId);
}
