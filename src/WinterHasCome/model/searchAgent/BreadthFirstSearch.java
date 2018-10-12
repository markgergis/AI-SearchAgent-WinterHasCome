package WinterHasCome.model.searchAgent;

import java.util.LinkedList;

public class BreadthFirstSearch extends QueueingFunction {

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
