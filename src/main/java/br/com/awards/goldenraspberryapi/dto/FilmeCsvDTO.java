package br.com.awards.goldenraspberryapi.dto;

public record FilmeCsvDTO(
	String ano,
	String titulo,
	String studio,
	String produtor,
	String ganhador
){}