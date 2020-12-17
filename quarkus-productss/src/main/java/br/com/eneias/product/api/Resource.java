package br.com.eneias.product.api;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.eneias.product.model.Product;
import br.com.eneias.product.repository.ProductRepository;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class Resource {

	@Inject
	ProductRepository productRepository;

	@GET
	public List<Product> list() {
		return productRepository.listAll();
	}

	@POST
	public Response create(@Valid Product product) {
		Product productEntity = productRepository.save(product);
		return Response.ok(productEntity).status(Response.Status.CREATED).build();
	}

	@PUT
	@Path("{id}")
	public Response update(@PathParam("id") Long id, Product product) {
		Product productUpdated = productRepository.update(id, product);

		return Response.ok(productUpdated).build();
	}

	@DELETE
	@Path("{id}")
	public Response remove(@PathParam("id") Long id) {
		productRepository.remove(id);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
}