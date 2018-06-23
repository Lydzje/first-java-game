package thecherno.rain.entity.projectile;

import java.util.Random;

import thecherno.rain.entity.Entity;
import thecherno.rain.graphics.Sprite;

public class Projectile extends Entity {
	// ********************************VARIABLES********************************
	protected final double xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double x, y;
	protected double nx, ny;
	protected double distance;
	protected double speed, range, damage;

	protected final Random random = new Random();

	// ****************************CONSTR._Y_GETTERS********************************
	public Projectile(double x, double y, double dir) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}

	public Sprite getSprite() {
		return sprite;
	}

	// ********************************MÉTODOS**********************************

	protected void move() {

	}
}
