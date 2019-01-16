package vms.vmsevents.dto;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MFRecordCurrentDTO {

  @Id
  public String id;
  public LocalDate date;
  public int machineId;
  public int sensorId;
  public int value;
  public int userId;

  public MFRecordCurrentDTO(LocalDate date, int machineId, int sensorId, int value, int userId) {
    super();
    this.date = date;
    this.machineId = machineId;
    this.sensorId = sensorId;
    this.value = value;
    this.userId = userId;
  }
}
