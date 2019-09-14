package fiap.scj.modulo1;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import fiap.scj.modulo1.interfaces.client.ProductResourceClient;
import lombok.Data;

public class ProductsClient {
    public static final ProductClientBuilder builder = new ProductClientBuilder();

    @Data
    public static class ProductClientBuilder {

        public static String DEFAULT_URL = "http://localhost:8080";
        private String url = DEFAULT_URL;

        ProductClientBuilder() {
        }

        public ProductClientBuilder setUrl(String url) {
            this.url = url;
            return this;
        }

        public ProductResourceClient build() {
            return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Logger.ErrorLogger())
                .logLevel(Logger.Level.FULL)
                .target(ProductResourceClient.class, this.url);
        }


    }
}
