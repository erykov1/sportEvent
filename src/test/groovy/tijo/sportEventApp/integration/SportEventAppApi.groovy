package tijo.sportEventApp.integration

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import tijo.sportEventApp.report.dto.CreateReportDto
import tijo.sportEventApp.report.dto.ReportDto
import tijo.sportEventApp.report.dto.UpdateReportDto
import tijo.sportEventApp.sportEvent.dto.CreateSportEventAddressDto
import tijo.sportEventApp.sportEvent.dto.CreateSportEventDto
import tijo.sportEventApp.sportEvent.dto.SportEventAddressDto
import tijo.sportEventApp.sportEvent.dto.SportEventDto
import tijo.sportEventApp.user.dto.CreateUserDto
import tijo.sportEventApp.user.dto.UserDto

import java.nio.charset.StandardCharsets

class SportEventAppApi {
  private final UserApi userApi
  private final ReportApi reportApi
  private final SportEventApi sportEventApi

  SportEventAppApi(MockMvc mockMvc, ObjectMapper objectMapper) {
    this.userApi = new UserApi(mockMvc, objectMapper)
    this.reportApi = new ReportApi(mockMvc, objectMapper)
    this.sportEventApi = new SportEventApi(mockMvc, objectMapper)
  }

  UserApi user() {
    userApi
  }

  ReportApi report() {
    reportApi
  }

  SportEventApi sportEvent() {
    sportEventApi
  }

  class UserApi {
    private final MockMvc mvc
    private final ObjectMapper mapper

    UserApi(MockMvc mvc, ObjectMapper mapper) {
      this.mvc = mvc
      this.mapper = mapper
    }

    UserDto registerUser(CreateUserDto createUser) {
      ResultActions perform = mvc.perform(MockMvcRequestBuilders.post("/api/user/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(createUser))
      )
      checkResponse(perform.andReturn().response)
      UserDto value = mapper.readValue(perform.andReturn().response.getContentAsString(StandardCharsets.UTF_8), mapper.getTypeFactory().constructType(UserDto.class))
      value
    }

    UserDto getUserByUsername(String username) {
      ResultActions perform = mvc.perform(MockMvcRequestBuilders.get("/api/user/{username}", username))
      checkResponse(perform.andReturn().response)
      UserDto value = mapper.readValue(perform.andReturn().response.getContentAsString(StandardCharsets.UTF_8), mapper.getTypeFactory().constructType(UserDto.class))
      value
    }

    void cleanup() {
      ResultActions perform = mvc.perform(MockMvcRequestBuilders.get("/api/user/cleanup"))
      checkResponse(perform.andReturn().response)
    }
  }

  class ReportApi {
    private final MockMvc mvc
    private final ObjectMapper mapper

    ReportApi(MockMvc mvc, ObjectMapper mapper) {
      this.mvc = mvc
      this.mapper = mapper
    }

    ReportDto createReport(CreateReportDto report) {
      ResultActions perform = mvc.perform(MockMvcRequestBuilders.post("/api/report/create")
          .contentType(MediaType.APPLICATION_JSON)
          .content(mapper.writeValueAsString(report))
      )
      checkResponse(perform.andReturn().response)
      ReportDto value = mapper.readValue(perform.andReturn().response.getContentAsString(StandardCharsets.UTF_8),
          mapper.getTypeFactory().constructType(ReportDto.class))
      value
    }

    UpdateReportDto acceptReport(UUID reportId) {
      ResultActions perform = mvc.perform(MockMvcRequestBuilders.patch("/api/report/accept/{reportId}", reportId)
          .contentType(MediaType.APPLICATION_JSON)
          .content(mapper.writeValueAsString(reportId))
      )
      checkResponse(perform.andReturn().response)
      UpdateReportDto value = mapper.readValue(perform.andReturn().response.getContentAsString(StandardCharsets.UTF_8),
          mapper.getTypeFactory().constructType(UpdateReportDto.class))
      value
    }

    UpdateReportDto declineReport(UUID reportId) {
      ResultActions perform = mvc.perform(MockMvcRequestBuilders.patch("/api/report/decline/{reportId}", reportId)
          .contentType(MediaType.APPLICATION_JSON)
          .content(mapper.writeValueAsString(reportId))
      )
      checkResponse(perform.andReturn().response)
      UpdateReportDto value = mapper.readValue(perform.andReturn().response.getContentAsString(StandardCharsets.UTF_8),
          mapper.getTypeFactory().constructType(UpdateReportDto.class))
      value
    }

    String deleteReport(UUID reportId) {
      ResultActions perform = mvc.perform(MockMvcRequestBuilders.delete("/api/report/delete/{reportId}", reportId)
          .contentType(MediaType.APPLICATION_JSON)
          .content(mapper.writeValueAsString(reportId))
      )
      checkResponse(perform.andReturn().response)
      String value = mapper.readValue(perform.andReturn().response.getContentAsString(StandardCharsets.UTF_8),
          mapper.getTypeFactory().constructType(String.class))
      value
    }

    List<ReportDto> findReports() {
      ResultActions perform = mvc.perform(MockMvcRequestBuilders.get("/api/report/all"))
      checkResponse(perform.andReturn().response)
      List<ReportDto> value = mapper.readValue(perform.andReturn().response.getContentAsString(StandardCharsets.UTF_8),
          mapper.getTypeFactory().constructCollectionType(List.class, ReportDto.class))
      value
    }

    void cleanup() {
      ResultActions perform = mvc.perform(MockMvcRequestBuilders.get("/api/report/cleanup"))
      checkResponse(perform.andReturn().response)
    }
  }

  class SportEventApi {
    private final MockMvc mvc
    private final ObjectMapper mapper

    SportEventApi(MockMvc mvc, ObjectMapper mapper) {
      this.mvc = mvc
      this.mapper = mapper
    }

    SportEventAddressDto createSportEventAddress(CreateSportEventAddressDto eventAddress) {
      ResultActions perform = mvc.perform(MockMvcRequestBuilders.post("/api/sportEvent/address/create")
          .contentType(MediaType.APPLICATION_JSON)
          .content(mapper.writeValueAsString(eventAddress))
      )
      checkResponse(perform.andReturn().response)
      SportEventAddressDto value = mapper.readValue(perform.andReturn().response.getContentAsString(StandardCharsets.UTF_8),
          mapper.getTypeFactory().constructType(SportEventAddressDto.class))
      value
    }

    SportEventDto createSportEvent(CreateSportEventDto sportEvent) {
      ResultActions perform = mvc.perform(MockMvcRequestBuilders.post("/api/sportEvent/create")
          .contentType(MediaType.APPLICATION_JSON)
          .content(mapper.writeValueAsString(sportEvent))
      )
      checkResponse(perform.andReturn().response)
      SportEventDto value = mapper.readValue(perform.andReturn().response.getContentAsString(StandardCharsets.UTF_8),
          mapper.getTypeFactory().constructType(SportEventDto.class))
      value
    }

    List<SportEventDto> getAllSportEvents() {
      ResultActions perform = mvc.perform(MockMvcRequestBuilders.get("/api/sportEvent/all"))
      checkResponse(perform.andReturn().response)
      List<SportEventDto> value = mapper.readValue(perform.andReturn().response.getContentAsString(StandardCharsets.UTF_8),
          mapper.getTypeFactory().constructCollectionType(List.class, SportEventDto.class))
      value
    }

    List<SportEventAddressDto> getAllSportEventsAddresses() {
      ResultActions perform = mvc.perform(MockMvcRequestBuilders.get("/api/sportEvent/address/all"))
      checkResponse(perform.andReturn().response)
      List<SportEventAddressDto> value = mapper.readValue(perform.andReturn().response.getContentAsString(StandardCharsets.UTF_8),
          mapper.getTypeFactory().constructCollectionType(List.class, SportEventAddressDto.class))
      value
    }

    void cleanup() {
      ResultActions perform = mvc.perform(MockMvcRequestBuilders.get("/api/sportEvent/cleanup"))
      checkResponse(perform.andReturn().response)
    }
  }

  private static void checkResponse(MockHttpServletResponse response) {
    if(response.status != 200) {
      throw new RuntimeException(response.getIncludedUrl() + " failed with status " + response.status)
    }
  }
}
