package pingpong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.concurrent.locks.ReentrantLock;

public class Plocica extends Thread implements Drawable {
	private int x;
	private int y;
	private int dx;
	private TablaZaPingPong tabla;
	private boolean plocicaGore;
	private int rezultat;

	public Plocica(int x, int y, int dx, TablaZaPingPong tabla,
			boolean plocicaGore) {
		super();
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.tabla = tabla;
		this.plocicaGore = plocicaGore;
		this.rezultat = 0;
	}

	@Override
	public void drawSelf(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, 80, 20);

		// Marjana i Aleksandar su fiktivni likovi, a svaka slicnost sa realnim
		// licnostima je slucajna
		if (plocicaGore) {
			g.setColor(Color.red);
			g.drawString(" Marjana", x + 10, 32);
			g.drawString("Marjana: " + rezultat, 20, 15);
			g.setColor(Color.black);
		} else {
			g.setColor(Color.white);
			g.drawString(" Aleksandar", x, y + 12);
			g.setColor(Color.black);
			g.drawString("Aleksandar: " + rezultat, PPGUI.w - 110, 15);
		}
	}

	private void move() {
		if (!plocicaGore) {
			if (tabla.getOdLopticeXnajblizeGore() > PPGUI.w - 55)
				return;
			if (tabla.getOdLopticeXnajblizeGore() < 45)
				return;

			if (tabla.getOdLopticeXnajblizeGore() > x + 40) {
				dx = Math.abs(dx);
			} else
				dx = -Math.abs(dx);
			this.x += dx;
		} else {
			if (tabla.getOdLopticeXnajblizeDole() > PPGUI.w - 55)
				return;
			if (tabla.getOdLopticeXnajblizeDole() < 45)
				return;

			if (tabla.getOdLopticeXnajblizeDole() > x + 40) {
				dx = Math.abs(dx);
			} else
				dx = -Math.abs(dx);
			this.x += dx;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public void run() {
		while (true) {
			move();
			tabla.repaint();
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void uvecajRez() {
		this.rezultat += 1;

	}

}
