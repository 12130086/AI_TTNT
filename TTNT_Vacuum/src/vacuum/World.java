package vacuum;

import static java.lang.Math.*;

/** The vacuum world. */
public class World {

	/** Action: do nothing. */
	public static final int NO_OP = 0;

	/** Action: suck dirt in this square. */
	public static final int SUCK = 1;

	/** Action: move up if possible. */
	public static final int UP = 2;

	/** Action: move down if possible. */
	public static final int DOWN = 3;

	/** Action: move left if possible. */
	public static final int LEFT = 4;

	/** Action: move right if possible. */
	public static final int RIGHT = 5;

	public static void main(String[] args) {
		int sum = 0;
		for (int i = 0; i < 100; i++) {
			World world = new World(25, 25);
			Agent agent = new StateAgent();
			world.place(agent);
			sum += world.simulate(agent, 10000);
		}
		System.out.println(sum / 100);
	}

	/** The squares in the world. */
	private Square[][] squares;

	public World(int height, int width) {
		squares = new Square[height][width];
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				squares[r][c] = new Square();
			}
		}
	}

	/** Returns the height of the world. */
	public int getHeight() {
		return squares.length;
	}

	/** Returns the square in row r, column c. */
	public Square getSquare(int r, int c) {
		return squares[r][c];
	}

	/** Returns the width of the world. */
	public int getWidth() {
		return squares[0].length;
	}

	/**
	 * Places agent randomly in the world (not on an obstacle).
	 * If the entire world is full of obstacles, this method goes into an infinite loop.
	 */
	public void place(Agent agent) {
		while (true) {
			int r = (int) (random() * getHeight());
			int c = (int) (random() * getHeight());
			if (!getSquare(r, c).isObstacle()) {
				agent.setPosition(r, c);
				return;
			}
		}
	}

	/**
	 * Runs the agent for the specified number of steps. Returns the agent's
	 * score: the sum, over all steps, of the number of clean squares and
	 * obstacles.
	 */
	public int simulate(Agent agent, int steps) {
		int score = 0;
		for (int t = 0; t < steps; t++) {
			step(agent);
			for (int r = 0; r < getHeight(); r++) {
				for (int c = 0; c < getHeight(); c++) {
					if (squares[r][c].isObstacle() || !squares[r][c].isDirty()) {
						score++;
					}
				}
			}
		}
		return score;
	}

	/** Performs one step of simulation: feeds the agent a percept and performs the agent's action. */
	public void step(Agent agent) {
		int r = agent.getRow();
		int c = agent.getColumn();
		Square square = getSquare(r, c);
		boolean percept = square.isDirty();
		int action = agent.react(percept);
		int dr = r; // Row of destination square
		int dc = c; // Column of destination square
		if (action == SUCK) {
			square.setDirty(false);
		} else if (action == UP) {
			if (r > 0) {
				dr--;
			}
		} else if (action == DOWN) {
			if (r < getHeight() - 1) {
				dr++;
			}
		} else if (action == LEFT) {
			if (c > 0) {
				dc--;
			}
		} else if (action == RIGHT) {
			if (c < getWidth() - 1) {
				dc++;
			}
		}
		if (!getSquare(dr, dc).isObstacle()) {
			agent.setPosition(dr, dc);
		}
	}

}
