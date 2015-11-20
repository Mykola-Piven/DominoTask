package task.service;

import java.util.Deque;
import java.util.List;

import task.entity.DominoTile;

public interface DominoChainService {

	public Deque<DominoTile> getLongestChain(List<DominoTile> dominoTiles);
	public Deque<DominoTile> getOutOfChain(List<DominoTile> dominoTiles);
	public Deque<DominoTile> getLostOfChain(List<DominoTile> dominoTiles);
}
