package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Player implements Serializable{
	
	private int seeds; //The seeds the player has recolected.
	private String name; //Letter R or M
	
	private String username; //Name of the player.
	private int score; //score = seeds*120-time in seconds.
	private boolean turn;
	
	public Player(String username, String name) {
		this.username = username;
		this.name = name;
		
		turn = false;
	}

	public int getSeeds() {
		return seeds;
	}

	public void addSeeds() {
		++seeds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void addScore(int addScore) {
		score += addScore;
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}
}
