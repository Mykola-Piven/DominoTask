package task.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import task.common.TestHelper;
import task.entity.DominoTile;

public class TestInputControllerImpl {

	private static InputControllerImpl inputControllerImpl;
	private static List<DominoTile> etalonDominoTiles = new ArrayList<>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		inputControllerImpl = TestHelper.getInputControllerImpl();
		etalonDominoTiles.addAll(TestHelper.createEtalonDominoTiles());
	}

	@Test
	public void testParseInputString() {
		List<DominoTile> dominoTiles = inputControllerImpl.getDominoTiles();
		assertNotNull(dominoTiles);
		assertArrayEquals(etalonDominoTiles.toArray(), dominoTiles.toArray());
	}

}
