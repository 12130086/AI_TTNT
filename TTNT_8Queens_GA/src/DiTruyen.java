import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

/* Class chứa các hàm di truyền */
public class DiTruyen {

	/* Tập hợp các cá thể bàn cờ */
	private LinkedList<BanCo> tapHopCaThe;

	/* Số lượng quần thể ban đầu */
	private int quanThe;

	/* Kích thước bàn cờ */
	private int size = 8;

	/* Constructor DiTruyen với n = 8 và theHe là số lượng quần thể ban đầu */
	public DiTruyen(int theHe) {
		tapHopCaThe = new LinkedList<>();
		quanThe = theHe;
	}

	/*
	 * Constructor DiTruyen với n được nhận vào, theHe là số lượng quần thể ban
	 * đầu và size là kích thước bàn cờ
	 */
	public DiTruyen(int theHe, int size) {
		this.size = size;
		tapHopCaThe = new LinkedList<>();
		quanThe = theHe;
	}

	/* Khởi tạo quần thể */
	public void taoQuanThe() {
		for (int i = 0; i < quanThe; i++) {
			int a;
			BanCo b;
			do {
				b = new BanCo(size);
				b.taoHau();
				a = tapHopCaThe.indexOf(b);
			} while (a != -1);
			tapHopCaThe.add(b);
		}
	}

	/* In toàn bộ quần thể */
	public void print() {
		for (int i = 0; i < tapHopCaThe.size(); i++) {
			System.out.println("Cá thể " + (i) + ":");
			BanCo caThe = tapHopCaThe.get(i);
			caThe.print();
			System.out.println("Fitness của cá thể: " + caThe.fitness() + "\n");
		}
	}

	/* Xếp hạng quần thể theo chất lượng (fitness càng bé càng tốt) */
	public void rank() {
		Collections.sort(tapHopCaThe, new Comparator<BanCo>() {
			@Override
			public int compare(BanCo o1, BanCo o2) {
				int f2 = o2.fitness();
				int f1 = o1.fitness();
				if (f2 > f1) {
					return -1;
				} else if (f2 < f1) {
					return 1;
				}
				return 0;
			}
		});
	}

	/* Lai ghép quần thể */
	public void laiGhepBanCo() {
		rank();
		Iterator<BanCo> i = tapHopCaThe.iterator();
		LinkedList<BanCo> b = new LinkedList<>(tapHopCaThe);
		BanCo b1 = null;
		BanCo b2 = null;
		for (int a = 0; a < quanThe; a++) {
			b2 = i.next();
			BanCo n = laiGhep(b1, b2);
			b.set(a, n);
			b1 = b2;
		}
		tapHopCaThe = b;
		rank();
	}

	/*
	 * Lai ghép 2 bàn cờ bằng cách cắt ra một nửa rồi ghép ngẫu nhiên với một
	 * nửa khác, sau đó sửa vị trí con hậu bị sai. Trả về bàn cờ được lai ghép.
	 */
	public static BanCo laiGhep(BanCo b1, BanCo b2) {
		if (b1 == null)
			return b2;
		if (b2 == null)
			return b1;
		if (b1.size() != b2.size())
			return null;
		BanCo b = new BanCo(b1.size());
		if (Math.random() > 0.5) {
			BanCo s = b1;
			b1 = b2;
			b2 = s;
		}
		for (int i = b.size() / 2; i < b.size(); i++) {
			if (i < b.size() / 2) {
				b.setHau(i, b1.getHau(i));
			} else {
				b.setHau(i, b2.getHau(i));
			}
		}
		b.suaBanCo();
		return b;
	}

	/* Đột biến quần thể bàn cờ, nhận vào độ đột biến theo phần trăm */
	public void dotBienToanBoCaThe(int mut) {
		int a = (tapHopCaThe.size() - 1) * mut / 100;
		if (mut > 0 && a < 1)
			a = 1;
		for (int i = 0; i < a; i++) {
			int pos = (int) (Math.random() * (tapHopCaThe.size() - 1));
			tapHopCaThe.set(pos, dotBienMotCaThe(tapHopCaThe.get(pos), mut));
			tapHopCaThe.get(pos).suaBanCo();
		}
	}

	/* Đột biến một cá thể bàn cờ */
	public static BanCo dotBienMotCaThe(BanCo b, int mut) {
		BanCo n = new BanCo(b.size());
		int a = Math.round(n.size() ^ 2 * mut / 100);
		if (mut > 0 && a < 1)
			a = 1;
		for (int i = 0; i < a; i++) {
			int p1 = (int) (Math.random() * n.size());
			int p2 = (int) (Math.random() * n.size());
			byte by = b.getHau(p1);
			// n.setHau(p1, b.getHau(p2));
			n.setHau(p2, by);
		}
		return n;
	}

	/* Trả về bàn cờ tốt nhất */
	public BanCo bestBoard() {
		return tapHopCaThe.getFirst();
	}

	public static void main(String[] args) {
		DiTruyen g = new DiTruyen(4, 8);
		g.taoQuanThe();
		g.print();
	}

}
