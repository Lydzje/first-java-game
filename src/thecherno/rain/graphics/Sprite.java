package thecherno.rain.graphics;

import thecherno.rain.level.tiles.Tile;
import thecherno.rain.level.tiles.spawn_level.SpawnWall;
import thecherno.rain.level.tiles.spawn_level.SpawnWater;

public class Sprite {

	// *********************************VARIBALES**************************
	private final int SIZE;
	public static int iSize;
	private int x, y;
	private int width, height;
	public int[] pixels;
	protected SpriteSheet sheet;

	// TILES TEXTURES
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite grass_flower = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite grass_2 = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite grass_rock = new Sprite(16, 3, 0, SpriteSheet.tiles);

	public static Sprite voidSprite = new Sprite(16, 14, 13, SpriteSheet.tiles);
	public static Sprite solidColour = new Sprite(16, 0x63A8BF);

	// SPAWNLEVEL SPRITES
	public static Sprite spawn_grass = new Sprite(16, 0, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_grass_flower = new Sprite(16, 0, 1, SpriteSheet.spawn_level);
	public static Sprite spawn_grass_2 = new Sprite(16, 1, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_lgrassS = new Sprite(16, 2, 1, SpriteSheet.spawn_level);
	public static Sprite spawn_lgrassT = new Sprite(16, 1, 2, SpriteSheet.spawn_level);
	public static Sprite spawn_lgrass = new Sprite(16, 2, 2, SpriteSheet.spawn_level);
	public static Sprite spawn_grass_rock = new Sprite(16, 1, 1, SpriteSheet.spawn_level);
	public static Sprite spawn_wall = new Sprite(16, 0, 2, SpriteSheet.spawn_level);
	public static Sprite spawn_water = new Sprite(16, 2, 0, SpriteSheet.spawn_level);

	// PROJECTILE SPRITES
	public static Sprite projectile_wizard = new Sprite(16, 0, 0, SpriteSheet.projectile_wizard);
	public static Sprite projectile_arrow = new Sprite(16, 1, 0, SpriteSheet.projectile_wizard);

	// PARTICLES SPRITES
	public static Sprite particle_normal = new Sprite(3, 0xffffff);

	/*
	 * // 16 BITS PLAYER public static Sprite player_back = new Sprite(16, 0,
	 * 13, SpriteSheet.tiles); public static Sprite player_right = new
	 * Sprite(16, 1, 13, SpriteSheet.tiles); public static Sprite player_forward
	 * = new Sprite(16, 2, 13, SpriteSheet.tiles); public static Sprite
	 * player_left = new Sprite(16, 3, 13, SpriteSheet.tiles);
	 * 
	 * // ANIMACIÓN public static Sprite player_back_L = new Sprite(16, 0, 14,
	 * SpriteSheet.tiles); public static Sprite player_back_R = new Sprite(16,
	 * 0, 15, SpriteSheet.tiles);
	 * 
	 * public static Sprite player_right_L = new Sprite(16, 1, 14,
	 * SpriteSheet.tiles); public static Sprite player_right_R = new Sprite(16,
	 * 1, 15, SpriteSheet.tiles);
	 * 
	 * public static Sprite player_forward_L = new Sprite(16, 2, 14,
	 * SpriteSheet.tiles); public static Sprite player_forward_R = new
	 * Sprite(16, 2, 15, SpriteSheet.tiles);
	 * 
	 * public static Sprite player_left_L = new Sprite(16, 3, 14,
	 * SpriteSheet.tiles); public static Sprite player_left_R = new Sprite(16,
	 * 3, 15, SpriteSheet.tiles);
	 */
	// 32 BITS
	// USA CUATRO SPRITES PARA 32 BITS O UN SPRITE DE 32 BITS
	// public static Sprite player0 = new Sprite(16, 2, 13, SpriteSheet.tiles);
	// public static Sprite player1 = new Sprite(16,2,15, SpriteSheet.tiles);
	// public static Sprite player2 = new Sprite(16,2,15, SpriteSheet.tiles);
	// public static Sprite player3 = new Sprite(16,2,15, SpriteSheet.tiles);

	// public static Sprite player32 = new Sprite(32, x, y, SpriteSheet.tiles);

	// ****************************CONSTR._Y_GETTERS********************************
	protected Sprite(SpriteSheet sheet, int width, int height) {
		SIZE = (width == height) ? width : -1;
		iSize = SIZE - 1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		iSize = size - 1;
		width = SIZE;
		height = SIZE;
		pixels = new int[SIZE * SIZE];
		this.x = x * SIZE;
		this.y = y * SIZE;
		this.sheet = sheet;
		load();
	}

	// CUADRADO
	public Sprite(int size, int colour) {
		SIZE = size;
		iSize = size - 1;
		width = size;
		height = size;
		pixels = new int[SIZE * SIZE];
		setColour(colour);
	}

	// RECTANGULO
	public Sprite(int width, int height, int colour) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColour(colour);
	}

	public Sprite(int[] pixels, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = new int[pixels.length];
		for (int i = 0; i < pixels.length; i++) {
			this.pixels[i] = pixels[i];
		}

	}

	public static Sprite[] split(SpriteSheet sheet) {
		int amount = sheet.getWidth() * sheet.getHeight() / (sheet.WIDTH * sheet.HEIGHT);
		Sprite[] sprites = new Sprite[amount];
		int current = 0;
		int[] pixels = new int[sheet.WIDTH * sheet.HEIGHT];

		for (int yP = 0; yP < sheet.getHeight() / sheet.HEIGHT; yP++) {
			for (int xP = 0; xP < sheet.getWidth() / sheet.WIDTH; xP++) {
				for (int y = 0; y < sheet.HEIGHT; y++) {
					for (int x = 0; x < sheet.WIDTH; x++) {
						int xOffset = x + xP * sheet.WIDTH;
						int yOffset = y + yP * sheet.HEIGHT;
						pixels[x + y * sheet.WIDTH] = sheet.pixels[xOffset + yOffset * sheet.getWidth()];
						// System.out.println(xOffset);
					}
				}
				sprites[current++] = new Sprite(pixels, sheet.WIDTH, sheet.HEIGHT);
			}
		}

		return sprites;
	}

	private void setColour(int colour) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = colour;
		}
	}

	public int getSIZE() {
		return SIZE;
	}

	public int getISize() {
		return iSize;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	// **********************************MÉTODOS***************************
	private void load() {
		for (int y = 0; y < height; y++) { // ESTO EXTRAE CADA PIXEL DEL
											// SPRITESHEET AL SPRITE OBJETIVO
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.WIDTH];
			}
		}
	}

	public static Sprite rotate(Sprite sprite, double angle) {
		return new Sprite(rotate(sprite.pixels, sprite.width, sprite.height, angle), sprite.width, sprite.height);
	}

	private static int[] rotate(int[] pixels, int width, int height, double angle) {
		int[] result = new int[width * height];

		double nx_x = rotX(-angle, 1.0, 0.0);
		double nx_y = rotY(-angle, 1.0, 0.0);
		double ny_x = rotX(-angle, 0.0, 1.0);
		double ny_y = rotY(-angle, 0.0, 1.0);

		double x0 = rotX(-angle, -width / 2.0, -height / 2.0) + width / 2.0; // VALOR_INICIAL_X
		double y0 = rotY(-angle, -width / 2.0, -height / 2.0) + height / 2.0; // VALOR_INICIAL_Y

		for (int y = 0; y < height; y++) {
			double x1 = x0;
			double y1 = y0;
			for (int x = 0; x < width; x++) {
				int xx = (int) x1;
				int yy = (int) y1;
				int col;

				if (xx < 0 || xx >= width || yy < 0 || yy >= height) col = 0xffff00ff;
				else col = pixels[xx + yy * width];
				result[x + y * width] = col;

				x1 += nx_x;
				y1 += nx_y;
			}
			x0 += ny_x;
			y0 += ny_y;
		}

		return result;
	}

	private static double rotX(double angle, double x, double y) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle); // Math.cos thecherno

		return x * cos + y * -sin; // ANGLE_EN_SENT_AGUJAS_DEL_RELOJ
	}

	private static double rotY(double angle, double x, double y) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle); // Math.cos thecherno

		return x * sin + y * cos; // ANGLE_EN_SENT_AGUJAS_DEL_RELOJ
	}

}
