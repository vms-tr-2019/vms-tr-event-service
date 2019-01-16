package vms.vmsevents.dto;

public interface ApiConstants {

  String RECORD = "record";
  String GET_MF_RECORD_CURRENT = "/current/malfunction"; // GET
  String GET_MT_RECORD_CURRENT = "/current/maintenance"; // GET
  String GET_ALL_CURRENT_RECORDS = "/current/all"; //GET
  String GET_CURRENT_RECORD_BY_MACHINE = "/current/by_machine"; // GET
  String GET_MF_RECORD_ARCHIVE = "/archive/malfunction"; // GET
  String GET_MT_RECORD_ARCHIVE = "/archive/maintenance"; // GET
  String GET_ALL_ARCHIVE_RECORDS = "/archive/all"; //GET
  String GET_ARCHIVE_RECORD_BY_MACHINE = "/archive/by_machine"; // GET
  String RECORD_ARCHIVE_BY_PERIOD = "/archive/by_period"; // GET
  String ASSIGN_TECHNICIAN_MF_CURRENT ="/current/assign_malfunction"; //GET
  String ASSIGN_TECHNICIAN_MT_CURRENT ="/current/assign_maintenance"; //GET
  String COMPLETE_RECORD = "/complete"; //POST
 
  
  

}
