/**
 * 
 */
package com.ticket.model;

/**
 * Level Class
 *
 */
public class Level {

	
	private int levelId;
	private String levelName;
	private double price;
	private int rows;
	private int numberOfSeatInRow;
    private int availableSeats;
	private int totalSeats;
	private int holdedSeats;
	
	public Level(){
		
	}
	
	public Level(int levelId, String levelName, double price, int rows,
			int numberOfSeatInRow) {
		super();
		this.levelId = levelId;
		this.levelName = levelName;
		this.price = price;
		this.rows = rows;
		this.numberOfSeatInRow = numberOfSeatInRow;
		this.availableSeats = getTotalSeats();
	}
	
	
	public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getNumberOfSeatInRow() {
		return numberOfSeatInRow;
	}
	public void setNumberOfSeatInRow(int numberOfSeatInRow) {
		this.numberOfSeatInRow = numberOfSeatInRow;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	public int getTotalSeats() {
		return numberOfSeatInRow*rows;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	public int getHoldedSeats() {
		return holdedSeats;
	}
	public void setHoldedSeats(int holdedSeats) {
		this.holdedSeats = holdedSeats;
	}
	
	
}
