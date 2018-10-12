package WinterHasCome.model.searchAgent;

import java.util.ArrayList;

public abstract class SearchProblem {

	public Operator[] operators;
	public State initialState;

	public abstract boolean goalTest(State state);

	public SearchTreeNode search(QueueingFunction queuingFunc) {
		queuingFunc.add(new SearchTreeNode(initialState));
		int i =0;
		while (true) {
			System.out.println(i++);
			if (queuingFunc.isEmpty()) {
				System.out.println("YOU KNOW NOTHING JON SNOW");
				return null;
			}
			SearchTreeNode node = queuingFunc.remove();
			if (goalTest(node.getState())) {
				System.out.println("KING OF THE NORTH");
				return node;
			}
			ArrayList<SearchTreeNode> expandedNodes = expand(node);
			for (SearchTreeNode searchTreeNode : expandedNodes) {
				queuingFunc.add(searchTreeNode);
			}
		}
	}

	private ArrayList<SearchTreeNode> expand(SearchTreeNode node) {
		ArrayList<SearchTreeNode> nextNodes = new ArrayList<SearchTreeNode>();
		for (Operator operator : operators) {
			SearchTreeNode newNode = operator.apply(node);
			if (newNode != null) {
				nextNodes.add(newNode);
			}

		}
		return nextNodes;
	}

}
