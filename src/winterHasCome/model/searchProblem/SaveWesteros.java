package winterHasCome.model.searchProblem;

import java.util.HashSet;
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
import winterHasCome.model.queueingFunction.AStarSearch;
import winterHasCome.model.queueingFunction.BreadthFirstSearch;
import winterHasCome.model.queueingFunction.DepthFirstSeach;
import winterHasCome.model.queueingFunction.GreedySearch;
import winterHasCome.model.queueingFunction.IterativeDeepeningSearch;
import winterHasCome.model.queueingFunction.UniformCostSearch;
import winterHasCome.model.searchTreeNode.SearchTreeNode;
import winterHasCome.model.state.State;
import winterHasCome.model.state.WesterosState;

public class SaveWesteros extends SearchProblem {

	CellType[][] Westerosgrid;
	public SaveWesteros() {
		initialState = genGrid4x4();
		operators = new Operator[5];

		operators[0] = new Attack( 3 * (initialState.getWidth() + initialState.getHeight()));
//		operators[0] = new Attack( 8);
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
		int m = 5; // this is y = column
		int n = 5; // this is x = row
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
		System.out.println(n+" "+ m);
		// print the grid for visualization 
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}System.out.println("Grid generated");
		Westerosgrid = grid;
		return new WesterosState(grid, n, m, dragonStone, obstacles, whiteWalkers, dragonStoneLimit, 0, n - 1,
				m - 1,wwc);
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
		grid[0][0]= grid[0][1] = grid[0][2] = grid[1][0] = grid[1][3]= grid[2][1] = grid[2][2] = grid[2][3] = grid[3][0]= grid[3][1] = grid[3][2] = CellType.EMPTY;
		grid[0][3] = CellType.OBSTACLE;
		obstacles.add(new Cell(3, 0));
		grid[1][1] = grid[2][0] = CellType.WHITEWALKER;
		whiteWalkers.add(new Cell(0, 2));
		whiteWalkers.add(new Cell(1, 1));
		System.out.println(n+" "+ m);
		// print the grid for visualization 
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}System.out.println("Grid generated");
		Westerosgrid = grid;
		return new WesterosState(grid, n, m, dragonStone, obstacles, whiteWalkers, dragonStoneLimit, 0, n - 1,
				m - 1,wwc);
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
		grid[1][6]= grid[1][7] = grid[2][5] = grid[3][2] = grid[3][6] = grid[4][0]= grid[4][6] = grid[5][2] = CellType.WHITEWALKER;
		whiteWalkers.add(new Cell(6, 1));whiteWalkers.add(new Cell(7, 1));whiteWalkers.add(new Cell(5, 2));
		whiteWalkers.add(new Cell(2, 3));whiteWalkers.add(new Cell(6, 3));whiteWalkers.add(new Cell(0, 4));
		whiteWalkers.add(new Cell(6, 4));whiteWalkers.add(new Cell(2, 5));
		grid[2][1]= grid[3][0] = grid[3][5] = grid[3][7] = grid[4][7]= grid[5][0] = grid[6][0] =grid[6][1]= grid[6][5] = grid[6][7] = CellType.OBSTACLE;
		obstacles.add(new Cell(1, 2));obstacles.add(new Cell(0, 3));obstacles.add(new Cell(5, 3));
		obstacles.add(new Cell(7, 3));obstacles.add(new Cell(7, 4));obstacles.add(new Cell(0, 5));
		obstacles.add(new Cell(0, 6));obstacles.add(new Cell(1, 6));obstacles.add(new Cell(5, 6));
		obstacles.add(new Cell(7, 6));
		
		System.out.println(n+" "+ m);
		// print the grid for visualization 
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}System.out.println("Grid generated");
		Westerosgrid = grid;
		return new WesterosState(grid, n, m, dragonStone, obstacles, whiteWalkers, dragonStoneLimit, 0, n - 1,
				m - 1,wwc);
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
		grid[0][2]= grid[0][3] = grid[2][0] = grid[3][1] = grid[3][2] = CellType.WHITEWALKER;
		whiteWalkers.add(new Cell(2, 0));whiteWalkers.add(new Cell(3, 0));whiteWalkers.add(new Cell(0, 2));
		whiteWalkers.add(new Cell(1, 3));whiteWalkers.add(new Cell(2, 3));
		grid[0][1]= grid[2][1] = grid[2][2] = grid[3][4] = CellType.OBSTACLE;
		obstacles.add(new Cell(1, 0));obstacles.add(new Cell(1, 2));obstacles.add(new Cell(2, 2));
		obstacles.add(new Cell(4, 3));
		
		System.out.println(n+" "+ m);
		// print the grid for visualization 
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}System.out.println("Grid generated");
		Westerosgrid = grid;
		return new WesterosState(grid, n, m, dragonStone, obstacles, whiteWalkers, dragonStoneLimit, 0, n - 1,
				m - 1,wwc);
	}

	public static void main(String[] args) {
		SaveWesteros s = new SaveWesteros();	
		s.search(s.Westerosgrid,"AS1",true);
	}
	
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
			n = search(new GreedySearch(1));
			break;
		case "GR2":
			n = search(new GreedySearch(2));
			break;
		case "AS1":
			n = search(new AStarSearch(1));
			break;
		case "AS2":
			n = search(new AStarSearch(2));
			break;
		default:
			break;
		}
		
		if(n != null && visualize) {
			List<SearchTreeNode> pathFromRoot = n.getPathFromRoot();
			for (int j = 0; j < pathFromRoot.size(); j++) {
				WesterosState pathFromRootState = ((WesterosState) pathFromRoot.get(j).getState());
				if (((Operator) pathFromRoot.get(j).getAction()) != null)
					System.out.println(((Operator) pathFromRoot.get(j).getAction()).getName());
				System.out.println(pathFromRootState.printGrid());
				System.out.println("KING OF THE NORTH");
			}
		} else {
			System.out.println("YOU KNOW NOTHING JON SNOW");
		}
		
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
