import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.CompoundBorder;

public class GiaoDien extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static GiaoDien board = new GiaoDien();
	static JPanel p1, p2, p3;

	ImageIcon quanHauIcon = new ImageIcon("quanHau.png");

	JButton btn1 = new JButton("Đặt 8 quân Hậu vào bàn cờ");

	public static final int CHIEU_CAO = 8;
	public static final int CHIEU_RONG = 8;

	JButton[][] btn = new JButton[CHIEU_CAO][CHIEU_RONG];

	int mines[][] = new int[CHIEU_CAO][CHIEU_RONG];

	public GiaoDien() {
		p1 = new JPanel();
		btn1.addActionListener(this);
		p1.add(btn1, BorderLayout.CENTER);

		p2 = new JPanel();
		p2.setLayout(new GridLayout(CHIEU_CAO, CHIEU_RONG));
		p2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
				BorderFactory.createLineBorder(Color.BLACK, 2)));

		for (int x = 0; x < CHIEU_CAO; x++) {
			for (int y = 0; y < CHIEU_RONG; y++) {
				btn[x][y] = new JButton("");
				btn[x][y].setFocusPainted(false);

				if ((y % 2 == 1 && x % 2 == 1) || (y % 2 == 0 && x % 2 == 0)) {
					btn[x][y].setBackground(Color.WHITE);
				} else {
					btn[x][y].setBackground(Color.GRAY);
				}
				p2.add(btn[x][y]);
			}
		}
	}

	public void printBanCo8Hau(BanCo banCo) {
		for (int y = 0; y < 8; y++) {
			btn[banCo.getHau(y)][y].setIcon(quanHauIcon);
		}
	}

	public void xoaBanCo() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				btn[x][y].setIcon(null);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) {
			xoaBanCo();
			int mut = 1; // Tỉ lệ đột biến
			long ketThucTime;
			long batDauTime;
			batDauTime = System.currentTimeMillis();

			DiTruyen diTruyen = new DiTruyen(4, 8);
			System.out.println("---------G---------");
			diTruyen.taoQuanThe();
			diTruyen.print();
			int i = 0;
			for (; diTruyen.bestBoard().fitness() > 0; i++) { // tìm kiếm tới
																// khi
																// gặp fitness =
																// 0

				System.out.println("---------" + i + "---------");
				diTruyen.laiGhepBanCo();
				diTruyen.print();
				diTruyen.dotBienToanBoCaThe(mut);
			}

			ketThucTime = System.currentTimeMillis();

			System.out.println("------------------");
			System.out.println("Thời gian giải: " + ((ketThucTime - batDauTime)) + " mili-giây");

			System.out.println(i + " Thế hệ\nGiải pháp:");
			diTruyen.bestBoard().print();
			printBanCo8Hau(diTruyen.bestBoard());
			JOptionPane.showMessageDialog(this,
					"Thời gian giải: " + ((ketThucTime - batDauTime)) + " mili-giây, với " + i + " thế hệ.", "Hoàn tất",
					JOptionPane.PLAIN_MESSAGE);
		}

	}

	public static void main(String[] args) {
		board.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		board.add(p1, BorderLayout.NORTH);
		board.add(p2, BorderLayout.CENTER);
		board.setSize(450, 500);
		board.setVisible(true);
		board.setResizable(false);

	}

}