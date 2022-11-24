package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import dataStructures.ALGraph;

class BoardTest {

	private Board board;
	
	private Player rick;
	private Player morty;
	
	public void setupStage1() {
		board = new Board(4,5,0);
	}
	
	public void setupStage6() {
		board = new Board(4,5,1);
	}
	
	public void setupStage2() {
		board = new Board(3,3,0);
	}
	
	public void setupStage3() {
		board = new Board(4,5,0);
		
		ALGraph<Square> graph = board.getBoardAL();
		
		morty = new Player("d", "M");
		rick = new Player("y", "R");
		
		board.addPlayer(rick);
		board.addPlayer(morty);
		
		graph.get(0).getValue().addPlayer(rick);
		board.setRickSq(graph.get(0).getValue());
		
		graph.get(7).getValue().addPlayer(morty);
		board.setMortySq(graph.get(7).getValue());
	}
	
	public void setupStage4() {
		board = new Board(4,5,0);
		
		ALGraph<Square> graph = board.getBoardAL();
		
		rick = new Player("y", "R");
		morty = new Player("d", "M");
		
		board.addPlayer(rick);
		board.addPlayer(morty);
		
		graph.get(0).getValue().addPlayer(rick);
		board.setRickSq(graph.get(0).getValue());
		
		graph.get(19).getValue().addPlayer(morty);
		board.setMortySq(graph.get(19).getValue());
	}
	
	public void setupStage5() {
		board = new Board(4,5,0);
		
		ALGraph<Square> graph = board.getBoardAL();
		
		rick = new Player("y", "R");
		morty = new Player("d", "M");
		
		board.addPlayer(rick);
		board.addPlayer(morty);
		
		graph.get(0).getValue().addPlayer(rick);
		board.setRickSq(graph.get(0).getValue());
		
		graph.get(17).getValue().addPlayer(morty);
		board.setMortySq(graph.get(17).getValue());
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
	void creationTest4() {
		setupStage6();
		
		ALGraph<Square> graph = board.getBoardAL();
		
		assertEquals(19, graph.get(19).getAdjacents().get(0).getValue().getValue().getNumber());
		assertEquals(1, graph.get(19).getAdjacents().get(1).getValue().getValue().getNumber());
		
		assertEquals(13, graph.get(19).getAdjacents().get(2).getValue().getValue().getNumber());
		assertEquals(3, graph.get(19).getAdjacents().size());
		
		assertEquals(2, graph.get(0).getAdjacents().get(0).getValue().getValue().getNumber());
		assertEquals(20, graph.get(0).getAdjacents().get(1).getValue().getValue().getNumber());
	
		assertEquals(8, graph.get(0).getAdjacents().get(2).getValue().getValue().getNumber());
		assertEquals(3, graph.get(0).getAdjacents().size());
	}
	
	@Test
	void creationTest5() {
		setupStage6();
		
		ALGraph<Square> graph = board.getBoardAL();
		
		assertEquals(10, graph.get(10).getAdjacents().get(0).getValue().getValue().getNumber());
		assertEquals(12, graph.get(10).getAdjacents().get(1).getValue().getValue().getNumber());
		
		assertEquals(6, graph.get(10).getAdjacents().get(2).getValue().getValue().getNumber());
		assertEquals(14, graph.get(10).getAdjacents().get(3).getValue().getValue().getNumber());
		assertEquals(4, graph.get(10).getAdjacents().size());
	}
	
	@Test
	void creationTest6() {
		setupStage6();
		
		ALGraph<Square> graph = board.getBoardAL();
		
		assertEquals(11, graph.get(11).getAdjacents().get(0).getValue().getValue().getNumber());
		assertEquals(13, graph.get(11).getAdjacents().get(1).getValue().getValue().getNumber());
		
		assertEquals(5, graph.get(11).getAdjacents().get(2).getValue().getValue().getNumber());
		assertEquals(3, graph.get(11).getAdjacents().size());
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
	void movePlayerTest1() {
		setupStage3();
		
		rick.setTurn(true);
		
		board.movePlayerAL(2,1);
		
		assertEquals(3,board.getRickSq().getNumber());
	}
		
	@Test
	void movePlayerTest2() {
		setupStage3();
		
		morty.setTurn(true);

		board.movePlayerAL(3,1);
		
		assertEquals(11,board.getMortySq().getNumber());
	}
	
	@Test
	void movePlayerTest3() {
		setupStage4();
		
		morty.setTurn(true);
		
		board.movePlayerAL(1,1);
		
		assertEquals(1,board.getMortySq().getNumber());
	}
	
	@Test
	void movePlayerTest4() {
		setupStage5();
		
		morty.setTurn(true);
		
		board.movePlayerAL(3,1);
		
		assertEquals(1,board.getMortySq().getNumber());
	}
	
	@Test
	void movePlayerTest5() {
		setupStage3();
		
		rick.setTurn(true);
		
		board.movePlayerAL(2,-1);
		
		assertEquals(19,board.getRickSq().getNumber());
	}
	
	@Test
	void movePlayerTest6() {
		setupStage3();
		
		morty.setTurn(true);
		
		board.movePlayerAL(3,-1);
		
		assertEquals(5,board.getMortySq().getNumber());
	}
	
	@Test
	void movePlayerTest7() {
		setupStage4();
		
		morty.setTurn(true);
		
		board.movePlayerAL(1,-1);
		
		assertEquals(19,board.getMortySq().getNumber());
	}
	
	@Test
	void movePlayerTest8() {
		setupStage5();
		
		morty.setTurn(true);
		
		board.movePlayerAL(3,-1);
		
		assertEquals(15,board.getMortySq().getNumber());
	}
	
	@Test
	void positionPlayerTest1() {
		setupStage1();
		
		board.positionPlayersAL("y", "d");
		
		assertTrue(board.getRickSq().containsPlayer("y"));
	}
	
	@Test
	void positionPlayerTest2() {
		setupStage1();
		
		board.positionPlayersAL("y", "d");
		
		assertTrue(board.getMortySq().containsPlayer("d"));
	}

	//The players are placed inside the board.
	@Test
	void positionPlayerTest3() {
		setupStage1();
		
		board.positionPlayersAL("y", "d");
		
		assertTrue(board.containsSquare(board.getRickSq()));
		assertTrue(board.containsSquare(board.getMortySq()));
	}
	
	@Test
	void collectSeedTest1() {
		setupStage3();
		
		ALGraph<Square> graph = board.getBoardAL();

		graph.get(7).getValue().setSeed(true);
		
		board.collectSeed();
		
		assertEquals(1, board.getMorty().getSeeds());
		assertEquals(0, board.getRick().getSeeds());
	}
	
	@Test
	void collectSeedTest2() {
		setupStage3();
		
		ALGraph<Square> graph = board.getBoardAL();

		graph.get(0).getValue().setSeed(true);
		
		board.collectSeed();
		
		assertEquals(1, board.getRick().getSeeds());
		assertEquals(0, board.getMorty().getSeeds());
	}
	
	@Test
	void collectSeedTest3() {
		setupStage3();
		
		ALGraph<Square> graph = board.getBoardAL();
		
		graph.get(9).getValue().setSeed(true);
		
		graph.get(0).getValue().setPortalLetter("A");
		graph.get(9).getValue().setPortalLetter("A");
		
		graph.get(0).getValue().setPortalPair(graph.get(9).getValue());
		graph.get(9).getValue().setPortalPair(graph.get(0).getValue());
		
		rick.setTurn(true);
		
		board.teleport();

		assertEquals(1, board.getRick().getSeeds());
		assertFalse(graph.get(9).getValue().isSeed());
	}
	
	@Test
	void teleportTest1() {
		setupStage3();
		
		ALGraph<Square> graph = board.getBoardAL();

		graph.get(0).getValue().setPortalLetter("A");
		graph.get(9).getValue().setPortalLetter("A");
		
		graph.get(0).getValue().setPortalPair(graph.get(9).getValue());
		graph.get(9).getValue().setPortalPair(graph.get(0).getValue());
		
		rick.setTurn(true);
		
		board.teleport();
		
		assertEquals(10, board.getRickSq().getNumber());
		assertFalse(graph.get(0).getValue().containsPlayer(rick));
		
		board.teleport();

		assertEquals(1, board.getRickSq().getNumber());
		assertFalse(graph.get(9).getValue().containsPlayer(rick));
	}
}
