package winterHasCome.model.operator;

import java.util.function.BiFunction;

import winterHasCome.model.searchTreeNode.SearchTreeNode;

public abstract class Operator {

	private int cost;
	private String name;
	private BiFunction<SearchTreeNode, Operator, Integer> pathCostFunc;

	public abstract SearchTreeNode apply(SearchTreeNode node);

	public Operator(int cost, String name, BiFunction<SearchTreeNode, Operator, Integer> pathCostFunc) {
		super();
		this.cost = cost;
		this.name = name;
		this.pathCostFunc = pathCostFunc;
	}

	public int getCost() {
		return cost;
	}

	public String getName() {
		return name;
	}

	public BiFunction<SearchTreeNode, Operator, Integer> getPathCostFunc() {
		return pathCostFunc;
	}
	

}
