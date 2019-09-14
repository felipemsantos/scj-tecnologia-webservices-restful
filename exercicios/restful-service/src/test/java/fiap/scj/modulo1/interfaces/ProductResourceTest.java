package fiap.scj.modulo1.interfaces;


import com.fasterxml.jackson.databind.ObjectMapper;
import fiap.scj.modulo1.application.ProductService;
import fiap.scj.modulo1.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductResource.class, secure = false)
//@AutoConfigureMockMvc
//@AutoConfigureTestDatabase
public class ProductResourceTest {

    Product mockProduct = new Product(9999l, "Gramepador", "Grapeia até 20 folhas", 19.90d);

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService service;

    @Before
    public void setup() throws Exception {
        when(service.search(any()))
                .thenReturn(Arrays.asList(mockProduct));
        when(service.create(any(Product.class)))
                .thenReturn(mockProduct);
        when(service.retrieve(anyLong()))
                .thenReturn(mockProduct);
        when(service.update(anyLong(), any(Product.class)))
                .thenReturn(mockProduct);
        doAnswer((i) -> {
            assertNotNull(i.getArgument(0));
            assertTrue(((Long)i.getArgument(0)) > 0);
            return null;
        })
                .when(service)
                .delete(anyLong());

    }


    @Test
    public void testSearchProductsWithoutKeyword() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/products")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = objectMapper.writeValueAsString(Arrays.asList(mockProduct));

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testSearchProductsWithKeyword() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/products?keyword=gramp")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = objectMapper.writeValueAsString(Arrays.asList(mockProduct));

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testCreate() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/products")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(mockProduct))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("http://localhost/products/9999",
                response.getHeader(HttpHeaders.LOCATION));

    }

    @Test
    public void testRetrieve() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/products/9999")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.FOUND.value(), response.getStatus());

        String expected = objectMapper.writeValueAsString(mockProduct);

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

    @Test
    public void testUpdate() throws Exception {
        Product product = mockProduct;
        product.setPrice(15.90d);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/products/9999")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(product))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.ACCEPTED.value(), response.getStatus());

        String expected = objectMapper.writeValueAsString(product);

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

    @Test
    public void testDelete() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/products/9999")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.ACCEPTED.value(), response.getStatus());

    }
}
