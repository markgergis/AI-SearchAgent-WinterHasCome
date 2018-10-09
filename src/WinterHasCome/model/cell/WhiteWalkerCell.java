package WinterHasCome.model.cell;

import WinterHasCome.model.character.WhiteWalker;

public class WhiteWalkerCell extends Cell {


	public WhiteWalkerCell() {
	}

	public String toString() {
		return "[w]";
	}


	@Override
	public void onStep() {
		if (getListener() != null) {
			this.getListener().onWhiteWalkerEncountered(null);

		}
	}

}
