package br.com.awards.goldenraspberryapi.launcher;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.awards.goldenraspberryapi.domain.Filme;
import br.com.awards.goldenraspberryapi.domain.Produtor;
import br.com.awards.goldenraspberryapi.domain.Studio;
import br.com.awards.goldenraspberryapi.dto.FilmeCsvDTO;
import br.com.awards.goldenraspberryapi.dto.FilmeRefactDTO;
import br.com.awards.goldenraspberryapi.repository.FilmeRepository;
import br.com.awards.goldenraspberryapi.repository.ProdutorRepository;
import br.com.awards.goldenraspberryapi.repository.StudioRepository;

@Component
public class CsvData implements CommandLineRunner {
	
	@Autowired
	ProdutorRepository produtorRepository;
	
	@Autowired
	StudioRepository studioRepository;
	
	@Autowired
	FilmeRepository filmeRepository;
	
    @Override
    public void run(String... args) throws Exception {
    	
        InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("csv/Movielist.csv");

        if (inputStream == null) {
            throw new RuntimeException("CSV nao encontrado");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        reader.readLine();

        String line;
        while ((line = reader.readLine()) != null) {

        	while ((line = reader.readLine()) != null) {

        	    String[] columns = line.split(";");
        	    String winner = columns.length > 4 ? columns[4] : "no";

        	    FilmeCsvDTO csvDTO = new FilmeCsvDTO(
        	        columns[0],
        	        columns[1],
        	        columns[2],
        	        columns[3],
        	        winner
        	    );

        	    FilmeRefactDTO importDTO = trataDTOCru(csvDTO);

        	    Filme filme = new Filme();
        	    filme.setAno(importDTO.ano());
        	    filme.setTitulo(importDTO.titulo());
        	    filme.setGanhador(importDTO.ganhador());

        	    for (String produtorNome : importDTO.produtores()) {
        	        Produtor produtor = produtorRepository
        	            .findByNome(produtorNome)
        	            .orElseGet(() -> produtorRepository.save(new Produtor(produtorNome)));

        	        filme.getProdutores().add(produtor);
        	    }

        	    for (String studioNome : importDTO.studios()) {
        	        Studio studio = studioRepository
        	            .findByNome(studioNome)
        	            .orElseGet(() -> studioRepository.save(new Studio(studioNome)));

        	        filme.getStudios().add(studio);
        	    }

        	    filmeRepository.save(filme);
        	}

        }

        reader.close();
        System.out.println("Carga do CSV finalizada");
    }

	
	private List<String> separaProdutores(String produtores) {
	    return Arrays.stream(
	    		produtores
	                .replace(", and ", ",")
	                .replace(" and ", ",")
	                .split(",")
	        )
	        .map(String::trim)
	        .filter(p -> !p.isBlank())
	        .toList();
	}
	
	private List<String> separaStudios(String studios) {
	    return Arrays.stream(studios.split(","))
	        .map(String::trim)
	        .filter(s -> !s.isBlank())
	        .toList();
	}
	
	private boolean separaGanhadores(String ganhadores) {
	    return "yes".equalsIgnoreCase(ganhadores);
	}
	
	private FilmeRefactDTO trataDTOCru(FilmeCsvDTO dto) {

	    List<String> producers = separaProdutores(dto.produtor());
	    List<String> studios = separaStudios(dto.studio());
	    boolean winner = separaGanhadores(dto.ganhador());

	    return new FilmeRefactDTO(
	            Integer.valueOf(dto.ano()),
	            dto.titulo(),
	            studios,
	            producers,
	            winner
	    );
	}
}
