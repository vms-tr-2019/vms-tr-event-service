package vms.vmsevents.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vms.vmsevents.jpa.MaintenanceRecordArchiveJPA;

@Repository
public interface MaintenanceArchiveRepository extends JpaRepository<MaintenanceRecordArchiveJPA, Integer> {

	List<MaintenanceRecordArchiveJPA> findByDateCloseBetween(LocalDate from, LocalDate to);
}
