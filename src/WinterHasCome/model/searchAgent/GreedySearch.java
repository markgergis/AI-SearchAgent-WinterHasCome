package WinterHasCome.model.searchAgent;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class GreedySearch extends QueueingFunction {

	int i;
	public GreedySearch(int i) {
		this.i = i;
		super.queue = new PriorityQueue<SearchTreeNode>(new Comparator<SearchTreeNode>() {

			@Override
			public int compare(SearchTreeNode o1, SearchTreeNode o2) {
				WesterosState node1S = (WesterosState)o1.getState();
				WesterosState node2S = (WesterosState)o2.getState();
				int distance_a = heuristicFunc(node1S);
		        int distance_b = heuristicFunc(node2S);
//		        System.out.println(distance_a+ " "+distance_b);
		        // Greedy Heuristic: f(n) = g(n)
		        if ( distance_a > distance_b ) return 1;
		        else if ( distance_a < distance_b ) return -1;
		        return 0;
			}
			
		});
	}
	
//	private static int euclidean_distance ( int x1, int y1, int x2, int y2 ) {
//        return (int) Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
//    }
	
	private static int heuristicFunc1(int enemyCount, int maxCost) {
		return (int) (Math.ceil(enemyCount/3.0))*maxCost; 
	}
	
	private static int heuristicFunc2(int x1, int y1,HashSet<Cell> ww, Cell dragon, int carried, int maxCost) {
		int estimate = 0;
		Cell z = new Cell(x1, y1);
		if(carried == 0) {
			estimate = (Math.abs(dragon.y-y1) + Math.abs(dragon.x-x1));
			z = dragon;
		}
		Cell[] wwList = ww.toArray(new Cell[ww.size()]);
		int max = -1;
		for (int i = 0; i < wwList.length; i++) {
			estimate += (Math.abs(wwList[i].y-z.y) + Math.abs(wwList[i].x-z.x) - 1 + maxCost);
			if(estimate>max) {
				max = estimate;
			}
		}
		return max;
//		return Math.abs(y2-y1) + Math.abs(x2-x1) - 1 + 15;
	}
	
	private int heuristicFunc(WesterosState s) {
		int maxCost = s.getWidth()*3 + s.getHeight();
		if(this.i == 1) {
			return heuristicFunc1(s.getEnemyCount(), maxCost);
		}
		return heuristicFunc2(s.getJonX(),s.getJonY(), s.getWhiteWalkers(), s.getDragonStone(), s.getDragonStoneCarried(), maxCost);
	}
	
	@Override
	public void add(SearchTreeNode s) {
		((PriorityQueue<SearchTreeNode>) queue).add(s);
	}

	@Override
	public SearchTreeNode remove() {
		return ((PriorityQueue<SearchTreeNode>) queue).poll();
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}
}
