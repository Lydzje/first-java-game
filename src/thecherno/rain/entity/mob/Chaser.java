package thecherno.rain.entity.mob;

import java.util.List;

import thecherno.rain.graphics.AnimatedSprite;
import thecherno.rain.graphics.Screen;
import thecherno.rain.graphics.SpriteSheet;

public class Chaser extends Mob {

	// ********************************VARIABLES********************************

	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 16, 16, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 16, 16, 3);
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 16, 16, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 16, 16, 3);

	private AnimatedSprite animSprite = down;

	private double xA = 0;
	private double yA = 0;

	// ****************************CONSTR._Y_GETTERS********************************

	public Chaser(int x, int y, double HEALTH) {
		super(HEALTH);
		this.x = x << 4;
		this.y = y << 4;
		speed = 0.7;
	}

	// ********************************MÉTODOS**********************************

	private void move() {
		xA = 0;
		yA = 0;
		List<Player> players = level.getPlayers(this, 100);

		if (players.size() > 0) {
			Player player = players.get(0); // CONSIDERAR SACAR DEL MOVE()
			if (x + speed < player.getX()) xA += speed;
			if (x - speed > player.getX()) xA -= speed;
			if (y + speed < player.getY()) yA += speed;
			if (y - speed > player.getY()) yA -= speed;
		}
		if (xA != 0 || yA != 0) {
			move(xA, yA, animSprite.getSprite());
			walking = true;
		} else walking = false;
	}

	public void update() {
		move();
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
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int) x, (int) y, this);
	}

}
