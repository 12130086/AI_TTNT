import java.util.ArrayList;

public class Solution {
	ArrayList<State> listState, states;
	State stateBegin, stateTemp;

	public Solution(State stateBegin) {
		this.stateBegin = stateBegin;

		listState = new ArrayList<State>();
		states = new ArrayList<State>();

		process(stateBegin);
	}

	public ArrayList<State> process(State currentState) {
		listState.add(currentState);
		states.add(currentState);
		while (!listState.isEmpty()) {
			stateTemp = listState.remove(0);
			if (testStateFinal(stateTemp)) {
				states.add(new State(stateTemp.getStateGame() + 1, stateTemp
						.getViTriThuyen() * (-1), 0, 0,
						stateTemp.getSoNguoiDoiTrai()
								+ stateTemp.getSoNguoiTrenThuyen(), stateTemp
								.getSoQuyDoiTrai()
								+ stateTemp.getSoQuyTrenThuyen(), 0, 0,
						stateTemp));
				System.out
						.println("Ket qua trang thai dau den trang thai dich: ");
				printStates(printStateCycle());
				return states;
			}
			createState(stateTemp);
		}
		return null;
	}

	private void createState(State currentState) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if ((i + j) > 0 && (i + j) < 3) {
					if (currentState.getViTriThuyen() == -1) {
						stateTemp = new State(
								currentState.getStateGame() + 1,
								1,
								i,
								j,
								currentState.getSoNguoiDoiTrai()
										+ currentState.getSoNguoiTrenThuyen()
										- i,
								currentState.getSoQuyDoiTrai()
										+ currentState.getSoQuyTrenThuyen() - j,
								currentState.getSoNguoiDoiPhai(), currentState
										.getSoQuyDoiPhai(), currentState);
					} else { // thuyen ben phai
						stateTemp = new State(
								currentState.getStateGame() + 1,
								-1,
								i,
								j,
								currentState.getSoNguoiDoiTrai(),
								currentState.getSoQuyDoiTrai(),
								currentState.getSoNguoiDoiPhai()
										+ currentState.getSoNguoiTrenThuyen()
										- i,
								currentState.getSoQuyDoiPhai()
										+ currentState.getSoQuyTrenThuyen() - j,
								currentState);
					}

					if (testState(stateTemp)) {
						if (!testEqualState(stateTemp)) {
							states.add(stateTemp);
							listState.add(stateTemp);
						}
					}
				}
			}
		}
	}

	private boolean testState(State currentState) {
		if (currentState.getSoNguoiDoiTrai() > 0
				&& currentState.getSoNguoiDoiTrai() < currentState
						.getSoQuyDoiTrai()) {
			return false;
		}
		if (currentState.getSoNguoiDoiPhai() > 0
				&& currentState.getSoNguoiDoiPhai() < currentState
						.getSoQuyDoiPhai()) {
			return false;
		}
		if ((currentState.getSoNguoiDoiTrai() >= 0)
				&& (currentState.getSoNguoiDoiTrai() <= stateBegin
						.getSoNguoiDoiPhai())
				&& (currentState.getSoQuyDoiTrai() >= 0)
				&& (currentState.getSoQuyDoiTrai() <= stateBegin
						.getSoQuyDoiPhai())
				&& (currentState.getSoNguoiDoiPhai() >= 0)
				&& (currentState.getSoNguoiDoiPhai() <= stateBegin
						.getSoNguoiDoiPhai())
				&& (currentState.getSoQuyDoiPhai() >= 0)
				&& (currentState.getSoQuyDoiPhai() <= stateBegin
						.getSoQuyDoiPhai())
				&& ((currentState.getSoNguoiTrenThuyen() + currentState
						.getSoQuyTrenThuyen()) > 0)
				&& ((currentState.getSoNguoiTrenThuyen() + currentState
						.getSoQuyTrenThuyen()) < 3)
				&& ((currentState.getSoNguoiDoiTrai()
						+ currentState.getSoNguoiDoiPhai() + currentState
							.getSoNguoiTrenThuyen()) == stateBegin
						.getSoNguoiDoiPhai())
				&& ((currentState.getSoQuyDoiTrai()
						+ currentState.getSoQuyDoiPhai() + currentState
							.getSoQuyTrenThuyen()) == stateBegin
						.getSoQuyDoiPhai())) {
			return true;
		}
		return false;
	}

	// goal Final
	private boolean testStateFinal(State currentState) {
		if ((currentState.getSoNguoiDoiPhai() == 0)
				&& currentState.getSoQuyDoiPhai() == 0) {
			return true;
		}
		return false;
	}

	private boolean testEqualState(State currentState) {
		for (State tempState : states) {
			if (tempState.getViTriThuyen() == currentState.getViTriThuyen()
					&& tempState.getSoNguoiTrenThuyen() == currentState
							.getSoNguoiTrenThuyen()
					&& tempState.getSoQuyTrenThuyen() == currentState
							.getSoQuyTrenThuyen()
					&& tempState.getSoNguoiDoiTrai() == currentState
							.getSoNguoiDoiTrai()
					&& tempState.getSoQuyDoiTrai() == currentState
							.getSoQuyDoiTrai()
					&& tempState.getSoNguoiDoiPhai() == currentState
							.getSoNguoiDoiPhai()
					&& tempState.getSoQuyDoiPhai() == currentState
							.getSoQuyDoiPhai()) {
				return true;
			}
		}
		return false;
	}

	private String printStates(ArrayList<State> state) {
		for (State tempState : state) {
			System.out.println("\nTrạng thái trò chơi: "
					+ tempState.getStateGame() + "\r\n\t Vị trí thuyền: "
					+ tempState.getViTriThuyen()
					+ "\r\n\t Số người trên thuyền: "
					+ tempState.getSoNguoiTrenThuyen()
					+ "\r\n\t Số quỷ trên thuyền: "
					+ tempState.getSoQuyTrenThuyen()
					+ "\r\n\t Số người đợi trái: "
					+ tempState.getSoNguoiDoiTrai()
					+ "\r\n\t Số quỷ đợi trái: " + tempState.getSoQuyDoiTrai()
					+ "\r\n\t Số người đợi phải: "
					+ tempState.getSoNguoiDoiPhai() + "\r\n\t Số quỷ đợt phải: "
					+ tempState.getSoQuyDoiPhai());
		}
		return "";
	}

	private ArrayList<State> printStateCycle() {
		ArrayList<State> tempStates = new ArrayList<State>();
		stateTemp = states.get(states.size() - 1);

		while (stateTemp != null) {
			tempStates.add(0, stateTemp);
			stateTemp = stateTemp.getParentStateGame();
		}

		return tempStates;
	}
}
