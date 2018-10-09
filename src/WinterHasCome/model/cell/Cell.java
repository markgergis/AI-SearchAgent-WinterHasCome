package WinterHasCome.model.cell;

public abstract class Cell {

	public abstract String toString();

	private CellListener listener;

	public CellListener getListener() {
		return listener;
	}

	public void setListener(CellListener listener) {
		this.listener = listener;
	}

	public abstract void onStep();

}
