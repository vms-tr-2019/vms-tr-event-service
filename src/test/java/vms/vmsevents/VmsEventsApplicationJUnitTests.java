package vms.vmsevents;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import vms.vmsevents.dto.CompleteRecord;
import vms.vmsevents.dto.OperationStatusEnum;
// import vms.vmsevents.dto.RecordCurrentDTO;
import vms.vmsevents.jpa.MFRecordCurrentJPA;
import vms.vmsevents.jpa.MTRecordCurrentJPA;
import vms.vmsevents.service.RecordsService;

	@SpringBootApplication
	public class VmsEventsApplicationJUnitTests {
		
		private static final int WORKER_ID_20 = 20;
		RecordsService service;
		ConfigurableApplicationContext ctx;
		
		MTRecordCurrentJPA recMT1 = new MTRecordCurrentJPA(1, 101);
		MTRecordCurrentJPA recMT2 = new MTRecordCurrentJPA(1, 102);
		MFRecordCurrentJPA recMF1 = new MFRecordCurrentJPA(1, 1);
		MFRecordCurrentJPA recMF2 = new MFRecordCurrentJPA(1, 1);
		
		@BeforeEach
		void setUp() {
			ctx = SpringApplication.run(VmsEventsApplicationJUnitTests.class);
			service = ctx.getBean(RecordsService.class);
			service.saveMFReord(recMF1);
			service.saveMTReord(recMT1);
		}
		
		@AfterEach
		void tearDown() {
			ctx.close();
		}

		@Test
		public void getAllRecordsCurentTest() {
			System.out.println(service.getAllCurrentRecord().size());
			assertEquals(2, service.getAllCurrentRecord().size());
		}
		
		@Test
		public void getCurrentMTAndMFRecordTest() {
			assertEquals(recMT1.convertJPAtoDTO(), service.getCurrentMTRecord
					(String.format("%d-%d", recMT1.getMachineId(), recMT1.getSensorId())));
			assertEquals(recMF1.convertJPAtoDTO(), service.getCurrentMFRecord
					(String.format("%d-%d", recMF1.getMachineId(), recMF1.getSensorId())));
			assertNull(service.getCurrentMFRecord
					(String.format("%d-%d", recMT2.getMachineId(), recMT2.getSensorId())));
			service.saveMTReord(recMT2);
			assertEquals(recMT2.convertJPAtoDTO(), service.getCurrentMTRecord
					(String.format("%d-%d", recMT2.getMachineId(), recMT2.getSensorId())));
		}
		
		@Test
		public void assingRecordTest() {
			assertEquals(service.assignTechnicianMF(recMF1.getId(), WORKER_ID_20), OperationStatusEnum.OK);
			assertEquals(service.getCurrentMFRecord(recMF1.getId()).getUserId(), WORKER_ID_20);
			assertEquals(service.assignTechnicianMT(recMT1.getId(), WORKER_ID_20), OperationStatusEnum.OK);
			assertEquals(service.getCurrentMTRecord(recMT1.getId()).getUserId(), WORKER_ID_20);
		}
		
		@Test
		public void completeREcord() {
			CompleteRecord record = new CompleteRecord(1, "Hi", 1);
			assertEquals(service.completeRecord(record), OperationStatusEnum.OK);
			assertEquals(service.getAllArchiveRecord(LocalDate.now(), LocalDate.now()).size(), 2);

		}
		
		@Test
		public void getCurrendRecordByMachine() {
			assertEquals(service.getCurrentRecordsByMachine(1).size(), 2);
		}

}
