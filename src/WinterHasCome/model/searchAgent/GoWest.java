package WinterHasCome.model.searchAgent;

import java.util.HashSet;

public class GoWest extends Operator {

	public GoWest(int cost) {
		super(cost, "Go West");
	}

	@Override
	public SearchTreeNode apply(SearchTreeNode node) {

		WesterosState state = ((WesterosState) node.getState());
		Cell leftCell = new Cell(state.getJonX() - 1, state.getJonY());

		if (state.getJonX() > 0 && !state.getWhiteWalkers().contains(leftCell)
				&& !state.getObstacles().contains(leftCell)) {

			int carried = state.getDragonStoneCarried();
			if (state.getDragonStaone().equals(leftCell)) {// Automatically pick up dragonglass if on dragonstone cell
				carried = state.getDragonStoneLimit();
			}

			@SuppressWarnings("unchecked")
			WesterosState newState = new WesterosState(state.getGrid(), state.getWidth(), state.getHeight(),
					state.getDragonStaone(), state.getObstacles(), (HashSet<Cell>) state.getWhiteWalkers().clone(),
					state.getDragonStoneLimit(), carried, state.getJonX() - 1, state.getJonY(), state.getEnemyCount());

			return new SearchTreeNode(newState, node, this, this.getCost());
		}
		return null;
	}

}
