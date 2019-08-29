package fiap.scj.modulo1.interfaces.client;

import fiap.scj.modulo1.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductResourceClientTest {

    @Autowired
    private ProductResourceClient client;

    @Test
    public void testSearchProducts() {

        List<Product> products = client.search(null);

        assertFalse(products.isEmpty());
    }

}