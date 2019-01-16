package vms.vmsevents.dto;
import java.time.LocalDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode
public class RecordArchiveDTO {

  public int id;
  public LocalDate dateOpen;
  public LocalDate dateClose;
  public int machineId;
  public int sensorId;
  public int userId;
  public String comment;
  public OperationStatusEnum type;
  
  
public RecordArchiveDTO(LocalDate dateOpen, LocalDate dateClose, int machineId, int sensorId, int userId,
		String comment, OperationStatusEnum type) {
	super();
	this.dateOpen = dateOpen;
	this.dateClose = dateClose;
	this.machineId = machineId;
	this.sensorId = sensorId;
	this.userId = userId;
	this.comment = comment;
	this.type = type;
}

  
}
