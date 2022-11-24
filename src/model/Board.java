package model;

import java.util.ArrayList;

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
//			collectSeed(); //Checks if there is a seed in the final square.
//			teleport(); //Checks if there is a portal in the final square.
//			collectSeed(); //Checks if there is a seed in the final square, after have been teleported (if is the case).
			
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
	
	public void addPlayer(Player player) {
		players.add(player);
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