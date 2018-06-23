package thecherno.rain.level;

public class TileCoordinate {

	// ********************************VARIABLES********************************
	private int x, y;
	private final int TILE_SIZE = 16;

	// ****************************CONSTR._Y_GETTERS********************************
	public TileCoordinate(int x, int y) {
		this.x = x * TILE_SIZE;
		this.y = y * TILE_SIZE;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int[] getXY() {
		int[] r = new int[2];
		r[0] = x;
		r[1] = y;

		return r;
	}

	// ********************************MÉTODOS**********************************

}
