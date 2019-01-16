package vms.vmsevents;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
// import org.springframework.test.context.junit4.SpringRunner;

import vms.vmsevents.jpa.MFRecordCurrentJPA;
import vms.vmsevents.jpa.MTRecordCurrentJPA;
import vms.vmsevents.service.RecordsService;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@SpringBootApplication
public class VmsEventsApplicationTests {
	
	RecordsService service;
	ConfigurableApplicationContext ctx;
	
	MTRecordCurrentJPA recMT1 = new MTRecordCurrentJPA(1, 101);
	MTRecordCurrentJPA recMT2 = new MTRecordCurrentJPA(1, 102);
	MFRecordCurrentJPA recMF1 = new MFRecordCurrentJPA(1, 1);
	MFRecordCurrentJPA recMF2 = new MFRecordCurrentJPA(1, 1);
	
	@BeforeEach
	void setUp() {
		System.out.println("start");
		ctx = SpringApplication.run(VmsEventsApplicationTests.class);
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
		assertEquals(2, service.getAllCurrentRecord().size());
	}

}

