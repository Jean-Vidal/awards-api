package br.com.awards.goldenraspberryapi.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.awards.goldenraspberryapi.domain.Filme;
import br.com.awards.goldenraspberryapi.domain.Produtor;
import br.com.awards.goldenraspberryapi.dto.IntervaloPremioDTO;
import br.com.awards.goldenraspberryapi.dto.ResultadoIntervaloDTO;
import br.com.awards.goldenraspberryapi.repository.FilmeRepository;

@Service
public class CalculoService {

    private final FilmeRepository filmeRepository;

    public CalculoService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public ResultadoIntervaloDTO calcularIntervalos() {

        List<Filme> vencedores = filmeRepository.findByGanhadorTrue();

        Map<String, List<Integer>> premiosPorProdutor = new HashMap<>();

        for (Filme filme : vencedores) {
            for (Produtor produtor : filme.getProdutores()) {
                premiosPorProdutor
                    .computeIfAbsent(produtor.getNome(), k -> new ArrayList<>())
                    .add(filme.getAno());
            }
        }

        List<IntervaloPremioDTO> intervalos = new ArrayList<>();

        for (Map.Entry<String, List<Integer>> entry : premiosPorProdutor.entrySet()) {

            List<Integer> anos = entry.getValue();
            if (anos.size() < 2) continue;

            Collections.sort(anos);

            for (int i = 1; i < anos.size(); i++) {
                intervalos.add(new IntervaloPremioDTO(
                    entry.getKey(),
                    anos.get(i) - anos.get(i - 1),
                    anos.get(i - 1),
                    anos.get(i)
                ));
            }
        }

        int min = intervalos.stream()
                .mapToInt(IntervaloPremioDTO::interval)
                .min()
                .orElse(0);

        int max = intervalos.stream()
                .mapToInt(IntervaloPremioDTO::interval)
                .max()
                .orElse(0);

        List<IntervaloPremioDTO> minList = intervalos.stream()
                .filter(i -> i.interval() == min)
                .toList();

        List<IntervaloPremioDTO> maxList = intervalos.stream()
                .filter(i -> i.interval() == max)
                .toList();

        return new ResultadoIntervaloDTO(minList, maxList);
    }
    
}
