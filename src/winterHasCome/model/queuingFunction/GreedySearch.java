package winterHasCome.model.queuingFunction;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.Function;

import winterHasCome.model.searchTreeNode.SearchTreeNode;
import winterHasCome.model.state.State;

public class GreedySearch extends QueuingFunction {

	public GreedySearch(Function<State, Integer> heuristicFunc) {
		super.queue = new PriorityQueue<SearchTreeNode>(new Comparator<SearchTreeNode>() {

			@Override
			public int compare(SearchTreeNode o1, SearchTreeNode o2) {
				State node1S = o1.getState();
				State node2S = o2.getState();
				int distance_a = heuristicFunc.apply(node1S);
				int distance_b = heuristicFunc.apply(node2S);
				
				return Integer.compare(distance_a, distance_b);
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
