package WinterHasCome.model.cell;

import java.util.EventListener;

import WinterHasCome.model.character.WhiteWalker;

public interface CellListener extends EventListener {
	void onWhiteWalkerEncountered(WhiteWalker whiteWalker);

}
