package br.unisantos.filmes.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "tb_ator")
@Entity
@NamedQueries({
	@NamedQuery(name = "Ator.listarTodos",
			query = "select a from Ator a order by a.nome"),
	@NamedQuery(name = "Ator.listarPorFilme",
			query = "select a from Filme f join f.atores a where f.id = ?1"),
	@NamedQuery(name = "Ator.listarPorNacionalidade",
			query = "select a from Ator a where a.nacionalidade.id = ?1"),
	@NamedQuery(name = "Ator.numeroPorNacionalidade",
			query = "select count(a) from Ator a where a.nacionalidade.id = ?1")
})
public class Ator extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "nm_nome", length = 80)
	private String nome;
	@Column(name = "dt_nascimento")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date nascimento;
	
	@ManyToOne
	private Nacionalidade nacionalidade;

	public Ator() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public Nacionalidade getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(Nacionalidade nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
}
