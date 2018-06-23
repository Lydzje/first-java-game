package thecherno.rain.graphics.ui;

import java.awt.Color;
import java.awt.Graphics;

import thecherno.rain.utl.Vector2i;

public class UIProgressBar extends UIComponent {

	private Vector2i size;
	private Color foreGroundColor;
	private double progress; // 0-100%

	public UIProgressBar(Vector2i position, Vector2i size, int color) {
		super(position);
		this.size = size;
		this.color = new Color(color);
		foreGroundColor = new Color(0xffafafaf);
		progress = 1;
	}

	public void setProgress(double progress) throws Exception {
		if (progress < 0 || progress > 1.0) throw new Exception("Bar progress out of the range (0-1.00)");
		this.progress = progress;
	}

	public double getProgress() {
		return progress;
	}

	int time = 1;
	boolean hurt = false;
	int lostHealth = 0;

	public void update() {
	}

	public void render(Graphics g) {
		g.setColor(foreGroundColor);
		g.fillRect(position.getX() + offset.getX(), position.getY() + offset.getY(), size.getX(), size.getY());

		g.setColor(color);
		g.fillRect(position.getX() + offset.getX(), position.getY() + offset.getY(), (int) (size.getX() * progress),
				size.getY());
	}

}
