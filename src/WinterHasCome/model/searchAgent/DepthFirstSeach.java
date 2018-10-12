package WinterHasCome.model.searchAgent;

import java.util.Stack;

public class DepthFirstSeach extends QueueingFunction {

	public DepthFirstSeach() {
		super.queue = new Stack<SearchTreeNode>();
	}

	@Override
	public void add(SearchTreeNode s) {
		((Stack<SearchTreeNode>) queue).push(s);
	}

	@Override
	public SearchTreeNode remove() {
		return ((Stack<SearchTreeNode>) queue).pop();
	}

	@Override
	public boolean isEmpty() {
		return ((Stack<SearchTreeNode>) queue).empty();
	}

}
