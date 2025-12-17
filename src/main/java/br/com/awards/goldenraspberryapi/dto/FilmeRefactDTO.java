package br.com.awards.goldenraspberryapi.dto;

import java.util.List;

public record FilmeRefactDTO(
	Integer ano,
	String titulo,
	List<String> studios,
	List<String> produtores,
	boolean ganhador
) {}