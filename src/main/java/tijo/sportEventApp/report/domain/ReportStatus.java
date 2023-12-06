package tijo.sportEventApp.report.domain;


import tijo.sportEventApp.report.dto.ReportStatusDto;

enum ReportStatus {
  PENDING,
  DECLINED,
  ACCEPTED;

  ReportStatusDto dto() {
    return ReportStatusDto.valueOf(name());
  }
}
