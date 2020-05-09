package br.unisantos.filmes.resources;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.unisantos.filmes.model.Ator;
import br.unisantos.filmes.model.dao.DAO;
import br.unisantos.filmes.util.jpa.JPAEntityManager;

@Path("/atores")
public class AtorResource {

	@GET
	@Produces("application/json")
	public Response getAll() {
		DAO<Ator> dao = new DAO<>(Ator.class);
		List<Ator> ators = dao.listarGenerico("Ator.listarTodos");

		return Response.ok(ators).build();
	}

	@Path("/{filme_id}")
	@GET
	@Produces("application/json")
	public Response getByLetra(@PathParam("filme_id") Long filmeId) {
		DAO<Ator> dao = new DAO<>(Ator.class);
		List<Ator> ators = dao.listarGenerico("Ator.listarPorFilme", filmeId);
		return Response.ok(ators).build();
	}

	@Path("/nacionalidade/{nacionalidade_id}")
	@GET
	@Produces("application/json")
	public Response getByAtor(@PathParam("nacionalidade_id") Long nacionalidadeId) {
		DAO<Ator> dao = new DAO<>(Ator.class);
		List<Ator> atores = dao.listarGenerico("Ator.listarPorNacionalidade", nacionalidadeId);
		return Response.ok(atores).build();
	}

	@Path("/numero/{nacionalidade_id}")
	@GET
	@Produces("application/json")
	public Response getByNumero(@PathParam("nacionalidade_id") Long nacionalidadeId) {
		EntityManager manager = JPAEntityManager.getEntityManager();
		TypedQuery<Long> query = manager.createNamedQuery("Ator.numeroPorNacionalidade", Long.class);
		query.setParameter(1, nacionalidadeId);
		Long qtde = query.getSingleResult();
		return Response.ok(qtde).build();
	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response add(Ator ator) {
		DAO<Ator> dao = new DAO<>(Ator.class);
		dao.adicionar(ator);
		return Response.ok(ator).build();
	}

	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	public Response update(Ator ator) {
		DAO<Ator> dao = new DAO<>(Ator.class);
		dao.alterar(ator);
		return Response.ok(ator).build();
	}

	@Path("/{id}")
	@DELETE
	@Produces("application/json")
	public Response delete(@PathParam("id") Long id) {
		DAO<Ator> dao = new DAO<>(Ator.class);
		if (dao.excluir(id)) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
