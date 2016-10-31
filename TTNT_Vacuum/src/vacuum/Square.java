package vacuum;

import static java.lang.Math.*;

public class Square {

	private boolean dirty;

	private boolean obstacle;

	public Square() {
		dirty = random() < 0.5;
		obstacle = random() < 0.1;
	}

	public boolean isDirty() {
		return dirty;
	}

	public boolean isObstacle() {
		return obstacle;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	public void setObstacle(boolean obstacle) {
		this.obstacle = obstacle;
	}

}
