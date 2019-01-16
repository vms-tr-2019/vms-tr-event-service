package vms.vmsevents.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode
public class CompleteRecord {
	
	int machineId;
	String comment;
	int userId;
	
	
	
	public CompleteRecord(int machineId, String comment, int userId) {
		super();
		this.machineId = machineId;
		this.comment = comment;
		this.userId = userId;
	}
	
}
