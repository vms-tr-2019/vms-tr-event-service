package vms.vmsevents.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vms.vmsevents.jpa.MalfunctionRecordArchiveJPA;


public interface MalfunctionArchiveRepository extends JpaRepository<MalfunctionRecordArchiveJPA, String> {

	List<MalfunctionRecordArchiveJPA> findByDateCloseBetween(LocalDate from, LocalDate to);
}