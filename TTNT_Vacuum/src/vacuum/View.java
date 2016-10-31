package vacuum;

import java.awt.*;
import static java.awt.Color.*;
import java.awt.geom.*;
import javax.swing.*;
import static javax.swing.JFrame.*;
import static java.lang.Math.*;

@SuppressWarnings("serial")
public class View extends JPanel {

	private Agent agent;

	private World world;

	public static final Color BROWN = new Color(153, 102, 51);

	public View(World world, Agent agent) {
		this.world = world;
		this.agent = agent;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (int r = 0; r < world.getHeight(); r++) {
			for (int c = 0; c < world.getWidth(); c++) {
				Square s = world.getSquare(r, c);
				double width = 1.0 * getWidth() / world.getWidth();
				double height = 1.0 * getHeight() / world.getHeight();
				double left = c * width;
				double top = r * height;
				g2.setColor(BLACK);
				g2.draw(new Rectangle2D.Double(left, top, width, height));
				if (s.isObstacle()) {
					g2.fill(new Rectangle2D.Double(left, top, width, height));
				} else if (s.isDirty()) {
					g2.setColor(BROWN);
					g2.fill(new Rectangle2D.Double(floor(left + 1),
							floor(top + 1), floor(width), floor(height)));
				}
				if ((r == agent.getRow()) && (c == agent.getColumn())) {
					g2.setColor(BLUE);
					g2.fill(new Ellipse2D.Double(floor(left + 1),
							floor(top + 1), floor(width), floor(height)));
				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame();
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		World world = new World(30, 30);
		Agent agent = new StateAgent();
		world.place(agent);
		frame.add(new View(world, agent));
		frame.setVisible(true);
		while (true) {
			world.step(agent);
			frame.repaint();
			Thread.sleep(10);
		}
	}

}
