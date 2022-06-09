package org.synjones.springframework.repositories;
 
import org.springframework.data.repository.CrudRepository;
import org.synjones.springframework.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
}
