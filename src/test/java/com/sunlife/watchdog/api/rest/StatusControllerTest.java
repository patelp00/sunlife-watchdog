package com.sunlife.watchdog.api.rest;

import com.sunlife.watchdog.api.service.StatusService;
import com.sunlife.watchdog.api.domain.StatusResponse;

import java.util.List;
import org.springframework.http.HttpStatus;
import static org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class StatusControllerTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private MockMvc mockMvc;

    @Mock
    private StatusService statusService;

    @InjectMock
    private StatusController testSubject;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(testSubject).build();
    }

    @Test
    public void testGetStatus() throws Exception {
        when(statusService.getStatus(any())).thenReturn(StatusResponse.builder().build());
        MvcResult mvcResult = mockMvc.perform(get("http://localhost/v1/amazon-status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        StatusResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                StatusResponse.class);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());


    }

    @Test
    public void testGetStatusForAll() throws Exception {
        when(statusService.getStatus(any())).thenReturn(StatusResponse.builder().build());
        MvcResult mvcResult = mockMvc.perform(get("http://localhost/v1/all-status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        List<StatusResponse> response = (List<StatusResponse>) objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                List.class);
        Assert.assertNotNull(response);
        Assert.assertEquals(2, response.size());
        //TODO - validate more
    }

}
