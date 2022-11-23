package model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import dataStructures.ALGraph;

class BoardTest {

	private Board board;
	
	public void setupStage1() {
		board = new Board(4,5);
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
		
		System.out.println(board.getBoardStr(1));
	}
}
