import java.awt.Color;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Slot extends JButton {
	public int i, j;
	public Piece piece = Piece.None;

	public Slot(int i, int j) {
		this.i = i;
		this.j = j;
		setOpaque(true);
		setColor();
	}

	public void setColorPiece(Piece piece) {
		this.piece = piece;
		setColor();
	}

	public void setColor() {
		switch (piece) {
		case Red:
			setBackground(Color.red);
			break;
		case Black:
			setBackground(Color.black);
			break;
		case None:
			setBackground(Color.white);
			break;
		}
	}
}