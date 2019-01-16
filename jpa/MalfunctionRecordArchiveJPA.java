package vms.vmsevents.jpa;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vms.vmsevents.dto.OperationStatusEnum;
import vms.vmsevents.dto.PersistanceConstants;
import vms.vmsevents.dto.RecordArchiveDTO;

@Table(name = PersistanceConstants.ARCHIVE_MALFUNCTION_RECORDS_TABLE)
@Entity
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode
public class MalfunctionRecordArchiveJPA {

  @Id
  @GeneratedValue
  public int id;
  @Column(name="date_open")
  public LocalDate dateOpen;
  @Column(name="date_close")
  public LocalDate dateClose;
  @Column(name="machine_id")
  public int machineId;
  @Column(name="sensor_id")
  public int sensorId;
  @Column(name="user_id")
  public int userId;
  public String comment;
  
  
  public MalfunctionRecordArchiveJPA(LocalDate dateOpen, int machineId,
		int sensorId, int userId, String comment) {
	super();	
	this.dateOpen = dateOpen;
	this.dateClose = LocalDate.now();
	this.machineId = machineId;
	this.sensorId = sensorId;
	this.userId = userId;
	this.comment = comment;
  }

	public RecordArchiveDTO convertJPAtoDTO() {
		return new RecordArchiveDTO(dateOpen, dateClose, machineId, sensorId,
			userId, comment, OperationStatusEnum.MALFUNCTION);
	}

 

}
