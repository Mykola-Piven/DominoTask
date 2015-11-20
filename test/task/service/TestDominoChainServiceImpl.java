package task.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import task.common.TestHelper;
import task.entity.DominoTile;

public class TestDominoChainServiceImpl {

	private static List<DominoTile> etalonDominoTiles = new ArrayList<>();
	private static DominoChainService dominoChainService;
	private static Deque<DominoTile> etalonLongestChain = new LinkedList<>();
	private static Deque<DominoTile> etalonLostOfChain = new LinkedList<>();
	private static Deque<DominoTile> etalonOutOfChain = new LinkedList<>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dominoChainService = TestHelper.getDominoChainServiceImpl();
		etalonDominoTiles.addAll(TestHelper.createEtalonDominoTiles());
		etalonLongestChain.addAll(TestHelper.createEtalonLongestChain());
		etalonLostOfChain.addAll(TestHelper.createEtalonLostOfChain());
		etalonOutOfChain.addAll(TestHelper.createEtalonOutOfChain());
	}

	@Test
	public void testGetLongestChain() {
		Deque<DominoTile> longestChain = dominoChainService.getLongestChain(etalonDominoTiles);
		
		assertNotNull(longestChain);
		assertArrayEquals(etalonLongestChain.toArray(), longestChain.toArray());
	}

	@Test
	public void testGetLostOfChain() {
		Deque<DominoTile> lostOfChain = dominoChainService.getLostOfChain(etalonDominoTiles);
		
		assertNotNull(lostOfChain);
		assertArrayEquals(etalonLostOfChain.toArray(), lostOfChain.toArray());
	}

	@Test
	public void testGetOutOfChain() {
		Deque<DominoTile> outOfChain = dominoChainService.getOutOfChain(etalonDominoTiles);
		
		assertNotNull(outOfChain);
		assertArrayEquals(etalonOutOfChain.toArray(), outOfChain.toArray());
	}

}
