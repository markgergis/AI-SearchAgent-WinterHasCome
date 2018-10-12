package WinterHasCome.model.searchAgent;

import java.util.Collection;

public abstract class QueueingFunction{
	
	Collection<SearchTreeNode> queue;

	public abstract void add(SearchTreeNode s);
	
	public abstract SearchTreeNode remove();
	
	public abstract boolean isEmpty();
}
