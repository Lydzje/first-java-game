package thecherno.rain.entity.mob;

import java.util.List;

import thecherno.rain.entity.Entity;
import thecherno.rain.entity.projectile.WizardProjectile;
import thecherno.rain.graphics.AnimatedSprite;
import thecherno.rain.graphics.Screen;
import thecherno.rain.graphics.Sprite;
import thecherno.rain.graphics.SpriteSheet;
import thecherno.rain.utl.Debug;
import thecherno.rain.utl.Vector2i;

public class Shooter extends Mob {

	// ********************************VARIABLES********************************
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 16, 16, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 16, 16, 3);
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 16, 16, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 16, 16, 3);

	private AnimatedSprite animSprite = down;

	private int time = 0;
	private double xA;
	private double yA;

	private Entity target = null;

	// ****************************CONSTR._Y_GETTERS********************************
	public Shooter(int x, int y, double HEALTH) {
		super(HEALTH);
		this.x = x << 4;
		this.y = y << 4;
		speed = 0.7;
		animSprite = down;
		fireRate = WizardProjectile.FIRE_RATE;
	}

	// ********************************MÉTODOS**********************************

	public void update() {
		randomMovement();
		setAnimation();
		updateShooting(randomTarget());
	}

	private void randomMovement() {
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
		if (xA != 0 || yA != 0) {
			move(xA, yA, animSprite.getSprite());
			walking = true;
		} else walking = false;
	}

	private Entity randomTarget() {
		if (time % (random.nextInt(90) + 30) == 0) {
			List<Entity> entities = level.getEntities(this, 200); // CREAR_MOBLIST_Y_SUSTITUIR_POR_ENTITIES
			if (level.getPlayers(this, 200).size() > 0) // CREAR_TARGETLIST_Y_SU_GETTER_CON_RADIUS
				entities.add(level.getPlayers(this, 200).get(random.nextInt(level.getPlayers(this, 200).size())));

			if (entities.size() > 0) {
				int index = random.nextInt(entities.size());
				target = entities.get(index);
			}
		}
		return target;
	}

	private Entity closestTarget() {
		List<Entity> entities = level.getEntities(this, 50); // CREAR_MOBLIST_Y_SUSTITUIR_POR_ENTITIES
		entities.add(level.getClientPlayer());

		double minDistance = 0;

		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			double distance = Vector2i.getDistance(new Vector2i((int) x, (int) y),
					new Vector2i((int) e.getX(), (int) e.getY()));
			if (i == 0 || distance < minDistance) {
				minDistance = distance;
				target = e;
			}
		}
		return target;
	}

	private void updateShooting(Entity e) {
		if (e != null) {
			if (fireRate > 0) fireRate--;
			if (fireRate == 0) {
				double dx = e.getX() - x;
				double dy = e.getY() - y;
				double dir = Math.atan2(dy, dx);

				shoot(x, y, dir);
				fireRate = WizardProjectile.FIRE_RATE;
			}
		}
	}

	private void setAnimation() {
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
		// screen.renderSprite(80, 80, new Sprite(80, 80, 0xff0000), false);
		sprite = animSprite.getSprite();
		// Debug.drawRect(screen, 40, 40, 32, 32, false);
		screen.renderMob((int) x, (int) y, this);
	}
}
