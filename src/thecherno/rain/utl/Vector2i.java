package thecherno.rain.utl;

public class Vector2i {
	// ********************************VARIABLES********************************

	private int x, y;

	// ****************************CONSTR._Y_GETTERS********************************

	public Vector2i() {
		set(0, 0);
	}

	public Vector2i(int x, int y) {
		set(x, y);
	}

	public Vector2i(Vector2i vector) {
		set(vector.x, vector.y);
	}

	public int getX() {//
		return x;
	}

	public int getY() {
		return y;
	}

	public Vector2i setX(int x) {
		this.x = x;
		return this;
	}

	public Vector2i setY(int y) {
		this.y = y;
		return this;
	}

	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// ********************************MÉTODOS**********************************

	public void test() {
		Vector2i player_position = new Vector2i(80, 40).setX(15);
		// Vector2i mob_position = new Vector2i(player_position).setX(50);

	}

	public Vector2i add(Vector2i vector) { // PASO_POR_REFERENCIA
		this.x += vector.x;
		this.y += vector.y;
		return this;
	}

	public Vector2i add(int x, int y) { // PASO_POR_REFERENCIA
		this.x += x;
		this.y += y;
		return this;
	}

	public Vector2i vectorTo(int x, int y) {
		return new Vector2i(this.x + x, this.y + y);
	}

	public Vector2i vectorTo(Vector2i vector) {
		return new Vector2i(this.x + vector.x, this.y + vector.y);
	}

	public Vector2i subtract(Vector2i vector) {
		this.x -= vector.x;
		this.y -= vector.y;
		return this;
	}

	public static double getDistance(Vector2i v0, Vector2i v1) {
		double x = v0.getX() - v1.getX();
		double y = v0.getY() - v1.getY();

		return Math.sqrt(x * x + y * y);
	}

	public boolean equals(Object object) {
		if (!(object instanceof Vector2i)) return false;
		Vector2i vec = (Vector2i) object;
		if (vec.getX() == this.getX() && vec.getY() == this.getY()) return true;

		return false;
	}

}
