package winterHasCome.model.queuingFunction;

import java.util.Collection;

import winterHasCome.model.searchTreeNode.SearchTreeNode;

public abstract class QueuingFunction {

	Collection<SearchTreeNode> queue;

	public abstract void add(SearchTreeNode s);

	public abstract SearchTreeNode remove();

	public abstract boolean isEmpty();
}
