package thecherno.rain.level.tiles;

import thecherno.rain.graphics.Screen;
import thecherno.rain.graphics.Sprite;

public class VoidTile extends Tile {

	// ********************************VARIABLES********************************

	// ****************************CONSTR._Y_GETTERS********************************
	public VoidTile(Sprite sprite) {
		super(sprite);
	}

	// ********************************MÉTODOS**********************************
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, sprite); // 1* NPP
	}
}

// *******************************NOTAS_A_PIE_DE_PÁGINA***************************
/**
 * 1>> "x << 4" = x * 2^4, USADO AHÍ PARA LA CONVERSION PIXEL->TILE
 * 
 * */
