public class State {
	private int stateGame;
	private int viTriThuyen;
	private int soNguoiTrenThuyen, soQuyTrenThuyen;
	private int soNguoiDoiTrai, soQuyDoiTrai;
	private int soNguoiDoiPhai, soQuyDoiPhai;
	private State parentStateGame;

	public State(int stateGame, int viTriThuyen, int soNguoiTrenThuyen,
			int soQuyTrenThuyen, int soNguoiDoiTrai, int soQuyDoiTrai,
			int soNguoiDoiPhai, int soQuyDoiPhai, State parentStateGame) {
		this.stateGame = stateGame;
		this.viTriThuyen = viTriThuyen;
		this.soNguoiTrenThuyen = soNguoiTrenThuyen;
		this.soQuyTrenThuyen = soQuyTrenThuyen;
		this.soNguoiDoiTrai = soNguoiDoiTrai;
		this.soQuyDoiTrai = soQuyDoiTrai;
		this.soNguoiDoiPhai = soNguoiDoiPhai;
		this.soQuyDoiPhai = soQuyDoiPhai;
		this.parentStateGame = parentStateGame;
	}

	public State getParentStateGame() {
		return parentStateGame;
	}

	public int getSoNguoiDoiPhai() {
		return soNguoiDoiPhai;
	}

	public int getSoNguoiDoiTrai() {
		return soNguoiDoiTrai;
	}

	public int getSoNguoiTrenThuyen() {
		return soNguoiTrenThuyen;
	}

	public int getSoQuyDoiPhai() {
		return soQuyDoiPhai;
	}

	public int getSoQuyDoiTrai() {
		return soQuyDoiTrai;
	}

	public int getSoQuyTrenThuyen() {
		return soQuyTrenThuyen;
	}

	public int getStateGame() {
		return stateGame;
	}

	public int getViTriThuyen() {
		return viTriThuyen;
	}

	public void setParentStateGame(State parentStateGame) {
		this.parentStateGame = parentStateGame;
	}

	public void setSoNguoiDoiPhai(int soNguoiDoiPhai) {
		this.soNguoiDoiPhai = soNguoiDoiPhai;
	}

	public void setSoNguoiDoiTrai(int soNguoiDoiTrai) {
		this.soNguoiDoiTrai = soNguoiDoiTrai;
	}

	public void setSoNguoiTrenThuyen(int soNguoiTrenThuyen) {
		this.soNguoiTrenThuyen = soNguoiTrenThuyen;
	}

	public void setSoQuyDoiPhai(int soQuyDoiPhai) {
		this.soQuyDoiPhai = soQuyDoiPhai;
	}

	public void setSoQuyDoiTrai(int soQuyDoiTrai) {
		this.soQuyDoiTrai = soQuyDoiTrai;
	}

	public void setSoQuyTrenThuyen(int soQuyTrenThuyen) {
		this.soQuyTrenThuyen = soQuyTrenThuyen;
	}

	public void setStateGame(int stateGame) {
		this.stateGame = stateGame;
	}

	public void setViTriThuyen(int viTriThuyen) {
		this.viTriThuyen = viTriThuyen;
	}

	public String printState() {
		return "( Trạng thái trò chơi: " + getStateGame() + ", Vi tri thuyen: "
				+ getViTriThuyen() + ", So nguoi tren thuyen: "
				+ getSoNguoiTrenThuyen() + ", So quy tren thuyen: "
				+ getSoQuyTrenThuyen() + ", So nguoi doi trai: "
				+ getSoNguoiDoiTrai() + ", So quy doi trai: "
				+ getSoQuyDoiTrai() + ", So nguoi doi phai: "
				+ getSoNguoiDoiPhai() + ", So quy doi phai:"
				+ getSoQuyDoiPhai();
	}
}
