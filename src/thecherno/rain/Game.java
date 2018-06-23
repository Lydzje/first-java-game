package thecherno.rain;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import thecherno.rain.entity.mob.Player;
import thecherno.rain.events.Event;
import thecherno.rain.events.EventListener;
import thecherno.rain.graphics.Font;
import thecherno.rain.graphics.Screen;
import thecherno.rain.graphics.layers.Layer;
import thecherno.rain.graphics.ui.UIManager;
import thecherno.rain.input.Keyboard;
import thecherno.rain.input.Mouse;
import thecherno.rain.level.Level;
import thecherno.rain.level.TileCoordinate;

public class Game extends Canvas implements Runnable, EventListener {
	private static final long serialVersionUID = 1L;

	// ************************VARIABLES**********************************************
	private static int width = 300 - 64;
	private static int height = 300 / 16 * 9;
	private static int scale = 3;
	public static String title = "Rain";

	private Thread thread;
	private JFrame frame;
	private Screen screen;
	private Level level;
	private Player player;
	private Keyboard key;
	private Mouse mouse;

	private static UIManager uiManager;

	private Font font;

	private boolean running = false;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);// CREAR
																								// IMAGEN
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();// ACCEDER
																							// A
	private List<Layer> layerStack = new ArrayList<Layer>(); // IMAGEN

	// ****************************CONSTR._Y_GETTERS********************************
	public Game() {
		super();
		Dimension size = new Dimension(width * scale + 64 * scale, height * scale);
		setPreferredSize(size);

		frame = new JFrame();
		screen = new Screen(width, height);

		key = new Keyboard();
		addKeyListener(key);

		mouse = new Mouse(this);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);

		uiManager = new UIManager();
		level = Level.spawn;
		addLayer(level);
		TileCoordinate playerSpawn = new TileCoordinate(19, 55);
		player = new Player(playerSpawn.getX(), playerSpawn.getY(), "Arch", 150, key);
		level.add(player);

		font = new Font();
	}

	public static int getWindomWidth() {
		return width * scale;
	}

	public static int getWindowHeight() {
		return height * scale;
	}

	public static int getScale() {
		return scale;
	}

	public static UIManager getUIManager() {
		return uiManager;
	}

	public void addLayer(Layer layer) {
		layerStack.add(layer);
	}

	// ************************MÉTODOS*************************************************

	public void onEvent(Event event) {
		for (int i = layerStack.size() - 1; i >= 0; i--) {
			layerStack.get(i).onEvent(event);
		}
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime(); // NANOSEGUNDOS AL EJECUTAR
		long timer = System.currentTimeMillis(); // MILISEGUNDOS AL EJECUTAR
		final double ns = 1000000000.0 / 60.0; // CONVERSION SEGUNDOS POR FRAME
		double dDelta = 0;
		int iFrames = 0; // CONTADOR FRAMES
		int iUpdates = 0; // CONTADOR UPDATES
		requestFocus();
		while (running) {
			long now = System.nanoTime(); // NANOSEGUNDOS EN EL CÓDIGO
			dDelta += (now - lastTime) / ns; // FRAME POR SEGUNDOS
			lastTime = now;
			while (dDelta >= 1) { // POR CADA SEGUNDO 1 UPDATE
				update();
				iUpdates++;
				dDelta--;
			}
			render();
			iFrames++; // FRAMES TOTALES QUE SE REINICIAN POR CADA SEGUNDO ABAJO

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println(iUpdates + " ups, " + iFrames + " fps");
				frame.setTitle(title + "  |  " + iUpdates + " ups, " + iFrames + " fps");
				iUpdates = 0;
				iFrames = 0;
			}
		}
		stop();
	}

	// int dX = 0, dY = 0;// dX, dY DESPLAZAMIENTO MAP POR update() EN
	// screen.render().

	public void update() {
		key.update();
		// level.update();
		uiManager.update();

		// UPDATE LAYERS HERE!

		for (int i = 0; i < layerStack.size(); i++) {
			layerStack.get(i).update();
		}

	}

	public void render() {
		BufferStrategy bs = getBufferStrategy(); // BUFFER STRATEGY
		if (bs == null) {
			createBufferStrategy(3); // TRIPLEBUFFER
			return;
		}

		screen.clear();

		double xScroll = player.getX() - screen.getWidth() / 2; // POSICIÓN
																// INCIAL DE
		double yScroll = player.getY() - screen.getHeight() / 2; // PLAYER

		level.setScroll((int) xScroll, (int) yScroll);

		// RENDER LAYERS HERE!

		for (int i = 0; i < layerStack.size(); i++) {
			layerStack.get(i).render(screen);
		}

		// level.render(screen);
		// uiManager.render(screen);

		font.render(50, 50, "Hello", screen);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth() - 64 * scale, getHeight(), null);

		uiManager.render(g);

		// g.setColor(Color.WHITE);
		// g.setFont(new Font("Verdana", 0, 50));
		// g.fillRect(Mouse.getX() - 32, Mouse.getY() - 32, 64, 64);
		// if (Mouse.getButton() != -1) g.drawString("Button: " +
		// Mouse.getButton(), 80, 80);
		// g.drawString("X: " + player.x + ", Y: " + player.y, 450, 400);

		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();
		// System.out.println(screen.pixels.length);
		game.frame.setResizable(false);
		game.frame.setTitle(Game.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();
	}

}
// ----------------------------PLANTILLAS--------------------------------------
// -----------------------------------------------------------------------------

// ********************************VARIABLES********************************
// ****************************CONSTR._Y_GETTERS********************************
// ********************************MÉTODOS**********************************
// ******************************INNER_CLASSES**********************************