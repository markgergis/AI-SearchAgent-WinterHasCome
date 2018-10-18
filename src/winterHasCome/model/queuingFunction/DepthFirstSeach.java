package winterHasCome.model.queuingFunction;

import java.util.Stack;

import winterHasCome.model.searchTreeNode.SearchTreeNode;

public class DepthFirstSeach extends QueuingFunction {

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
