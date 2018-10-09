package WinterHasCome.model.searchAgent;

import java.util.ArrayList;
import java.util.Random;

import WinterHasCome.model.character.WhiteWalker;
import WinterHasCome.model.player.Player;
import WinterHasCome.model.world.World;
import WinterHasCome.model.world.WorldListener;

public class SaveWesteros extends SearchProblem {
	
	private Player player;
	private World world;
	
	public SaveWesteros() {
		world = new World(getRandomNumberInRange(4, 20), getRandomNumberInRange(4,20));
		world.genGrid();
		player = new Player(world.getJonDragonglass());
		System.out.println(world.toString());

	}
	
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	public Player getPlayer() {
		return player;
	}

	public World getWorld() {
		return world;
	}
	
	public static void main(String[] args) {
		SaveWesteros search = new SaveWesteros();
	}

	@Override
	public boolean goalTest(State state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int pathCost(ArrayList action) {
		// TODO Auto-generated method stub
		return 0;
	}

}
