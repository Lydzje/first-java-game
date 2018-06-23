package thecherno.rain.entity;

import java.util.Random;

import thecherno.rain.graphics.Screen;
import thecherno.rain.graphics.Sprite;
import thecherno.rain.level.Level;

public class Entity {

	// ********************************VARIABLES********************************
	protected double x, y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();

	// ****************************CONSTR._Y_GETTERS********************************

	public boolean isRemoved() {
		return removed;
	}

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	// ********************************MÉTODOS**********************************

	public void update() {
	}

	public void render(Screen screen) {

	}

	public void remove() {
		removed = true;
	}

	public void init(Level level) {
		this.level = level;
	}
}
