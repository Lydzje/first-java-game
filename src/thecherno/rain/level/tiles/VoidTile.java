package thecherno.rain.level.tiles;

import thecherno.rain.graphics.Screen;
import thecherno.rain.graphics.Sprite;

public class VoidTile extends Tile {

	// ********************************VARIABLES********************************

	// ****************************CONSTR._Y_GETTERS********************************
	public VoidTile(Sprite sprite) {
		super(sprite);
	}

	// ********************************M�TODOS**********************************
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, sprite); // 1* NPP
	}
}

// *******************************NOTAS_A_PIE_DE_P�GINA***************************
/**
 * 1>> "x << 4" = x * 2^4, USADO AH� PARA LA CONVERSION PIXEL->TILE
 * 
 * */
