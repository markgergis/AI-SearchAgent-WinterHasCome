package winterHasCome.model.queueingFunction;

import java.util.Stack;

import winterHasCome.model.searchTreeNode.SearchTreeNode;
import winterHasCome.model.state.State;

public class IterativeDeepeningSearch extends QueueingFunction {

	private int depth;
	private int max;
	State initialState;

	public IterativeDeepeningSearch(int max, State initialState) {
		super.queue = new Stack<SearchTreeNode>();
		depth = 0;
		this.max = max;
		this.initialState = initialState;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	@Override
	public void add(SearchTreeNode s) {
		if (depth >= (s.getDepth())) {
			((Stack<SearchTreeNode>) queue).push(s);
		}

	}

	@Override
	public SearchTreeNode remove() {
		return ((Stack<SearchTreeNode>) queue).pop();
	}

	@Override
	public boolean isEmpty() {
		if (((Stack<SearchTreeNode>) queue).empty()) {
			depth++;
			if (depth <= max) {
				super.queue = new Stack<SearchTreeNode>();
				add(new SearchTreeNode(initialState));
			}
		}
		return ((Stack<SearchTreeNode>) queue).empty();
	}
}
