package thecherno.rain.graphics;

import java.util.Random;

import thecherno.rain.entity.mob.Chaser;
import thecherno.rain.entity.mob.Mob;
import thecherno.rain.entity.mob.Shooter;
import thecherno.rain.entity.mob.Star;
import thecherno.rain.entity.projectile.Projectile;

public class Screen {

	// **************************************VARIABLES*****************************
	private int width, height; // ANCHO Y ALTO DEL SCREEN DEFINIDOS POR EL
								// CONSTR.
	public int[] pixels; // UN ARRAY CON TODOS LOS PIXELES

	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;

	public int xOffset, yOffset;

	public int[] tiles = new int[MAP_SIZE * MAP_SIZE]; // UN ARRAY CON TODAS LAS
														// CELDAS Y SU TAMAÑO

	private Random random = new Random();

	// ****************************CONSTR._Y_GETTERS*******************************
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		tiles[0] = 0x000000;
		for (int i = 1; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff); // DEFINICIÓN DEL COLOR DE CADA
													// CELDA
		}

	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	// ***************************************MÉTODOS******************************

	public void clear() { // MÉTODO PARA LIMPIAR LOS RESIDUOS
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	// CÓDIGO QUE RENDERIZA UN MAPA DE GRASS----------------------------------
	// public void render(int xOffset, int yOffset) { // AQUÍ SE DEFINEN LOS
	// GRÁFICOS
	// for (int y = 0; y < height; y++) {
	// int dY = y + yOffset;
	// if (dY < 0 || dY >= height)
	// continue;
	// for (int x = 0; x < width; x++) {
	// int dX = x + xOffset;
	// if (dX < 0 || dX >= width)
	// continue;
	// CÓDIGO DE CELDAS DE COLORES, NPP 1,2-----------------------
	/*
	 * int tileIndex = ((dX >> 4) & MAP_SIZE_MASK) + ((dY >> 4) & MAP_SIZE_MASK)
	 * * MAP_SIZE; -----------------------------------------------------------
	 */

	// REPITE EL MAPA AL MOVERSE-----------------------------------
	/*
	 * pixels[x + y * width] = Sprite.grass.pixels[(dX & Sprite.iSize) + (dY &
	 * Sprite.iSize) * Sprite.grass.SIZE];
	 */

	// CÓDIGO QUE MUEVE EL MAPA AL MOVERSE--------------------------

	// pixels[dX + dY * width] = Sprite.grass.pixels[(x & Sprite.iSize)
	// + (y & Sprite.iSize) * Sprite.grass.getSIZE()];

	// }
	// }

	// }---------------------------------------------------------------------

	/**
	 * @xP = POSICION X
	 * @xA = POSICIÓN ABSOLUTA DE X
	 * @x = POSICION RELATIVA DE X
	 * */

	public void renderTile(int xP, int yP, Sprite sprite) {
		xP -= xOffset;
		yP -= yOffset;
		for (int y = 0; y < sprite.getSIZE(); y++) {
			int yA = y + yP;
			for (int x = 0; x < sprite.getSIZE(); x++) {
				int xA = x + xP;
				if (xA < -sprite.getSIZE() || xA >= width || yA < 0 || yA >= height) break; // *3NPP
				if (xA < 0) xA = 0;
				pixels[xA + yA * width] = sprite.pixels[x + y * sprite.getSIZE()];
			}
		}
	}

	public void renderProjectile(int xP, int yP, Projectile p) {
		xP -= xOffset;
		yP -= yOffset;
		for (int y = 0; y < p.getSprite().getSIZE(); y++) {
			int yA = y + yP;
			for (int x = 0; x < p.getSprite().getSIZE(); x++) {
				int xA = x + xP;
				if (xA < -p.getSprite().getSIZE() || xA >= width || yA < 0 || yA >= height) break; // *3NPP
				if (xA < 0) xA = 0;

				int col = p.getSprite().pixels[x + y * p.getSprite().getSIZE()];
				if (col != 0xffff00ff) pixels[xA + yA * width] = col; // *4NPP
			}
		}
	}

	public void renderSheet(int xP, int yP, SpriteSheet sheet, boolean fixed) {
		if (fixed) {
			xP -= xOffset;
			yP -= yOffset;
		}
		for (int y = 0; y < sheet.HEIGHT; y++) {
			int yA = y + yP;
			for (int x = 0; x < sheet.WIDTH; x++) {
				int xA = x + xP;
				if (xA < 0 || xA >= width || yA < 0 || yA >= height) continue;
				pixels[xA + yA * width] = sheet.pixels[x + y * sheet.WIDTH];
			}
		}

	}

	public void renderSprite(int xP, int yP, Sprite sprite, boolean fixed) {
		if (fixed) {
			xP -= xOffset;
			yP -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int yA = y + yP;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xA = x + xP;
				if (xA < 0 || xA >= width || yA < 0 || yA >= height) continue;
				int col = sprite.pixels[x + y * sprite.getWidth()];
				if (col != 0xffff00ff) pixels[xA + yA * width] = col;
			}
		}

	}

	public void renderMob(int xP, int yP, Sprite sprite) {
		xP -= xOffset;
		yP -= yOffset;
		int size = sprite.getSIZE();
		for (int y = 0; y < size; y++) {
			int yA = y + yP;
			for (int x = 0; x < size; x++) {
				int xA = x + xP;
				if (xA < -size || xA >= width || yA < 0 || yA >= height) break;
				if (xA < 0) xA = 0;
				int col = sprite.pixels[x + y * size];
				if (col != 0xffff00ff) pixels[xA + yA * width] = col; // *4NPP
			}
		}
	}

	public void renderMob(int xP, int yP, Mob mob) {
		xP -= xOffset;
		yP -= yOffset;
		int size = mob.getSprite().getSIZE();
		for (int y = 0; y < size; y++) {
			int yA = y + yP;
			for (int x = 0; x < size; x++) {
				int xA = x + xP;
				if (xA < -size || xA >= width || yA < 0 || yA >= height) break;
				if (xA < 0) xA = 0;
				int col = mob.getSprite().pixels[x + y * size];

				if (mob instanceof Chaser && col == 0xff1EA06D) col = 0xff255D9E;
				if (mob instanceof Chaser && col == 0xff22B77C) col = 0xff2F76C6;
				if (mob instanceof Chaser && col == 0xff146B48) col = 0xff1B4472;

				if (mob instanceof Star && col == 0xff1EA06D) col = 0xff9E1E1E;
				if (mob instanceof Star && col == 0xff22B77C) col = 0xffCE2727;
				if (mob instanceof Star && col == 0xff146B48) col = 0xff721515;

				if (mob instanceof Shooter && col == 0xff1EA06D) col = 0xff343533;
				if (mob instanceof Shooter && col == 0xff22B77C) col = 0xff525451;
				if (mob instanceof Shooter && col == 0xff146B48) col = 0xff0F0F0E;

				if (col != 0xffff00ff) pixels[xA + yA * width] = col; // *4NPP
			}
		}
	}

	public void drawRect(int xP, int yP, int width, int height, int color, boolean fixed) {
		if (fixed) {
			xP -= xOffset;
			yP -= yOffset;
		}

		for (int x = xP; x <= xP + width; x++) {
			if (yP >= this.height || x < 0 || x >= this.width) continue;
			if (yP > 0) pixels[x + yP * this.width] = color;

			if (yP + height >= this.height) continue;
			if (yP + height > 0) pixels[x + (yP + height) * this.width] = color;
		}

		for (int y = yP; y <= yP + height; y++) {
			if (xP >= this.width || y < 0 || y >= this.height) continue;
			if (xP > 0) pixels[xP + y * this.width] = color;

			if (xP + width >= this.width) continue;
			if (xP + width > 0) pixels[xP + width + y * this.width] = color;
		}
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

}

// **********************NPP_NOTAS_A_PIE_DE_PÁGINA*********************************

/**
 * 1>> El operador "&" significa que al exceder en el ejemplo "dX" al valor
 * "MAP_SIZE_MASK",dX" se reiniciará a "0".
 */

/**
 * 2>> (dX >> 4) = dX/(2^4) = dX/16
 * */

/**
 * 3>> El funcionamiento de esa línea de código es el siguiente: Para cambiar la
 * cantidad de mapa renderizado A LA IZQUIERDA se deben cambiar los valores
 * "-16". Estos son los margenes del render a la izquierda, a partir de ahí,
 * como indique la expresión if, no se renderizará nada.
 */
/**
 * 4>> el código "0xffff00ff" también especifica el alpha, no solo el rgb
 */
