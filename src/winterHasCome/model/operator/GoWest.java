package winterHasCome.model.operator;

import winterHasCome.model.cell.Cell;
import winterHasCome.model.searchTreeNode.SearchTreeNode;
import winterHasCome.model.state.WesterosState;

public class GoWest extends Operator {

	public GoWest(int cost) {
		super(cost, "Go West");
	}

	@Override
	public SearchTreeNode apply(SearchTreeNode node) {

		WesterosState state = ((WesterosState) node.getState());
		Cell leftCell = new Cell(state.getJonX() - 1, state.getJonY());
		if(node.getParent() != null) {
		WesterosState parentState = (WesterosState) node.getParent().getState();
		Cell parentCell = new Cell(parentState.getJonX(), parentState.getJonY());
		if(parentCell.equals(leftCell) &&!state.getDragonStone().equals(new Cell(state.getJonX() - 1, state.getJonY()))) {
			return null;
		}
		}
		if (state.getJonX() > 0 && !state.getWhiteWalkers().contains(leftCell)
				&& !state.getObstacles().contains(leftCell)) {

			int carried = state.getDragonStoneCarried();
			if (state.getDragonStone().equals(leftCell)) {// Automatically pick up dragonglass if on dragonstone cell
				carried = state.getDragonStoneLimit();
			}

			WesterosState newState = new WesterosState(state.getGrid(), state.getWidth(), state.getHeight(),
					state.getDragonStone(), state.getObstacles(), state.getWhiteWalkers(), state.getDragonStoneLimit(),
					carried, state.getJonX() - 1, state.getJonY(), state.getEnemyCount());

			return new SearchTreeNode(newState, node, this, this.getCost());
		}
		return null;
	}

}
