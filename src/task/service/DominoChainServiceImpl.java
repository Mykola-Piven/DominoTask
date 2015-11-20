package task.service;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import task.entity.DominoTile;

public class DominoChainServiceImpl implements DominoChainService {
	private Deque<DominoTile> longestChain = new LinkedList<>();
	private Deque<DominoTile> outOfChain = new LinkedList<>();

	@Override
	public Deque<DominoTile> getLongestChain(List<DominoTile> dominoTiles) {
		List<DominoTile> correctDominoTiles = new ArrayList<>();
		correctDominoTiles.addAll(dominoTiles);
		findTilesOutOfChain(correctDominoTiles);
		correctDominoTiles.removeAll(outOfChain);

		for (int i = 0; i < correctDominoTiles.size(); i++) {
			Deque<DominoTile> candidateChane = findDominoChain(correctDominoTiles, i);
			if (candidateChane.size() > longestChain.size()) {
				longestChain = candidateChane;
			}
			if (longestChain.size() == correctDominoTiles.size()) {
				break;
			}
		}
		return longestChain;

	}

	@Override
	public Deque<DominoTile> getOutOfChain(List<DominoTile> dominoTiles) {
		if (outOfChain.isEmpty()) {
			findTilesOutOfChain(dominoTiles);
		}
		return outOfChain;
	}

	@Override
	public Deque<DominoTile> getLostOfChain(List<DominoTile> dominoTiles) {
		Deque<DominoTile> outOfChain = new LinkedList<>();
		Deque<DominoTile> longestChain = new LinkedList<>();
		Deque<DominoTile> lostOfChain = new LinkedList<>();
		if (this.outOfChain.isEmpty()) {
			outOfChain.addAll(getOutOfChain(dominoTiles));
		} else {
			outOfChain.addAll(this.outOfChain);
		}
		if (this.longestChain.isEmpty()) {
			longestChain.addAll(getLongestChain(dominoTiles));
		} else {
			longestChain.addAll(this.longestChain);
		}
		lostOfChain.addAll(dominoTiles);
		for (Iterator<DominoTile> iterator = lostOfChain.iterator(); iterator.hasNext();) {
			DominoTile dominoTile = iterator.next();
			if (longestChain.contains(dominoTile) || longestChain.contains(dominoTile.getReverseTile())) {
				iterator.remove();
			} else if (outOfChain.contains(dominoTile) || outOfChain.contains(dominoTile.getReverseTile())) {
				iterator.remove();
			}
		}
		return lostOfChain;
	}

	private Deque<DominoTile> findDominoChain(List<DominoTile> dominoTiles, int iterator) {
		Deque<DominoTile> dominoChain = new LinkedList<>();
		dominoChain.add(dominoTiles.get(iterator));

		List<DominoTile> candidatesToChain = new ArrayList<>();
		candidatesToChain.addAll(dominoTiles);
		candidatesToChain.remove(iterator);
		buildDominoChain(dominoChain, candidatesToChain);
		return dominoChain;

	}

	private void buildDominoChain(Deque<DominoTile> dominoChain, List<DominoTile> candidatesToChain) {

		int lostTilesNumber = 0;
		while (lostTilesNumber <= candidatesToChain.size()) {
			Iterator<DominoTile> iterator = candidatesToChain.iterator();
			while (iterator.hasNext()) {
				DominoTile tile = iterator.next();
				if (dominoChain.getLast().getNum2() == tile.getNum1()) {
					// connect to right
					dominoChain.addLast(tile);
					iterator.remove();
				} else if (dominoChain.getLast().getNum2() == tile.getNum2()) {
					// connect to right and turn over
					dominoChain.addLast(tile.getReverseTile());
					iterator.remove();
				} else if (dominoChain.getFirst().getNum1() == tile.getNum2()) {
					// connect to left
					dominoChain.addFirst(tile);
					iterator.remove();
				} else if (dominoChain.getFirst().getNum1() == tile.getNum1()) {
					// connect to left and turn over
					dominoChain.addFirst(tile.getReverseTile());
					iterator.remove();
				} else {
					lostTilesNumber++;
				}
			}
		}
	}

	private void findTilesOutOfChain(List<DominoTile> dominoTiles) {
		boolean linkToOtherTile = false;
		for (int i = 0; i < dominoTiles.size() - 1; i++) {
			linkToOtherTile = false;
			for (int j = 0; j < dominoTiles.size(); j++) {
				if (i != j) {
					if (dominoTiles.get(i).getNum1() == dominoTiles.get(j).getNum1()
							|| dominoTiles.get(i).getNum1() == dominoTiles.get(j).getNum2()
							|| dominoTiles.get(i).getNum2() == dominoTiles.get(j).getNum1()
							|| dominoTiles.get(i).getNum2() == dominoTiles.get(j).getNum2()) {
						linkToOtherTile = true;
						break;
					}
				}
			}
			if (!linkToOtherTile) {
				if (!outOfChain.contains(dominoTiles.get(i))
						|| !outOfChain.contains(dominoTiles.get(i).getReverseTile())) {
					outOfChain.add(dominoTiles.get(i));
				}
			}
		}
	}

}
