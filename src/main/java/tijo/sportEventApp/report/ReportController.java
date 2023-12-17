package tijo.sportEventApp.report;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tijo.sportEventApp.report.domain.ReportFacade;
import tijo.sportEventApp.report.dto.CreateReportDto;
import tijo.sportEventApp.report.dto.ReportDto;
import tijo.sportEventApp.report.dto.UpdateReportDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/report")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class ReportController {
  ReportFacade reportFacade;

  @PostMapping("/create")
  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  ResponseEntity<ReportDto> createReport(@RequestBody CreateReportDto report) {
    return ResponseEntity.ok(reportFacade.createReport(report));
  }

  @PatchMapping("/accept/{reportId}")
  @PreAuthorize("hasRole('ADMIN')")
  ResponseEntity<UpdateReportDto> acceptReport(@PathVariable UUID reportId) {
    return ResponseEntity.ok(reportFacade.acceptReport(reportId));
  }

  @PatchMapping("/decline/{reportId}")
  @PreAuthorize("hasRole('ADMIN')")
  ResponseEntity<UpdateReportDto> declineReport(@PathVariable UUID reportId) {
    return ResponseEntity.ok(reportFacade.declineReport(reportId));
  }

  @DeleteMapping("/delete/{reportId}")
  @PreAuthorize("hasRole('ADMIN')")
  ResponseEntity<String> deleteReport(@PathVariable UUID reportId) {
    reportFacade.deleteReport(reportId);
    return ResponseEntity.ok("Deleted report with id : " + reportId);
  }

  @GetMapping("/all")
  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  ResponseEntity<List<ReportDto>> findReports() {
    return ResponseEntity.ok(reportFacade.findAllReports());
  }
}
