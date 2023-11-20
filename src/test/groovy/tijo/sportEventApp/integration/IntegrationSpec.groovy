package tijo.sportEventApp.integration

import com.fasterxml.jackson.databind.ObjectMapper
import org.aspectj.lang.annotation.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification
import tijo.sportEventApp.utils.InstantProvider

@SpringBootTest
@RunWith(SpringRunner.class)
abstract class IntegrationSpec extends Specification {
  @Autowired
  protected WebApplicationContext context

  private MockMvc mockMvc

  private ObjectMapper objectMapper

  @Autowired
  InstantProvider instantProvider

  SportEventAppApi api

  @Before
  def setupMockMvc() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context)
      .apply(SecurityMockMvcConfigurers.springSecurity())
      .build()
    api = new SportEventAppApi(mockMvc, objectMapper)
  }
}
