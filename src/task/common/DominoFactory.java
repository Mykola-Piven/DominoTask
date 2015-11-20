package task.common;

import task.controller.InputController;
import task.controller.InputControllerImpl;
import task.controller.OutputController;
import task.controller.OutputControllerImpl;
import task.service.DominoChainService;
import task.service.DominoChainServiceImpl;

public class DominoFactory {

	public static DominoChainService getService() {
		return new DominoChainServiceImpl();
	}

	public static InputController getInputController() {
		return new InputControllerImpl(System.in, System.out);
	}

	public static OutputController getOutputController() {
		return new OutputControllerImpl(System.out);
			
	}

}
