package fiap.scj.modulo1.application;

import fiap.scj.modulo1.domain.Product;
import fiap.scj.modulo1.infrastructure.ProductServiceException;

import java.io.Serializable;
import java.util.List;

public interface ProductService extends Serializable {

    List<Product> search(String keyword) throws ProductServiceException;

    Product create(Product product) throws ProductServiceException;

    Product retrieve(Long id) throws ProductServiceException;

    Product update(Long id, Product product) throws ProductServiceException;

    void delete(Long id) throws ProductServiceException;
}
