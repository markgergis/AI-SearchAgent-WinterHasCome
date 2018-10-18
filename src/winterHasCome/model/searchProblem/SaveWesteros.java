package winterHasCome.model.searchProblem;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import winterHasCome.model.cell.Cell;
import winterHasCome.model.cell.CellType;
import winterHasCome.model.operator.Attack;
import winterHasCome.model.operator.GoEast;
import winterHasCome.model.operator.GoNorth;
import winterHasCome.model.operator.GoSouth;
import winterHasCome.model.operator.GoWest;
import winterHasCome.model.operator.Operator;
import winterHasCome.model.queuingFunction.AStarSearch;
import winterHasCome.model.queuingFunction.BreadthFirstSearch;
import winterHasCome.model.queuingFunction.DepthFirstSeach;
import winterHasCome.model.queuingFunction.GreedySearch;
import winterHasCome.model.queuingFunction.IterativeDeepeningSearch;
import winterHasCome.model.queuingFunction.UniformCostSearch;
import winterHasCome.model.searchTreeNode.SearchTreeNode;
import winterHasCome.model.state.State;
import winterHasCome.model.state.WesterosState;

public class SaveWesteros extends SearchProblem {

	CellType[][] Westerosgrid;

	public SaveWesteros() {
		initialState = genGrid4x4();

		System.out.println("[" + ((WesterosState) initialState).getWidth() + ","
				+ ((WesterosState) initialState).getHeight() + "]");
		// print the grid for visualization
		System.out.println(((WesterosState) initialState).printGrid());
		System.out.println("Grid generated");
		operators = new Operator[5];

		operators[0] = new Attack(
				(((WesterosState) initialState).getWidth() * ((WesterosState) initialState).getHeight()));
//		operators[0] = new Attack(
//				3 * (((WesterosState) initialState).getWidth() + ((WesterosState) initialState).getHeight()));
//		operators[0] = new Attack(8);
		operators[1] = new GoNorth(1);
		operators[2] = new GoWest(1);
		operators[3] = new GoSouth(1);
		operators[4] = new GoEast(1);
	}

	@Override
	public boolean goalTest(State state) {
		return ((WesterosState) state).getEnemyCount() == 0;
	}
	
	@Override
	public int pathCost(SearchTreeNode node, Operator operator) {
		return node.getPathCost() + operator.getCost();
	}

	/* Generating Grid */

	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public WesterosState genGrid() {
		Random rand = new Random();
		int m = getRandomNumberInRange(4, 8); // this is y = column
		int n = getRandomNumberInRange(4, 8); // this is x = row
		CellType[][] grid = new CellType[m][n];
		int dc = getRandomNumberInRange(m * n / 10, m * n / 4);
		int wwc = dc;
		int oc = getRandomNumberInRange(m * n / 10, m * n / 4);
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

		Westerosgrid = grid;
		return new WesterosState(grid, n, m, dragonStone, obstacles, whiteWalkers, dragonStoneLimit, 0, n - 1, m - 1,
				wwc);
	}

	public WesterosState genGrid(int width, int height) {
		Random rand = new Random();
		int m = height; // this is y = column
		int n = width; // this is x = row
		CellType[][] grid = new CellType[m][n];
		int dc = getRandomNumberInRange(m * n / 10, m * n / 4);
		int wwc = dc;
		int oc = getRandomNumberInRange(m * n / 10, m * n / 4);
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

		Westerosgrid = grid;
		return new WesterosState(grid, n, m, dragonStone, obstacles, whiteWalkers, dragonStoneLimit, 0, n - 1, m - 1,
				wwc);
	}

	public WesterosState genGrid4x4() {
		int m = 4; // this is y = column
		int n = 4; // this is x = row
		CellType[][] grid = new CellType[m][n];
		int dc = 2;
		int wwc = dc;
		int oc = 1;
		HashSet<Cell> obstacles = new HashSet<Cell>(oc);
		int dragonStoneLimit = 4;
		HashSet<Cell> whiteWalkers = new HashSet<>(dc);
		grid[m - 1][n - 1] = CellType.EMPTY;
		grid[1][2] = CellType.DRAGONSTONE;
		Cell dragonStone = new Cell(2, 1);
		grid[0][0] = grid[0][1] = grid[0][2] = grid[1][0] = grid[1][3] = grid[2][1] = grid[2][2] = grid[2][3] = grid[3][0] = grid[3][1] = grid[3][2] = CellType.EMPTY;
		grid[0][3] = CellType.OBSTACLE;
		obstacles.add(new Cell(3, 0));
		grid[1][1] = grid[2][0] = CellType.WHITEWALKER;
		whiteWalkers.add(new Cell(0, 2));
		whiteWalkers.add(new Cell(1, 1));

		Westerosgrid = grid;
		return new WesterosState(grid, n, m, dragonStone, obstacles, whiteWalkers, dragonStoneLimit, 0, n - 1, m - 1,
				wwc);
	}

	public WesterosState genGrid8x8() {
		int m = 8; // this is y = column
		int n = 8; // this is x = row
		CellType[][] grid = new CellType[m][n];
		int dc = 8;
		int wwc = dc;
		int oc = 10;
		HashSet<Cell> obstacles = new HashSet<Cell>(oc);
		int dragonStoneLimit = 10;
		HashSet<Cell> whiteWalkers = new HashSet<>(dc);
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = CellType.EMPTY;
			}
		}
		grid[7][4] = CellType.DRAGONSTONE;
		Cell dragonStone = new Cell(4, 7);
		grid[1][6] = grid[1][7] = grid[2][5] = grid[3][2] = grid[3][6] = grid[4][0] = grid[4][6] = grid[5][2] = CellType.WHITEWALKER;
		whiteWalkers.add(new Cell(6, 1));
		whiteWalkers.add(new Cell(7, 1));
		whiteWalkers.add(new Cell(5, 2));
		whiteWalkers.add(new Cell(2, 3));
		whiteWalkers.add(new Cell(6, 3));
		whiteWalkers.add(new Cell(0, 4));
		whiteWalkers.add(new Cell(6, 4));
		whiteWalkers.add(new Cell(2, 5));
		grid[2][1] = grid[3][0] = grid[3][5] = grid[3][7] = grid[4][7] = grid[5][0] = grid[6][0] = grid[6][1] = grid[6][5] = grid[6][7] = CellType.OBSTACLE;
		obstacles.add(new Cell(1, 2));
		obstacles.add(new Cell(0, 3));
		obstacles.add(new Cell(5, 3));
		obstacles.add(new Cell(7, 3));
		obstacles.add(new Cell(7, 4));
		obstacles.add(new Cell(0, 5));
		obstacles.add(new Cell(0, 6));
		obstacles.add(new Cell(1, 6));
		obstacles.add(new Cell(5, 6));
		obstacles.add(new Cell(7, 6));

		Westerosgrid = grid;
		return new WesterosState(grid, n, m, dragonStone, obstacles, whiteWalkers, dragonStoneLimit, 0, n - 1, m - 1,
				wwc);
	}

	public WesterosState genGrid5x5() {
		int m = 5; // this is y = column
		int n = 5; // this is x = row
		CellType[][] grid = new CellType[m][n];
		int dc = 5;
		int wwc = dc;
		int oc = 4;
		HashSet<Cell> obstacles = new HashSet<Cell>(oc);
		int dragonStoneLimit = 10;
		HashSet<Cell> whiteWalkers = new HashSet<>(dc);
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = CellType.EMPTY;
			}
		}
		grid[1][2] = CellType.DRAGONSTONE;
		Cell dragonStone = new Cell(2, 1);
		grid[0][2] = grid[0][3] = grid[2][0] = grid[3][1] = grid[3][2] = CellType.WHITEWALKER;
		whiteWalkers.add(new Cell(2, 0));
		whiteWalkers.add(new Cell(3, 0));
		whiteWalkers.add(new Cell(0, 2));
		whiteWalkers.add(new Cell(1, 3));
		whiteWalkers.add(new Cell(2, 3));
		grid[0][1] = grid[2][1] = grid[2][2] = grid[3][4] = CellType.OBSTACLE;
		obstacles.add(new Cell(1, 0));
		obstacles.add(new Cell(1, 2));
		obstacles.add(new Cell(2, 2));
		obstacles.add(new Cell(4, 3));

		Westerosgrid = grid;
		return new WesterosState(grid, n, m, dragonStone, obstacles, whiteWalkers, dragonStoneLimit, 0, n - 1, m - 1,
				wwc);
	}

	/* Search */
	public void search(CellType[][] grid, String strategy, boolean visualize) {
		SearchTreeNode n = null;
		switch (strategy) {
		case "BF":
			n = search(new BreadthFirstSearch());
			break;
		case "DF":
			n = search(new DepthFirstSeach());
			break;
		case "UC":
			n = search(new UniformCostSearch());
			break;
		case "ID":
			n = search(new IterativeDeepeningSearch(Integer.MAX_VALUE, initialState));
			break;
		case "GR1":
			n = search(new GreedySearch(this::optimalAttacks));
			break;
		case "GR2":
			n = search(new GreedySearch(this::manhattanFurthestAndAttack));
			break;
		case "AS1":
			n = search(new AStarSearch(this::optimalAttacks));
			break;
		case "AS2":
			n = search(new AStarSearch(this::manhattanFurthestAndAttack));
			break;
		default:
			break;
		}

		if (n != null && visualize) {
			List<SearchTreeNode> pathFromRoot = n.getPathFromRoot();
			for (int j = 0; j < pathFromRoot.size(); j++) {
				WesterosState pathFromRootState = ((WesterosState) pathFromRoot.get(j).getState());
				if (((Operator) pathFromRoot.get(j).getOperator()) != null)
					System.out.println(((Operator) pathFromRoot.get(j).getOperator()).getName());
				System.out.println(pathFromRootState.printGrid());
			}
			System.out.println("Solution cost:" + pathFromRoot.get(pathFromRoot.size() - 1).getPathCost());
			System.out.println("KING OF THE NORTH");

		} else {
			System.out.println("YOU KNOW NOTHING JON SNOW");
		}

	}

	/* Heuristic Functions */

	public int optimalAttacks(State state) {
		// Where operators[0] is the Attack operator
		return (int) Math.ceil(((WesterosState) state).getEnemyCount() / 3.0) * operators[0].getCost();
	}

	private int manhattanFurthestAndAttack(State state) {
		// Where operators[0] is the Attack operator
		// Where operators[1] is a movement operator and all movements cost the same
		WesterosState westerosState = (WesterosState) state;
		int baseEstimate = 0;
		if (westerosState.getEnemyCount() == 0) {
			return 0;
		}
		Cell z = new Cell(westerosState.getJonX(), westerosState.getJonY());
		if (westerosState.getDragonStoneCarried() == 0) {
			baseEstimate = (Math.abs(westerosState.getDragonStone().y - z.y)
					+ Math.abs(westerosState.getDragonStone().x - z.x)) * operators[1].getCost();
			z = westerosState.getDragonStone();
		}
		Iterator<Cell> i = westerosState.getWhiteWalkers().iterator();
		int max = -1;
		while (i.hasNext()) {
			Cell c = i.next();
			int estimate = (Math.abs(c.y - z.y) + Math.abs(c.x - z.x) - 1) * operators[1].getCost()
					+ operators[0].getCost();
			if (estimate > max) {
				max = estimate;
			}
		}
		return max + baseEstimate;
	}

	/* Main */

	public static void main(String[] args) {
		SaveWesteros s = new SaveWesteros();
		s.search(s.Westerosgrid, "AS1", true);
	}
}
