package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import dataStructures.ALGraph;
import dataStructures.ALVertex;
import dataStructures.Graph;
import dataStructures.Vertex;

class BoardTest {

	private Board board;
	
	private Player rick;
	private Player morty;
	
	public void setupStage1() {
		board = new Board(4,5,0,0);
	}
	
	public void setupStage2() {
		board = new Board(3,3,0,0);
	}
	
	public void setupStage3() {
		board = new Board(4,5,0,0);
		
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
		board = new Board(4,5,0,0);
		
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
		board = new Board(4,5,0,0);
		
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
	
	public void setupStage6() {
		board = new Board(4,5,1,0);
	}
	
	public void setupStage7() {
		board = new Board(4,5,1,0);
		
		ALGraph<Square> graph = board.getBoardAL();
		
		rick = new Player("y", "R");
		morty = new Player("d", "M");
		
		board.addPlayer(rick);
		board.addPlayer(morty);
		
		graph.get(0).getValue().addPlayer(rick);
		board.setRickSq(graph.get(0).getValue());
		
		graph.get(17).getValue().addPlayer(morty);
		board.setMortySq(graph.get(17).getValue());
		
		graph.get(8).getValue().setSeed(true);
		rick.setTurn(true);
	}
	
	public void setupStage8() {
		board = new Board(4,5,0,1);
	}
	
	public void setupStage9() {
		board = new Board(4,5,1,1);
	}
	
	public void setupStage10() {
		board = new Board(4,5,0,1);
		
		Graph<Square> graph = board.getBoardAM();
		
		morty = new Player("d", "M");
		rick = new Player("y", "R");
		
		board.addPlayer(rick);
		board.addPlayer(morty);
		
		graph.get(0).getValue().addPlayer(rick);
		board.setRickSq(graph.get(0).getValue());
		
		graph.get(7).getValue().addPlayer(morty);
		board.setMortySq(graph.get(7).getValue());
	}
	
	public void setupStage11() {
		board = new Board(4,5,1,1);
		
		Graph<Square> graph = board.getBoardAM();
		
		morty = new Player("d", "M");
		rick = new Player("y", "R");
		
		board.addPlayer(rick);
		board.addPlayer(morty);
		
		graph.get(0).getValue().addPlayer(rick);
		board.setRickSq(graph.get(0).getValue());
		
		graph.get(7).getValue().addPlayer(morty);
		board.setMortySq(graph.get(7).getValue());
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
	
	//The squares are correctly connected.
	@Test
	void creationAMTest2() {
		setupStage8();
		
		Graph<Square> graph = board.getBoardAM();
		
		Square auxSq1 = board.searchSquareAM(6);
		Square auxSq2 = board.searchSquareAM(7);
		Square auxSq3 = board.searchSquareAM(8);
		
		Vertex<Square> v1 = graph.get(board.searchVertexIndexAM(auxSq1));
		Vertex<Square> v2 = graph.get(board.searchVertexIndexAM(auxSq2));
		Vertex<Square> v3 = graph.get(board.searchVertexIndexAM(auxSq3));
		
		assertTrue(graph.searchEdge(v1, v2));
		assertTrue(graph.searchEdge(v3, v2));
		
		auxSq1 = board.searchSquareAM(16);
		auxSq2 = board.searchSquareAM(17);
		auxSq3 = board.searchSquareAM(18);
		
		v1 = graph.get(board.searchVertexIndexAM(auxSq1));
		v2 = graph.get(board.searchVertexIndexAM(auxSq2));
		v3 = graph.get(board.searchVertexIndexAM(auxSq3));
		
		assertTrue(graph.searchEdge(v1, v2));
		assertTrue(graph.searchEdge(v3, v2));
	}
	
	//The extremes are connected.
	@Test
	void creationAMTest3() {
		setupStage8();
		
		Graph<Square> graph = board.getBoardAM();
		
		Square auxSq1 = board.searchSquareAM(1);
		Square auxSq2 = board.searchSquareAM(20);
		Square auxSq3 = board.searchSquareAM(2);
		Square auxSq4 = board.searchSquareAM(19);
		
		Vertex<Square> v1 = graph.get(board.searchVertexIndexAM(auxSq1));
		Vertex<Square> v2 = graph.get(board.searchVertexIndexAM(auxSq2));
		Vertex<Square> v3 = graph.get(board.searchVertexIndexAM(auxSq3));
		Vertex<Square> v4 = graph.get(board.searchVertexIndexAM(auxSq4));
		
		assertTrue(graph.searchEdge(v1, v2));
		assertTrue(graph.searchEdge(v1, v3));
		assertTrue(graph.searchEdge(v4, v2));
	}
	
	//The vertexes are correctly connected when we use the other graph's type.
	@Test
	void creationAMTest4() {
		setupStage9();
		
		Graph<Square> graph = board.getBoardAM();
		
		Square auxSq1 = board.searchSquareAM(19);
		Square auxSq2 = board.searchSquareAM(20);
		Square auxSq3 = board.searchSquareAM(1);
		
		Vertex<Square> v1 = graph.get(board.searchVertexIndexAM(auxSq1));
		Vertex<Square> v2 = graph.get(board.searchVertexIndexAM(auxSq2));
		Vertex<Square> v3 = graph.get(board.searchVertexIndexAM(auxSq3));
		
		assertTrue(graph.searchEdge(v1, v2));
		assertTrue(graph.searchEdge(v2, v3));
		
		auxSq1 = board.searchSquareAM(13);
		
		v1 = graph.get(board.searchVertexIndexAM(auxSq1));
		
		assertTrue(graph.searchEdge(v1, v2));
		
		auxSq1 = board.searchSquareAM(8);
		
		v1 = graph.get(board.searchVertexIndexAM(auxSq1));
		
		assertTrue(graph.searchEdge(v1, v3));
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
	void boardStrAMTest1() {
		setupStage8();
		
		assertEquals("[ 1 ]	[ 2 ]	[ 3 ]	[ 4 ]	\n"
				+ "[ 8 ]	[ 7 ]	[ 6 ]	[ 5 ]	\n"
				+ "[ 9 ]	[ 10 ]	[ 11 ]	[ 12 ]	\n"
				+ "[ 16 ]	[ 15 ]	[ 14 ]	[ 13 ]	\n"
				+ "[ 17 ]	[ 18 ]	[ 19 ]	[ 20 ]	", board.getBoardStr(1));
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
	void movePlayerAMTest1() {
		setupStage10();
		
		rick.setTurn(true);
		
		board.movePlayerAM(2,1);
		
		assertEquals(3,board.getRickSq().getNumber());
	}
	
	@Test
	void movePlayerAMTest2() {
		setupStage10();
		
		morty.setTurn(true);

		board.movePlayerAM(3,1);
		
		assertEquals(11,board.getMortySq().getNumber());
	}
	
	@Test
	void movePlayerAMTest3() {
		setupStage10();
		
		morty.setTurn(true);

		board.movePlayerAM(3,-1);
		
		assertEquals(5,board.getMortySq().getNumber());
	}
	
	@Test
	void movePlayerAMTest4() {
		setupStage10();
		
		rick.setTurn(true);
		
		board.movePlayerAM(2,-1);
		
		assertEquals(19,board.getRickSq().getNumber());
	}
	
	@Test
	void movePlayerAMTest5() {
		setupStage11();
		
		morty.setTurn(true);
		
		board.movePlayerUpOrDownAM(2,1);
		
		assertEquals(1,board.getMortySq().getNumber());
	}
	
	@Test
	void movePlayerAMTest6() {
		setupStage11();
		
		rick.setTurn(true);
		
		board.movePlayerUpOrDownAM(2,-1);
		
		assertEquals(8,board.getRickSq().getNumber());
	}
	
	//The searchUpVertexAM method returns the correct up vertex.
	@Test
	void searchUpVertexAMTest1() {
		setupStage9();
		
		Graph<Square> graph = board.getBoardAM();
		
		Square auxSq1 = board.searchSquareAM(6);
		Square auxSq2 = board.searchSquareAM(11);
		
		Vertex<Square> v1 = graph.get(board.searchVertexIndexAM(auxSq1));
		Vertex<Square> v2 = graph.get(board.searchVertexIndexAM(auxSq2));
		
		assertTrue(graph.searchEdge(v1, v2));
	}
	
	//The squares in the first row don't have up vertex.
	@Test
	void searchUpVertexAMTest2() {
		setupStage9();
		
		Graph<Square> graph = board.getBoardAM();
		
		Square auxSq1 = board.searchSquareAM(2);
		Square auxSq2 = board.searchSquareAM(18);
		
		Vertex<Square> v1 = graph.get(board.searchVertexIndexAM(auxSq1));
		Vertex<Square> v2 = graph.get(board.searchVertexIndexAM(auxSq2));
		
		assertFalse(graph.searchEdge(v1, v2));
	}
	
	//The searchUpVertexAM method returns the correct up vertex.
	@Test
	void searchUpVertexAMTest3() {
		setupStage9();
		
		Graph<Square> graph = board.getBoardAM();
		
		Square auxSq1 = board.searchSquareAM(8);
		Square auxSq2 = board.searchSquareAM(1);
		
		Vertex<Square> v1 = graph.get(board.searchVertexIndexAM(auxSq1));
		Vertex<Square> v2 = graph.get(board.searchVertexIndexAM(auxSq2));
		
		assertTrue(graph.searchEdge(v1, v2));
	}
	
	//The searchDownVertexAM method returns the correct down vertex.
	@Test
	void searchDownVertexAMTest1() {
		setupStage9();
		
		Graph<Square> graph = board.getBoardAM();
		
		Square auxSq1 = board.searchSquareAM(15);
		Square auxSq2 = board.searchSquareAM(18);
		
		Vertex<Square> v1 = graph.get(board.searchVertexIndexAM(auxSq1));
		Vertex<Square> v2 = graph.get(board.searchVertexIndexAM(auxSq2));
		
		assertTrue(graph.searchEdge(v1, v2));
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
	void positionPlayerAMTest1() {
		setupStage8();
		
		board.positionPlayersAM("y", "d");
		
		assertTrue(board.getRickSq().containsPlayer("y"));
	}
	
	@Test
	void positionPlayerAMTest2() {
		setupStage8();
		
		board.positionPlayersAM("y", "d");
		
		assertTrue(board.getMortySq().containsPlayer("d"));
	}
	
	//The players are placed inside the board.
	@Test
	void positionPlayerAMTest3() {
		setupStage8();
		
		board.positionPlayersAM("y", "d");
		
		assertTrue(board.containsSquareAM(board.getRickSq()));
		assertTrue(board.containsSquareAM(board.getMortySq()));
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
	
	@Test
	void upOrDownMovementTest() {
		setupStage7();
		
		int dice = 2;
		
		board.movePlayerUpOrDownAL(dice, -1);
		
		assertEquals(8, board.getRickSq().getNumber());
	}
	
	@Test
	void createSeedsAMTest1() {
		setupStage9();
		
		board.createSeedsAM(3);
		
		assertEquals(3, board.getTotalSeeds());
	}
	
	@Test
	void constructShortestPath() {
		setupStage6();
		
		String path = board.constructShortestPathAL(1, 9);
	}
}
