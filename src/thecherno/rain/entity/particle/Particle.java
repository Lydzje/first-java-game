package thecherno.rain.entity.particle;

import thecherno.rain.entity.Entity;
import thecherno.rain.graphics.Screen;
import thecherno.rain.graphics.Sprite;

public class Particle extends Entity {
	// ********************************VARIABLES********************************
	private Sprite sprite;

	private int life;
	private int time = 0;

	protected double dX, dY, dZ;
	protected double xA, yA, zA; // doubleX, doubleY, xAmount, yAmount

	// ****************************CONSTR._Y_GETTERS********************************
	public Particle(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.dX = x;
		this.dY = y;
		this.life = life + random.nextInt(20) - 10;
		sprite = Sprite.particle_normal;

		this.xA = random.nextGaussian();
		this.yA = random.nextGaussian();
		this.dZ = random.nextFloat() + 3.0;
	}

	// ********************************MÉTODOS**********************************
	public void update() {
		time++;
		if (time >= 7400) time = 0;
		if (time > life) remove();
		zA -= 0.1;
		move();
	}

	public void move() {
		if (!level.tileCollision(xA, yA, dX, dY, sprite.getSIZE() - 1)) {
			if (dZ < 0) {
				dZ = 0;
				zA *= -0.55;
				xA *= 0.5;
				yA *= 0.5;
			}
			this.dX += xA * 0.5;
			this.dY += yA * 0.5;
			this.dZ += zA;
		} 
	}

	public void render(Screen screen) {
		screen.renderSprite((int) dX, (int) dY - (int) dZ, sprite, true);
	}
}
