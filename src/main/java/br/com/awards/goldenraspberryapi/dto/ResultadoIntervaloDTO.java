package br.com.awards.goldenraspberryapi.dto;

import java.util.List;

public record ResultadoIntervaloDTO(
	List<IntervaloPremioDTO> min,
	List<IntervaloPremioDTO> max
) {}