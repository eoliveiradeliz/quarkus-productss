package br.com.eneias.product.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import br.com.eneias.product.model.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.List;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

	public List<Product> list() {
		return listAll();
	}

	@Transactional
	public Product save(Product product) {
		persist(product);
		return product;
	}

	@Transactional
	public Product update(Long id, Product product) {
		Product productEntity = findById(id);

		productEntity.setName(product.getName());
		productEntity.setBrand(product.getBrand());
		productEntity.setPrice(product.getPrice());
		persist(productEntity);
		return productEntity;
	}

	@Transactional
	public void remove(Long id) {
		Product productEntity = findById(id);

		delete(productEntity);
	}
}
