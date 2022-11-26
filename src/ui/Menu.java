package ui;

import java.util.Scanner;

import model.GameData;

public class Menu {
	
	private static Scanner in = new Scanner(System.in);
	
	private static final int NORMAL_MODE = 0;
	private static final int AUMENTED_MOVEMENT_MODE = 1;
	
	private static final int MOVE_FORWARD = 1;
	private static final int MOVE_BACKWARD = -1;
	private static final int MOVE_UP = 1;
	private static final int MOVE_DOWN = -1;
	
	private static final int GRAPH_TYPE_IN_USE = 1; //0 to use an AL Graph, 1 to use an AM Graph.
	
	private static int gameMode = -1;
	
	public static void menu() {
		System.out.println("\n----- Selecciona lo que quieres hacer -----"
				+ "\n1) Iniciar juego."
				+ "\n2) Ver puntajes globales."
				+ "\n0) Salir");
		
		int selection = in.nextInt();
		
		switch (selection) {
		case 1:
			selectGameMode();
			break;

		case 2:
			seeScores();
			break;
			
		case 0:
			exit();
			break;
			
		default:
			System.out.println("\nLa opci�n seleccionada es inv�lida. Vuelve a intentar.");
			menu();
			break;
		}
	}
	
	public static void selectGameMode() {
		System.out.println("\n----- Iniciar juego -----\n"
				+ "Selecciona el modo de juego que deseas:"
				+ "\n1) Modo normal."
				+ "\n2) Modo de movilidad aumentada."
				+ "\n0) Informaci�n acerca de los modos de juego.");
		
		int selection = in.nextInt();
		
		switch (selection) {
		case 1:
			gameMode = NORMAL_MODE;
			startGame();
			break;
			
		case 2:
			gameMode = AUMENTED_MOVEMENT_MODE;
			startGame();
			break;
			
		case 0:
			getGameModesInformation();
			selectGameMode();
			break;

		default:
			System.out.println("\nLa opci�n que has elegido no es v�lida. Intenta de "
					+ "nuevo.");
			selectGameMode();
			break;
		}
	}
	
	public static void getGameModesInformation() {
		System.out.println("\n----- Informaci�n acerca de los modos de juego -----"
				+ "\nModo normal: Los jugadores pueden moverse hacia adelante o hacia "
				+ "atr�s �nicamente."
				+ "\nModo de movilidad aumentada: Los jugadores pueden moverse hacia ade"
				+ "lante, hacia atr�s, hacia arriba y hacia abajo.");
	}
	
	public static void startGame() {
		System.out.println("\nDigita las medidas del tablero (columnas | filas):");
		
		int columns = in.nextInt();
		int rows = in.nextInt();
		
		if(!GameData.createBoard(columns, rows, gameMode, GRAPH_TYPE_IN_USE)) {
			System.out.println("\nNo es posible crear un tablero tan peque�o. Intenta Nuevamente.");
			startGame();
		}
		
		createSeeds();
	}
	
	public static void createSeeds() {
		System.out.println("\nDigita el n�mero de semillas (no deben ser m�s de las casillas totales):");
		int seeds = in.nextInt();
		
		if(!GameData.createSeeds(seeds)) {
			System.out.println("\nNo es posible crear tantas semillas o crear un juego sin semillas. Intenta Nuevamente.");
			createSeeds();
		}
		
		createPortals();
	}
	
	public static void createPortals() {
		System.out.println("\nDigita el n�mero de enlaces de portales (deben ser menos de la mitad de las casillas digitadas):");
		int portals = in.nextInt();
		
		if(!GameData.createPortals(portals)) {
			System.out.println("\nNo es posible crear tantos enlaces. Intenta Nuevamente.");
			createPortals();
		}
		
		createPlayers();
	}
	
	public static void createPlayers() {
		System.out.println("\nDigita el username del primer jugador (usar� a Rick):");
		in.nextLine();
		String rick = in.nextLine();
		
		System.out.println("\nDigita el username del segundo jugador (usar� a Morty):");
		String morty = in.nextLine();
		
		GameData.createPlayers(rick, morty);
		
		isEndGame();
		
		playing();
	}
	
	public static void playing() {
		System.out.println("\n----- Opciones de jugador -----"
				+ "\n�Es el turno de " + GameData.getTurn() + "! �Qu� deseas hacer?"
				+ "\n1) Tirar dado."
				+ "\n2) Ver tablero."
				+ "\n3) Ver enlaces."
				+ "\n4) Ver semillas."
				+ "\n5) Ver marcador."
				+ "\n0) Salir.");
		
		int selection = in.nextInt();
		
		switch (selection) {
		case 1:
			launchDice();
			break;

		case 2:
			seeBoard();
			break;
			
		case 3:
			seePortals();
			break;
		
		case 4:
			seeBoardSeeds();
			break;
			
		case 5:
			showMarker();
			break;
			
		case 0:
			menu();
			break;
			
		default:
			System.out.println("\nLa opci�n seleccionada es inv�lida. Vuelve a intentar.");
			startGame();
			break;
		}
	}
	
	public static void launchDice() {
		int diceResult = GameData.launchDice();
		System.out.println("\nResultado del dado: " + diceResult);		
		directionToMove();
	}
	
	public static void directionToMove() {
		System.out.println("\n�Hacia d�nde deseas moverte?"
				+ "\n1) Adelante."
				+ "\n2) Atr�s."
				+ "\n3) Arriba."
				+ "\n4) Abajo.");
		
		int selection = in.nextInt();
		
		switch (selection) {
		case 1:
			System.out.println("\n*** Te mover�s hacia adelante ***");
			GameData.movePlayer(MOVE_FORWARD);
			break;
			
		case 2:
			System.out.println("\n*** Te mover�s hacia atr�s ***");
			GameData.movePlayer(MOVE_BACKWARD);
			break;
			
		case 3:
			if(gameMode == AUMENTED_MOVEMENT_MODE) {
				System.out.println("\n*** Te mover�s hacia arriba ***");
			} else {
				System.out.println("\nLa acci�n selecionada no est� disponible en el "
						+ "modo de juego actual. Intenta nuevamente.");
				directionToMove();
			}
			
			moveUpOrDown(MOVE_UP);
			
			break;
			
		case 4:
			if(gameMode == AUMENTED_MOVEMENT_MODE) {
				System.out.println("\n*** Te mover�s hacia abajo ***");
			} else {
				System.out.println("\nLa acci�n selecionada no est� disponible en el "
						+ "modo de juego actual. Intenta nuevamente.");
				directionToMove();
			}
			
			moveUpOrDown(MOVE_DOWN);
			
			break;

		default:
			System.out.println("\nTu elecci�n es inv�lida. Intenta nuevamente.");
			directionToMove();
			break;
		}
		
		isEndGame();
	}
	
	public static void moveUpOrDown(int direction) {
		if(!GameData.movePlayerUpOrDown(direction)) {
			System.out.println("\nLos movimientos disponibles no son suficientes para "
					+ "hacer el movimiento seleccionado, o el movimiento no ha sido "
					+ "posible. Intenta nuevamente.");
			
			directionToMove();
			return;
		} 
		
		System.out.println("\nTe has movido en la direcci�n seleccionada de manera "
				+ "correcta.");
		
		int remainingMovements = GameData.getDice();
		
		if(remainingMovements == 0) {
			System.out.println("\nNo te quedan m�s movimientos.");
			
			playing();
			
			return;
		} else {
			System.out.println("\nA�n te quedan " + remainingMovements + " movimientos.");
			directionToMove();	
		}
	}
	
	public static void seeBoard() {
		int boardVersion = 1; // Normal board
		
		System.out.println("\n" + GameData.printBoard(boardVersion));
		playing();
	} 
	
	public static void seePortals() {
		int boardVersion = 2; // Board with just portals
		
		System.out.println("\n" + GameData.printBoard(boardVersion));
		playing();
	}
	
	public static void seeBoardSeeds() {
		int boardVersion = 3; // Board with just seeds
		
		System.out.println("\n" + GameData.printBoard(boardVersion));
		playing();
	}
	
	public static void showMarker() {
		System.out.println("\nRick: " + GameData.getPlayerSeeds("R") + " semillas." +
				"\nMorty: " + GameData.getPlayerSeeds("M") + " semillas.");
		
		playing();
	}
	
	public static void seeScores() {
		System.out.println("\n----- Puntajes globales -----"
				+ GameData.getScoresStr());
		
		menu();
	}
	
	public static void exit() {
		System.out.println("\n�Hasta luego!");
		
		System.exit(0);
	}
	
	public static void isEndGame() {
		if(GameData.isEndGame()) {
			System.out.println("\n�Ya se han recolectado todas las semillas! El juego ha acabado."
					+ "\nEl ganador es: " + GameData.getWinner());
			
			GameData.registerScore();
			
			System.out.println("\n----- Top 5 -----"
					+ GameData.getTop5());
			
			menu();
		} else {
			playing();
		}
	}
}