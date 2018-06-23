package thecherno.rain.entity.projectile;

import thecherno.rain.entity.spawner.ParticleSpawner;
import thecherno.rain.entity.spawner.Spawner;
import thecherno.rain.graphics.Screen;
import thecherno.rain.graphics.Sprite;

public class WizardProjectile extends Projectile {
	// ********************************VARIABLES********************************
	public static final int FIRE_RATE = 20;

	// ****************************CONSTR._Y_GETTERS********************************
	public WizardProjectile(double x, double y, double dir) {
		super(x, y, dir);
		range = 200;// random.nextInt(100); PARA HACERLO RANDOM -___-
		speed = 4;
		damage = 20;

		sprite = Sprite.rotate(Sprite.projectile_arrow, angle + Math.PI / 2);

		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	// ********************************MÉTODOS**********************************
	public void update() {
		move();
		if (level.tileCollision(x, y, nx, ny, 6)) { // 6->WIZARD 11->ARROW
			level.add(new ParticleSpawner((int) x, (int) y, 64, 25, level));
			remove();
		}
		;
	}

	protected void move() {
		if (!level.tileCollision(x, y, nx, ny, 6)) {
			x += nx;
			y += ny;
		}
		if (distance() > range) remove();
	}

	private double distance() {
		double dist = 0;
		dist = Math.sqrt((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y));

		return dist;
	}

	public void render(Screen screen) {
		screen.renderProjectile((int) x, (int) y, this);
	}
}
