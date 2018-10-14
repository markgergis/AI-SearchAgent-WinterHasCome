package WinterHasCome.model.searchAgent;


public class GoSouth extends Operator {

	public GoSouth(int cost) {
		super(cost, "Go South");
	}

	@Override
	public SearchTreeNode apply(SearchTreeNode node) {

		WesterosState state = ((WesterosState) node.getState());
		Cell downCell = new Cell(state.getJonX(), state.getJonY() + 1);

		if (state.getJonY() < state.getHeight() - 1 && !state.getWhiteWalkers().contains(downCell)
				&& !state.getObstacles().contains(downCell)) {

			int carried = state.getDragonStoneCarried();
			if (state.getDragonStaone().equals(downCell)) {// Automatically pick up dragonglass if on dragonstone cell
				carried = state.getDragonStoneLimit();
			}

			WesterosState newState = new WesterosState(state.getGrid(), state.getWidth(), state.getHeight(),
					state.getDragonStaone(), state.getObstacles(), state.getWhiteWalkers(), state.getDragonStoneLimit(),
					carried, state.getJonX(), state.getJonY() + 1, state.getEnemyCount());

			return new SearchTreeNode(newState, node, this, this.getCost());
		}
		return null;
	}
}
