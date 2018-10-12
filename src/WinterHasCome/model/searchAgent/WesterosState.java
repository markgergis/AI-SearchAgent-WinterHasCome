package WinterHasCome.model.searchAgent;

import java.util.Arrays;
import java.util.HashSet;

public class WesterosState extends State {

	private CellType[][] grid;
	private int width;
	private int height;
	private Cell dragonStaone;
	private HashSet<Cell> obstacles;
	private HashSet<Cell> whiteWalkers;
	private int dragonStoneLimit;
	private int dragonStoneCarried;
	private int jonX;
	private int jonY;
	private int enemyCount;

	public WesterosState(CellType[][] grid, int width, int height, Cell dragonStone, HashSet<Cell> obstacles,
			HashSet<Cell> whiteWalkers, int dragonStoneLimit, int dragonStoneCarried, int jonX, int jonY,
			int enemyCount) {
		super();
		this.grid = grid;
		this.width = width;
		this.height = height;
		this.dragonStaone = dragonStone;
		this.obstacles = obstacles;
		this.whiteWalkers = whiteWalkers;
		this.dragonStoneLimit = dragonStoneLimit;
		this.dragonStoneCarried = dragonStoneCarried;
		this.jonX = jonX;
		this.jonY = jonY;
		this.enemyCount = enemyCount;
	}
	
	public String printGrid() {
		String ret = "";
		for (int i = 0; i < grid.length; i++) {
			ret += Arrays.toString(grid) + "\n";
		}
		return ret;
	}
	
	public CellType[][] getGrid() {
		return grid;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Cell getDragonStaone() {
		return dragonStaone;
	}

	public HashSet<Cell> getObstacles() {
		return obstacles;
	}

	public HashSet<Cell> getWhiteWalkers() {
		return whiteWalkers;
	}

	public int getDragonStoneLimit() {
		return dragonStoneLimit;
	}

	public int getDragonStoneCarried() {
		return dragonStoneCarried;
	}

	public int getJonX() {
		return jonX;
	}

	public int getJonY() {
		return jonY;
	}

	public int getEnemyCount() {
		return enemyCount;
	}

}
