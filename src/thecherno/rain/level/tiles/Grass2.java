package thecherno.rain.level.tiles;

import thecherno.rain.graphics.Screen;
import thecherno.rain.graphics.Sprite;

public class Grass2 extends Tile {
	// ********************************VARIABLES********************************

	// ****************************CONSTR._Y_GETTERS********************************
	public Grass2(Sprite sprite) {
		super(sprite);
	}

	// ********************************M�TODOS**********************************
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, sprite); // DUDAS EN VoidTile.class
	}

}
