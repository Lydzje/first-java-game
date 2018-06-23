package thecherno.rain.level.tiles.spawn_level;

import thecherno.rain.graphics.Screen;
import thecherno.rain.graphics.Sprite;
import thecherno.rain.level.tiles.Tile;

public class SpawnGrassRock extends Tile {

	public SpawnGrassRock(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, sprite); // DUDAS EN VoidTile.class
	}

	public boolean solid() {
		return true;
	}
}
