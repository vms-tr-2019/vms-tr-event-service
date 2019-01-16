package vms.vmsevents.jpa;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vms.vmsevents.dto.ProductDTO;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "products", indexes = {
  @Index(name = "product_name", columnList= "product_name")
})
public class ProductJPA {

  @Id
  @GeneratedValue
  public int productId;

  @Column(name = "product_name", unique = true)
  public String name;

  public int price;

  public boolean available = true;

  public ProductJPA(int productId, String name, int price, boolean available) {
    super();
    this.productId = productId;
    this.name = name;
    this.price = price;
    this.available = available;
  }

  public ProductDTO toProductDTO() {
    return new ProductDTO(this.productId, this.name, this.price, this.available);
  }
}
