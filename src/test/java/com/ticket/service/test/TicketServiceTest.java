package com.ticket.service.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ticket.service.TicketService;
import com.ticket.service.TicketServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = "classpath*:/spring/applicationContext*.xml")
public class TicketServiceTest {
	
	private TicketService ticketService=new TicketServiceImpl();

	@Test
	public void testTicketServiceReserveSeats() {
		// Check if the return reserveSeats has a string.
		assertTrue(ticketService.reserveSeats("code").contains("code not generated"));
	}

	@Test
	public void testTicketServiceNumSeatsAvailable() {
		// Check if the return NumSeatsAvailable returns integer.
		assertTrue(ticketService.numSeatsAvailable(1)>=0);
	}
	

}
