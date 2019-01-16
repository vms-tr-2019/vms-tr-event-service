
package vms.vmsevents.dto;

import java.time.LocalDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode
public class RecordCurrentDTO {

  public String id;
  public LocalDate dateOpen;
  public int userId;
  public OperationStatusEnum type;

  public RecordCurrentDTO(OperationStatusEnum type, String id, LocalDate dateOpen, int userId) {
    super();
    this.id = id;
    this.dateOpen = dateOpen;
    this.userId = userId;
    this.type = type;
  }
}
