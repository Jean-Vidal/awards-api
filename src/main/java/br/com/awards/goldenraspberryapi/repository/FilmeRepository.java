package br.com.awards.goldenraspberryapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.awards.goldenraspberryapi.domain.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long>{
	List<Filme> findByGanhadorTrue();
}