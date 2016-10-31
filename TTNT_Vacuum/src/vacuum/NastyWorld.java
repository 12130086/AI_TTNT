package vacuum;

public class NastyWorld extends World {

	public NastyWorld(int height, int width) {
		super(height, width);
		Square s;
		int r = 0, c = 0;
		for (r = 0; r < 25; r++) {
			for (c = 0; c < 25; c++) {
				s = getSquare(r, c);
				s.setDirty(true);
				s.setObstacle(false);
			}
		}

		for (r = 1; r < 25; r += 2) {

			for (c = 1; c < 24; c++) {
				s = getSquare(r, c);
				s.setObstacle(true);
			}

			for (c = 0; c < 24; c += 4) {
				s = getSquare(c, 1);
				s.setObstacle(true);
			}

			for (c = 3; c < 24; c += 4) {
				s = getSquare(c, 24);
				s.setObstacle(true);
			}
		}

	}
}
