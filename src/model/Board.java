package model;

import java.util.ArrayList;

import dataStructures.ALGraph;
import dataStructures.ALVertex;
import dataStructures.Graph;

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
	 * To connect two squares, they must be consecutive. At the end, connects the final square with 
	 * the initial one.
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
	
//	------------------------------NOT SO IMPORTANT THING------------------------------
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

	public String getBoardStr() {
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