package tijo.sportEventApp.report.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import org.springframework.context.event.EventListener;
import tijo.sportEventApp.report.dto.*;
import tijo.sportEventApp.report.exception.ReportNotFoundException;
import tijo.sportEventApp.sportEvent.exception.FullParticipantsException;
import tijo.sportEventApp.sportEvent.exception.NotExistingSportEventException;
import tijo.sportEventApp.utils.InstantProvider;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReportFacade {
  ReportRepository reportRepository;
  SportEventAssignRepository sportEventAssignRepository;
  InstantProvider instantProvider;

  public ReportDto createReport(CreateReportDto createReportDto) {
    checkIfReachMaxParticipant(createReportDto.getSportEventId(), countAllSportEventReports(createReportDto.getSportEventId()));
    Report saveReport = Report.builder()
        .reportId(UUID.randomUUID())
        .username(createReportDto.getUsername())
        .reportStatus(ReportStatus.PENDING)
        .reportedAt(instantProvider.now())
        .sportEventId(createReportDto.getSportEventId())
        .build();
    return reportRepository.save(saveReport).dto();
  }

  public List<ReportDto> getAllUserReports(String username) {
    return reportRepository.findAllReportsByUsername(username).stream()
        .map(Report::dto)
        .collect(Collectors.toList());
  }

  public UpdateReportDto acceptReport(UUID reportId) {
    Report acceptReport = reportRepository.findByReportId(reportId)
        .orElseThrow(() -> new ReportNotFoundException("Report with such id not found"));
    acceptReport = acceptReport.toBuilder()
        .reportStatus(ReportStatus.ACCEPTED)
        .build();
    reportRepository.save(acceptReport).dto();
    return new UpdateReportDto(reportId, ReportStatus.ACCEPTED.dto());
  }

  public UpdateReportDto declineReport(UUID reportId) {
    Report declineReport = reportRepository.findByReportId(reportId)
        .orElseThrow(() -> new ReportNotFoundException("Report with such id not found"));
    declineReport = declineReport.toBuilder()
        .reportStatus(ReportStatus.ACCEPTED)
        .build();
    reportRepository.save(declineReport).dto();
    return new UpdateReportDto(reportId, ReportStatus.DECLINED.dto());
  }

  public List<ReportDto> getAllReportsByStatus(String reportStatus) {
    return reportRepository.findAll().stream()
        .filter(report -> report.dto().getReportStatus().name().equals(reportStatus))
        .map(Report::dto)
        .collect(Collectors.toList());
  }

  public void deleteReport(UUID reportId) {
    reportRepository.deleteById(reportId);
  }

  public List<ReportDto> findAllReports() {
    return reportRepository.findAll().stream()
        .map(Report::dto)
        .collect(Collectors.toList());
  }

  private void checkIfReachMaxParticipant(Long sportEventId, Long currentParticipantNumber) {
    SportEventAssign sportEventAssign = sportEventAssignRepository.findSportEventBySportEventId(sportEventId)
        .orElseThrow(() -> new NotExistingSportEventException("Sport event does not exist"));
    if(sportEventAssign.dto().getMaxParticipants() >= currentParticipantNumber) {
      throw new FullParticipantsException("There are already full participants");
    }
  }

  private Long countAllSportEventReports(Long sportEventId) {
    return (long) reportRepository.findAllReportsBySportEventId(sportEventId).size();
  }

  @EventListener
  private SportEventAssignDto onSportEventCreate(SportEventAssignDto sportEventPublish) {
    SportEventAssign sportEventAssign = SportEventAssign.builder()
        .sportEventId(sportEventPublish.getSportEventId())
        .maxParticipants(sportEventPublish.getMaxParticipants())
        .registrationDeadline(sportEventPublish.getRegistrationDeadline())
        .eventTime(sportEventPublish.getEventTime())
        .build();
    return sportEventAssignRepository.save(sportEventAssign).dto();
  }
}
