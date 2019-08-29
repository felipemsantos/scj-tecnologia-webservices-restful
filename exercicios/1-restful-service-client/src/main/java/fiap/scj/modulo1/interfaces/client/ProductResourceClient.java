package fiap.scj.modulo1.interfaces.client;

import fiap.scj.modulo1.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(value = "products", url = "http://localhost:8080")
public interface ProductResourceClient {

    @RequestMapping(method = RequestMethod.GET, path = "/products")
    List<Product> search(@RequestParam("keyword") String keyword);

}
