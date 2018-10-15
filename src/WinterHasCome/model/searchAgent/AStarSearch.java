package WinterHasCome.model.searchAgent;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStarSearch extends QueueingFunction {

	int i;
	public AStarSearch(int i) {
		this.i = i;
		super.queue = new PriorityQueue<SearchTreeNode>(new Comparator<SearchTreeNode>() {

			@Override
			public int compare(SearchTreeNode o1, SearchTreeNode o2) {
				WesterosState node1S = (WesterosState)o1.getState();
				WesterosState node2S = (WesterosState)o2.getState();
				int distance_a = heuristicFunc(node1S.getJonX(),node1S.getJonY(), node1S.getEnemyCount(), node1S.getWhiteWalkers(), node1S.getDragonStaone(), node1S.getDragonStoneCarried()) + o1.getPathCost();
		        int distance_b = heuristicFunc(node2S.getJonX(),node2S.getJonY(), node2S.getEnemyCount(), node2S.getWhiteWalkers(), node2S.getDragonStaone(), node2S.getDragonStoneCarried()) + o2.getPathCost();
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
	
	private static int heuristicFunc1(int enemyCount) {
		return (int) (Math.ceil(enemyCount/3.0))*2; 
	}
	
	private static int heuristicFunc2(int x1, int y1,HashSet<Cell> ww, Cell dragon, int carried) {
		int estimate = 0;
		Cell z = new Cell(x1, y1);
		if(carried == 0) {
			estimate = (Math.abs(dragon.y-y1) + Math.abs(dragon.x-x1));
			z = dragon;
		}
		Cell[] wwList = ww.toArray(new Cell[ww.size()]);
		int max = -1;
		for (int i = 0; i < wwList.length; i++) {
			estimate += (Math.abs(wwList[i].y-z.y) + Math.abs(wwList[i].x-z.x) - 1 + 30);
			if(estimate>max) {
				max = estimate;
			}
		}
		return max;
//		return Math.abs(y2-y1) + Math.abs(x2-x1) - 1 + 15;
	}
	
	private int heuristicFunc(int x1, int y1, int enemyCount,HashSet<Cell> ww, Cell dragon, int carried) {
		if(this.i == 1) {
			return heuristicFunc1(enemyCount);
		}
		return heuristicFunc2(x1, y1, ww, dragon, carried);
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
