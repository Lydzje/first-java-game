package thecherno.rain.entity.spawner;

import thecherno.rain.entity.Entity;
import thecherno.rain.entity.particle.Particle;
import thecherno.rain.level.Level;

public abstract class Spawner extends Entity {
	// ********************************VARIABLES********************************

	public enum Type {
		MOB, PARTICLE;
	}

	private Type type;

	// ****************************CONSTR._Y_GETTERS********************************
	public Spawner(int x, int y, Type type, int amount, Level level) {
		init(level);
		this.x = x;
		this.y = y;
		this.type = type;

	}
	// ********************************MÉTODOS**********************************

}
