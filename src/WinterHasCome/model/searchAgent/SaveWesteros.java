package WinterHasCome.model.searchAgent;

import java.util.HashSet;
import java.util.Random;

public class SaveWesteros extends SearchProblem {

	public SaveWesteros() {
		initialState = genGrid();
		operators = new Operator[5];
		operators[0] = new Attack(10);
		operators[1] = new GoNorth(1);
		operators[2] = new GoWest(1);
		operators[3] = new GoSouth(1);
		operators[4] = new GoEast(1);
	}

	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public WesterosState genGrid() {
		Random rand = new Random();
		int m = getRandomNumberInRange(4, 20);
		int n = getRandomNumberInRange(4, 20);
		CellType[][] grid = new CellType[m][n];
		int dc = rand.nextInt(m * n / 4);
		int oc = rand.nextInt(m * n / 5);
		HashSet<Cell> obstacles = new HashSet<Cell>(oc);
		int dragonStoneLimit = rand.nextInt(m * n);
		int x;
		int y;
		HashSet<Cell> whiteWalkers = new HashSet<>(dc);
		do {
			x = rand.nextInt(m);
			y = rand.nextInt(n);
		} while (x == m - 1 && y == n - 1);
		grid[x][y] = CellType.DRAGONSTONE;
		Cell dragonStone = new Cell(x, y);
		grid[m - 1][n - 1] = CellType.EMPTY;
		while (dc-- > 0) {
			do {
				x = rand.nextInt(m);
				y = rand.nextInt(n);
			} while (grid[x][y] != null);
			grid[x][y] = CellType.WHITEWALKER;
			whiteWalkers.add(new Cell(x, y));
		}

		while (oc-- > 0) {
			do {
				x = rand.nextInt(m);
				y = rand.nextInt(n);
			} while (grid[x][y] != null);
			grid[x][y] = CellType.OBSTACLE;
			obstacles.add(new Cell(x, y));
		}
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if ((grid[i][j] != null))
					continue;
				grid[i][j] = CellType.EMPTY;
			}
		}
		return new WesterosState(grid, n, m, dragonStone, obstacles, whiteWalkers, dragonStoneLimit, 0, dc, n - 1,
				m - 1);
	}

	public static void main(String[] args) {
			SaveWesteros s = new SaveWesteros();
			s.search(new BreadthFirstSearch());
	}

	@Override
	public boolean goalTest(State state) {
		return ((WesterosState) state).getEnemyCount() == 0;
	}
}
