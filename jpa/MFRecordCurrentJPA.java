package vms.vmsevents.jpa;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vms.vmsevents.dto.OperationStatusEnum;
import vms.vmsevents.dto.RecordCurrentDTO;




@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode
@Entity
@Table(name = "current_maintenance")
public class MFRecordCurrentJPA {
	
	@Id
	String id;
	@Column(name="date_open")
	LocalDate dateOpen;
	@Column(name="machine_id")
	int machineId;
	@Column(name="sensor_id")
	int sensorId;
	@Column(name="user_id")
	int userId;
	
	


	public MFRecordCurrentJPA(int machineId, int sensorId) {
		super();
		this.id = String.format("%d-%d", machineId, sensorId);
		this.dateOpen = LocalDate.now();
		this.machineId = machineId;
		this.sensorId = sensorId;
		this.userId = 0;
	}
	
public RecordCurrentDTO convertJPAtoDTO() {
		
		return new RecordCurrentDTO(OperationStatusEnum.MAINTENANCE, id, dateOpen, userId);
	}

	

}
