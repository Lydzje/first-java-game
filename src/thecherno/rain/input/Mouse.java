package thecherno.rain.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import thecherno.rain.events.EventListener;
import thecherno.rain.events.type.MouseMovedEvent;
import thecherno.rain.events.type.MousePressedEvent;
import thecherno.rain.events.type.MouseReleasedEvent;

public class Mouse implements MouseListener, MouseMotionListener {

	// ********************************VARIABLES********************************
	private static int xMouse = -1;
	private static int yMouse = -1;
	private static int bMouse = -1;

	private EventListener eventListener;

	// ****************************CONSTR._Y_GETTERS********************************

	public Mouse(EventListener listener) {
		eventListener = listener;
	}

	public static int getX() {
		return xMouse;
	}

	public static int getY() {
		return yMouse;
	}

	public static int getButton() {
		return bMouse;
	}

	// ********************************MÉTODOS**********************************
	// MOUSE LISTENER

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		bMouse = e.getButton();

		MousePressedEvent event = new MousePressedEvent(e.getButton(), e.getX(), e.getY());
		eventListener.onEvent(event);
	}

	public void mouseReleased(MouseEvent e) {
		bMouse = MouseEvent.NOBUTTON;

		MouseReleasedEvent event = new MouseReleasedEvent(e.getButton(), e.getX(), e.getY());
		eventListener.onEvent(event);
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	// MOUSE MOTION LISTENER
	public void mouseDragged(MouseEvent e) {
		xMouse = e.getX();
		yMouse = e.getY();

		MouseMovedEvent event = new MouseMovedEvent(e.getX(), e.getY(), true);
		eventListener.onEvent(event);
	}

	public void mouseMoved(MouseEvent e) {
		xMouse = e.getX();
		yMouse = e.getY();

		MouseMovedEvent event = new MouseMovedEvent(e.getX(), e.getY(), false);
		eventListener.onEvent(event);
	}
}
