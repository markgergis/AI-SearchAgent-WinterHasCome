package WinterHasCome.model.searchAgent;

public class GoEast extends Operator {

	public GoEast(int cost) {
		super(cost, "Go East");
	}

	@Override
	public SearchTreeNode apply(SearchTreeNode node) {

		WesterosState state = ((WesterosState) node.getState());
		Cell rightCell = new Cell(state.getJonX() + 1, state.getJonY());

		if (state.getJonX() < state.getWidth() - 1 && !state.getWhiteWalkers().contains(rightCell)
				&& !state.getObstacles().contains(rightCell)) {

			int carried = state.getDragonStoneCarried();
			if (state.getDragonStaone().equals(rightCell)) {// Automatically pick up dragonglass if on dragonstone cell
				carried = state.getDragonStoneLimit();
			}

			WesterosState newState = new WesterosState(state.getGrid(), state.getWidth(), state.getHeight(),
					state.getDragonStaone(), state.getObstacles(), state.getWhiteWalkers(), state.getDragonStoneLimit(),
					carried, state.getJonX() + 1, state.getJonY(), state.getEnemyCount());

			return new SearchTreeNode(newState, node, this, this.getCost());
		}
		return null;
	}

}
