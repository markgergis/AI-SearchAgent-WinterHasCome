package WinterHasCome.model.searchAgent;

public enum CellType {
	EMPTY, WHITEWALKER, OBSTACLE, DRAGONSTONE;
	
	public String toString() {
		if (this == EMPTY) {
			return ("[ ]");
		} 
		if (this == WHITEWALKER) {
			return ("[W]");
		}
		if (this == OBSTACLE) {
			return ("[X]");
		}
		if (this == DRAGONSTONE) {
			return ("[D]");
		}
		return "";
		
	}
}
