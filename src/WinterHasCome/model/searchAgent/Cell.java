package WinterHasCome.model.searchAgent;

public class Cell implements Comparable<Cell> {
	public int x;
	public int y;

	public Cell(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Cell) {
			Cell cell = (Cell) obj;
			return cell.x == x && cell.y == y;
		}
		return super.equals(obj);
	}
	
	@Override
	public int compareTo(Cell other) {
	    return Integer.compare(this.x, other.x) - Integer.compare(this.y, other.y);
	}
	
	@Override
	public int hashCode() {
		return x*1000+y;//for simplicity reason
	}

}
