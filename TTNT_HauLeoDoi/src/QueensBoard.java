import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QueensBoard {

	public static int TOTAL_QUEENS = 8;
	private int[][] board;
	private int[] queenPositions;

	public static void main(String[] args) {

		boolean climb = true;
		int climbCount = 0;

		while (climb) {
			QueensBoard board = new QueensBoard(new int[TOTAL_QUEENS][TOTAL_QUEENS], new int[8]);

			// Đặt Hậu ngẫu nhiên
			board.placeQueens();
			System.out.println("Lần thử thứ: " + (climbCount + 1));
			System.out.println("Bàn cờ gốc:");
			board.printBoard();
			System.out.println("Số cặp hậu tấn công lẫn nhau: " + board.h() + "\n");

			// Điểm để so sánh
			int localMin = board.h();
			boolean best = false;
			// Mảng chứa vị trí Hậu tốt nhất theo hàng
			int[] bestQueenPositions = new int[8];

			// Duyệt từng cột
			for (int j = 0; j < board.board.length; j++) {
				System.out.println("Duyệt CỘT " + j + ":");
				best = false;
				// Duyệt từng hàng
				for (int i = 0; i < board.board.length; i++) {

					// Bỏ qua tính toán điểm với bảng gốc
					if (i != board.queenPositions[j]) {

						// Di chuyển Hậu
						board.moveQueen(i, j);
						board.printBoard();
						System.out.println();

						// Tính điểm, nếu điểm tốt nhất thì lưu vị trí Hậu
						if (board.h() < localMin) {
							best = true;
							localMin = board.h();
							bestQueenPositions[j] = i;
						}

						// Reset về vị trí Hậu ban đầu
						board.resetQueen(i, j);

					}
				}

				// Đổi 2 về 1
				board.resetBoard(j);
				if (best) {
					// Nếu best thì bỏ hậu vào
					board.placeBestQueen(j, bestQueenPositions[j]);
					System.out.println("Bàn cờ tốt nhất của lần duyệt này: ");
					board.printBoard();
					System.out.println("Số cặp hậu tấn công lẫn nhau: " + board.h() + "\n");
				} else {
					System.out.println("Không tìm được bàn cờ tốt hơn.");
					board.printBoard();
					System.out.println("Số cặp hậu tấn công lẫn nhau: " + board.h() + "\n");
				}
			}

			// Nếu score = 0, thuật toán leo đồi đã giải ra
			if (board.h() == 0)
				climb = false;

			climbCount++;

			// Chỉ 5 lần lặp lại
			if (climbCount == 6) {
				climb = false;
			}
			System.out.println("Hoàn thành trong " + (climbCount - 1) + " lần khởi tạo lại.");
		}

	}

	public QueensBoard(int[][] board, int[] positions) {

		this.board = board;
		this.queenPositions = positions;

	}

	private int[] generateQueens() {

		List<Integer> randomPos = new ArrayList<Integer>();

		Random r = new Random();
		for (int i = 0; i < TOTAL_QUEENS; i++) {
			randomPos.add(r.nextInt(8));
		}

		int[] randomPositions = new int[TOTAL_QUEENS];

		for (int i = 0; i < randomPos.size(); i++) {
			randomPositions[i] = randomPos.get(i);
		}

		return randomPositions;
	}

	public void placeQueens() {

		queenPositions = generateQueens();

		for (int i = 0; i < board.length; i++) {
			board[queenPositions[i]][i] = 1;
		}

	}

	public int h() {

		int totalPairs = 0;

		// checking rows
		for (int i = 0; i < board.length; i++) {
			ArrayList<Boolean> pairs = new ArrayList<Boolean>();
			for (int j = 0; j < board[i].length; j++) {

				if (board[i][j] == 1) {
					pairs.add(true);
				}

			}
			if (pairs.size() != 0) {
				totalPairs = totalPairs + (pairs.size() - 1);

			}

		}

		// Kiểm tra đường chéo xuôi
		int rows = board.length;
		int cols = board.length;
		int maxSum = rows + cols - 2;

		for (int sum = 0; sum <= maxSum; sum++) {
			ArrayList<Boolean> pairs = new ArrayList<Boolean>();
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if (i + j - sum == 0) {
						if (board[i][j] == 1) {
							pairs.add(true);
						}
					}
				}

			}
			if (pairs.size() != 0) {
				totalPairs = totalPairs + (pairs.size() - 1);

			}
		}

		// Kiểm tra đường chéo ngược
		int pairs = checkMirrorDiagonal();

		return totalPairs + pairs;
	}

	private int checkMirrorDiagonal() {

		int[] b1 = { board[7][0] };
		int[] b2 = { board[7][1], board[6][0] };
		int[] b3 = { board[7][2], board[6][1], board[5][0] };
		int[] b4 = { board[7][3], board[6][2], board[5][1], board[4][0] };
		int[] b5 = { board[7][4], board[6][3], board[5][2], board[4][1], board[3][0] };
		int[] b6 = { board[7][5], board[6][4], board[5][3], board[4][2], board[3][1], board[2][0] };
		int[] b7 = { board[7][6], board[6][5], board[5][4], board[4][3], board[3][2], board[2][1], board[1][0] };
		int[] b8 = { board[7][7], board[6][6], board[5][5], board[4][4], board[3][3], board[2][2], board[1][1],
				board[0][0] };
		int[] b9 = { board[6][7], board[5][6], board[4][5], board[3][4], board[2][3], board[1][2], board[0][1] };
		int[] b10 = { board[5][7], board[4][6], board[3][5], board[2][4], board[1][3], board[0][2] };
		int[] b11 = { board[4][7], board[3][6], board[2][5], board[1][4], board[0][3] };
		int[] b12 = { board[3][7], board[2][6], board[1][5], board[0][4] };
		int[] b13 = { board[2][7], board[1][6], board[0][5] };
		int[] b14 = { board[1][7], board[0][6] };
		int[] b15 = { board[0][7] };

		int totalPairs = 0;

		totalPairs += checkMirrorHorizontal(b1);
		totalPairs += checkMirrorHorizontal(b2);
		totalPairs += checkMirrorHorizontal(b3);
		totalPairs += checkMirrorHorizontal(b4);
		totalPairs += checkMirrorHorizontal(b5);
		totalPairs += checkMirrorHorizontal(b6);
		totalPairs += checkMirrorHorizontal(b7);
		totalPairs += checkMirrorHorizontal(b8);
		totalPairs += checkMirrorHorizontal(b9);
		totalPairs += checkMirrorHorizontal(b10);
		totalPairs += checkMirrorHorizontal(b11);
		totalPairs += checkMirrorHorizontal(b12);
		totalPairs += checkMirrorHorizontal(b13);
		totalPairs += checkMirrorHorizontal(b14);
		totalPairs += checkMirrorHorizontal(b15);

		return totalPairs;

	}

	public void moveQueen(int row, int col) {

		// Vị trí Hậu gốc sẽ thành 2
		board[queenPositions[col]][col] = 2;

		board[row][col] = 1;

	}

	private int checkMirrorHorizontal(int[] b) {

		int totalPairs = 0;

		ArrayList<Boolean> pairs = new ArrayList<Boolean>();
		for (int i = 0; i < b.length; i++) {
			if (b[i] == 1)
				pairs.add(true);

		}

		if (pairs.size() != 0)
			totalPairs = (pairs.size() - 1);

		return totalPairs;
	}

	public void resetQueen(int row, int col) {

		if (board[row][col] == 1)
			board[row][col] = 0;
	}

	public void resetBoard(int col) {

		for (int i = 0; i < board.length; i++) {
			if (board[i][col] == 2)
				board[i][col] = 1;
		}
	}

	public void placeBestQueen(int col, int queenPos) {

		for (int i = 0; i < board.length; i++) {
			if (board[i][col] == 1)
				board[i][col] = 2;

		}
		board[queenPos][col] = 1;
		for (int i = 0; i < board.length; i++) {
			if (board[i][col] == 2)
				board[i][col] = 0;

		}
	}

	public void printBoard() {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

}