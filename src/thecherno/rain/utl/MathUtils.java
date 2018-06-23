package thecherno.rain.utl;

public class MathUtils {

	private MathUtils() {
	}

	public static int max(int value, int max) {
		return value > max ? max : value;
	}

	public static int min(int value, int min) {
		return value < min ? min : value;
	}

	public static int clamp(int value, int min, int max) {
		if (value < min) return min;
		else if (value > max) return max;
		else return value;
	}
}
