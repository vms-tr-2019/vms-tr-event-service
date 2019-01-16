package vms.vmsevents.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.kafka.common.errors.ControllerMovedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import vms.vmsevents.dto.CompleteRecord;
import vms.vmsevents.dto.OperationStatusEnum;
import vms.vmsevents.dto.RecordArchiveDTO;
import vms.vmsevents.dto.RecordCurrentDTO;
import vms.vmsevents.jpa.MFRecordCurrentJPA;
import vms.vmsevents.jpa.MTRecordCurrentJPA;
import vms.vmsevents.jpa.MaintenanceRecordArchiveJPA;
import vms.vmsevents.jpa.MalfunctionRecordArchiveJPA;
import vms.vmsevents.repository.MaintenanceArchiveRepository;
import vms.vmsevents.repository.MaintenanceCurrentRepository;
import vms.vmsevents.repository.MalfunctionArchiveRepository;
import vms.vmsevents.repository.MalfunctionCurrentRepository;

@Service
public class RecordsService implements IRecords {
	
	@Autowired
	MaintenanceCurrentRepository curMaintenance;
	
	@Autowired
	MalfunctionCurrentRepository curMalfunction;
	
	@Autowired
	MaintenanceArchiveRepository archiveMan;
	
	@Autowired
	MalfunctionArchiveRepository archiveMal;

	@Override
	public List<RecordCurrentDTO> getAllCurrentRecord() {
		List<RecordCurrentDTO> listRecord = new ArrayList<>();
		listRecord.addAll(curMaintenance.findAll().stream().map(MTRecordCurrentJPA::convertJPAtoDTO).
				collect(Collectors.toList()));
		listRecord.addAll(curMalfunction.findAll().stream().map(MFRecordCurrentJPA::convertJPAtoDTO).
				collect(Collectors.toList()));
		return listRecord;
	}

	@Override
	public RecordCurrentDTO getCurrentMTRecord(String recordId) {
		System.out.println("recordId = " + recordId);
		MTRecordCurrentJPA jpa =  curMaintenance.findById(recordId).orElse(null);
		if(jpa == null) {return null;}
		return jpa.convertJPAtoDTO();
	}

	@Override
	public RecordCurrentDTO getCurrentMFRecord(String recordId) {
		MFRecordCurrentJPA jpa = curMalfunction.findById(recordId).orElse(null);
		if(jpa == null) {return null;}
		return jpa.convertJPAtoDTO();
	}

	
	@Override
	public List<RecordArchiveDTO> getArchiveMalFunctionRecord(LocalDate from, LocalDate to) {
		 return archiveMal.findByDateCloseBetween(from,to).stream().
				 map(MalfunctionRecordArchiveJPA::convertJPAtoDTO).collect(Collectors.toList());
	}

	@Override
	public List<RecordArchiveDTO> getArchiveMaintenanceRecord(LocalDate from, LocalDate to) {
		return archiveMan.findByDateCloseBetween(from,to).stream().map(MaintenanceRecordArchiveJPA::convertJPAtoDTO).
				collect(Collectors.toList());
	}

	@Override
	public List<RecordArchiveDTO> getAllArchiveRecord(LocalDate from, LocalDate to) {
		List<RecordArchiveDTO> listRecords = new ArrayList<>();
		listRecords.addAll(archiveMal.findByDateCloseBetween(from,to).stream().
				 map(MalfunctionRecordArchiveJPA::convertJPAtoDTO).collect(Collectors.toList()));
		listRecords.addAll(archiveMan.findByDateCloseBetween(from,to).stream().map(MaintenanceRecordArchiveJPA::convertJPAtoDTO).
				collect(Collectors.toList()));
		return listRecords;
	}

	@Override
	public List<RecordCurrentDTO> getCurrentRecordsByMachine(int machineId) {
		List<RecordCurrentDTO> listRecord = new ArrayList<>();
		listRecord.addAll(curMaintenance.findByMachineId(machineId).stream().map(MTRecordCurrentJPA::convertJPAtoDTO).
				collect(Collectors.toList()));
		listRecord.addAll(curMalfunction.findByMachineId(machineId).stream().map(MFRecordCurrentJPA::convertJPAtoDTO).
				collect(Collectors.toList()));
		return listRecord;

	}
	
	@Override
	@Transactional
	public OperationStatusEnum completeRecord(CompleteRecord complete) {
		RestTemplate rest = new RestTemplate();
		String url = "http://localhost:53882/complete/" + complete.getMachineId();
//		String url = "http://localhost:8080/user/test/complete/" + machineId;
		rest.exchange(url, HttpMethod.GET, null, Boolean.class);
		
		completeMalfunctionRecord(complete);
		completeMaintenanceRecord(complete);
		
		return OperationStatusEnum.OK;
	}

	private void completeMaintenanceRecord(CompleteRecord complete) {
		curMaintenance.findByMachineId(complete.getMachineId()).stream().forEach(x ->{
			MaintenanceRecordArchiveJPA jpa = new MaintenanceRecordArchiveJPA(x.getDateOpen(),
					x.getMachineId(), x.getSensorId(), complete.getUserId(), complete.getComment());
			curMaintenance.deleteById(x.getId());
			archiveMan.save(jpa);
		});;
	}

	private void completeMalfunctionRecord(CompleteRecord complete) {
		curMalfunction.findByMachineId(complete.getMachineId()).stream().forEach(rec->{
			MalfunctionRecordArchiveJPA jpa = new MalfunctionRecordArchiveJPA(rec.getDateOpen(),
					rec.getMachineId(), rec.getSensorId(), complete.getUserId(), complete.getComment());
			curMalfunction.deleteById(rec.getId());
			archiveMal.save(jpa);
		});;
		
		
	}

	
	@Override
	@Transactional
	public OperationStatusEnum assignTechnicianMF(String recordId, int workerId) {
		MFRecordCurrentJPA record = curMalfunction.findById(recordId).orElse(null);
		if(record == null) return OperationStatusEnum.NOT_EXISTS;
		record.setUserId(workerId);
		return OperationStatusEnum.OK;
	}

	@Override
	@Transactional
	public OperationStatusEnum assignTechnicianMT(String recordId, int workerId) {
		MTRecordCurrentJPA record = curMaintenance.findById(recordId).orElse(null);
		if(record == null) return OperationStatusEnum.NOT_EXISTS;
		record.setUserId(workerId);
		return OperationStatusEnum.OK;
	}
	
	
	public void saveMTReord( MTRecordCurrentJPA jpa) {
		System.out.println("save  MTRecordCurrentJPA = " + jpa);
		curMaintenance.save(jpa);
	}
	
	public void saveMFReord( MFRecordCurrentJPA jpa) {
		System.out.println("save  MFRecordCurrentJPA = " + jpa);
		curMalfunction.save(jpa);
	}
	

	

	
}
