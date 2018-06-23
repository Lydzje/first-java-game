package thecherno.rain.entity.mob;

import java.util.List;

import thecherno.rain.graphics.AnimatedSprite;
import thecherno.rain.graphics.Screen;
import thecherno.rain.graphics.SpriteSheet;
import thecherno.rain.level.Node;
import thecherno.rain.utl.Vector2i;

public class Star extends Mob {
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 16, 16, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 16, 16, 3);
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 16, 16, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 16, 16, 3);

	private AnimatedSprite animSprite = down;

	private double xA = 0;
	private double yA = 0;
	private List<Node> path = null;

	private int time = 0;

	// ****************************CONSTR._Y_GETTERS********************************

	public Star(int x, int y, double HEALTH) {
		super(HEALTH);
		this.x = x << 4;
		this.y = y << 4;
		speed = 1; // NO_FUNCIONA_CON_DOUBLES :_(
	}

	// ********************************MÉTODOS**********************************

	private void move() { // SE MUEVE RARO!!!!!!!!!!!!!
		xA = 0;
		yA = 0;

		int px = level.getPlayerAt(0).getX();
		int py = level.getPlayerAt(0).getY();
		Vector2i start = new Vector2i(getX() >> 4, getY() >> 4);
		Vector2i destination = new Vector2i(px >> 4, py >> 4);
		if (time % 2 == 0) path = level.findPath(start, destination);
		if (path != null) {
			if (path.size() > 0) {
				Vector2i vec = path.get(path.size() - 1).tile;
				if (x < vec.getX() << 4) xA += speed; // SI_FALLA_PRUEBA_xA++
				if (x > vec.getX() << 4) xA -= speed;
				if (y < vec.getY() << 4) yA += speed;
				if (y > vec.getY() << 4) yA -= speed;
			}
		}

		if (xA != 0 || yA != 0) {
			move(xA, yA, animSprite.getSprite());
			walking = true;
		} else walking = false;
	}

	public void update() {
		time++;
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
