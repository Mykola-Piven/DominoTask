package task.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import task.controller.InputControllerImpl;
import task.entity.DominoTile;
import task.service.DominoChainServiceImpl;

public class TestHelper {
	private static final String TEST_STRING = "[1;1][6;0][5;6][3;4][2;4][6;4]\n" + "exit\n";

	public static InputControllerImpl getInputControllerImpl() {
		InputStream inputStream = new ByteArrayInputStream(TEST_STRING.getBytes());
		PrintStream printStream = new PrintStream(new ByteArrayOutputStream());
		InputControllerImpl inputControllerImpl = new InputControllerImpl(inputStream, printStream);
		return inputControllerImpl;
	}

	public static DominoChainServiceImpl getDominoChainServiceImpl() {
		DominoChainServiceImpl dominoChainServiceImpl = new DominoChainServiceImpl();
		return dominoChainServiceImpl;
	}

	public static List<DominoTile> createEtalonDominoTiles() {
		List<DominoTile> etalonDominoTiles = new ArrayList<>();
		etalonDominoTiles.add(createDominoTile("1", "1"));
		etalonDominoTiles.add(createDominoTile("6", "0"));
		etalonDominoTiles.add(createDominoTile("5", "6"));
		etalonDominoTiles.add(createDominoTile("3", "4"));
		etalonDominoTiles.add(createDominoTile("2", "4"));
		etalonDominoTiles.add(createDominoTile("6", "4"));
		return etalonDominoTiles;
	}

	public static Deque<DominoTile> createEtalonLongestChain() {
		Deque<DominoTile> etalonLongestChain = new LinkedList<>();
		addDominoTileToChain(etalonLongestChain, createDominoTile("0", "6"));
		addDominoTileToChain(etalonLongestChain, createDominoTile("6", "4"));
		addDominoTileToChain(etalonLongestChain, createDominoTile("4", "3"));
		return etalonLongestChain;
	}

	public static Deque<DominoTile> createEtalonOutOfChain() {
		Deque<DominoTile> etalonOutOfChain = new LinkedList<>();
		addDominoTileToChain(etalonOutOfChain, createDominoTile("1", "1"));
		return etalonOutOfChain;
	}

	public static Deque<DominoTile> createEtalonLostOfChain() {
		Deque<DominoTile> etalonLostOfChain = new LinkedList<>();

		addDominoTileToChain(etalonLostOfChain, createDominoTile("5", "6"));
		addDominoTileToChain(etalonLostOfChain, createDominoTile("2", "4"));
		return etalonLostOfChain;
	}

	private static DominoTile createDominoTile(String num1, String num2) {
		return new DominoTile(Byte.valueOf(num1), Byte.valueOf(num2));
	}

	private static void addDominoTileToChain(Deque<DominoTile> chain, DominoTile tile) {
		chain.addLast(tile);
	}

}
