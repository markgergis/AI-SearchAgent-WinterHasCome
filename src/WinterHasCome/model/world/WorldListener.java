package WinterHasCome.model.world;

import java.util.EventListener;

import WinterHasCome.model.character.WhiteWalker;

public interface WorldListener extends EventListener{
	public void onWhiteWalkerEncountered(WhiteWalker whiteWalker);

}
