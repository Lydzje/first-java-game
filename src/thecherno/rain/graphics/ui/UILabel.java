package thecherno.rain.graphics.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import thecherno.rain.utl.Vector2i;

public class UILabel extends UIComponent {

	// ********************************VARIABLES********************************

	public String text;
	private Font font;
	private int fontSize;
	private boolean shadow;

	// ****************************CONSTR._Y_GETTERS********************************

	public UILabel(Vector2i position, String text, int fontSize, int color, boolean shadow) {
		super(position.add(0, fontSize));
		this.text = text;
		this.fontSize = fontSize;
		font = new Font("Helvetica", font.PLAIN, fontSize);
		this.color = new Color(color);
		this.shadow = shadow;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public int getFontSize() {
		return fontSize;
	}

	public Font setFontSize(int fontSize) {
		return new Font("Helvetica", font.PLAIN, fontSize);
	}

	// ********************************MÉTODOS**********************************

	public void render(Graphics g) {
		if (shadow) {
			g.setColor(Color.BLACK);
			g.setFont(font);
			g.drawString(text, position.getX() + fontSize / 22 + offset.getX(), position.getY() + fontSize / 22
					+ offset.getY());
		}
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, position.getX() + offset.getX(), position.getY() + offset.getY());
	}
}
