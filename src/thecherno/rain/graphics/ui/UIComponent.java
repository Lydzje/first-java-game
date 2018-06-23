package thecherno.rain.graphics.ui;

import java.awt.Color;
import java.awt.Graphics;

import thecherno.rain.utl.Vector2i;

public class UIComponent {

	// ********************************VARIABLES********************************

	public Vector2i position, offset; // NO LO MEJOR
	public Color color;

	protected boolean active = true;

	// ****************************CONSTR._Y_GETTERS********************************

	public UIComponent(Vector2i position) {
		this.position = position;
		offset = new Vector2i(); // OFFSET_PARA_POSICION_RELATIVA_A_PANEL
	}

	public void setOffset(Vector2i offset) {
		this.offset = offset;
	}

	public void setColor(int color) {
		this.color = new Color(color);
	}

	public Vector2i getAbsolutePosition() {
		return position.vectorTo(offset);
	}

	// ********************************MÉTODOS**********************************

	public void update() {
	}

	public void render(Graphics g) {
		// screen.drawRect(50, 50, 60, 30, 0xff000000, false);

	}

}
