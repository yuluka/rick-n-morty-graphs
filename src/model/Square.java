package model;

public class Square {
	
	private final int NORMAL_BOARD = 1;
	private final int PORTALS_BOARD = 2;
	
	private int number; //Es le número de la casilla.
	private Player player;
	
	private String portalLetter;
	private Square portalPair;
	
	private boolean seed;
	
//	private Square next;
//	private Square previous;
	
	public Square(int number) {
		this.number = number;
		
		this.player = null;
		this.seed = false;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
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
	
	public String squareToStringNormal() {
		if(!seed && player == null) {
			return "[ " + number + " ]";
		} else if(player != null) {
			return "[ " + player.getName() + " ]";
		} else {
			return "[ * ]";
		}		
	}
	
	public String squareToStringPortals() {
		if(portalPair != null) {
			return "[ " + portalLetter + " ]";
		} else {
			return "[  ]";
		}		
	}
	
	public String squareToStringSeeds() {
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
