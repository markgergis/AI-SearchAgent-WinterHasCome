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

		if (state.getDragonStoneCarried() > 0 && state.getWhiteWalkers().contains(rightCell)
				&& state.getWhiteWalkers().contains(leftCell) && state.getWhiteWalkers().contains(downCell)
				&& state.getWhiteWalkers().contains(upCell)) {
			int enemiesKilled = 0;
			@SuppressWarnings("unchecked")
			HashSet<Cell> newEnemySet = (HashSet<Cell>) state.getWhiteWalkers().clone();
			if (newEnemySet.remove(rightCell)) {
				enemiesKilled++;
			}
			if (newEnemySet.remove(leftCell)) {
				enemiesKilled++;
			}
			if (newEnemySet.remove(downCell)) {
				enemiesKilled++;
			}
			if (newEnemySet.remove(upCell)) {
				enemiesKilled++;
			}
			int carried = state.getDragonStoneCarried();
			if (state.getDragonStaone().equals(new Cell(state.getJonX(), state.getJonY()))) {// Automatically pick up
																								// dragonglass if on
																								// dragonstone cell
				carried = state.getDragonStoneLimit();
			}

			WesterosState newState = new WesterosState(state.getGrid(), state.getWidth(), state.getHeight(),
					state.getDragonStaone(), state.getObstacles(), newEnemySet, state.getDragonStoneLimit(), carried,
					state.getJonX(), state.getJonY(), state.getEnemyCount() - enemiesKilled);

			return new SearchTreeNode(newState, node, this, this.getCost());

		}
		return null;
	}

}
