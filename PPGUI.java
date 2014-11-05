package pingpong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class PPGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	static int w = 500;
	static int h = 600;

	public PPGUI() {

		setPreferredSize(new Dimension(w, h));
		addComponents();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		center();
		pack();
	}

	private void addComponents() {
		TablaZaPingPong tabla = new TablaZaPingPong(w, h);
		getContentPane().add(tabla);
	}

	private void center() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) (d.getWidth() - w) / 2;
		int y = (int) (d.getHeight() - h) / 2;
		setLocation(new Point(x, y));
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				@SuppressWarnings("unused")
				PPGUI gui = new PPGUI();
			}
		});
	}
}
