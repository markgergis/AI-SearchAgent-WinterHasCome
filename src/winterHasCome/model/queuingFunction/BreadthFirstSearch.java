package winterHasCome.model.queuingFunction;

import java.util.LinkedList;

import winterHasCome.model.searchTreeNode.SearchTreeNode;

public class BreadthFirstSearch extends QueuingFunction {

	public BreadthFirstSearch() {
		super.queue = new LinkedList<SearchTreeNode>();
	}

	@Override
	public void add(SearchTreeNode s) {
		((LinkedList<SearchTreeNode>) super.queue).add(s);
	}

	@Override
	public SearchTreeNode remove() {
		return ((LinkedList<SearchTreeNode>) super.queue).removeFirst();
	}

	@Override
	public boolean isEmpty() {
		return super.queue.isEmpty();
	}

}
