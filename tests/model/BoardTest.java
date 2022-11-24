package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import dataStructures.ALGraph;

class BoardTest {

	private Board board;
	
	public void setupStage1() {
		board = new Board(4,5);
	}
	
	public void setupStage2() {
		board = new Board(3,3);
	}
	
	@Test
	void creationTest1() {
		setupStage1();
		
		ALGraph<Square> graph = board.getBoardAL();
		
		assertEquals(20, graph.getVertexes().size());
	}

	//The edges has been creates succesfully.
	@Test
	void creationTest2() {
		setupStage1();
		
		ALGraph<Square> graph = board.getBoardAL();
		
		assertEquals(6, graph.get(6).getAdjacents().get(0).getValue().getValue().getNumber());
		assertEquals(8, graph.get(6).getAdjacents().get(1).getValue().getValue().getNumber());

		assertEquals(7, graph.get(5).getAdjacents().get(1).getValue().getValue().getNumber());
		assertEquals(5, graph.get(5).getAdjacents().get(0).getValue().getValue().getNumber());

		assertEquals(16, graph.get(16).getAdjacents().get(0).getValue().getValue().getNumber());
		assertEquals(18, graph.get(16).getAdjacents().get(1).getValue().getValue().getNumber());		
	}
	
	@Test
	void creationTest3() {
		setupStage1();
		
		ALGraph<Square> graph = board.getBoardAL();
		
		assertEquals(19, graph.get(19).getAdjacents().get(0).getValue().getValue().getNumber());
		assertEquals(1, graph.get(19).getAdjacents().get(1).getValue().getValue().getNumber());
		
		assertEquals(2, graph.get(0).getAdjacents().get(0).getValue().getValue().getNumber());
		assertEquals(20, graph.get(0).getAdjacents().get(1).getValue().getValue().getNumber());
	}
	
	@Test
	void boardStrTest1() {
		setupStage1();
		
		assertEquals("[ 1 ]	[ 2 ]	[ 3 ]	[ 4 ]	\n"
				+ "[ 8 ]	[ 7 ]	[ 6 ]	[ 5 ]	\n"
				+ "[ 9 ]	[ 10 ]	[ 11 ]	[ 12 ]	\n"
				+ "[ 16 ]	[ 15 ]	[ 14 ]	[ 13 ]	\n"
				+ "[ 17 ]	[ 18 ]	[ 19 ]	[ 20 ]	", board.getBoardStr(1));
	}
	
	@Test
	void boardStrTest2() {
		setupStage2();
		
		assertEquals("[ 1 ]	[ 2 ]	[ 3 ]	\n"
				+ "[ 6 ]	[ 5 ]	[ 4 ]	\n"
				+ "[ 7 ]	[ 8 ]	[ 9 ]	", board.getBoardStr(1));
	}
	
	@Test
	void movePlayerForwardTest1() {
		setupStage1();
		
		ALGraph<Square> graph = board.getBoardAL();
		
		Player morty = new Player("d", "M");
		
		Player rick = new Player("y", "R");
		rick.setTurn(true);
		
		board.addPlayer(rick);
		board.addPlayer(morty);
		
		graph.get(0).getValue().addPlayer(rick);
		board.setRickSq(graph.get(0).getValue());
		
		graph.get(7).getValue().addPlayer(morty);
		board.setMortySq(graph.get(7).getValue());
		
		assertTrue(graph.get(0).getValue().getPlayers().contains(rick));
		
		board.movePlayerForwardAL(2);
		
		assertEquals(3,board.getRickSq().getNumber());
	}
	
	@Test
	void movePlayerForwardTest2() {
		setupStage1();
		
		ALGraph<Square> graph = board.getBoardAL();
		
		Player rick = new Player("y", "R");
		
		Player morty = new Player("d", "M");
		morty.setTurn(true);
		
		board.addPlayer(rick);
		board.addPlayer(morty);
		
		graph.get(0).getValue().addPlayer(rick);
		board.setRickSq(graph.get(0).getValue());
		
		graph.get(7).getValue().addPlayer(morty);
		board.setMortySq(graph.get(7).getValue());
		
		assertTrue(graph.get(7).getValue().getPlayers().contains(morty));
		
		board.movePlayerForwardAL(3);
		
		assertEquals(11,board.getMortySq().getNumber());
	}
	
	@Test
	void movePlayerForwardTest3() {
		setupStage1();
		
		ALGraph<Square> graph = board.getBoardAL();
		
		Player rick = new Player("y", "R");
		
		Player morty = new Player("d", "M");
		morty.setTurn(true);
		
		board.addPlayer(rick);
		board.addPlayer(morty);
		
		graph.get(0).getValue().addPlayer(rick);
		board.setRickSq(graph.get(0).getValue());
		
		graph.get(19).getValue().addPlayer(morty);
		board.setMortySq(graph.get(19).getValue());
		
		assertTrue(graph.get(19).getValue().getPlayers().contains(morty));
		
		board.movePlayerForwardAL(1);
		
		assertEquals(1,board.getMortySq().getNumber());
	}
	
	@Test
	void movePlayerForwardTest4() {
		setupStage1();
		
		ALGraph<Square> graph = board.getBoardAL();
		
		Player rick = new Player("y", "R");
		
		Player morty = new Player("d", "M");
		morty.setTurn(true);
		
		board.addPlayer(rick);
		board.addPlayer(morty);
		
		graph.get(0).getValue().addPlayer(rick);
		board.setRickSq(graph.get(0).getValue());
		
		graph.get(17).getValue().addPlayer(morty);
		board.setMortySq(graph.get(17).getValue());
		
		assertTrue(graph.get(17).getValue().getPlayers().contains(morty));
		
		board.movePlayerForwardAL(3);
		
		assertEquals(1,board.getMortySq().getNumber());
	}
}
