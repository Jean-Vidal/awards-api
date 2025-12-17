package br.com.awards.goldenraspberryapi.integracao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.awards.goldenraspberryapi.dto.ResultadoIntervaloDTO;
import br.com.awards.goldenraspberryapi.service.CalculoService;

@SpringBootTest
@ActiveProfiles("test")
public class CalculoServiceIT {
	
	@Autowired
    private CalculoService calculoService;

    @Test
    void deveCalcularIntervalosCorretamente() {
        ResultadoIntervaloDTO resultado = calculoService.calcularIntervalos();

        assertNotNull(resultado);
        assertFalse(resultado.min().isEmpty());
        assertFalse(resultado.max().isEmpty());
    }

}