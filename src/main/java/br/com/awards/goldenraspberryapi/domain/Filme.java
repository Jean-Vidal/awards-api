package br.com.awards.goldenraspberryapi.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "filmes")
public class Filme {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ano;

    private String titulo;

    private boolean ganhador;
    
    public Filme() {}
    
    public Filme(Long id, Integer ano, String titulo, boolean ganhador, Set<Produtor> produtores, Set<Studio> studios) {
		this.id = id;
		this.ano = ano;
		this.titulo = titulo;
		this.ganhador = ganhador;
		this.produtores = produtores;
		this.studios = studios;
	}

	@ManyToMany
    @JoinTable(
        name = "filme_produtor",
        joinColumns = @JoinColumn(name = "filme_id"),
        inverseJoinColumns = @JoinColumn(name = "produtor_id")
    )
    private Set<Produtor> produtores = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "filme_studio",
        joinColumns = @JoinColumn(name = "filme_id"),
        inverseJoinColumns = @JoinColumn(name = "studio_id")
    )
    private Set<Studio> studios = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public boolean isGanhador() {
		return ganhador;
	}

	public void setGanhador(boolean ganhador) {
		this.ganhador = ganhador;
	}

	public Set<Produtor> getProdutores() {
		return produtores;
	}

	public void setProdutores(Set<Produtor> produtores) {
		this.produtores = produtores;
	}

	public Set<Studio> getStudios() {
		return studios;
	}

	public void setStudios(Set<Studio> studios) {
		this.studios = studios;
	}
}