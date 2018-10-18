package winterHasCome.model.searchProblem;

import java.util.ArrayList;
import java.util.Collection;

import winterHasCome.model.operator.Operator;
import winterHasCome.model.queuingFunction.QueuingFunction;
import winterHasCome.model.searchTreeNode.SearchTreeNode;
import winterHasCome.model.state.State;

public abstract class SearchProblem {

	public Operator[] operators;
	public State initialState;

	public abstract boolean goalTest(State state);

	public SearchTreeNode search(QueuingFunction queuingFunc) {
		queuingFunc.add(new SearchTreeNode(initialState));
		int i = 0;
		while (true) {
			if (queuingFunc.isEmpty()) {
				System.out.println(i);

				return null;
			}

			SearchTreeNode node = queuingFunc.remove();
			if (goalTest(node.getState())) {
				System.out.println(i);
				return node;
			}
			Collection<SearchTreeNode> expandedNodes = expand(node);
			for (SearchTreeNode searchTreeNode : expandedNodes) {
				queuingFunc.add(searchTreeNode);
			}
			i++;
		}
	}

	private Collection<SearchTreeNode> expand(SearchTreeNode node) {
		Collection<SearchTreeNode> nextNodes = new ArrayList<SearchTreeNode>();
		for (Operator operator : operators) {
			SearchTreeNode newNode = operator.apply(node);
			if (newNode != null) {
				nextNodes.add(newNode);
			}
		}
		return nextNodes;
	}

}
