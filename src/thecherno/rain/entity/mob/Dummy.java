package thecherno.rain.entity.mob;

import thecherno.rain.graphics.AnimatedSprite;
import thecherno.rain.graphics.Screen;
import thecherno.rain.graphics.SpriteSheet;

public class Dummy extends Mob {

	// ********************************VARIABLES********************************
	// private int x, y;

	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 16, 16, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 16, 16, 3);
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 16, 16, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 16, 16, 3);

	private AnimatedSprite animSprite = down;

	private int time = 0;
	private double xA;
	private double yA;

	// ****************************CONSTR._Y_GETTERS********************************

	public Dummy(int x, int y, double HEALTH) {
		super(HEALTH);
		this.x = x << 4;
		this.y = y << 4;
		speed = 0.7;
		animSprite = down;
	}

	// ********************************MÉTODOS**********************************

	public void update() {
		time++;

		if (time % (random.nextInt(50) + 30) == 0) {
			if (random.nextInt(3) == 0) {
				xA = 0;
				yA = 0;
			} else {
				xA = (random.nextInt(3) - 1) * speed; // RANDOM ENTRE -1 A 1
				yA = (random.nextInt(3) - 1) * speed;
			}
		}

		if (collision(xA, yA, sprite)) {
			if (random.nextInt(2) == 0) {
				xA = 0;
				yA = 0;
			} else {
				xA *= -1;
				yA *= -1;
			}
		}

		if (walking) animSprite.update();
		else animSprite.setFrame(0);

		if (yA < 0) {
			animSprite = up;
			dir = Direction.UP;
		}
		if (yA > 0) {
			animSprite = down;
			dir = Direction.DOWN;
		}
		if (xA < 0) {
			animSprite = left;
			dir = Direction.LEFT;
		}
		if (xA > 0) {
			animSprite = right;
			dir = Direction.RIGHT;
		}

		if (xA != 0 || yA != 0) {
			move(xA, yA, animSprite.getSprite());
			walking = true;
		} else walking = false;
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int) x, (int) y, sprite);
	}
}
