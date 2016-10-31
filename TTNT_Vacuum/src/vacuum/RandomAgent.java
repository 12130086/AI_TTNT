package vacuum;

public class RandomAgent extends Agent {

	java.util.Random generator = new java.util.Random();

	@Override
	public int react(boolean dirty) {
		if (dirty == true) {
			return World.SUCK;
		} else {

			int die = generator.nextInt(4);
			if (die == 0) {
				return World.UP;
			} else if (die == 1) {
				return World.DOWN;
			} else if (die == 2) {
				return World.LEFT;
			} else {
				return World.RIGHT;
			}
		}
	}

}
