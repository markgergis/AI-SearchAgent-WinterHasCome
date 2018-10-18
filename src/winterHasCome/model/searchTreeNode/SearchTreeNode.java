package winterHasCome.model.searchTreeNode;

import java.util.*;

import winterHasCome.model.operator.Operator;
import winterHasCome.model.state.State;

public class SearchTreeNode{

	private State state;
    private SearchTreeNode parent;
    private Operator operator;
    private int pathCost;
    private int depth;


	public SearchTreeNode(State state) {
        this.state = state;
        this.pathCost = 0;
        this.depth = 0;
    }
	
	 public SearchTreeNode(State state, SearchTreeNode parent, Operator operator) {
	        this(state);
	        this.parent = parent;
	        this.operator = operator;
	        this.pathCost = operator.getPathCostFunc().apply(parent,operator);
	        this.depth = parent.depth + 1;
	    }

    public SearchTreeNode(State state, SearchTreeNode parent, Operator operator, int stepCost) {
        this(state);
        this.parent = parent;
        this.operator = operator;
        this.pathCost = parent.pathCost + stepCost;
        this.depth = parent.depth + 1;
    }

    public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
	
    public State getState() {
        return state;
    }

    public SearchTreeNode getParent() {
        return parent;
    }

    public Object getOperator() {
        return operator;
    }

    public int getPathCost() {
        return pathCost;
    }

	public boolean isRootNode() {
        return parent == null;
    }

    public List<SearchTreeNode> getPathFromRoot() {
        List<SearchTreeNode> path = new ArrayList<SearchTreeNode>();
        SearchTreeNode current = this;
        while (!current.isRootNode()) {
            path.add(0, current);
            current = current.getParent();
        }
        // ensure the root node is added
        path.add(0, current);
        return path;
    }

    public String toString() {
        return "[parent=" + parent + ", operator=" + operator + ", state="
                + getState() + ", pathCost=" + pathCost + "]";
    }

    public String pathToString() {
        String s = "";
        List<SearchTreeNode> nodes = getPathFromRoot();
        for (int i = 0; i < nodes.size(); i++) {
            System.out.println("Operator : " + nodes.get(i).getOperator());
            System.out.println("State    : " + nodes.get(i).getState());
        }
        return s;
    }

	
}
