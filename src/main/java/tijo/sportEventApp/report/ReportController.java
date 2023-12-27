package tijo.sportEventApp.report;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
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
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class ReportController {
  ReportFacade reportFacade;

  @Autowired
  ReportController(ReportFacade reportFacade) {
    this.reportFacade = reportFacade;
  }

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
  ResponseEntity<Void> deleteReport(@PathVariable UUID reportId) {
    reportFacade.deleteReport(reportId);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/all")
  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  ResponseEntity<List<ReportDto>> findReports() {
    return ResponseEntity.ok(reportFacade.findAllReports());
  }

  @GetMapping("/all/{username}")
  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  ResponseEntity<List<ReportDto>> findUserReports(@PathVariable String username) {
    return ResponseEntity.ok(reportFacade.getAllUserReports(username));
  }

  @GetMapping("/cleanup")
  @Hidden
  ResponseEntity<String> cleanup() {
    reportFacade.cleanup();
    return ResponseEntity.ok("report cleanup");
  }
}
