package winterHasCome.model.searchProblem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import winterHasCome.model.operator.Operator;
import winterHasCome.model.queueingFunction.QueueingFunction;
import winterHasCome.model.searchTreeNode.SearchTreeNode;
import winterHasCome.model.state.State;
import winterHasCome.model.state.WesterosState;

public abstract class SearchProblem {

	public Operator[] operators;
	public WesterosState initialState;

	public abstract boolean goalTest(State state);

	public SearchTreeNode search(QueueingFunction queuingFunc) {
		queuingFunc.add(new SearchTreeNode(initialState));
		int i = 0;
		while (true) {
//			
			if (queuingFunc.isEmpty()) {
				System.out.println(i);
				
				return null;
			}
			
			 SearchTreeNode node = queuingFunc.remove();
			// if(node == null) {
			// 	System.out.println(i);
			// 	System.out.println("YOU KNOW NOTHING JON SNOW");
			// 	return null;
			// }

//			System.out.println("DragonStoneCarried: " + ((WesterosState) node.getState()).getDragonStoneCarried());
			if (goalTest(node.getState())) {
				System.out.println(i);
//				List<SearchTreeNode> pathFromRoot = node.getPathFromRoot();
//				for (int j = 0; j < pathFromRoot.size(); j++) {
//					WesterosState pathFromRootState = ((WesterosState) pathFromRoot.get(j).getState());
//					if (((Operator) pathFromRoot.get(j).getAction()) != null)
//						System.out.println(((Operator) pathFromRoot.get(j).getAction()).getName());
//					System.out.println(pathFromRootState.printGrid());
//
//				}
//				System.out.println("KING OF THE NORTH");

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
