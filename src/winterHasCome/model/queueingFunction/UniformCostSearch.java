package winterHasCome.model.queueingFunction;

import java.util.Comparator;
import java.util.PriorityQueue;

import winterHasCome.model.searchTreeNode.SearchTreeNode;

public class UniformCostSearch extends QueueingFunction {

	public UniformCostSearch() {
		super.queue = new PriorityQueue<SearchTreeNode>(new Comparator<SearchTreeNode>() {

			@Override
			public int compare(SearchTreeNode o1, SearchTreeNode o2) {
				if (o1.getPathCost() > o2.getPathCost()) {
					return 1;
				} else if (o1.getPathCost() < o2.getPathCost()) {
					return -1;
				} else {
					return 0;
				}
			}

		});
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
