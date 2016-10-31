package vacuum;

public class StateAgent extends Agent {

	@SuppressWarnings("unused")
	private int clean, totalClean, prev;
	private int currentMovement = 0;
	int die = 0;

	java.util.Random generator = new java.util.Random();

	@Override
	public int react(boolean dirty) {

		if (totalClean < 250) {
			if (dirty == true) {
				totalClean++;
				return World.SUCK;
			}

			if (currentMovement == 2) {
				die = generator.nextInt(4);

				while ((prev == 0) && (die == 1)) {
					die = generator.nextInt(4);
				}
				while ((prev == 1) && (die == 0)) {
					die = generator.nextInt(4);
				}
				while ((prev == 2) && (die == 3)) {
					die = generator.nextInt(4);
				}
				while ((prev == 3) && (die == 2)) {
					die = generator.nextInt(4);
				}

				currentMovement = 0;
				return move(die);
			} else {
				currentMovement++;
				prev = die;
				return move(die);
			}
		} else {
			if (dirty == true) {
				totalClean++;
				return World.SUCK;
			}
			if (currentMovement == 3) {
				die = generator.nextInt(4);
				while ((prev == 0) && (die == 1)) {
					die = generator.nextInt(4);
				}
				while ((prev == 1) && (die == 0)) {
					die = generator.nextInt(4);
				}
				while ((prev == 2) && (die == 3)) {
					die = generator.nextInt(4);
				}
				while ((prev == 3) && (die == 2)) {
					die = generator.nextInt(4);
				}
				currentMovement = 0;
				return move(die);
			} else {
				currentMovement++;
				prev = die;
				return move(die);
			}

		}

	}

	public int move(int x) {
		if (x == 0) {
			return World.UP;
		} else if (x == 1) {
			return World.DOWN;
		} else if (x == 2) {
			return World.LEFT;
		} else if (x == 3) {
			return World.RIGHT;
		}
		return World.NO_OP;
	}

}
