package thecherno.rain.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	// ********************************VARIABLES****************************
	private int iKeys = 5; // NÚMERO DE TECLAS A USAR
	private boolean[] keys = new boolean[iKeys];
	public boolean up, down, left, right, suicideKey;

	// ********************************MÉTODOS*******************************
	public Keyboard() {

	}

	public void update() {
		/*
		 * up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W]; down =
		 * keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S]; left =
		 * keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A]; right =
		 * keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		 */
		up = keys[0];
		down = keys[1];
		left = keys[2];
		right = keys[3];
		suicideKey = keys[4];
	}

	public void keyTyped(KeyEvent e) {
		// keys[e.getKeyCode()] = true;

	}

	public void keyPressed(KeyEvent e) {
		// keys[e.getKeyCode()] = true;
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_T) keys[4] = true;
	}

	public void keyReleased(KeyEvent e) {
		// keys[e.getKeyCode()] = false;
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_T) keys[4] = false;
	}

}
