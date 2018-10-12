package WinterHasCome.model.searchAgent;

import java.util.HashSet;

public class GoNorth extends Operator {

	public GoNorth(int cost) {
		super(cost, "Go North");
	}

	@Override
	public SearchTreeNode apply(SearchTreeNode node) {

		WesterosState state = ((WesterosState) node.getState());
		Cell upCell = new Cell(state.getJonX(), state.getJonY() - 1);

		if (state.getJonY() > 0 && !state.getWhiteWalkers().contains(upCell)
				&& !state.getObstacles().contains(upCell)) {

			int carried = state.getDragonStoneCarried();
			if (state.getDragonStaone().equals(upCell)) {// Automatically pick up dragonglass if on dragonstone cell
				carried = state.getDragonStoneLimit();
			}

			@SuppressWarnings("unchecked")
			WesterosState newState = new WesterosState(state.getGrid(), state.getWidth(), state.getHeight(),
					state.getDragonStaone(), state.getObstacles(), (HashSet<Cell>) state.getWhiteWalkers().clone(),
					state.getDragonStoneLimit(), carried, state.getJonX(), state.getJonY() - 1, state.getEnemyCount());

			return new SearchTreeNode(newState, node, this, this.getCost());
		}
		return null;
	}

}
