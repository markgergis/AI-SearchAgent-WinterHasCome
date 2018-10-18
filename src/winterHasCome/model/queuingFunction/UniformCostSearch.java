package winterHasCome.model.queuingFunction;

import java.util.Comparator;
import java.util.PriorityQueue;

import winterHasCome.model.searchTreeNode.SearchTreeNode;

public class UniformCostSearch extends QueuingFunction {

	public UniformCostSearch() {
		super.queue = new PriorityQueue<SearchTreeNode>(new Comparator<SearchTreeNode>() {

			@Override
			public int compare(SearchTreeNode o1, SearchTreeNode o2) {
				return Integer.compare(o1.getPathCost(), o2.getPathCost());
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
