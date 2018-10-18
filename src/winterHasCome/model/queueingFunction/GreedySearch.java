package winterHasCome.model.queueingFunction;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.Function;

import winterHasCome.model.searchTreeNode.SearchTreeNode;
import winterHasCome.model.state.State;
import winterHasCome.model.state.WesterosState;

public class GreedySearch extends QueueingFunction {

	public GreedySearch(Function<State, Integer> heuristicFunc) {
		super.queue = new PriorityQueue<SearchTreeNode>(new Comparator<SearchTreeNode>() {

			@Override
			public int compare(SearchTreeNode o1, SearchTreeNode o2) {
				WesterosState node1S = (WesterosState) o1.getState();
				WesterosState node2S = (WesterosState) o2.getState();
				int distance_a = heuristicFunc.apply(node1S);
				int distance_b = heuristicFunc.apply(node2S);

				if (distance_a > distance_b)
					return 1;
				else if (distance_a < distance_b)
					return -1;
				return 0;
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
