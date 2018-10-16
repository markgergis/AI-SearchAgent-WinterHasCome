package WinterHasCome.model.searchAgent;

import java.util.PriorityQueue;

public class UniformCostSearch extends QueueingFunction {

	public UniformCostSearch() {
		super.queue = new PriorityQueue<SearchTreeNode>();
	}
	
	@Override
	public void add(SearchTreeNode s) {
		((PriorityQueue<SearchTreeNode>) queue).add(s);
	}

	@Override
	public SearchTreeNode remove() {
		return ((PriorityQueue<SearchTreeNode>) queue).poll();
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

}
