package thecherno.rain.events.type;

import thecherno.rain.events.Event;
import thecherno.rain.events.Event.Type;

public class MouseButtonEvent extends Event {

	protected int button;
	protected int x, y;

	protected MouseButtonEvent(int button, int x, int y, Type type) {
		super(type);
		this.button = button;
		this.x = x;
		this.y = y;
	}

	public int getButton() {
		return button;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
