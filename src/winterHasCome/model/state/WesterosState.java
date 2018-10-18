package winterHasCome.model.state;

import java.util.HashSet;

import winterHasCome.model.cell.Cell;
import winterHasCome.model.cell.CellType;

public class WesterosState extends State {

	private CellType[][] grid;
	private int width;
	private int height;
	private Cell dragonStone;
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
		this.dragonStone = dragonStone;
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
			for (int j = 0; j < grid[i].length; j++) {
				if (i == jonY && j == jonX) {
					ret += "[J]";
				} else {
					if (grid[i][j] == CellType.WHITEWALKER) {
						if (whiteWalkers.contains(new Cell(j, i))) {
							ret += grid[i][j];
						} else {
							ret += CellType.EMPTY;
						}
					} else {
						ret += grid[i][j];
					}
				}
			}
			ret += "\n";
		}
		return ret;
	}

	public CellType[][] cloneGrid() {
		CellType[][] newgrid = new CellType[grid.length][grid[0].length];
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[i].length; j++)
				newgrid[i][j] = grid[i][j];
		return newgrid;
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

	public Cell getDragonStone() {
		return dragonStone;
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
