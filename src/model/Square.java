package model;

import java.util.ArrayList;

public class Square {
	
	private final int NORMAL_BOARD = 1;
	private final int PORTALS_BOARD = 2;
	
	private int number; //Es el número de la casilla.
	private ArrayList<Player> players;
	
	private String portalLetter;
	private Square portalPair;
	
	private boolean seed;
	
//	private Square next;
//	private Square previous;
	
	public Square(int number) {
		this.number = number;
		
		this.players = new ArrayList<>();
		this.seed = false;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public void removePlayer(Player player) {
		players.remove(player);
	}

	public String squareToString(int boardVersion) {
		if(boardVersion == NORMAL_BOARD) {
			return squareToStringNormal();
		} else if(boardVersion == PORTALS_BOARD) {
			return squareToStringPortals();
		} else {
			return squareToStringSeeds();
		}
	}
	
	private String squareToStringNormal() {
		if(!seed && players.isEmpty()) {
			return "[ " + number + " ]";
		} else if(!players.isEmpty()) {
			String sqAux = "[ ";
			
			for (int i = 0; i < players.size(); i++) {
				sqAux += players.get(i) + " ";
			}
			
			sqAux += "]";
			
			return sqAux;
		} else {
			return "[ * ]";
		}		
	}
	
	private String squareToStringPortals() {
		if(portalPair != null) {
			return "[ " + portalLetter + " ]";
		} else {
			return "[  ]";
		}		
	}
	
	private String squareToStringSeeds() {
		if(seed) {
			return "[ * ]";
		} else {
			return "[  ]";
		}	
	}

//	public Square getNext() {
//		return next;
//	}
//
//	public void setNext(Square next) {
//		this.next = next;
//	}
//	
//	public Square getXNext(int x) {
//		return getXNext(x, next);
//	}
//
//	public Square getXNext(int x, Square current) {
//		if(x == 0) {
//			return current;
//		}
//		
//		current = current.getNext();
//		--x;
//		return getXNext(x, current);
//	}
//	
//	public Square getPrevious() {
//		return previous;
//	}
//
//	public void setPrevious(Square previous) {
//		this.previous = previous;
//	}

	/**
	 * Returns the number, unique value between all the squares in the board, of the square. 
	 * 
	 * @return the number of the square.
	 */
	public int getNumber() {
		return number;
	}

	public String getPortalLetter() {
		return portalLetter;
	}

	public void setPortalLetter(String portalLetter) {
		this.portalLetter = portalLetter;
	}

	public Square getPortalPair() {
		return portalPair;
	}

	public void setPortalPair(Square portalPair) {
		if(portalLetter.isEmpty()) {
			portalPair = null;
		}
		
		this.portalPair = portalPair;
	}

	public boolean isSeed() {
		return seed;
	}

	public void setSeed(boolean seed) {
		this.seed = seed;
	}
}
