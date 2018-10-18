package winterHasCome.model.cell;

public enum CellType {
	EMPTY {
		public String toString() {
			return ("[ ]");
		}
	},
	WHITEWALKER {
		public String toString() {
			return ("[W]");
		}
	},
	OBSTACLE {
		public String toString() {
			return ("[X]");
		}
	},
	DRAGONSTONE {
		public String toString() {
			return ("[D]");
		}
	}

}
