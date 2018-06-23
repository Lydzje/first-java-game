package thecherno.rain.entity.mob;

import thecherno.rain.entity.Entity;
import thecherno.rain.entity.projectile.Projectile;
import thecherno.rain.entity.projectile.WizardProjectile;
import thecherno.rain.graphics.Screen;
import thecherno.rain.graphics.Sprite;

public abstract class Mob extends Entity {

	// ********************************VARIABLES********************************
	protected Sprite sprite;
	protected boolean walking = false;
	protected boolean solid = false;
	protected double speed;
	protected int fireRate = 0;
	protected final double HEALTH;
	protected double healthF;
	protected boolean hurt = false;
	protected int lostHealth = 0;
	protected double damageTaken = 0;
	protected int healthDecreasingSpeed = 10;

	protected enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}

	protected Direction dir;

	// ****************************CONSTR._Y_GETTERS********************************

	public Mob(double HEALTH) {
		this.HEALTH = HEALTH;
		healthF = HEALTH;
	}

	private boolean isSolid() {
		return solid;
	}

	public Sprite getSprite() {
		return sprite;
	}

	// ********************************MÉTODOS**********************************
	public void move(double xA, double yA, Sprite sprite) {
		if (xA != 0 && yA != 0) {
			move(0, yA, sprite);
			move(xA, 0, sprite);

			return;
		}

		if (xA > 0) dir = Direction.RIGHT;
		else if (xA < 0) dir = Direction.LEFT;
		else if (yA > 0) dir = Direction.DOWN;
		else if (yA < 0) dir = Direction.UP;

		while (xA != 0) {
			if (Math.abs(xA) > 1) {
				if (!collision(direction(xA), yA, sprite)) this.x += direction(xA);
				xA -= direction(xA);
			} else {
				if (!collision(direction(xA), yA, sprite)) this.x += xA;
				xA = 0;
			}

		}
		while (yA != 0) {
			if (Math.abs(yA) > 1) {
				if (!collision(xA, direction(yA), sprite)) this.y += direction(yA);
				yA -= direction(yA);
			} else {
				if (!collision(xA, direction(yA), sprite)) this.y += yA;
				yA = 0;
			}
		}
	}

	private int direction(double value) {
		if (value < 0) return -1;
		return 1;
	}

	public abstract void update();

	protected void shoot(double x, double y, double dir) {

		Projectile p = new WizardProjectile(x, y, dir);
		level.add(p);
	}

	public abstract void render(Screen screen);

	public boolean collision(double xA, double yA, Sprite sprite) {
		this.sprite = sprite;
		boolean solid = false; // CON_COLISION_EN_LAS_CABEZAS_:_(
		if (level.getTile((int) (x + xA) / 16, (int) (y + yA) / 16).solid()
				|| level.getTile((int) (x + xA + sprite.getISize()) / 16, (int) (y + yA) / 16).solid()
				|| level.getTile((int) (x + xA) / 16, (int) (y + yA + sprite.getISize()) / 16).solid()
				|| level.getTile((int) (x + xA + sprite.getISize()) / 16, (int) (y + yA + sprite.getISize()) / 16)
						.solid()) solid = true;

		return solid;
	}
}
