package WinterHasCome.model.searchAgent;

public class Cell {
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

}
