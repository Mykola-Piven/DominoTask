package task.controller;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import task.entity.DominoTile;

public class InputControllerImpl implements InputController {
	private static final String EXIT = "exit";
	private static final String TAIL_SPLITTER = "\\[|\\]";
	private List<DominoTile> dominoTiles = new ArrayList<>();
	private Set<DominoTile> dominoTileSet = new HashSet<>();
	private StringBuilder errorMessage = new StringBuilder();
	private InputStream inputStream;
	private PrintStream printStream;
	
	public InputControllerImpl(InputStream inputStream, PrintStream printStream) {
		super();
		this.inputStream = inputStream;
		this.printStream = printStream;
	}

	@Override
	public List<DominoTile> getDominoTiles() {

		printStream.println("Enter domino tiles in following format [number1;number2]. Example: [5;4].");
		printStream.println("At the end please type 'exit': ");

		Scanner scanner = new Scanner(inputStream);
		while (scanner.hasNextLine()) {
			String inputString = scanner.nextLine();
			if (!inputString.equalsIgnoreCase(EXIT)) {
				parseInputString(inputString);
			} else{
				break;
			}
		}
		scanner.close();
		if (errorMessage.length() > 0) {
			printStream.println(errorMessage);
		}
		return dominoTiles;
	}

	private void parseInputString(String inputString) {
		String[] tiles = inputString.split(TAIL_SPLITTER);
		for (String tile : tiles) {
			if (!tile.isEmpty()) {
				byte num1 = Byte.parseByte(tile.substring(0, 1));
				byte num2 = Byte.parseByte(tile.substring(2, 3));
				DominoTile dominoTile = new DominoTile(num1, num2);
				if (verifyTile(dominoTile)) {
					dominoTiles.add(dominoTile);
				}
			}
		}
	}

	private boolean verifyTile(DominoTile dominoTile) {
		if (dominoTile.getNum1() < 0 || dominoTile.getNum1() > 6 || dominoTile.getNum2() < 0
				|| dominoTile.getNum2() > 6) {
			errorMessage.append("Incorrect domino tail: " + dominoTile + " is excluded \n");
			return false;
		}
		if (dominoTileSet.contains(dominoTile) || dominoTileSet.contains(dominoTile.getReverseTile())) {
			errorMessage.append("Current domino tail already present in chain: " + dominoTile + "\n");
			return false;
		}
		dominoTileSet.add(dominoTile);
		return true;
	}
}
