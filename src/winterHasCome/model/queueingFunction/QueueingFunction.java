package winterHasCome.model.queueingFunction;

import java.util.Collection;

import winterHasCome.model.searchTreeNode.SearchTreeNode;

public abstract class QueueingFunction {

	Collection<SearchTreeNode> queue;

	public abstract void add(SearchTreeNode s);

	public abstract SearchTreeNode remove();

	public abstract boolean isEmpty();
}
