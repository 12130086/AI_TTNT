public class BanCo {

	/* Kích thước bàn cờ n*n */
	private int n;

	/*
	 * Con Hậu với i là tọa độ Y của con hậu trên bàn cờ, quanHau[i] là tọa độ X
	 * của con hậu trên bàn cờ
	 */
	private byte quanHau[];

	public BanCo(int n) {
		this.n = n;
		quanHau = new byte[n];
	}

	/* Trả về kích thước của bàn cờ */
	public int size() {
		return n;
	}

	/*
	 * Tạo bàn cờ với những con Hậu ngẫu nhiên. Không con Hậu nào có thể ở cùng
	 * hàng hoặc cột với con Hậu khác.
	 */
	public void taoHau() {
		for (int i = 0; i < n; i++) {
			byte r = (byte) (Math.random() * n);
			for (int j = 0; j < i; j++) {
				if (quanHau[j] == r) {
					j = -1;
					r = (byte) (Math.random() * n);
				}
			}
			quanHau[i] = r;
		}
	}

	/* In bàn cờ ra màn hình Console */
	public void print() {
		for (int i = 0; i < n; i++) {
			int a = 0;
			for (; a < quanHau[i]; a++) {
				System.out.print(". ");
			}
			System.out.print("H ");
			a++;
			for (; a < n; a++) {
				System.out.print(". ");
			}
			System.out.println();
		}
	}

	/*
	 * Hàm thể hiện đánh giá chất lượng của một cá thể bàn cờ, với giá trị 0 là
	 * kết quả cuối cùng.
	 */
	public int fitness() { // |q[i]-q[j]|=|i-j|
		int f = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (Math.abs(quanHau[i] - quanHau[j]) == Math.abs(i - j))
					f++;
			}
		}
		return f / 2;
	}

	/* Trả về true nếu hai bàn cờ bằng nhau */
	@Override
	public boolean equals(Object obj) {
		try {
			BanCo b = (BanCo) obj;
			return b.quanHau.equals(quanHau);
		} catch (Exception e) {
			return super.equals(obj);
		}
	}

	/*
	 * Lưu con Hậu với i là tọa độ Y của con hậu trên bàn cờ, quanHau[i]=val là
	 * tọa độ X của con hậu trên bàn cờ
	 */
	public void setHau(int i, byte val) {
		quanHau[i] = val;
	}

	/*
	 * Trả về con Hậu với i là tọa độ Y của con hậu trên bàn cờ, quanHau[i] là
	 * tọa độ X của con hậu trên bàn cờ
	 */
	public byte getHau(int i) {
		return quanHau[i];
	}

	/* Sửa bàn cờ sao cho chỉ có một quân Hậu mỗi hàng và cột */
	public void suaBanCo() {
		for (int a = 0; a < size(); a++) {
			for (int ab = 0; ab < size(); ab++) {
				if (a == ab) {
					ab++;
					if (ab >= size()) {
						break;
					}
				}
				if (getHau(a) == getHau(ab)) {
					int i;
					if (Math.random() > 0.5)
						i = a;
					else
						i = ab;
					byte r = (byte) (Math.random() * size());
					for (int j = 0; j < size(); j++) {
						if (getHau(j) == r) {
							j = -1;
							r = (byte) (Math.random() * size());
						}
					}
					setHau(i, r);
				}
			}
		}
	}
}