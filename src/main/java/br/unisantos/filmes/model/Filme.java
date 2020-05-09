package br.unisantos.filmes.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "tb_filme")
@Entity
@NamedQueries({
	@NamedQuery(name = "Filme.listarTodos",
			query = "select f from Filme f order by f.ano"),
	@NamedQuery(name = "Filme.listarPorAtor",
			query = "select f from Filme f join f.atores a on a.id = ?1"),
	@NamedQuery(name = "Filme.listarPorAno",
			query = "select f from Filme f where f.ano = ?1"),
	@NamedQuery(name = "Filme.numeroPorPeriodo",
			query = "select count(f) from Filme f where f.ano between ?1 and ?2")
})
public class Filme extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "nm_titulo", length = 120)
	private String titulo;
	@Column(name = "nr_ano")
	private Integer ano;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Ator> atores;

	public Filme() {
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public List<Ator> getAtores() {
		return atores;
	}

	public void setAtores(List<Ator> atores) {
		this.atores = atores;
	}	

}
