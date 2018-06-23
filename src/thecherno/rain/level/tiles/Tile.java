package thecherno.rain.level.tiles;

import thecherno.rain.graphics.Screen;
import thecherno.rain.graphics.Sprite;
import thecherno.rain.level.tiles.spawn_level.SpawnGrass;
import thecherno.rain.level.tiles.spawn_level.SpawnGrass2;
import thecherno.rain.level.tiles.spawn_level.SpawnGrassFlower;
import thecherno.rain.level.tiles.spawn_level.SpawnGrassRock;
import thecherno.rain.level.tiles.spawn_level.SpawnLGrass;
import thecherno.rain.level.tiles.spawn_level.SpawnLGrassSide;
import thecherno.rain.level.tiles.spawn_level.SpawnLGrassTop;
import thecherno.rain.level.tiles.spawn_level.SpawnWall;
import thecherno.rain.level.tiles.spawn_level.SpawnWater;

public class Tile {

	// *******************************VARIABLES*********************
	private int x, y;
	public Sprite sprite;

	// TILES TEXTURES
	public static Tile grass = new Grass(Sprite.grass);
	public static Tile grass_flower = new GrassFlower(Sprite.grass_flower);
	public static Tile grass_2 = new Grass2(Sprite.grass_2);
	public static Tile grass_rock = new GrassRock(Sprite.grass_rock);

	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile solidColor = new SolidColour(Sprite.solidColour);

	// SPAWNLEVEL TILES TEXTURES
	public static Tile spawn_grass = new SpawnGrass(Sprite.spawn_grass);
	public static Tile spawn_grass_flower = new SpawnGrassFlower(Sprite.spawn_grass_flower);
	public static Tile spawn_grass_2 = new SpawnGrass2(Sprite.spawn_grass_2);

	public static Tile spawn_lgrassS = new SpawnLGrassSide(Sprite.spawn_lgrassS);
	public static Tile spawn_lgrassT = new SpawnLGrassTop(Sprite.spawn_lgrassT);
	public static Tile spawn_lgrass = new SpawnLGrass(Sprite.spawn_lgrass);

	public static Tile spawn_grass_rock = new SpawnGrassRock(Sprite.spawn_grass_rock);
	public static Tile spawn_wall = new SpawnWall(Sprite.spawn_wall);
	public static Tile spawn_water = new SpawnWater(Sprite.spawn_water);

	public final static int col_spawn_grass = 0xff0C3300;
	public final static int col_spawn_grass_flower = 0xff;// unused
	public final static int col_spawn_grass_rock = 0xff;// unused
	public final static int col_spawn_grass_2 = 0xff;// unused

	public final static int col_spawn_lgrassS = 0xff;// unused
	public final static int col_spawn_lgrassT = 0xff;// unused
	public final static int col_spawn_lgrass = 0xff5B8900;

	public final static int col_spawn_wall = 0xff8A9DA8;

	public final static int col_spawn_water = 0xff; // unused

	// ****************************CONSTR._Y_GETTERS****************
	public Tile(Sprite sprite) { // A CADA TILE SE LE DEBE
		this.sprite = sprite; // ASOCIAR UN SPRITE
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// *******************************MÉTODOS************************
	public void render(int x, int y, Screen screen) {

	}

	public boolean solid() {
		return false;
	}
}
