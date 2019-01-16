package vms.vmsevents.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



import vms.vmsevents.jpa.MTRecordCurrentJPA;


public interface MaintenanceCurrentRepository extends JpaRepository<MTRecordCurrentJPA, String>{

	List<MTRecordCurrentJPA> findByMachineId(int machineId);
}
