package com.ticket.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ticket.model.SeatHold;
import com.ticket.service.TicketService;

/**
 * TicketController
 * 
 * Only GET and POST will return values
 * 
 */
@Controller
@RequestMapping("/")
public class TicketController {

	protected static Logger logger = Logger.getLogger("TicketController");

	@Autowired
	private TicketService ticketService;

	/**
	 * numSeatsAvailable get seats based on the level
	 * 
	 * @param levelId
	 * @return
	 */
	@RequestMapping(value = "/getSeatsAvailable", method = RequestMethod.GET)
	public @ResponseBody
	Integer numSeatsAvailable(@RequestParam(value = "levelId") String levelId) {
		int level = 0;
		try {
			level = Integer.parseInt(levelId);
		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		}
		return ticketService.numSeatsAvailable(level);

	}

	/**
	 * findAndHoldSeats will find and hold seats based on the levels
	 * 
	 * @param numSeats
	 * @param minLevel
	 * @param maxLevel
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/holdSeats", method = RequestMethod.GET)
	public @ResponseBody
	SeatHold findAndHoldSeats(@RequestParam(value = "seats") int numSeats,
			@RequestParam(value = "minLevel") int minLevel,
			@RequestParam(value = "maxLevel") int maxLevel,
			@RequestParam(value = "email") String email) {
		SeatHold seatHold = null;
		try {
			seatHold = ticketService.findAndHoldSeats(numSeats, minLevel,
					maxLevel, email);
		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		}
		return seatHold;

	}

	/**
	 * reserveSeats is used to reserve the holded seats
	 * 
	 * @param seatHoldId
	 * @param customerEmail
	 * @return
	 */
	@RequestMapping(value = "/reserveSeats", method = RequestMethod.GET)
	public @ResponseBody
	String reserveSeats(@RequestParam(value = "email") String customerEmail) {
		String confirmCode = null;
		try {
			confirmCode = ticketService.reserveSeats(customerEmail);
		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		}
		return confirmCode;

	}

}