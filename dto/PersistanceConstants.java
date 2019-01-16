package vms.vmsevents.dto;

public interface PersistanceConstants {

  // ---SQL---//

  public static final String ARCHIVE_MAINTANANCE_RECORDS_TABLE = "archive_maintenance";
  public static final String ARCHIVE_MALFUNCTION_RECORDS_TABLE = "archive_malfunction";
  public static final String CURRENT_MALFUNCTION_RECORDS_TABLE = "current_malfunction";
  public static final String CURRENT_MAINTANANCE_RECORDS_TABLE = "current_maintenance";
  public static final String MACHINES_TABLE = "machines";

  // ---MONGO---//

  public static final String CURRENT_MACHINES_STATE_COLLECTION = "current_state_machines";
}