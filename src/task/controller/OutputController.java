package task.controller;

import java.util.Deque;

import task.entity.DominoTile;

public interface OutputController {
	 void showDominoChain(Deque<DominoTile> dominoChain);
	 void showOutOfChain(Deque<DominoTile> outOfChain);
	 void ShowLostTilesOfChaine(Deque<DominoTile> lostOfChain);
}
