package WinterHasCome.model.searchAgent;

import java.util.HashSet;
import java.util.Random;

public class SaveWesteros extends SearchProblem {

	public SaveWesteros() {
		initialState = genGrid();
		operators = new Operator[5];
		operators[0] = new Attack(2);
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
		int m = getRandomNumberInRange(4, 5); // this is y = column
		int n = getRandomNumberInRange(4, 5); // this is x = row
		CellType[][] grid = new CellType[m][n];
		int dc = rand.nextInt(m * n / 4);
		int wwc = dc;
		int oc = rand.nextInt(m * n / 5);
		HashSet<Cell> obstacles = new HashSet<Cell>(oc);
		int dragonStoneLimit = rand.nextInt(m * n);
		int x;
		int y;
		HashSet<Cell> whiteWalkers = new HashSet<>(dc);
		do {
			y = rand.nextInt(m);
			x = rand.nextInt(n);
		} while (x == n - 1 && y == m - 1);
		grid[y][x] = CellType.DRAGONSTONE;
		Cell dragonStone = new Cell(x, y);
		grid[m - 1][n - 1] = CellType.EMPTY;
		while (dc-- > 0) {
			do {
				y = rand.nextInt(m);
				x = rand.nextInt(n);
			} while (grid[y][x] != null);
			grid[y][x] = CellType.WHITEWALKER;
			whiteWalkers.add(new Cell(x, y));
		}

		while (oc-- > 0) {
			do {
				y = rand.nextInt(m);
				x = rand.nextInt(n);
			} while (grid[y][x] != null);
			grid[y][x] = CellType.OBSTACLE;
			obstacles.add(new Cell(x, y));
		}
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if ((grid[i][j] != null))
					continue;
				grid[i][j] = CellType.EMPTY;
			}
		}
		System.out.println(n+" "+ m);
		// print the grid for visualization 
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
		return new WesterosState(grid, n, m, dragonStone, obstacles, whiteWalkers, dragonStoneLimit, 0, n - 1,
				m - 1,wwc);
	}

	public static void main(String[] args) {
			SaveWesteros s = new SaveWesteros();
			s.search(new IterativeDeepeningSearch((Integer.MAX_VALUE),s.initialState));
	}

	@Override
	public boolean goalTest(State state) {
//		System.out.println(((WesterosState) state).getDragonStaone().x + " " + ((WesterosState) state).getDragonStaone().y);
//		System.out.println(((WesterosState) state).getJonX() + " " + ((WesterosState) state).getJonY());
//		return ((WesterosState) state).getDragonStaone().x == ((WesterosState) state).getJonX() && ((WesterosState) state).getDragonStaone().y == ((WesterosState) state).getJonY();
//		System.out.println("number of ww: "+ ((WesterosState) state).getEnemyCount());
//		System.out.println(initialState.getEnemyCount());
		return ((WesterosState) state).getEnemyCount() == 0;
	}
}
