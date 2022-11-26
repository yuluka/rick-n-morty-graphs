package model;

import java.util.ArrayList;

public class Square {
	
	private final int NORMAL_BOARD = 1;
	private final int PORTALS_BOARD = 2;
	
	private int number; //The square number.
	private ArrayList<Player> players; //The list of the players placed in this squares.
	
	private String portalLetter; //The letter representation of the portal.
	private Square portalPair; //The other part of the portal.
	
	private boolean seed;
	
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
	
	/**
	 * Search for a player in the square. If the player is in, returns true. Otherwise, 
	 * returns false.
	 * 
	 * @param player the searched player.
	 * @return true if the player is in. False if not.
	 */
	public boolean containsPlayer(Player player) {
		return containsPlayer(player.getUsername());
	}
	
	public boolean containsPlayer(String playerUsername) {
		for (int i = 0; i < players.size(); i++) {
			if(players.get(i).getUsername().equals(playerUsername)) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Returns the string representation of the square, depending on the boardVersion
	 * value.
	 * 
	 * @param boardVersion the wanted version of the string representation. 1 to get the 
	 * normal, 2 to see the portals placed in, and any other to see the seed in the square.
	 * @return the string representation of the square
	 */
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
				sqAux += players.get(i).getName() + " ";
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
