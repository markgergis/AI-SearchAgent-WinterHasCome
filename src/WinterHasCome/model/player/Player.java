package WinterHasCome.model.player;

public class Player {

	private String name;
	private int dragonglass;
	private final int numOfPieces;
	
	public Player(int numOfPieces) {
		name = "Jon Snow";
		dragonglass = 0;
		this.numOfPieces = numOfPieces;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDragonglass() {
		return dragonglass;
	}

	public void setDragonglass(int dragonglass) {
		this.dragonglass = dragonglass;
	}

	public int getNumOfPieces() {
		return numOfPieces;
	}
	
	
}
