package pingpong;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Loptica extends Thread implements Drawable {
	private int x;
	private int y;
	private int precnik;
	private int dx;
	private int dy;
	private TablaZaPingPong tabla;
	private Random lopticaRandom;
	private boolean loptica_odbijena;
	private int boja; // 0 = crna, 1=crvena

	public Loptica(int x, int y, int precnik, int dx, int dy,
			TablaZaPingPong tabla) {
		super();
		loptica_odbijena = false;
		this.boja = 0;
		this.x = x;
		this.y = y;
		this.precnik = precnik;
		this.dx = dx;
		this.dy = dy;
		this.tabla = tabla;
		lopticaRandom = new Random();
	}

	@Override
	public void drawSelf(Graphics g) {
		if (this.boja == 0) {
			g.setColor(Color.black);
		} else
			g.setColor(Color.red);
		g.fillOval(x, y, precnik, precnik);
		g.setColor(Color.black);
	}

	private void move() {
		if (this.y < 270 && this.y > 250)
			this.loptica_odbijena = false;
		if (this.y > 45 && this.y < 510)
			this.boja = 0;
		if (x < 1 || x > PPGUI.w - 15)
			dx = -dx;
		if (y < 40 || y > PPGUI.h - 60 - 10) {
			if (tabla.plocice.get(0).getY() + 20 >= this.y)
				if (tabla.plocice.get(0).getX() <= this.x
						&& tabla.plocice.get(0).getX() + 80 >= this.x) {
					this.loptica_odbijena = true;
					this.dy = -this.dy;
				} else {
					tabla.plocice.get(1).uvecajRez();
					this.boja = 1;
					this.dx = lopticaRandom.nextInt(2) + 2;
					this.dy = -this.dy;
				}
		}
		if (tabla.plocice.get(1).getY() <= this.y + 10) // ovo +10 je iz
														// "Swingovskih" razloga
			if (tabla.plocice.get(1).getX() <= this.x
					&& tabla.plocice.get(1).getX() + 80 >= this.x) {
				this.dy = -this.dy;
			} else {
				tabla.plocice.get(0).uvecajRez();
				this.boja = 1;
				this.dx = lopticaRandom.nextInt(20) % 2 + 2;
				this.dy = -this.dy;
			}
		this.x += dx;
		this.y += dy;
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean getLopticaOdbijena() {
		return this.loptica_odbijena;
	}
}
