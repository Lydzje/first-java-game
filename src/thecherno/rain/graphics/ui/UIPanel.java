package thecherno.rain.graphics.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import thecherno.rain.graphics.Sprite;
import thecherno.rain.utl.Vector2i;

public class UIPanel {

	// ********************************VARIABLES********************************

	private List<UIComponent> components = new ArrayList<UIComponent>();
	private Vector2i position, size;

	// private Sprite sprite;

	private Color color;

	// ****************************CONSTR._Y_GETTERS********************************

	public UIPanel(Vector2i position, Vector2i size, int color) {
		this.position = position;
		this.size = size;
		// sprite = new Sprite(64, 300 / 16 * 9, 0xffcacaca);
		this.color = new Color(color, false);
	}

	public Vector2i getPosition() {
		return position;
	}

	public Vector2i getSize() {
		return size;
	}

	// ********************************MÉTODOS**********************************

	public void update() {
		for (UIComponent component : components) {
			component.setOffset(position);
			component.update();
		}

	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(position.getX(), position.getY(), size.getX(), size.getY());
		for (UIComponent component : components) {
			component.render(g);
		}

	}

	public void addComponent(UIComponent component) {
		components.add(component);
	}
}
