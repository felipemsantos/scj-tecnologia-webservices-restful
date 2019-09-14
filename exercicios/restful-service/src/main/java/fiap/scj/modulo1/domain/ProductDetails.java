package fiap.scj.modulo1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "products_details")
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String key;
    private String description;

    private Product product;

}
