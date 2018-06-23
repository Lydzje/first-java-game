package thecherno.rain.graphics;

public class Font {

	// ********************************VARIABLES********************************
	private static SpriteSheet font = new SpriteSheet("/fonts/arial.png", 16);
	private static Sprite[] characters = Sprite.split(font);

	private static String charIndex = "" //
			+ "ABCDEFGHIJKLM"//
			+ "NOPQRSTUVWXYZ"//
			+ "abcdefghijklm"//
			+ "nopqrstuvwxyz"//
			+ "0123456789.,`"//
			+ "´“”;:!@$%()-+";

	// TODO: EXTRACT SHEET CELLS INTO INDIVIDUAL SPRITES

	// ****************************CONSTR._Y_GETTERS********************************

	public Font() {

	}

	// ********************************MÉTODOS**********************************

	public void render(int x, int y, String text, Screen screen) {

		for (int i = 0; i < text.length(); i++) {
			char currentChar = text.charAt(i);
			int index = charIndex.indexOf(currentChar);
			if (index == -1) continue;
			screen.renderSprite(x + i * font.getSIZE(), y, characters[index], false);
		}
	}
}
