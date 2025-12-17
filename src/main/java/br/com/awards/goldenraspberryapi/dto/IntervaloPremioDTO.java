package br.com.awards.goldenraspberryapi.dto;

public record IntervaloPremioDTO(
		String producer,
		int interval,
	    int previousWin,
	    int followingWin
) {}
