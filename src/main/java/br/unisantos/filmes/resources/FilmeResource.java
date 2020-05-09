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

import br.unisantos.filmes.model.Filme;
import br.unisantos.filmes.model.dao.DAO;
import br.unisantos.filmes.util.jpa.JPAEntityManager;

@Path("/filmes")
public class FilmeResource {

	@GET
	@Produces("application/json")
	public Response getAll() {
		DAO<Filme> dao = new DAO<>(Filme.class);
		List<Filme> filmes = dao.listarGenerico("Filme.listarTodos");

		return Response.ok(filmes).build();
	}


	@Path("/ator/{ator_id}")
	@GET
	@Produces("application/json")
	public Response getByAtor(@PathParam("ator_id") Long atorId) {
		DAO<Filme> dao = new DAO<>(Filme.class);
		List<Filme> filmes = dao.listarGenerico("Filme.listarPorAtor", atorId);
		return Response.ok(filmes).build();
	}

	@Path("/ano/{ano}")
	@GET
	@Produces("application/json")
	public Response getByName(@PathParam("ano") Integer ano) {
		DAO<Filme> dao = new DAO<>(Filme.class);
		List<Filme> filmes = dao.listarGenerico("Filme.listarPorAno", ano);
		return Response.ok(filmes).build();
	}

	@Path("/periodo/{from}/{to}")
	@GET
	@Produces("application/json")
	public Response getByPeriodo(@PathParam("from") Integer from,
			@PathParam("to") Integer to) {
		EntityManager manager = JPAEntityManager.getEntityManager();
		TypedQuery<Long> query = manager.createNamedQuery("Filme.numeroPorPeriodo", Long.class);
		query.setParameter(1, from);
		query.setParameter(2, to);
		Long qtde = query.getSingleResult();
		return Response.ok(qtde).build();
	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response add(Filme filme) {
		DAO<Filme> dao = new DAO<>(Filme.class);
		dao.adicionar(filme);
		return Response.ok(filme).build();
	}

	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	public Response update(Filme filme) {
		DAO<Filme> dao = new DAO<>(Filme.class);
		dao.alterar(filme);
		return Response.ok(filme).build();
	}

	@Path("/{id}")
	@DELETE
	@Produces("application/json")
	public Response delete(@PathParam("id") Long id) {
		DAO<Filme> dao = new DAO<>(Filme.class);
		if (dao.excluir(id)) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
