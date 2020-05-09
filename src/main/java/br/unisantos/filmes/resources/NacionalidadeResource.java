package br.unisantos.filmes.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.unisantos.filmes.model.Nacionalidade;
import br.unisantos.filmes.model.dao.DAO;

@Path("/nacionalidades")
public class NacionalidadeResource {

	@GET
	@Produces("application/json")
	public Response getAll() {
		DAO<Nacionalidade> dao = new DAO<>(Nacionalidade.class);
		List<Nacionalidade> nacionalidades = dao.listarGenerico("Nacionalidade.listarTodas");

		return Response.ok(nacionalidades).build();
	}

	@Path("/{letra}")
	@GET
	@Produces("application/json")
	public Response getByLetra(@PathParam("letra") String letra) {
		DAO<Nacionalidade> dao = new DAO<>(Nacionalidade.class);
		List<Nacionalidade> nacionalidades = dao.listarGenerico("Nacionalidade.listarPorLetra", letra + '%');
		return Response.ok(nacionalidades).build();
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response add(Nacionalidade nacionalidade) {
		DAO<Nacionalidade> dao = new DAO<>(Nacionalidade.class);
		dao.adicionar(nacionalidade);
		return Response.ok(nacionalidade).build();
	}

	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	public Response update(Nacionalidade nacionalidade) {
		DAO<Nacionalidade> dao = new DAO<>(Nacionalidade.class);
		dao.alterar(nacionalidade);
		return Response.ok(nacionalidade).build();
	}

	@Path("/{id}")
	@DELETE
	@Produces("application/json")
	public Response delete(@PathParam("id") Long id) {
		DAO<Nacionalidade> dao = new DAO<>(Nacionalidade.class);
		if (dao.excluir(id)) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
