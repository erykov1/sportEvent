package tijo.sportEventApp.report.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import tijo.sportEventApp.report.dto.CreateReportDto;
import tijo.sportEventApp.report.dto.ReportDto;
import tijo.sportEventApp.report.dto.ReportStatusDto;
import tijo.sportEventApp.report.dto.UpdateReportDto;
import tijo.sportEventApp.utils.InstantProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReportFacade {
  ReportRepository reportRepository;
  SportEventAssignRepository sportEventAssignRepository;
  InstantProvider instantProvider;

  public ReportDto createReport(CreateReportDto createReportDto) {
    return ReportDto.builder().build();
  }

  public List<ReportDto> getAllUserReports(String username) {
    return new ArrayList<>();
  }

  public UpdateReportDto acceptReport(UUID reportId) {
    return UpdateReportDto.builder().build();
  }

  public UpdateReportDto declineReport(UUID reportId) {
    return UpdateReportDto.builder().build();
  }

  public List<ReportDto> getAllReportsByStatus(ReportStatusDto reportStatus) {
    return new ArrayList<>();
  }

  public void deleteReport(UUID reportId) {
  }

  public List<ReportDto> findAllReports() {
    return new ArrayList<>();
  }
}
