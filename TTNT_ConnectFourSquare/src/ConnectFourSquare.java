import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

enum Piece {
	Red, Black, None
}

@SuppressWarnings("serial")
public class ConnectFourSquare extends JFrame implements ActionListener {
	JLabel lblPlayer = new JLabel("Lượt: ");
	JLabel lblCurrentPlayer = new JLabel("Đen");
	JPanel pnlMenu = new JPanel();
	JPanel pnlSlots = new JPanel();
	JButton btnNewGame = new JButton("Trò chơi mới (2 người chơi)");
	JButton btnNewGameAI = new JButton("Trò chơi mới (chơi với máy)");

	Slot[][] slots = new Slot[7][6];

	boolean winnerExists = false;
	int currentPlayer = 1;
	boolean AI;

	public ConnectFourSquare(boolean AI) {
		super("Bốn quân một hàng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		currentPlayer = (int) (Math.random() * 2) + 1;

		this.AI = AI;

		btnNewGame.addActionListener(this);
		btnNewGameAI.addActionListener(this);

		switch (currentPlayer) {
		case 1:
			lblCurrentPlayer.setForeground(Color.black);
			lblCurrentPlayer.setText("Đen");
			break;
		case 2:
			lblCurrentPlayer.setForeground(Color.red);
			lblCurrentPlayer.setText("Đỏ");
			break;
		}
		pnlMenu.add(btnNewGame);
		pnlMenu.add(btnNewGameAI);
		pnlMenu.add(lblPlayer);
		pnlMenu.add(lblCurrentPlayer);

		pnlSlots.setLayout(new GridLayout(6, 7));

		for (int j = 0; j < 6; j++)
			for (int i = 0; i < 7; i++) {
				slots[i][j] = new Slot(i, j);
				slots[i][j].addActionListener(this);
				pnlSlots.add(slots[i][j]);
			}

		add(pnlMenu, BorderLayout.NORTH);
		add(pnlSlots, BorderLayout.CENTER);
		setSize(500, 500);
		setVisible(true);

		if (currentPlayer == 2 && AI)
			insertTo(minimax());
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == btnNewGame) {
			if (JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát trò chơi hiện tại không?", "Xác nhận",
					JOptionPane.YES_NO_OPTION) == 0) {
				dispose();
				new ConnectFourSquare(false);
				return;
			}
		}
		if (ae.getSource() == btnNewGameAI) {
			if (JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát trò chơi hiện tại không?", "Xác nhận",
					JOptionPane.YES_NO_OPTION) == 0) {
				dispose();
				new ConnectFourSquare(true);
				return;
			}
		} else if (!winnerExists) {
			Slot slot = (Slot) ae.getSource();
			insertTo(slot);
		}
	}

	void insertTo(Slot slot) {
		if (slot.piece != Piece.None)
			return;

		int i = slot.i;
		int j = slot.j;

		while (j < slots[0].length - 1 && slots[i][j + 1].piece == Piece.None)
			j++;

		switch (currentPlayer) {
		case 1:
			slots[i][j].setColorPiece(Piece.Black);
			System.out.println("Quân Đen đánh ở: [" + i + "|" + j + "]");
			break;
		case 2:
			slots[i][j].setColorPiece(Piece.Red);
			System.out.println("Quân Đỏ đánh ở: [" + i + "|" + j + "]");
			break;
		}

		currentPlayer = (currentPlayer % 2) + 1;

		if (thereIsAWinner()) {
			lblPlayer.setText("Thắng: ");
			winnerExists = true;
		} else {

			// Nếu đang chơi với người
			switch (currentPlayer) {
			case 1:
				lblCurrentPlayer.setForeground(Color.black);
				lblCurrentPlayer.setText("Đen");
				break;
			case 2:
				lblCurrentPlayer.setForeground(Color.red);
				lblCurrentPlayer.setText("Đỏ");
				break;
			}

			// Nếu đang chơi với máy
			if (currentPlayer == 2 && AI) {
				insertTo(minimax());
			}
		}
	}

	public boolean thereIsAWinner() {
		for (int j = 0; j < 6; j++) {
			for (int i = 0; i < 7; i++) {
				if (slots[i][j].piece != Piece.None && connectsToFour(i, j))
					return true;
			}
		}
		return false;
	}

	public boolean connectsToFour(int i, int j) {
		if (lineOfFour(i, j, -1, -1))
			return true;
		if (lineOfFour(i, j, -1, 0))
			return true;
		if (lineOfFour(i, j, -1, 1))
			return true;
		if (lineOfFour(i, j, 0, -1))
			return true;
		if (lineOfFour(i, j, 0, 1))
			return true;
		if (lineOfFour(i, j, 1, -1))
			return true;
		if (lineOfFour(i, j, 1, 0))
			return true;
		if (lineOfFour(i, j, 1, 1))
			return true;
		return false;
	}

	public boolean lineOfFour(int x, int y, int i, int j) {
		Piece color = slots[x][y].piece;

		for (int k = 1; k < 4; k++) {
			if (x + i * k < 0 || y + j * k < 0 || x + i * k >= slots.length || y + j * k >= slots[0].length)
				return false;
			if (slots[x + i * k][y + j * k].piece != color)
				return false;
		}
		return true;
	}

	public Slot minimax() {
		Tree tree = new Tree(slots, 0);
		return slots[tree.getX()][0];
	}

	public static void main(String[] args) throws Exception {
		new ConnectFourSquare(false);
	}
}