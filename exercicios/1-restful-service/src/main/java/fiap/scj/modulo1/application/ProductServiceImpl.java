package fiap.scj.modulo1.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import fiap.scj.modulo1.domain.Product;
import fiap.scj.modulo1.domain.repository.ProductRepository;
import fiap.scj.modulo1.infrastructure.ProductServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static fiap.scj.modulo1.infrastructure.ProductServiceException.*;

@Service
@Transactional(propagation = Propagation.REQUIRED)
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    private final ObjectMapper objectMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Product> search(String keyword) throws ProductServiceException {
        log.info("Searching products for keyword={}", keyword);
        try {
            List<Product> result = Collections.emptyList();

            if (keyword == null || keyword.isEmpty()) {
                log.debug("No keyword specified, listing all products");
                repository.findAll().forEach(product -> result.add(product));
            } else {
                log.debug("Finding products by name or description");
                result.addAll(repository.findByNameOrDescriptionAllIgnoreCase(keyword, keyword));
            }

            return result;
        } catch (Exception e) {
            log.error("Error searching product", e);
            throw new ProductServiceException(SEARCH_OPERATION_ERROR, e);
        }
    }

    @Override
    public Product create(Product product) throws ProductServiceException {
        log.info("Creating product ({})", product);
        try {
            if (product == null) {
                log.error("Invalid product");
                throw new ProductServiceException(INVALID_PARAMETER_ERROR, null);
            }
            Product result = repository.save(product);
            return result;
        } catch (Exception e) {
            log.error("Error creating product", e);
            throw new ProductServiceException(CREATE_OPERATION_ERROR, e);
        }
    }

    @Override
    public Product retrieve(Long id) throws ProductServiceException {
        log.info("Retrieving product for id={}", id);
        try {
            if (id == null) {
                log.error("Invalid id");
                throw new ProductServiceException(INVALID_PARAMETER_ERROR, null);
            }
            Product result = repository.findById(id).get();
            return result;
        } catch (Exception e) {
            log.error("Error creating product", e);
            throw new ProductServiceException(RETRIEVE_OPERATION_ERROR, e);
        }
    }

    @Override
    public Product update(Long id, Product product) throws ProductServiceException {
        log.info("Updating product ({}) for id={}", product, id);
        try {
            if (id == null || product == null) {
                log.error("Invalid id or product");
                throw new ProductServiceException(INVALID_PARAMETER_ERROR, null);
            }
            if (!repository.existsById(id)) {
                log.debug("Product not found for id={}", id);
                return null;
            }
            Product result = repository.save(product);
            return result;
        } catch (Exception e) {
            log.error("Error creating product", e);
            throw new ProductServiceException(RETRIEVE_OPERATION_ERROR, e);
        }
    }

    @Override
    public void delete(Long id) throws ProductServiceException {
        log.info("Deleting product for id={}", id);
        try {
            if (id == null) {
                log.error("Invalid id or product");
                throw new ProductServiceException(INVALID_PARAMETER_ERROR, null);
            }
            if (!repository.existsById(id)) {
                log.debug("Product not found for id={}", id);
                throw new ProductServiceException(PRODUCT_NOT_FOUND_ERROR, null);
            }
            repository.deleteById(id);
        } catch (Exception e) {
            log.error("Error creating product", e);
            throw new ProductServiceException(DELETE_OPERATION_ERROR, e);
        }
    }
}
