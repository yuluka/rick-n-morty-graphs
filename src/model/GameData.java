package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@SuppressWarnings("serial")
public class GameData implements Serializable {
	
	private static int GRAPH_TYPE_IN_USE = 0;
	
	private final static int MAX_PORTALS = 48; //The maximum quantity of portals that can be created.
	private final static int MIN_PORTALS = 0;
	
	private final static int FORWARD_DIRECTION = 1;
	private final static int BACKWARD_DIRECTION = -1;
	private final static int UP_DIRECTION = 1;
	private final static int DOWN_DIRECTION = -1;
	
	private static Board board;
	private static ArrayList<Player> scores = new ArrayList<Player>();
	
	private static int dice;
	
	public GameData() {
		dice = 0;
	}
	
	/**
	 * Creates a new board with the specified dimensions.
	 * 
	 * @param col the number of columns the board will have.
	 * @param rows the number of rows the board will have.
	 * @return false if the dimensions are 1*1. True otherwise.
	 */
	public static boolean createBoard(int col, int rows, int gameMode, int graphType) {
		if((col*rows) <= 1) {
			return false;
		}
		
		board = new Board(col, rows, gameMode, graphType);
		
		GRAPH_TYPE_IN_USE = graphType;
		
		return true;
	}
	
	/**
	 * Creates the specified number of portals inside the previously existent board.
	 * 
	 * @param portals the board's portals quantity.
	 * @return false if the specified portals quantity is greater than (columns*rows)/2,
	 * or is greater than the maximum quantity of portals allowed, or is less than the 
	 * minimum quantity of portals allowed. True otherwise.
	 */
	public static boolean createPortals(int portals) {
		if(portals >= (board.getColumns()*board.getRows())/2 || portals > MAX_PORTALS 
				|| portals < MIN_PORTALS) {
			return false;
		} else {
			if(GRAPH_TYPE_IN_USE == 0) {
				board.generatePortalsAL(portals);
			} else {
				board.generatePortalsAM(portals);
			}
			
			return true;
		}
	}
	
	/**
	 * Creates the specified quantity of seeds inside the board.
	 * 
	 * @param seeds the board's seeds quantity.
	 * @return false if the specified quantity is greater than the dimensions of the board,
	 * or is less or equals to 0. True otherwise.
	 */
	public static boolean createSeeds(int seeds) { 
		if(seeds > (board.getColumns()*board.getRows()) || seeds <= 0) { 
			return false; 
		} else { 
			if(GRAPH_TYPE_IN_USE == 0) {
				board.createSeedsAL(seeds);
			} else {
				board.createSeedsAM(seeds);
			}
			
			return true; 
		} 
	}
	
	/**
	 * Creates the players in the board.
	 * 
	 * @param usernameR the nickname of the player that is playing as Rick.
	 * @param usernameM the nickname of the player that is playing as Morty.
	 */
	public static void createPlayers(String usernameR, String usernameM) {
		if(GRAPH_TYPE_IN_USE == 0) {
			board.positionPlayersAL(usernameR, usernameM);
		} else {
			board.positionPlayersAM(usernameR, usernameM);
		}
	}
	
	public static String printBoard(int boardVersion) {
		return board.getBoardStr(boardVersion);
	}
	
	/**
	 * Launchs the dice.
	 * 
	 * @return the launching result.
	 */
	public static int launchDice() {
		dice = board.launchDice();
		
		return dice;
	}
	
	/**
	 * Moves the player with the current turn to the specified direction (forward or 
	 * backward). If the specified direction is not valid, the method does nothing.
	 * 
	 * @param direction the direction to move.
	 */
	public static void movePlayer(int direction) {
		if(direction != FORWARD_DIRECTION && direction != BACKWARD_DIRECTION) {
			return;
		}
		
		if(GRAPH_TYPE_IN_USE == 0) {
			board.movePlayerAL(dice, direction);
		} else {
			board.movePlayerAM(dice, direction);
		}
	}
	
	/**
	 * Moves the player with the current turn to the specified direction (up or 
	 * down). If the specified direction is not valid, the method does nothing.
	 * 
	 * @param direction the direction to move.
	 */
	public static boolean movePlayerUpOrDown(int direction) {
		if(direction != UP_DIRECTION && direction != DOWN_DIRECTION) {
			return false;
		}
		
		if(GRAPH_TYPE_IN_USE == 0) {
			if(board.movePlayerUpOrDownAL(dice, direction)){
				dice -= board.getUP_DOWN_MOVEMENTS_COST();
				return true;
			} else {
				return false;
			}
		} else {
			if(board.movePlayerUpOrDownAM(dice, direction)){
				dice -= board.getUP_DOWN_MOVEMENTS_COST();
				return true;
			} else {
				return false;
			}
		}
	}
	
	public static String constructShortestPath(int goalSq) {
		if(GRAPH_TYPE_IN_USE == 0) {
			return constructShortestPathAL(goalSq);
		} else {
			return constructShortestPathAM(goalSq);
		}
	}
	
	public static String constructShortestPathAL(int goalSq) {
		if(goalSq > (board.getColumns()*board.getRows()) || goalSq < 1) {
			return null;
		}
		
		if(getTurn().equals("R")) {
			return board.constructShortestPathAL(board.getRickSq().getNumber(), goalSq);
		} else {
			return board.constructShortestPathAL(board.getMortySq().getNumber(), goalSq);
		}
	}
	
	public static String constructShortestPathAM(int goalSq) {
		if(goalSq > (board.getColumns()*board.getRows()) || goalSq < 1) {
			return null;
		}
		
		if(getTurn().equals("R")) {
			return board.constructShortestPathAM(board.getRickSq().getNumber(), goalSq);
		} else {
			return board.constructShortestPathAM(board.getMortySq().getNumber(), goalSq);
		}
	}
	
	/**
	 * Returns the quantity of seeds that the specified player has.
	 * 
	 * @param playerName the player to be consulted.
	 * @return the quantity of seeds that the specified player has.
	 */
	public static int getPlayerSeeds(String playerName) {
		if(playerName == "R") {
			return board.getRick().getSeeds();
		} else {
			return board.getMorty().getSeeds();
		}
	}
	
	/**
	 * Returns the letter representation of the player that has the actual turn.
	 * 
	 * @return "R" if the player that is playing as Rick has the turn. "M" if not.
	 */
	public static String getTurn() {
		if(board.getRick().isTurn()) {
			return "R";
		} else {
			return "M";
		}
	}
	
	/**
	 * Checks if the game has to end.
	 * 
	 * @return true if the game has to end. False if not.
	 */
	public static boolean isEndGame() {
		return board.isEndGame();
	}
	
	public static void registerScore() {
		Player winner = board.getWinner();
		winner.setScore(board.calculateScore());
		
		int index = searchPlayerScore(winner);
		
		if(index < 0) {
			scores.add(winner);
		} else {
			scores.get(index).addScore(winner.getScore());
		}
		
		saveScores();
	}
	
	/**
	 * Searches for a player in the list whit all the scores and returns the index where
	 * is located, in the list.
	 * 
	 * @param player the searched player.
	 * @return the index where the player is located. If the player is not found, returns
	 * -1.
	 */
	public static int searchPlayerScore(Player player) {
		if(scores.size() == 0) {
			return -1;
		}
		
		sortScoresByName();
		
		return searchPlayerScore(0, scores.size(), player);
	}
	
	private static int searchPlayerScore(int beg, int end, Player goal) {
		if(end < beg) {
			return -1;	
		}
		
		int mid = (end+beg)/2;
		
		if(mid >= scores.size()) {
			return -1;
		}
		
		int comparation = goal.getUsername().compareTo(scores.get(mid).getUsername());
		
		if(comparation == 0) {
			return mid;
		} else if(comparation < 0) {
			end = mid-1;
			return searchPlayerScore(beg,end,goal);
		} else {
			beg = mid+1;
			return searchPlayerScore(beg,end,goal);
		}
	}
	
	/**
	 * Sorts the list with all the scores by the players names.
	 */
	private static void sortScoresByName() {
		Collections.sort(scores, new Comparator<Player>() {
			@Override
			public int compare(Player A, Player B) {
				return A.getUsername().compareTo(B.getUsername());
			}
		});
	}
	
	/**
	 * Generates the top 5 of the greatest global scores, and returns it.
	 * 
	 * @return the top 5 of the greatest global scores.
	 */
	public static String getTop5() {
		String top5 = "";
		sortScoresByScore();
		
		for (int i = 0; i < scores.size() && i < 5; i++) {
			top5 += "\n" + scores.get(i).getUsername() + ": " + scores.get(i).getScore();
		}
		
		return top5;
	}
	
	/**
	 * Sorts the list with all the scores by the players scores.
	 */
	private static void sortScoresByScore() {
		for (int i = 1; i < scores.size(); i++) {
			for (int j = 0; j < i; j++) {
				if(scores.get(i).getScore() > scores.get(j).getScore()) {
					Player aux = scores.get(i);
					scores.remove(i);
					scores.add(j, aux);
					break;
				}
			}
		}
	}
	
	public static String getScoresStr() {
		String scoresStr = "";
		
		for (int i = 0; i < scores.size(); i++) {
			scoresStr += "\n" + scores.get(i).getUsername() + ": " + 
					scores.get(i).getScore();
		}
		
		return scoresStr;
	}
	
	public static void saveScores() {
		try {
			File file = new File("data/scores.temp");
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(scores);
			
			oos.close();			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void loadScores() {
		try {
			File file = new File("data/scores.temp");
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object object = ois.readObject();
			scores = (ArrayList<Player>) object;
			
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the winner of the last game.
	 * 
	 * @return the winner of the last game.
	 */
	public static String getWinner() {
		if(board.getWinner().getName().equals("M")) {
			return "Morty con " + board.getMorty().getSeeds() + " semillas.";
		} else {
			return "Rick con " + board.getRick().getSeeds() + " semillas.";
		}
	}

	public static Board getBoard() {
		return board;
	}

	public static void setBoard(Board board) {
		GameData.board = board;
	}

	public static ArrayList<Player> getScores() {
		return scores;
	}

	public static void setScores(ArrayList<Player> scores) {
		GameData.scores = scores;
	}

	public static int getDice() {
		return dice;
	}	
}