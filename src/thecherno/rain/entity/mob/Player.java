package thecherno.rain.entity.mob;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import thecherno.rain.Game;
import thecherno.rain.entity.projectile.Projectile;
import thecherno.rain.entity.projectile.WizardProjectile;
import thecherno.rain.events.Event;
import thecherno.rain.events.EventDispatcher;
import thecherno.rain.events.EventListener;
import thecherno.rain.events.type.MousePressedEvent;
import thecherno.rain.events.type.MouseReleasedEvent;
import thecherno.rain.graphics.AnimatedSprite;
import thecherno.rain.graphics.Screen;
import thecherno.rain.graphics.Sprite;
import thecherno.rain.graphics.SpriteSheet;
import thecherno.rain.graphics.ui.UIActionListener;
import thecherno.rain.graphics.ui.UIButton;
import thecherno.rain.graphics.ui.UIButtonListener;
import thecherno.rain.graphics.ui.UILabel;
import thecherno.rain.graphics.ui.UIManager;
import thecherno.rain.graphics.ui.UIPanel;
import thecherno.rain.graphics.ui.UIProgressBar;
import thecherno.rain.input.Keyboard;
import thecherno.rain.input.Mouse;
import thecherno.rain.utl.ImageUtils;
import thecherno.rain.utl.Vector2i;

public class Player extends Mob implements EventListener {

	// ********************************VARIABLES********************************

	private String name;
	private Keyboard input;
	private Sprite sprite;
	private boolean shooting = false;
	// private int anim = 0;
	// private boolean walking = false;

	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 16, 16, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 16, 16, 3);
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 16, 16, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 16, 16, 3);

	private AnimatedSprite animSprite = null;

	private UIManager ui;

	private UIPanel panel;

	private UIProgressBar uiHealthBar;

	private UIButton uiButton;

	private UIButton healButton;
	private BufferedImage healthButtonImage;

	// ****************************CONSTR._Y_GETTERS********************************
	public Player(String name, double HEALTH, Keyboard input) {
		super(HEALTH);
		this.name = name;
		this.input = input;
		speed = 1.5;
		animSprite = down;
		ui = Game.getUIManager();
	}

	public Player(int x, int y, String name, double HEALTH, Keyboard input) {
		super(HEALTH);
		this.x = x;
		this.y = y;
		this.name = name;
		this.input = input;
		speed = 1;
		animSprite = down;
		fireRate = WizardProjectile.FIRE_RATE;
		ui = Game.getUIManager(); // PASO_POR_REFERENCIA!!

		panel = new UIPanel(new Vector2i((300 - 64) * Game.getScale(), 0), new Vector2i(64 * Game.getScale(),
				300 / 16 * 9 * Game.getScale()), 0xff1f1f1f);
		ui.addPanel(panel);
		UILabel label = new UILabel(new Vector2i(10, 200), name, 24, 0xff9C6E0C, true);
		panel.addComponent(label);

		uiHealthBar = new UIProgressBar(new Vector2i(10, 250), new Vector2i(50 * Game.getScale(), 5), 0xffaa0000);
		panel.addComponent(uiHealthBar);
		UILabel hpLabel = new UILabel(uiHealthBar.position.vectorTo(0, -10), "HP: Press T to lose some health", 9,
				0xffFFFFFF, true);
		panel.addComponent(hpLabel);

		uiButton = new UIButton(new Vector2i(10, 270), new Vector2i(20, 20), 0xff94510A, new UIActionListener() {
			public void perform() {
				System.out.println("Button pressed!");
			}
		});
		panel.addComponent(uiButton);

		try {
			healthButtonImage = ImageIO.read(getClass().getResource("/textures/sheets/UI/buttons/healButton.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		healButton = new UIButton(new Vector2i(10, 300), healthButtonImage, new UIActionListener() {
			public void perform() {
				try {
					damageTaken = 0;
					uiHealthBar.setProgress(1);
					healthF = HEALTH;

				} catch (Exception e) {
					System.out.println("Hay algún problemmilla con uiHealthBar.setProgress(HEALTH) :/");
				}
			}
		});
		healButton.setButtonListener(new UIButtonListener() {
			public void entered(UIButton button) {
				button.setImage(ImageUtils.changeBrightness(healthButtonImage, 20));
			}

			public void exited(UIButton button) {
				button.setImage(healthButtonImage);
			}

			public void pressed(UIButton button) {
				button.setImage(ImageUtils.changeBrightness(healthButtonImage, -50));
			}

			public void realeased(UIButton button) {
				button.setImage(ImageUtils.changeBrightness(healthButtonImage, 20));
			}
		});
		panel.addComponent(healButton);

	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	// ********************************MÉTODOS**********************************

	public void onEvent(Event event) {
		EventDispatcher dispatcher = new EventDispatcher(event);
		dispatcher.dispatch(Event.Type.MOUSE_PRESSED, (Event e) -> (onMousePressed((MousePressedEvent) e)));
		dispatcher.dispatch(Event.Type.MOUSE_RELEASED, (Event e) -> (onMouseReleased((MouseReleasedEvent) e)));
	}

	public void update() {
		if (walking) animSprite.update();
		else animSprite.setFrame(0);

		double xA = 0, yA = 0;

		if (fireRate > 0) fireRate--;
		/*
		 * if (anim < 7500) anim++; else anim = 0;
		 */

		if (input.up && !input.down) { // SOLUCIONAR_CUANDO_INPUTS>2
			yA -= speed;
			animSprite = up;
		}
		if (input.down && !input.up) {
			yA += speed;
			animSprite = down;
		}
		if (input.left && !input.right) {
			xA -= speed;
			animSprite = left;
		}
		if (input.right && !input.left) {
			xA += speed;
			animSprite = right;
		}

		if (xA != 0 || yA != 0) {
			move(xA, yA, sprite);
			walking = true;
		} else walking = false;
		clear();
		updateShooting();

		try {
			if (input.suicideKey && healthF > 0 && !hurt) {
				hurt = true;
				damageTaken += 20;
			}
			if (lostHealth < healthDecreasingSpeed && hurt) {
				healthF -= damageTaken / 10;
				System.out.println(lostHealth + "  |  " + damageTaken + "   |   " + healthF + " de " + HEALTH);
				uiHealthBar.setProgress(healthF / HEALTH);
				lostHealth++;
			} else {
				damageTaken = 0;
				lostHealth = 0;
				hurt = false;
			}
		} catch (Exception e) {
			hurt = false;
			healthF = 0;
			System.out.println("Ha' muerto illo, lo siento");
		}

	}

	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved()) level.getProjectiles().remove(i);
		}
	}

	public boolean onMousePressed(MousePressedEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			shooting = true;

			return true;
		}

		return false;
	}

	public boolean onMouseReleased(MouseReleasedEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			shooting = false;
			return true;
		}
		return false;
	}

	private void updateShooting() {

		if (shooting && fireRate == 0) {
			double dx = Mouse.getX() - Game.getWindomWidth() / 2.0;
			double dy = Mouse.getY() - Game.getWindowHeight() / 2.0;
			double dir = Math.atan2(dy, dx);

			shoot(x, y, dir);
			fireRate = WizardProjectile.FIRE_RATE;
		}

		else return;
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int) x, (int) y, sprite);
	}

}
