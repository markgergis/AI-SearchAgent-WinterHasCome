package winterHasCome.model.operator;

import winterHasCome.model.searchTreeNode.SearchTreeNode;

public abstract class Operator {

	private int cost;
	private String name;

	public abstract SearchTreeNode apply(SearchTreeNode node);

	public Operator(int cost, String name) {
		super();
		this.cost = cost;
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public String getName() {
		return name;
	}

}
