package vms.vmsevents.controller;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vms.vmsevents.dto.ApiConstants;
import vms.vmsevents.dto.CompleteRecord;
import vms.vmsevents.dto.OperationStatusEnum;
import vms.vmsevents.dto.RecordArchiveDTO;
import vms.vmsevents.dto.RecordCurrentDTO;
import vms.vmsevents.jpa.MFRecordCurrentJPA;
import vms.vmsevents.jpa.MaintenanceRecordArchiveJPA;
import vms.vmsevents.jpa.MalfunctionRecordArchiveJPA;
import vms.vmsevents.service.IRecords;


@RestController
@RequestMapping(ApiConstants.RECORD)
public class RecordsController {
	
	@Autowired
	IRecords records;
	
	@GetMapping(ApiConstants.GET_ALL_CURRENT_RECORDS)
	public List<RecordCurrentDTO> getCurAllRecord() {
		return  records.getAllCurrentRecord();
	}
	
	@GetMapping(ApiConstants.GET_MT_RECORD_CURRENT + "/{recordId}")
	public RecordCurrentDTO getCurrentMTRecord(@PathVariable("recordId") String recordId) {
		return records.getCurrentMTRecord(recordId);
	}
	
	@GetMapping(ApiConstants.GET_MF_RECORD_CURRENT + "/{recordId}")
	public RecordCurrentDTO getCurrentMFRecord(@PathVariable("recordId") String recordId) {
		return records.getCurrentMFRecord(recordId);
	}
	
	@GetMapping(ApiConstants.GET_MF_RECORD_ARCHIVE)
	public List<RecordArchiveDTO> getArchiveMalFunctionRecord(@RequestParam(name = "from", defaultValue = "2010-01-01") String from,
			@RequestParam(name = "to", defaultValue = "now") String to){
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if(to.equals("now")) {
			return records.getArchiveMalFunctionRecord(LocalDate.parse(from, format), LocalDate.now());
		}
		return records.getArchiveMalFunctionRecord(LocalDate.parse(from, format), LocalDate.parse(to, format));
	}
	
	@GetMapping(ApiConstants.GET_MT_RECORD_ARCHIVE)
	public List<RecordArchiveDTO> getArchiveMaintenanceRecord(@RequestParam(name = "from", defaultValue = "2010-01-01") String from,
			@RequestParam(name = "to", defaultValue = "now") String to) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if(to.equals("now")) {
			return records.getArchiveMaintenanceRecord(LocalDate.parse(from, format), LocalDate.now());
		}
		return records.getArchiveMaintenanceRecord(LocalDate.parse(from, format), LocalDate.parse(to, format));
	}
	
	@GetMapping(ApiConstants.GET_ALL_ARCHIVE_RECORDS)
	public List<RecordArchiveDTO> getAllArchiveRecord(@RequestParam(name = "from", defaultValue = "2010-01-01") String from,
			@RequestParam(name = "to", defaultValue = "now") String to) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if(to.equals("now")) {
			return records.getAllArchiveRecord(LocalDate.parse(from, format), LocalDate.now());
		}
		return records.getAllArchiveRecord(LocalDate.parse(from, format), LocalDate.parse(to, format));
	}
	
	@GetMapping(ApiConstants.GET_CURRENT_RECORD_BY_MACHINE + "/{machineId}")
	public List<RecordCurrentDTO> getCurrentRecordsByMachine(@PathVariable("machineId") int machineId) {
		return records.getCurrentRecordsByMachine(machineId);
	}
	
	@PostMapping(ApiConstants.COMPLETE_RECORD)
	public OperationStatusEnum completeRecord(@RequestBody CompleteRecord complete) {
	
		return records.completeRecord(complete);
	}
	
	@GetMapping(ApiConstants.ASSIGN_TECHNICIAN_MF_CURRENT + "/{recordId}/{workerId}")
	public OperationStatusEnum assignTechnicianMF(@PathVariable("recordId") String recordId, 
			@PathVariable("workerId") int workerId) {
		return records.assignTechnicianMF(recordId, workerId);
	}
	
	@GetMapping(ApiConstants.ASSIGN_TECHNICIAN_MT_CURRENT + "/{recordId}/{workerId}")
	public OperationStatusEnum assignTechnicianMT(@PathVariable("recordId") String recordId, 
			@PathVariable("workerId") int workerId) {
		return records.assignTechnicianMT(recordId, workerId);
	}
	
	
	
	
	


}
