package WinterHasCome.model.world;

import java.util.Arrays;
import java.util.Random;

import WinterHasCome.model.cell.Cell;
import WinterHasCome.model.cell.CellListener;
import WinterHasCome.model.cell.DragonstoneCell;
import WinterHasCome.model.cell.EmptyCell;
import WinterHasCome.model.cell.ObstacleCell;
import WinterHasCome.model.cell.WhiteWalkerCell;
import WinterHasCome.model.character.WhiteWalker;

public class World implements CellListener{

	private Cell[][] map;
	private int jonColumn;
	private int jonRow;
	private int jonDragonglass;
	public int getJonDragonglass() {
		return jonDragonglass;
	}

	private WorldListener listener;
	

	public World(int m, int n) {
		map = new Cell[m][n];
		
	}

	public Cell[][] getMap() {
		return map;
	}

	public int getPlayerColumn() {
		return jonColumn;
	}

	public int getPlayerRow() {
		return jonRow;
	}
	
	public String toString() {
		String ret = "";
		for (int i = 0; i < map.length; i++) {
			ret += Arrays.toString(map[i]) + "\n";
		}
		return ret;
	}

	public WorldListener getListener() {
		return listener;
	}

	public void setListener(WorldListener listener) {
		this.listener = listener;
	}
	
	public void genGrid() {
		Random rand = new Random();
		var m = map.length;
		var n = map[0].length;
		var dc = rand.nextInt(m*n/4);
		var oc = rand.nextInt(m*n/5);
		jonDragonglass = rand.nextInt(m*n);
		int x;
		int y;
		do {
			x = rand.nextInt(m);
			y = rand.nextInt(n);
		} while (x == m-1 && y == n-1);
		map[x][y] = new DragonstoneCell();
		map[m-1][n-1] = new EmptyCell();
		while (dc-- > 0) {
			do {
				x = rand.nextInt(m);
				y = rand.nextInt(n);
			} while (map[x][y] != null);
			map[x][y] = new WhiteWalkerCell();
		}
		while (oc-- > 0) {
			do {
				x = rand.nextInt(m);
				y = rand.nextInt(n);
			} while (map[x][y] != null);
			map[x][y] = new ObstacleCell();
		}
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if((map[i][j] != null)) continue;
				map[i][j] = new EmptyCell();
//				switch (rand.nextInt(3)) {
//				case 0:
//					map[i][j] = new EmptyCell();
//					break;
//				case 1:
//					map[i][j] = new WhiteWalkerCell();
//					break;
//				case 2:
//					map[i][j] = new ObstacleCell();
//					break;
//
//				default: map[i][j] = new EmptyCell();
//					break;
//				}
			}
		}
	}
	

	@Override
	public void onWhiteWalkerEncountered(WhiteWalker whiteWalker) {
		// TODO Auto-generated method stub
		if (listener != null) {
			listener.onWhiteWalkerEncountered(whiteWalker);

		}
	}
	
	public void setEmptyCell(int c, int r) {
		map[r][c] = new EmptyCell();
	}
	
	public void moveDown() {

		if (jonRow < map.length -1) {
			jonRow++;
			map[jonRow][jonColumn].onStep();
			return;
		}

	}

	public void moveUp() {
		if (jonRow > 0) {
			jonRow--;
			map[jonRow][jonColumn].onStep();
			return;
		}
	}

	public void moveRight() {
		if (jonColumn <  map[0].length -1) {
			jonColumn++;
			map[jonRow][jonColumn].onStep();
			return;
		}
	}

	public void moveLeft() {
		if (jonColumn > 0) {
			jonColumn--;
			map[jonRow][jonColumn].onStep();
			return;
		}
	}
}
