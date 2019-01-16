package vms.vmsevents.service;

import java.time.LocalDate;
import java.util.List;

import vms.vmsevents.dto.CompleteRecord;
import vms.vmsevents.dto.OperationStatusEnum;
import vms.vmsevents.dto.RecordArchiveDTO;
import vms.vmsevents.dto.RecordCurrentDTO;



public interface IRecords {

  public RecordCurrentDTO getCurrentMTRecord(String recordId);

  public RecordCurrentDTO getCurrentMFRecord(String recordId);
  
  public List<RecordCurrentDTO> getAllCurrentRecord();

  public List<RecordArchiveDTO> getArchiveMalFunctionRecord(LocalDate from, LocalDate to);

  public List<RecordArchiveDTO> getArchiveMaintenanceRecord(LocalDate from, LocalDate to);

  public List<RecordArchiveDTO> getAllArchiveRecord(LocalDate from, LocalDate to);

  public List<RecordCurrentDTO> getCurrentRecordsByMachine(int machineId);

  public OperationStatusEnum completeRecord(CompleteRecord complete);
  
  public OperationStatusEnum assignTechnicianMF(String recordId, int workerId);
  
  public OperationStatusEnum assignTechnicianMT(String recordId, int workerId);
 

}
