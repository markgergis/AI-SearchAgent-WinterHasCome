package WinterHasCome.model.searchAgent;

import java.util.Stack;

public class IterativeDeepeningSearch extends QueueingFunction {

	private int depth;
	private int max;
	WesterosState initialState;
	
	public IterativeDeepeningSearch(int max, WesterosState initialState) {
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
		((Stack<SearchTreeNode>) queue).push(s);		
		
	}

	@Override
	public SearchTreeNode remove() {
		System.out.println(((Stack<SearchTreeNode>) queue).peek().getDepth());
		if(depth >= ((Stack<SearchTreeNode>) queue).peek().getDepth()) {
			return ((Stack<SearchTreeNode>) queue).pop();
		}
		depth++;
		if(depth<=max) {
			super.queue = new Stack<SearchTreeNode>();
			add(new SearchTreeNode(initialState));
			return ((Stack<SearchTreeNode>) queue).pop();
		}
			
		return null;
	}

	@Override
	public boolean isEmpty() {
		return ((Stack<SearchTreeNode>) queue).empty();
	}
}
