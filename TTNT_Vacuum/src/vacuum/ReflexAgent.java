package vacuum;

public class ReflexAgent extends Agent {

	@Override
	public int react(boolean dirty) {

		if (dirty == true) {
			return World.SUCK;
		} else {
			return World.RIGHT;
		}
	}

}
