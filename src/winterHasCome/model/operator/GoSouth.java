package winterHasCome.model.operator;

import winterHasCome.model.cell.Cell;
import winterHasCome.model.searchTreeNode.SearchTreeNode;
import winterHasCome.model.state.WesterosState;

public class GoSouth extends Operator {

	public GoSouth(int cost) {
		super(cost, "Go South");
	}

	@Override
	public SearchTreeNode apply(SearchTreeNode node) {

		WesterosState state = ((WesterosState) node.getState());
		Cell downCell = new Cell(state.getJonX(), state.getJonY() + 1);
		if (node.getParent() != null) {
			WesterosState parentState = (WesterosState) node.getParent().getState();
			Cell parentCell = new Cell(parentState.getJonX(), parentState.getJonY());
			if (parentCell.equals(downCell)
					&& !state.getDragonStone().equals(new Cell(state.getJonX() - 1, state.getJonY()))) {
				return null;
			}
		}
		if (state.getJonY() < state.getHeight() - 1 && !state.getWhiteWalkers().contains(downCell)
				&& !state.getObstacles().contains(downCell)) {

			int carried = state.getDragonStoneCarried();
			if (state.getDragonStone().equals(downCell)) {// Automatically pick up dragonglass if on dragonstone cell
				carried = state.getDragonStoneLimit();
			}

			WesterosState newState = new WesterosState(state.getGrid(), state.getWidth(), state.getHeight(),
					state.getDragonStone(), state.getObstacles(), state.getWhiteWalkers(), state.getDragonStoneLimit(),
					carried, state.getJonX(), state.getJonY() + 1, state.getEnemyCount());

			return new SearchTreeNode(newState, node, this, this.getCost());
		}
		return null;
	}
}
