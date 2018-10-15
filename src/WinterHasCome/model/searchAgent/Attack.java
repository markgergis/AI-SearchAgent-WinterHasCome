package WinterHasCome.model.searchAgent;

import java.util.HashSet;

public class Attack extends Operator {

	public Attack(int cost) {
		super(cost, "Attack");
	}

	@Override
	public SearchTreeNode apply(SearchTreeNode node) {
		WesterosState state = ((WesterosState) node.getState());
		Cell rightCell = new Cell(state.getJonX() + 1, state.getJonY());
		Cell leftCell = new Cell(state.getJonX() - 1, state.getJonY());
		Cell downCell = new Cell(state.getJonX(), state.getJonY() + 1);
		Cell upCell = new Cell(state.getJonX(), state.getJonY() - 1);

		if (state.getDragonStoneCarried() > 0) {

			int enemiesKilled = 0;
			@SuppressWarnings("unchecked")
			HashSet<Cell> newEnemySet = (HashSet<Cell>) state.getWhiteWalkers().clone();
			CellType[][] grid = state.getGrid();
			
			if (state.getWhiteWalkers().contains(rightCell))
				if (newEnemySet.remove(rightCell)) {
					grid[rightCell.y][rightCell.x] = CellType.EMPTY;
					enemiesKilled++;
				}
			if (state.getWhiteWalkers().contains(leftCell))
				if (newEnemySet.remove(leftCell)) {
					grid[leftCell.y][leftCell.x] = CellType.EMPTY;
					enemiesKilled++;
				}
			if (state.getWhiteWalkers().contains(downCell))
				if (newEnemySet.remove(downCell)) {
					grid[downCell.y][downCell.x] = CellType.EMPTY;
					enemiesKilled++;
				}
			if (state.getWhiteWalkers().contains(upCell))
				if (newEnemySet.remove(upCell)) {
					grid[upCell.y][upCell.x] = CellType.EMPTY;
					enemiesKilled++;
				}
			if (enemiesKilled == 0) {
				return null;
			}
//			System.out.println();
//			for (int i = 0; i < grid.length; i++) {
//				for (int j = 0; j < grid[i].length; j++) {
//					if(state.getJonX() == j && state.getJonY() == i) {
//						System.out.print("[J]");
//					} else
//					System.out.print(grid[i][j]);
//				}
//				System.out.println();
//			}
			int carried = state.getDragonStoneCarried();
			if (state.getDragonStaone().equals(new Cell(state.getJonX(), state.getJonY()))) {
				// Automatically pick up dragonglass if on
				carried = state.getDragonStoneLimit();
			} else {
				carried--;
			}

			WesterosState newState = new WesterosState(grid, state.getWidth(), state.getHeight(),
					state.getDragonStaone(), state.getObstacles(), newEnemySet, state.getDragonStoneLimit(), carried,
					state.getJonX(), state.getJonY(), state.getEnemyCount() - enemiesKilled);

			return new SearchTreeNode(newState, node, this, this.getCost());

		}
		return null;
	}

}
