package thecherno.rain.level;

import java.util.Random;

import thecherno.rain.level.tiles.Tile;

public class RandomLevel extends Level {

	// *******************************VARIABLES**********************
	private static final Random random = new Random();

	// ****************************CONSTR._Y_GETTERS*****************
	public RandomLevel(int width, int height) {
		super(width, height);
	}

	// *******************************MÉTODOS*************************
	protected void generateLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				iTiles[x + y * width] = random.nextInt(4); // INT'S ENTRE 0 Y 3
			}
		}
	}
	
	// GETTILE_PARA_RANDOM_LEVEL._
	// ESTE_MÉTODO_EN_LEVEL_ES_PUBLIC_ASÍ_QUE_HABRÁ_QUE_CAMBIARLO_A_PROTECTED
	//protected void getTile(int x, int y){
	//	if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
	//	if (iTiles[Math.abs(x + y * width)] == 0) {
	//		return Tile.grass;
	//	} else if (iTiles[Math.abs(x + y * width)] == 1) {
	//		return Tile.grass_flower;
	//	} else if (iTiles[Math.abs(x + y * width)] == 2) {
	//		return Tile.grass_2;
	//	} else if (iTiles[Math.abs(x + y * width)] == 3) {
	//		return Tile.grass_rock;
	//	} else return Tile.voidTile;
	//}

}
