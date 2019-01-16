package vms.vmsevents.jpa;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "machine_sensor_product")
public class MachineProductSensorJPA {

  @Id
  @GeneratedValue
  String id;

  @ManyToOne
  @JoinColumn(name = "machine_id")
  MachineJPA machine;

  @ManyToOne
  @JoinColumn(name = "product_name")
  ProductJPA product;

  int sensorId;

  public MachineProductSensorJPA(MachineJPA machine, ProductJPA product, int sensorId) {
    this.machine = machine;
    this.product = product;
    this.sensorId = sensorId;
  }
}