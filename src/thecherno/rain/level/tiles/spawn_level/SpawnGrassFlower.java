package thecherno.rain.level.tiles.spawn_level;

import thecherno.rain.graphics.Screen;
import thecherno.rain.graphics.Sprite;
import thecherno.rain.level.tiles.Tile;

public class SpawnGrassFlower extends Tile {

	public SpawnGrassFlower(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, sprite); // DUDAS EN VoidTile.class
	}
}
