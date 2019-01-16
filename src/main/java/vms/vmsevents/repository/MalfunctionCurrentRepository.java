package vms.vmsevents.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vms.vmsevents.jpa.MFRecordCurrentJPA;


public interface MalfunctionCurrentRepository extends JpaRepository<MFRecordCurrentJPA, String> {

	List<MFRecordCurrentJPA> findByMachineId(int machineId);

}
