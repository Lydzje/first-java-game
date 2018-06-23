package thecherno.rain.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import thecherno.rain.entity.mob.Chaser;
import thecherno.rain.entity.mob.Dummy;
import thecherno.rain.entity.mob.Shooter;
import thecherno.rain.entity.mob.Star;

public class SpawnLevel extends Level {

	// ********************************VARIABLES********************************

	// ****************************CONSTR._Y_GETTERS********************************
	public SpawnLevel(String path) {
		super(path);
	}

	// ********************************MÉTODOS**********************************
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			// tiles = new Tile[w * h];
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load level file");
		}
		for (int i = 0; i < 1; i++) {
			// if(random.nextInt(2) == 0)
			add(new Dummy(20 + random.nextInt(7) - 4, 45 + random.nextInt(7) - 4, 150));
			// else
			add(new Chaser(20 + random.nextInt(7) - 4, 45 + random.nextInt(7) - 4, 150));
			add(new Star(20 + random.nextInt(7) - 4, 45 + random.nextInt(7) - 4, 150));
			add(new Shooter(20 + random.nextInt(7) - 4, 45 + random.nextInt(7) - 4, 150));
		}
	}

	protected void generateLevel() {

	}
}
