import java.util.ArrayList;

public class Tree {
	public int value;
	Slot[][] slots;
	private ArrayList<Integer> bestMoves;
	Slot prev = null;
	int depth;
	static int maxDepth = 6; // Độ sâu tối đa của cây tìm kiếm, cũng là chiều
								// cao của bảng trò chơi

	public Tree(Slot[][] slots, int depth) {
		this.value = 0;
		this.slots = slots;
		this.bestMoves = new ArrayList<Integer>();
		this.depth = depth;

		if (depth < maxDepth) {
			// Tập chứa các bước đi tiếp theo trong cây tìm kiếm
			ArrayList<Integer> nextMoves = new ArrayList<Integer>();
			for (int i = 0; i < 7; i++)
				// Duyệt qua 7 vị trí đặt quân
				if (slots[i][0].piece == Piece.None)
					// Chưa có quân đặt thì thêm vào tập duyệt
					nextMoves.add(i);

			for (int i = 0; i < nextMoves.size(); i++) {
				insertTo(slots[nextMoves.get(i)][0]); // Nhét thử quân vào ô

				Tree child = new Tree(slots, depth + 1); // Đệ quy cây tìm kiếm
															// (cây con)
				prev.setPiece(Piece.None);

				// Nếu là trạng thái gốc
				if (i == 0) {
					bestMoves.add(nextMoves.get(i));
					value = child.value;

				} // Nếu là Max
				else if (depth % 2 == 0) {
					if (value < child.value) {
						bestMoves.clear();
						bestMoves.add(nextMoves.get(i));
						this.value = child.value;
					} else if (value == child.value)
						bestMoves.add(nextMoves.get(i));

				} // Nếu là Min
				else if (depth % 2 == 1) {
					if (value > child.value) {
						bestMoves.clear();
						bestMoves.add(nextMoves.get(i));
						this.value = child.value;
					} else if (value == child.value)
						bestMoves.add(nextMoves.get(i));
				}
			}
		} else {
			this.value = getValue(); // Hàm đánh giá
		}
	}

	void printSlots() {
		for (int j = 0; j < 6; j++) {
			for (int i = 0; i < 7; i++) {
				switch (slots[i][j].piece) {
				case Blue:
					System.out.print("X");
					break;
				case Red:
					System.out.print("Đ");
					break;
				default:
					System.out.print("-");
					break;
				}
			}
			System.out.println();
		}
	}

	// Hàm thêm quân vào ô
	void insertTo(Slot slot) {
		if (slot.piece != Piece.None)
			// Nếu đã có quân thì không thêm được
			return;

		int i = slot.i;
		int j = slot.j;

		while (j < slots[0].length - 1 && slots[i][j + 1].piece == Piece.None)
			j++;

		if (depth % 2 == 0)
			slots[i][j].setPiece(Piece.Red);
		else
			slots[i][j].setPiece(Piece.Blue);
		prev = slots[i][j];
	}

	public int getX() {
		int random = (int) (Math.random() * 100) % bestMoves.size();
		return bestMoves.get(random);
	}

	// Hàm đánh giá trạng thái
	public int getValue() {
		int value = 0;
		for (int j = 0; j < 6; j++) { // Duyệt 6 tầng trò chơi
			for (int i = 0; i < 7; i++) { // Duyệt 7 vị trí trên hàng
				if (slots[i][j].piece != Piece.None) { // Nếu đã đánh mới xét
					if (slots[i][j].piece == Piece.Red) {
						value += nextConnect(i, j); // Nếu là quân Đỏ (Máy) thì
													// cộng điểm đánh giá
					} else {
						value -= nextConnect(i, j);
					}
				}
			}
		}
		return value;
	}

	public int nextConnect(int i, int j) {
		int value = 0;
		// Duyệt về các hướng và đánh giá hàng sắp được 4
		value += lineOfFour(i, j, -1, -1);
		value += lineOfFour(i, j, -1, 0);
		value += lineOfFour(i, j, -1, 1);
		value += lineOfFour(i, j, 0, -1);
		value += lineOfFour(i, j, 0, 1);
		value += lineOfFour(i, j, 1, -1);
		value += lineOfFour(i, j, 1, 0);
		value += lineOfFour(i, j, 1, 1);

		return value;
	}

	public int lineOfFour(int x, int y, int i, int j) {
		int value = 1;
		Piece color = slots[x][y].piece; // Mảng các quân

		for (int k = 1; k < 4; k++) {
			// Nếu quân đang xét có các quân nằm trên hướng nối đến 4 không nằm
			// trong bảng trò chơi
			if (x + i * k < 0 || y + j * k < 0 || x + i * k >= slots.length
					|| y + j * k >= slots[0].length)
				return 0;

			// Nếu các quân nằm trên hướng nối là quân cùng màu với quân đang
			// xét thì tăng điểm đánh giá
			if (slots[x + i * k][y + j * k].piece == color)
				value++;
			// Nếu các quân nằm trên hướng nối là là quân khác màu
			else if (slots[x + i * k][y + j * k].piece != Piece.None)
				return 0;
			// Nếu các quân nằm trên hướng nối là quân rỗng với quân đang
			// xét thì giảm điểm đánh giá
			else {
				for (int l = y + j * k; l >= 0; l--)
					if (slots[x + i * k][l].piece == Piece.None)
						value--;
			}
		}

		if (value == 4)
			return 100; // Điểm đánh giá của trạng thái thắng
		if (value < 0) // Nếu giá trị của điểm đánh giá là âm
			return 0;
		return value;
	}
}