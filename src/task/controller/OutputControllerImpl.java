package task.controller;

import java.io.PrintStream;
import java.util.Deque;

import task.entity.DominoTile;

public class OutputControllerImpl implements OutputController {
	private PrintStream printStream;

	public OutputControllerImpl(PrintStream printStream) {
		super();
		this.printStream = printStream;
	}

	@Override
	public void showDominoChain(Deque<DominoTile> dominoChain) {
		printStream.println("Longest chain is " + dominoChain);
	}

	@Override
	public void showOutOfChain(Deque<DominoTile> outOfChain) {
		printStream.println("Out of chain tiles are " + outOfChain);
	}

	@Override
	public void ShowLostTilesOfChaine(Deque<DominoTile> lostOfChain) {
		printStream.println("Lost of longest chain tiles are " + lostOfChain);
		
	}

}
