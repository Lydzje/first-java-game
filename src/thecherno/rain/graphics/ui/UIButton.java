package thecherno.rain.graphics.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import thecherno.rain.input.Mouse;
import thecherno.rain.utl.Vector2i;

public class UIButton extends UIComponent {

	// ********************************VARIABLES********************************

	private Vector2i size;

	public Rectangle rect;
	public UILabel label;

	private UIButtonListener buttonListener;
	private UIActionListener actionListener;

	private Image image;

	private boolean inside = false;
	private boolean pressed = false;
	private boolean initiated;
	private boolean leftMouseButtonDown;
	private boolean ignorePressed = false;

	// ****************************CONSTR._Y_GETTERS********************************

	public UIButton(Vector2i position, Vector2i size, int color, UIActionListener actionListener) {
		super(position);
		this.size = size;
		this.color = new Color(color);
		this.actionListener = actionListener; // /!\
		buttonListener = new UIButtonListener();
		initiated = false;
	}

	public UIButton(Vector2i position, BufferedImage image, UIActionListener actionListener) {
		super(position);
		this.size = new Vector2i(image.getWidth(), image.getHeight());
		this.image = image;
		this.actionListener = actionListener;
		buttonListener = new UIButtonListener();
		initiated = false;
	}

	public void setText(String text) {
		if (text == "") label.active = false;
		else label.text = text;
	}

	public void setButtonListener(UIButtonListener buttonListener) {
		this.buttonListener = buttonListener;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	// ********************************MÉTODOS**********************************

	public void init() {
		rect = new Rectangle(getAbsolutePosition().getX(), getAbsolutePosition().getY(), this.size.getX(),
				this.size.getY());
		label = new UILabel(position.vectorTo(offset.getX(), offset.getY()), " V", 16, 0xffffffff, false);
		initiated = true;
	}

	public void update() {
		if (!initiated) init();
		leftMouseButtonDown = Mouse.getButton() == MouseEvent.BUTTON1;
		if (rect.contains(new Point(Mouse.getX(), Mouse.getY()))) {
			if (!inside) {
				if (leftMouseButtonDown) ignorePressed = true;
				else ignorePressed = false;

				buttonListener.entered(this);
			}
			inside = true;

			if (!pressed && !ignorePressed && leftMouseButtonDown) {
				buttonListener.pressed(this);
				pressed = true;

			} else if (Mouse.getButton() == MouseEvent.NOBUTTON) {
				if (pressed) {
					buttonListener.realeased(this);
					actionListener.perform();
					pressed = false;
				}
				ignorePressed = false;
			}
		} else {
			if (inside) {
				buttonListener.exited(this);
				pressed = false;
			}
			inside = false;
		}
	}

	public void render(Graphics g) {
		int x = position.getX() + offset.getX();
		int y = position.getY() + offset.getY();

		if (image != null) {
			g.drawImage(image, x, y, null);
		} else {
			g.setColor(color);
			g.fillRect(x, y, size.getX(), size.getY());

			// MEJOR_NO_INSTANCIAR_LABEL_AQUÍ_Y_DISEÑAR_MEJOR_LA_/UI_PARA_panel.add(label)
			if (label != null) label.render(g);
		}
	}
	// ******************************INNER_CLASSES**********************************

}
