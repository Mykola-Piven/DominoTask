package task.common;

import java.util.Deque;
import java.util.List;

import task.controller.InputController;
import task.controller.OutputController;
import task.entity.DominoTile;
import task.service.DominoChainService;

public class DominoChain {

	private static InputController inputController = DominoFactory.getInputController();
	private static OutputController outputController = DominoFactory.getOutputController();
	private static DominoChainService dominoChainService = DominoFactory.getService();

	public static void main(String[] args) {
		List<DominoTile> dominoTiles = inputController.getDominoTiles();
		Deque<DominoTile> dominoChain = dominoChainService.getLongestChain(dominoTiles);
		Deque<DominoTile> outOfChain = dominoChainService.getOutOfChain(dominoTiles);
		Deque<DominoTile> lostOfChain = dominoChainService.getLostOfChain(dominoTiles);
		outputController.showDominoChain(dominoChain);
		outputController.showOutOfChain(outOfChain);
		outputController.ShowLostTilesOfChaine(lostOfChain);
	}
}
