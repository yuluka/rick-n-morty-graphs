package model;

import java.util.ArrayList;
import java.util.Random;

import dataStructures.ALGraph;
import dataStructures.ALVertex;
import dataStructures.Graph;
import dataStructures.Vertex;

public class Board {
	
	private final int FORWARD_BACKWARD_MOVEMENTS_COST = 1; //The cost of movements to move from one square to other (placed front or behind it).
	private final int UP_DOWN_MOVEMENTS_COST = 2; //The cost of movements to move from one square to other (placed above or below it).
	private final int DIAGONALLY_MOVEMENTS_COST = 3; //The cost of movements to move from one square to other (placed diagonally to it).
	
	private final int RICK_INDEX = 0;
	private final int MORTY_INDEX = 1;
	
	private int rows;
	private int columns;
	
	private int totalSeeds;
	
	private String boardStr;
	
	private ALGraph<Square> boardAL; //Board created by using ALGraph.
	private Graph<Square> board; //Board created by using Graph.
	
	private long timeBeg;
	private long timeEnd;
	private long totalTime;
	
	private Square rickSq;
	private Square mortySq;	//Pointers of the positions of Rick and Morty.
	
	private ArrayList<Player> players;
	
	private ArrayList<Character> alphabet;
	
	public Board(int columns, int rows) {
		this.columns = columns;
		this.rows = rows;
		
		this.players = new ArrayList<>();
		this.alphabet = new ArrayList<>();
		
		this.boardAL = new ALGraph<>();
		
		createBoardALGraph();
		generateAlphabet();
	}
	
//	------------------------------THINGS WITH GRAPHS------------------------------
	
	/**
	 * Creates the board and all its squares.
	 * To do that, the method uses an ALGraph.
	 */
	public void createBoardALGraph() {
		createBoardALGraph(new Square(1), 0);
	}
	
	private void createBoardALGraph(Square newSq, int i) {
		if(i == columns*rows) {
			/*
			 * All the squares has been created.
			 */
			connectSquaresALGraph();
						
			return;
		}
		
		boardAL.addVertex(new ALVertex<Square>(newSq));
		
		++i;
		createBoardALGraph(new Square(i+1), i);
	}
	
	/**
	 * Connects the squares of the board (the one created with ALGraph). 
	 * 
	 * To connect two squares, they must be consecutive. At the end, connects the final square 
	 * with the initial one.
	 */
	private void connectSquaresALGraph() {
		for (int i = 0; i < (columns*rows)-1; i++) {
			int auxSq = boardAL.get(i).getValue().getNumber();
			int auxSq2 = boardAL.get(i+1).getValue().getNumber();
			
			if((auxSq+1) == auxSq2) {
				boardAL.addEdge(boardAL.get(auxSq-1), boardAL.get(auxSq2-1), FORWARD_BACKWARD_MOVEMENTS_COST);
			}
		}
		
		boardAL.addEdge(boardAL.get((columns*rows)-1), boardAL.get(0), FORWARD_BACKWARD_MOVEMENTS_COST);
	}
	
	/**
	 * Constructs a String representation of the board. Depending of the boardVersion value, 
	 * constructs the normal board representation, the portals board representation, or the seeds
	 * board representation.
	 * 
	 * @param boardVersion the value to decide which representation constructs.
	 */
	public void createBoardStrAL(int boardVersion) {
		boardStr = "";
		
		for (int i = 0; i < (columns*rows); i++) {
			if(i %columns == 0 && i!= 0) {
				boardStr += "\n";
				
				for (int j = (i+columns-1); j >= i; j--) {
					boardStr += boardAL.get(j).getValue().squareToString(boardVersion) + "	";
				}
				
				i += columns;
				
				boardStr += "\n";
			}
			
			boardStr += boardAL.get(i).getValue().squareToString(boardVersion) + "	";
		}
	}
	
//	--------Players movement--------
	
	/**
	 * Emulates a dice launching by generating a random number between 1 and 6.
	 * 
	 * @return the launching result.
	 */
	public int launchDice() {
		int dice = (int) (Math.random()*6+1);
		
		return dice;
	}
	
	/**
	 * Moves the player with the current turn forward.
	 * 
	 * @param dice the number of movements the player can make.
	 * @param direction the direction the player will move. -1 if the player wants to move backward
	 * and 1 if the player wants to move forward.
	 */
	public void movePlayerAL(int dice, int direction) {
		if(dice == 0) {
			collectSeed(); //Checks if there is a seed in the final square.
			teleport(); //Checks if there is a portal in the final square.
			collectSeed(); //Checks if there is a seed in the final square, after have been teleported (if is the case).
			
			changeTurn();
			
			return;
		}
		
		if(players.get(RICK_INDEX).isTurn()) { //Rick has the turn.
			int index = searchVertexIndex(rickSq);
			
			ALVertex<Square> auxVertex = boardAL.get(index);
			
			int weight = 0;
			
			for (int i = 0; i < auxVertex.getAdjacents().size(); i++) {
				if(auxVertex.getValue().getNumber() == (columns*rows) && direction > 0) {
					/*
					 * The vertex where the wanted square is placed is the last one and the player
					 * is moving forward.
					 */
					rickSq.removePlayer(players.get(RICK_INDEX));
					
					rickSq = boardAL.get(0).getValue();
					
					weight = auxVertex.getAdjacents().get(1).getWeight();
					
					break;
					
				} else if(auxVertex.getValue().getNumber() == 1 && direction < 0) {
					/*
					 * The vertex where the wanted square is placed is the first one and the player
					 * is moving backward.
					 */
					rickSq.removePlayer(players.get(RICK_INDEX));
					
					rickSq = boardAL.get((columns*rows)-1).getValue();
					
					weight = auxVertex.getAdjacents().get(1).getWeight();
					
					break;
				} else if(auxVertex.getAdjacents().get(i).getValue().getValue().getNumber() == 
						(rickSq.getNumber()+(direction))) {
					rickSq.removePlayer(players.get(RICK_INDEX));
					
					rickSq = auxVertex.getAdjacents().get(i).getValue().getValue();
					
					weight = auxVertex.getAdjacents().get(i).getWeight();
					
					break;
				}
			}
			
			rickSq.addPlayer(players.get(RICK_INDEX));
			
			movePlayerAL(dice-weight,direction);
			return;
			
		} else { //Exactly the same thing but when it is the morty's turn.
			int index = searchVertexIndex(mortySq);
			
			ALVertex<Square> auxVertex = boardAL.get(index);
			
			int weight = 0;
			
			for (int i = 0; i < auxVertex.getAdjacents().size(); i++) {
				if(auxVertex.getValue().getNumber() == (columns*rows) && direction > 0) {
					/*
					 * The vertex where the wanted square is placed is the last one and the player
					 * is moving forward.
					 */
					mortySq.removePlayer(players.get(MORTY_INDEX));
					
					mortySq = boardAL.get(0).getValue();
					
					weight = auxVertex.getAdjacents().get(1).getWeight();
					
					break;
					
				} else if(auxVertex.getValue().getNumber() == 1 && direction < 0) {
					/*
					 * The vertex where the wanted square is placed is the first one and the player
					 * is moving backward.
					 */
					mortySq.removePlayer(players.get(MORTY_INDEX));
					
					mortySq = boardAL.get((columns*rows)-1).getValue();
					
					weight = auxVertex.getAdjacents().get(1).getWeight();
					
					break;
				} else if(auxVertex.getAdjacents().get(i).getValue().getValue().getNumber() == 
						(mortySq.getNumber()+(direction))) {
					mortySq.removePlayer(players.get(MORTY_INDEX));
										
					mortySq = auxVertex.getAdjacents().get(i).getValue().getValue();
					
					weight = auxVertex.getAdjacents().get(i).getWeight();
					
					break;
				}
			}
			
			mortySq.addPlayer(players.get(MORTY_INDEX));
			
			movePlayerAL(dice-weight,direction);
			return;
		}
		
	}
	
	/**
	 * Gives the turn to the other player.
	 */
	private void changeTurn() {
		if(players.get(RICK_INDEX).isTurn()) {
			players.get(MORTY_INDEX).setTurn(true);
			players.get(RICK_INDEX).setTurn(false);
		} else {
			players.get(RICK_INDEX).setTurn(true);
			players.get(MORTY_INDEX).setTurn(false);
		}
	}
	
	/**
	 * Search the index where the vertex that contains the specified square in the graph.
	 * 
	 * @param sq the square that the searched vertex has to contain.
	 * @return the index where the vertex that contains the specified square in the graph.
	 */
	public int searchVertexIndex(Square sq) {
		for (int i = 0; i < (columns*rows); i++) {
			if(boardAL.getVertexes().get(i).getValue().equals(sq)) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * Creates and places the players in random squares in the board.
	 * 
	 * @param usernameR the nickname of the player that is playing as Rick.
	 * @param usernameM the nickname of the player that is playing as Morty.
	 */
	public void positionPlayersAL(String usernameR, String usernameM) {
		Player rick = new Player(usernameR, "R");
		Player morty = new Player(usernameM, "M"); //Creates the players.
		
		rick.setTurn(true); //Rick begins 'cause he's better. 
		morty.setTurn(false); //Buh. Morty's bullshit.
		
		players.add(rick);
		players.add(morty);
		
		rickSq = randomSquareAL();
		
		mortySq = randomSquareAL();
		
		while(mortySq.getNumber() == rickSq.getNumber()) {
			mortySq = randomSquareAL();
		}
		
		rickSq.addPlayer(rick);
		mortySq.addPlayer(morty);
		
		collectSeed();
		
		timeBeg = System.currentTimeMillis();
	}
	
	/**
	 * Generates a random square inside the board.
	 * 
	 * @return the generated square.
	 */
	public Square randomSquareAL() {
		int randomPos = (int) (Math.random()*(columns*rows)+1);
		
		return boardAL.getVertexes().get(randomPos-1).getValue();
	}
//	----------------Seeds----------------
	
	/**
	 * Creates the specified amount of seeds inside the board.
	 * 
	 * @param seeds the amount of seeds.
	 */
	public void createSeedsAL(int seeds) {
		totalSeeds = seeds;
		
		spreadSeedsAL(seeds);
	}
	
	/**
	 * Spreads the seeds in the board.
	 * 
	 * @param seedsToSpread the seeds to spread.
	 */
	private void spreadSeedsAL(int seedsToSpread) {
		if(seedsToSpread == 0) {
			return;
		}
		
		Square aux = randomSquareAL();
		
		while(aux.isSeed()) {
			aux = randomSquareAL();
		}
		
		aux.setSeed(true);
		
		spreadSeedsAL(--seedsToSpread);
	}
	
	/**
	 * Checks if there is a seed in the square in which the player is placed. If yes, adds it 
	 * to the player.
	 */
	public void collectSeed() {
		if(rickSq.isSeed()) {
			players.get(RICK_INDEX).addSeeds();
			rickSq.setSeed(false);
			--totalSeeds;
		} 
		if(mortySq.isSeed()) {
			players.get(MORTY_INDEX).addSeeds();
			mortySq.setSeed(false);
			--totalSeeds;
		}
	}
	
	
//	---------Portals---------
	
	/**
	 * Generates the specified amount of portals in the board. 
	 * 
	 * Each portal has two parts: one for the entrance and the other for the exit.
	 * 
	 * So, the method generates, first, a random square for the first part of the portal 
	 * and, after that, generates other random square, can not be the same, for the second
	 * part of the portal.
	 * 
	 * @param portals the amounts of portals to create.
	 */
	public void generatePortalsAL(int portals) {
		if(portals == 0) {
			return;
		}
		
		/*
		 * "Launch" the portal.
		 */
		Square portal1 = randomSquareAL();
		
		while(portal1.getPortalLetter() != null) {
			/*
			 * If there already exists a portal in the random square generated, generates
			 * other.
			 */
			portal1 = randomSquareAL();
		}
		
		portal1.setPortalLetter(randomChar()+"");
		
		/*
		 * "Appears" the portal1 exit.
		 */
		
		Square portal2 = randomSquareAL();
		
		while(portal2.getPortalLetter() != null) {
			portal2 = randomSquareAL();
		}
		
		portal2.setPortalLetter(portal1.getPortalLetter());
		
		portal1.setPortalPair(portal2);
		portal2.setPortalPair(portal1);
		
		generatePortalsAL(--portals);
	}
	
	/**
	 * Checks if there is a portal in the square in which the player is placed. If yes, 
	 * "teleports" the player to the portal pair and checks if there is any seed in the 
	 * new position.
	 */
	public void teleport() {
		if(players.get(RICK_INDEX).isTurn() && rickSq.getPortalPair() != null) {
			rickSq.removePlayer(players.get(RICK_INDEX));
			
			rickSq = rickSq.getPortalPair();
			
			rickSq.addPlayer(players.get(RICK_INDEX));
			
			collectSeed();
		} else if(mortySq.getPortalPair() != null) {
			mortySq.removePlayer(players.get(MORTY_INDEX));
			
			mortySq = mortySq.getPortalPair();
			
			mortySq.addPlayer(players.get(MORTY_INDEX));
			
			collectSeed();
		}
	}
	
	public char randomChar() {
		Random random = new Random();
		
		int index = random.nextInt(alphabet.size());
		char character = alphabet.get(index);
		alphabet.remove(index);
		
		return character;
	}
	
//	------------------------------NOT SO IMPORTANT THINGS------------------------------
	/**
	 * Generates a list of lowercase and uppercase letters of the alphabet letters.
	 */
	public void generateAlphabet() {
		for (int i = 0; i < 26; i++) {
			if(i+65 != 'R' && i+65 != 'M') {
				alphabet.add((char) (i+65));
			}
		}
		
		for (int i = 0; i < 26; i++) {
			if(i+97 != 'r' && i+97 != 'm') {
				alphabet.add((char) (i+97));
			}
		}
	}
	
	/**
	 * Adds a player to the board.
	 * 
	 * @param player the player to add.
	 */
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	/**
	 * Returns which player has the current turn. If it is the Rick's turn, returns true.
	 * If it is the Morty's turn, returns false.
	 * 
	 * @return true if Rick has the turn. False otherwise.
	 */
	public boolean getTurn() {
		if(players.get(RICK_INDEX).isTurn()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Search for a square in the board. If the square is in, returns true. Otherwise, 
	 * returns false.
	 * 
	 * @param sq the searched square.
	 * @return true if the square is in. False if not.
	 */
	public boolean containsSquare(Square sq) {
		for (int i = 0; i < (columns*rows); i++) {
			if(boardAL.get(i).getValue().equals(sq)) {
				return true;
			}
		}
		
		return false;
	}
	
	public Player getMorty() {
		return players.get(MORTY_INDEX);
	}
	
	public Player getRick() {
		return players.get(RICK_INDEX);
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public int getTotalSeeds() {
		return totalSeeds;
	}

	public void setTotalSeeds(int totalSeeds) {
		this.totalSeeds = totalSeeds;
	}

	/**
	 * Returns the string representation of the board. Depending of the boardVersion value, 
	 * constructs the normal board representation, the portals board representation, or 
	 * the seeds board representation.
	 * 
	 * @param boardVersion the value to decide which representation constructs.
	 * @return the string representation of the board.
	 */
	public String getBoardStr(int boardVersion) {
		createBoardStrAL(boardVersion);
		
		return boardStr;
	}

	public void setBoardStr(String boardStr) {
		this.boardStr = boardStr;
	}

	public ALGraph<Square> getBoardAL() {
		return boardAL;
	}

	public long getTimeBeg() {
		return timeBeg;
	}

	public void setTimeBeg(long timeBeg) {
		this.timeBeg = timeBeg;
	}

	public long getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(long timeEnd) {
		this.timeEnd = timeEnd;
	}

	public long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}

	public Square getRickSq() {
		return rickSq;
	}

	public void setRickSq(Square rickSq) {
		this.rickSq = rickSq;
	}

	public Square getMortySq() {
		return mortySq;
	}

	public void setMortySq(Square mortySq) {
		this.mortySq = mortySq;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
}