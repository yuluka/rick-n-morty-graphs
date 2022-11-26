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
			System.out.println("\nLa opción seleccionada es inválida. Vuelve a intentar.");
			menu();
			break;
		}
	}
	
	public static void selectGameMode() {
		System.out.println("\n----- Iniciar juego -----\n"
				+ "Selecciona el modo de juego que deseas:"
				+ "\n1) Modo normal."
				+ "\n2) Modo de movilidad aumentada."
				+ "\n0) Información acerca de los modos de juego.");
		
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
			System.out.println("\nLa opción que has elegido no es válida. Intenta de "
					+ "nuevo.");
			selectGameMode();
			break;
		}
	}
	
	public static void getGameModesInformation() {
		System.out.println("\n----- Información acerca de los modos de juego -----"
				+ "\nModo normal: Los jugadores pueden moverse hacia adelante o hacia "
				+ "atrás únicamente."
				+ "\nModo de movilidad aumentada: Los jugadores pueden moverse hacia ade"
				+ "lante, hacia atrás, hacia arriba y hacia abajo.");
	}
	
	public static void startGame() {
		System.out.println("\nDigita las medidas del tablero (columnas | filas):");
		
		int columns = in.nextInt();
		int rows = in.nextInt();
		
		if(!GameData.createBoard(columns, rows, gameMode, GRAPH_TYPE_IN_USE)) {
			System.out.println("\nNo es posible crear un tablero tan pequeño. Intenta Nuevamente.");
			startGame();
		}
		
		createSeeds();
	}
	
	public static void createSeeds() {
		System.out.println("\nDigita el número de semillas (no deben ser más de las casillas totales):");
		int seeds = in.nextInt();
		
		if(!GameData.createSeeds(seeds)) {
			System.out.println("\nNo es posible crear tantas semillas o crear un juego sin semillas. Intenta Nuevamente.");
			createSeeds();
		}
		
		createPortals();
	}
	
	public static void createPortals() {
		System.out.println("\nDigita el número de enlaces de portales (deben ser menos de la mitad de las casillas digitadas):");
		int portals = in.nextInt();
		
		if(!GameData.createPortals(portals)) {
			System.out.println("\nNo es posible crear tantos enlaces. Intenta Nuevamente.");
			createPortals();
		}
		
		createPlayers();
	}
	
	public static void createPlayers() {
		System.out.println("\nDigita el username del primer jugador (usará a Rick):");
		in.nextLine();
		String rick = in.nextLine();
		
		System.out.println("\nDigita el username del segundo jugador (usará a Morty):");
		String morty = in.nextLine();
		
		GameData.createPlayers(rick, morty);
		
		isEndGame();
		
		playing();
	}
	
	public static void playing() {
		System.out.println("\n----- Opciones de jugador -----"
				+ "\n¡Es el turno de " + GameData.getTurn() + "! ¿Qué deseas hacer?"
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
			System.out.println("\nLa opción seleccionada es inválida. Vuelve a intentar.");
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
		System.out.println("\n¿Hacia dónde deseas moverte?"
				+ "\n1) Adelante."
				+ "\n2) Atrás."
				+ "\n3) Arriba."
				+ "\n4) Abajo."
				+ "\n0) Usar herramienta de ayuda de movimiento.");
		
		int selection = in.nextInt();
		
		switch (selection) {
		case 1:
			System.out.println("\n*** Te moverás hacia adelante ***");
			GameData.movePlayer(MOVE_FORWARD);
			break;
			
		case 2:
			System.out.println("\n*** Te moverás hacia atrás ***");
			GameData.movePlayer(MOVE_BACKWARD);
			break;
			
		case 3:
			if(gameMode == AUMENTED_MOVEMENT_MODE) {
				System.out.println("\n*** Te moverás hacia arriba ***");
			} else {
				System.out.println("\nLa acción selecionada no está disponible en el "
						+ "modo de juego actual. Intenta nuevamente.");
				directionToMove();
			}
			
			moveUpOrDown(MOVE_UP);
			
			break;
			
		case 4:
			if(gameMode == AUMENTED_MOVEMENT_MODE) {
				System.out.println("\n*** Te moverás hacia abajo ***");
			} else {
				System.out.println("\nLa acción selecionada no está disponible en el "
						+ "modo de juego actual. Intenta nuevamente.");
				directionToMove();
			}
			
			moveUpOrDown(MOVE_DOWN);
			
			break;

		case 0:
			movementHelpTool();
			break;
			
		default:
			System.out.println("\nTu elección es inválida. Intenta nuevamente.");
			directionToMove();
			break;
		}
		
		isEndGame();
	}
	
	public static void movementHelpTool() {
		System.out.println("\n----- Herramienta de ayuda de movimiento -----"
				+ "\nDigita la casilla a la que deseas ir para obtener el camino más corto "
				+ "a recorrer:");
		int goalSq = in.nextInt();
		
		String shortestPath = GameData.constructShortestPath(goalSq);
		
//		if(GRAPH_TYPE_IN_USE == 0) {
//			shortestPath = GameData.constructShortestPathAL(goalSq);
//		} else {
//			shortestPath = GameData.constructShortestPathAM(goalSq);
//		}
		
		if (shortestPath == null) {
			System.out.println("\nLa casilla a la que deseas ir no es válida. Intenta de nuevo.");
			movementHelpTool();
		} else {
			System.out.println("\nEl camino que necesita menos movimientos para llegar desde "
					+ "tu posición hasta la casilla " + goalSq + " es: " + shortestPath);
		}
		
		directionToMove();
	}
	
	public static void moveUpOrDown(int direction) {
		if(!GameData.movePlayerUpOrDown(direction)) {
			System.out.println("\nLos movimientos disponibles no son suficientes para "
					+ "hacer el movimiento seleccionado, o el movimiento no ha sido "
					+ "posible. Intenta nuevamente.");
			
			directionToMove();
			return;
		} 
		
		System.out.println("\nTe has movido en la dirección seleccionada de manera "
				+ "correcta.");
		
		int remainingMovements = GameData.getDice();
		
		if(remainingMovements == 0) {
			System.out.println("\nNo te quedan más movimientos.");
			
			playing();
			
			return;
		} else {
			System.out.println("\nAún te quedan " + remainingMovements + " movimientos.");
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
		System.out.println("\n¡Hasta luego!");
		
		System.exit(0);
	}
	
	public static void isEndGame() {
		if(GameData.isEndGame()) {
			System.out.println("\n¡Ya se han recolectado todas las semillas! El juego ha acabado."
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