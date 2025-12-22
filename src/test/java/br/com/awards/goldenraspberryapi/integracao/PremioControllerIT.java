package br.com.awards.goldenraspberryapi.integracao;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PremioControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveRetornarIntervalosDePremiosConformeContrato() throws Exception {
    	
    	mockMvc.perform(get("/api/premios/intervalos"))
    		.andExpect(status().isOk())

            .andExpect(jsonPath("$.min").isArray())
            .andExpect(jsonPath("$.max").isArray())

            .andExpect(jsonPath("$.min[0].producer").exists())
            .andExpect(jsonPath("$.min[0].interval").exists())
            .andExpect(jsonPath("$.min[0].previousWin").exists())
            .andExpect(jsonPath("$.min[0].followingWin").exists())

            .andExpect(jsonPath("$.max[0].producer").exists())
            .andExpect(jsonPath("$.max[0].interval").exists())
            .andExpect(jsonPath("$.max[0].previousWin").exists())
            .andExpect(jsonPath("$.max[0].followingWin").exists());
    	
    }
}