package com.ticket.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.ticket.model.Level;
import com.ticket.model.SeatHold;

@Service
public class TicketServiceImpl implements TicketService {

	public static Map<String, SeatHold> seatHoldMap = new HashMap<String, SeatHold>();
	public static Map<Integer, Level> totalSeatLevels = new HashMap<Integer, Level>();
	public static long seatHoldTime = 2;

	protected static Logger logger = Logger.getLogger("TicketServiceImpl");

	static {
		Level orchestra = new Level(1, "Orchestra", 100.00, 25, 50);
		totalSeatLevels.put(orchestra.getLevelId(), orchestra);
		Level main = new Level(2, "Main", 75.00, 20, 100);
		totalSeatLevels.put(main.getLevelId(), main);
		Level balcony1 = new Level(3, "Balcony 1", 50.00, 15, 100);
		totalSeatLevels.put(balcony1.getLevelId(), main);
		Level balcony2 = new Level(4, "Balcony 2", 40.00, 15, 100);
		totalSeatLevels.put(balcony2.getLevelId(), main);
	}

	/**
	 * The number of seats in the requested level that are neither held nor
	 * reserved
	 * 
	 * @param venueLevel
	 *            a numeric venue level identifier to limit the search
	 * @return the number of tickets available on the provided level
	 */
	@Override
	public int numSeatsAvailable(Integer levelId) {
		logger.info("getting all available seats based on level");
		Level level = totalSeatLevels.get(levelId);
		int seats = 0;
		if (level != null) {
			seats = level.getAvailableSeats();
		}
		return seats;
	}

	/**
	 * Find and hold the best available seats for a customer
	 * 
	 * @param numSeats
	 *            the number of seats to find and hold
	 * @param minLevel
	 *            the minimum venue level
	 * @param maxLevel
	 *            the maximum venue level
	 * @param customerEmail
	 *            unique identifier for the customer
	 * @return a SeatHold object identifying the specific seats and related
	 *         information
	 */
	@Override
	public SeatHold findAndHoldSeats(int numSeats, Integer minLevel,
			Integer maxLevel, String customerEmail) {
		logger.info("finding and holding seats based on min and max level");
		SeatHold seatHold = new SeatHold();
		// Assuming one customer can hold only one seat hold object
		// assigning seat hold id as customer email
		seatHold.setSeatHoldId(customerEmail);
		seatHold.setCustomerEmail(customerEmail);
		seatHold.setCreatedTime(Calendar.getInstance());
		seatHold.setLevels(new ArrayList<Level>());
		for (int i = minLevel; i <= maxLevel; i++) {
			if (numSeats != 0) {
				int availableSeats = numSeatsAvailable(i);
				if (availableSeats >= numSeats) {
					Level level = new Level();
					level.setLevelId(i);
					level.setHoldedSeats(numSeats);
					totalSeatLevels.get(i).setAvailableSeats(
							totalSeatLevels.get(i).getAvailableSeats()
									- numSeats);
					seatHold.getLevels().add(level);
					numSeats = 0;
				} else {
					numSeats = numSeats - availableSeats;
					Level level = new Level();
					level.setLevelId(i);
					level.setHoldedSeats(availableSeats);
					totalSeatLevels.get(i).setAvailableSeats(
							totalSeatLevels.get(i).getAvailableSeats()
									- availableSeats);
					seatHold.getLevels().add(level);
				}
			}
		}
		seatHoldMap.put(customerEmail, seatHold);
		return seatHold;
	}

	/**
	 * Commit seats held for a specific customer
	 * 
	 * @param customerEmail
	 *            the email address of the customer to which the seat hold is
	 *            assigned
	 * @return a reservation confirmation code
	 */
	@Override
	public String reserveSeats(String customerEmail) {
		logger.info("reserve seats based on customer email");
		SeatHold seatHold = seatHoldMap.get(customerEmail);
		if (seatHold != null)
			seatHold.setConfirmed(true);
		return generateConfirmationCode(seatHold);
	}

	/**
	 * This method is to generate confirmation code using seat hold object
	 * 
	 * @param seatHold
	 * @return
	 */
	private String generateConfirmationCode(SeatHold seatHold) {
		logger.info("generate confirmation code based on seat hold");
		StringBuffer confirmationCode = new StringBuffer();
		confirmationCode.append("code not generated");
		if (seatHold != null ) {
			if(isValidSeatHold(seatHold.getCreatedTime())){
			confirmationCode.delete(0, confirmationCode.length());
			for (Level level : seatHold.getLevels()) {
				String levelName=totalSeatLevels.get(level.getLevelId()).getLevelName();
				confirmationCode.append(levelName + "~"
						+ level.getHoldedSeats()+" ");
			}
			}
			else{
				confirmationCode.delete(0, confirmationCode.length());
				confirmationCode.append("seat holding time expired !!");
			}
		}
		return confirmationCode.toString();
	}

	/**
	 * 
	 * @param createdTime
	 * @return
	 */
	private boolean isValidSeatHold(Calendar createdTime) {
		Calendar cal2 = Calendar.getInstance();
		// Time Difference Calculations Begin
		long milliSec1 = createdTime.getTimeInMillis();
		long milliSec2 = cal2.getTimeInMillis();
		long timeDifInMilliSec = milliSec2 - milliSec1;

		long timeDifMinutes = timeDifInMilliSec / (60 * 1000);
		if(timeDifMinutes<=seatHoldTime)return true;

		return false;
	}
}
