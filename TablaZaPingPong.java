package pingpong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JPanel;

public class TablaZaPingPong extends JPanel {

	private List<Loptica> loptice = new ArrayList<Loptica>();
	public List<Plocica> plocice = new ArrayList<Plocica>();
	private List<Drawable> drawables = new ArrayList<Drawable>();

	private static final long serialVersionUID = 1L;

	Random rg = new Random();
	Random rg1 = new Random();
	Random rg2 = new Random();

	public TablaZaPingPong(int sirina, int visina) {
		super();
		setBackground(new Color(47, 163, 255));
		setPreferredSize(new Dimension(sirina, visina));
		// za vise loptica samo dodati ovde
		loptice.add(new Loptica(rg.nextInt(sirina - 60) + 30, rg
				.nextInt(sirina - 100) + 30, 10, rg.nextInt(1) + 2, rg
				.nextInt(1) + 2, this));
		loptice.add(new Loptica(rg1.nextInt(sirina - 60) + 30, rg1
				.nextInt(sirina - 100) + 30, 10, rg1.nextInt(1) + 2, rg1
				.nextInt(1) + 2, this));
		loptice.add(new Loptica(rg.nextInt(sirina - 60) + 30, rg
				.nextInt(sirina - 100) + 30, 10, rg.nextInt(1) + 2, rg
				.nextInt(1) + 2, this));
		plocice.add(new Plocica(rg1.nextInt(sirina - 80) + 20, 20, 3, this,
				true));
		plocice.add(new Plocica(rg2.nextInt(sirina - 80) + 20, visina - 60, 3,
				this, false));

		for (Loptica l : loptice)
			l.start();
		for (Plocica p : plocice)
			p.start();

		drawables.addAll(plocice);
		drawables.addAll(loptice);
	}

	public int getOdLopticeXnajblizeDole() {
		int min = 1000;
		int vrati = 300;
		for (Loptica l : loptice)
			if (l.getY() < min && !l.getLopticaOdbijena()) {
				min = l.getY();
				vrati = l.getX();
			}
		return vrati;
	}

	public int getOdLopticeXnajblizeGore() {
		int max = 1;
		int vrati = 300;
		for (Loptica l : loptice)
			if (l.getY() > max && !l.getLopticaOdbijena()) {
				max = l.getY();
				vrati = l.getX();
			}
		return vrati;
	}

	public boolean lopticaUdaraUPlocicu(Loptica l) {
		if (l.getY() < 100) {
			if (l.getX() >= plocice.get(0).getX()
					&& l.getX() <= plocice.get(0).getX() + 80)
				return true;
		}
		if (l.getY() > 100) {
			if (l.getX() >= plocice.get(1).getX()
					&& l.getX() <= plocice.get(1).getX() + 80)
				return true;
		}
		return false;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Drawable d : drawables)
			d.drawSelf(g);
	}

}
