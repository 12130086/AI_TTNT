package vacuum;

public abstract class Agent {

	private int row;

	private int column;

	public void setPosition(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public abstract int react(boolean dirty);

}
