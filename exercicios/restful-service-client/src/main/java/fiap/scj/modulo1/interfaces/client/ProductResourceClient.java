package fiap.scj.modulo1.interfaces.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;
import fiap.scj.modulo1.domain.Product;

import java.util.List;

@Headers({"Content-Type: application/json", "Accept: application/json"})
public interface ProductResourceClient {

    @RequestLine("GET /products")
    List<Product> search();

    @RequestLine("GET /products?keyword={keyword}")
    List<Product> search(@Param("keyword") String keyword);

    @RequestLine("POST /products")
    Response create(Product product);

    @RequestLine("GET /products/{id}")
    Product retrieve(@Param("id") Long id);

    @RequestLine("PUT /products/{id}")
    Product update(@Param("id") Long id, Product product);

    @RequestLine("DELETE /products/{id}")
    Response delete(@Param("id") Long id);

}
