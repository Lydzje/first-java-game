package thecherno.rain.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet { // ESTO CARGA LA SHEET O HOJA, NOT INDIVIDUAL SPRITES

	// **************************VARIABLES***********************
	private String path;
	private final int SIZE;
	public final int WIDTH, HEIGHT; // TAMAÑO DEL SPRITE
	private int width, height; // TAMAÑO DEL SHEET
	public int[] pixels;

	private Sprite[] sprites;

	public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/spriteSheet.png", 256);
	public static SpriteSheet spawn_level = new SpriteSheet("/textures/sheets/spawnSheet.png", 48);
	public static SpriteSheet projectile_wizard = new SpriteSheet("/textures/sheets/projectiles/wizard.png", 48);

	// PLAYER SPRITESHEET
	public static SpriteSheet player = new SpriteSheet("/textures/sheets/mobs/playerSheet.png", 64, 48);
	public static SpriteSheet player_up = new SpriteSheet(player, 0, 0, 1, 3, 16);
	public static SpriteSheet player_right = new SpriteSheet(player, 1, 0, 1, 3, 16);
	public static SpriteSheet player_down = new SpriteSheet(player, 2, 0, 1, 3, 16);
	public static SpriteSheet player_left = new SpriteSheet(player, 3, 0, 1, 3, 16);

	// DUMMY SPRITESHEET
	public static SpriteSheet dummy = new SpriteSheet("/textures/sheets/mobs/dummySheet.png", 64, 48);
	public static SpriteSheet dummy_up = new SpriteSheet(dummy, 0, 0, 1, 3, 16);
	public static SpriteSheet dummy_right = new SpriteSheet(dummy, 1, 0, 1, 3, 16);
	public static SpriteSheet dummy_down = new SpriteSheet(dummy, 2, 0, 1, 3, 16);
	public static SpriteSheet dummy_left = new SpriteSheet(dummy, 3, 0, 1, 3, 16);

	// ****************************CONSTR._Y_GETTERS***************
	// EXTRAE UN SUBSHEET DE UN SHEET
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		if (width == height) SIZE = spriteSize;
		else SIZE = -1;
		WIDTH = w;
		HEIGHT = h;
		pixels = new int[w * h];
		for (int y0 = 0; y0 < h; y0++) {
			int yP = yy + y0;
			for (int x0 = 0; x0 < w; x0++) {
				int xP = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xP + yP * sheet.WIDTH];
			}
		}
		int frame = 0;
		sprites = new Sprite[width * height];
		for (int ya = 0; ya < height; ya++) {
			for (int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSize * spriteSize];
				for (int y0 = 0; y0 < spriteSize; y0++) {
					for (int x0 = 0; x0 < spriteSize; x0++) {
						spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize)
								* WIDTH];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}
	}

	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		WIDTH = size;
		HEIGHT = size;
		this.pixels = new int[SIZE * SIZE]; // new int[SIZE * SIZE];
		load();
	}

	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		SIZE = -1;
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[WIDTH * HEIGHT]; // [WIDTH * HEIGHT];
		load();
	}

	public int getSIZE() {
		return SIZE;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Sprite[] getSprites() {

		return sprites;
	}

	// ****************************MÉTODOS**********************
	private void load() { // CARGA LA IMAGEN
		try {
			System.out.print("Trying to load: " + path + "...");
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			System.out.println(" succeded!");
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width); // "TRADUCE LA IMAGEN A PIXELES"
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(" failed!");
		}

	}

}
