package br.unisantos.filmes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "tb_nacionalidade")
@Entity
@NamedQueries({
	@NamedQuery(name = "Nacionalidade.listarTodas",
		query = "select n from Nacionalidade n order by n.pais"),
	@NamedQuery(name = "Nacionalidade.listarPorLetra",
		query = "select n from Nacionalidade n where n.pais like ?1")
})
public class Nacionalidade extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "nm_pais", length = 50)
	private String pais;

	public Nacionalidade() {
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
		
}
