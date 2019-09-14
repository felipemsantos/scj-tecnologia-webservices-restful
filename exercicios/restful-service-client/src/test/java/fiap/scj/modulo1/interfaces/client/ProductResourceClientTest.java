package fiap.scj.modulo1.interfaces.client;

import feign.Response;
import fiap.scj.modulo1.ProductsClient;
import fiap.scj.modulo1.domain.Product;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.JVM)
public class ProductResourceClientTest {

    private ProductResourceClient products;
    private Product mockProduct = new Product(99l, "Grampeador", "Grampeia até 20 folhas", 19.90d);


    @Before
    public void setup() {
        products = ProductsClient.builder.build();
    }

    @Test
    public void test1Search() throws Exception {
        List<Product> result = products.search();

        assertNotNull(result);

    }

    @Test
    public void test1SearchKeyword() throws Exception {
        List<Product> result = products.search("grampeador");

        assertNotNull(result);

    }

    @Test
    public void test2Create() throws Exception {
        Response response = products.create(mockProduct);
        String location = (String) response.headers().get("Location").toArray()[0];
        String[] aux = location.split("/");
        Long id = Long.valueOf(aux[aux.length - 1]);
        mockProduct.setId(id);
        assertNotNull(id);
    }

    @Test
    public void test3Retrieve() throws Exception {

        Product response = products.retrieve(102l);

        assertNotNull(response);

    }

    @Test
    public void test4Update() throws Exception {
        mockProduct.setPrice(mockProduct.getPrice() + 20);

        Product response = products.update(102l, mockProduct);

        assertNotNull(response);

    }

    @Test
    public void test5Delete() throws Exception {
        Response response = products.delete(104l);

        assertEquals(202, response.status());
    }

}
