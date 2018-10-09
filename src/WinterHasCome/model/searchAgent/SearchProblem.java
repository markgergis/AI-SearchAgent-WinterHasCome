package WinterHasCome.model.searchAgent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

public abstract class SearchProblem {

	public Object[] operators;
	public State initialState;
	public ArrayList<State> stateSpace;
	public abstract boolean goalTest(State state);
	public abstract int pathCost(@SuppressWarnings("rawtypes") ArrayList action);
	public SearchTreeNode generalSearch(Method fuc) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ArrayList<SearchTreeNode> nodes = new ArrayList<>();
		nodes.add(new SearchTreeNode(initialState));
		while(true) {
			if(nodes.isEmpty()) {
				System.out.println("YOU KNOW NOTHING JON SNOW");
				return null;
			}
			SearchTreeNode node = nodes.remove(0);
			if(goalTest(node.getState())) {
				System.out.println("The King of the North");
				return node;
			}
			ArrayList<SearchTreeNode> expandedNodes = expand(node);
			for (SearchTreeNode searchTreeNode : expandedNodes) {
				Object[] param = {node, searchTreeNode};
				fuc.invoke(this, param);
			}
		}
	}
	
	
	
	private ArrayList<SearchTreeNode> expand(SearchTreeNode node) {
		// TODO Auto-generated method stub
		return null;
	}
	public abstract Object getInitialState();
    public abstract boolean isGoal(Object state);
    public abstract Collection<Object> getActions(Object state);
    public abstract Object getNextState(Object state,Object action);
    public abstract double getStepCost(Object start, Object action, Object dest);
}
