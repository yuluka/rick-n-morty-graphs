package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SquareTest {

	private Square sq;
	
	public void setupStage1() {
		sq = new Square(1);
	}
	
	@Test
	void playersTest1() {
		setupStage1();
		
		Player rick = new Player("y","R");
		Player morty = new Player("d","M");
		
		sq.addPlayer(rick);
		sq.addPlayer(morty);
		
		assertEquals("[ R M ]", sq.squareToString(1));
	}

	@Test
	void playersTest2() {
		setupStage1();
		
		Player rick = new Player("y","R");
		Player morty = new Player("d","M");
		
		sq.addPlayer(morty);
		sq.addPlayer(rick);
		
		assertEquals("[ M R ]", sq.squareToString(1));
	}
	
	@Test
	void playersTest3() {
		setupStage1();
		
		Player rick = new Player("y","R");
		Player morty = new Player("d","M");
		
		sq.addPlayer(morty);
		sq.addPlayer(rick);
		
		sq.removePlayer(morty);
		
		assertEquals("[ R ]", sq.squareToString(1));
	}
	
	@Test
	void playersTest4() {
		setupStage1();
		
		Player rick = new Player("y","R");
		Player morty = new Player("d","M");
		
		sq.addPlayer(morty);
		sq.addPlayer(rick);
		
		sq.removePlayer(rick);
		
		assertEquals("[ M ]", sq.squareToString(1));
	}
}
