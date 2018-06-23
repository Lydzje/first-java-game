package thecherno.rain.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import thecherno.rain.entity.Entity;
import thecherno.rain.entity.mob.Player;
import thecherno.rain.entity.particle.Particle;
import thecherno.rain.entity.projectile.Projectile;
import thecherno.rain.entity.spawner.ParticleSpawner;
import thecherno.rain.events.Event;
import thecherno.rain.graphics.Screen;
import thecherno.rain.graphics.layers.Layer;
import thecherno.rain.level.tiles.Tile;
import thecherno.rain.utl.Vector2i;

public class Level extends Layer {

	// *******************************VARIABLES**********************
	protected int width, height;
	protected int[] tiles;
	protected int[] iTiles; // CADA ÍNDICE ESPECIFICARÁ EL TIPO DE TILE O CELDA

	private int xScroll, yScroll;

	protected Random random = new Random();

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Particle> particles = new ArrayList<Particle>();
	private List<ParticleSpawner> particleSpawners = new ArrayList<ParticleSpawner>();

	private List<Player> players = new ArrayList<Player>();

	private Comparator<Node> nodeSorter = new Comparator<Node>() {
		public int compare(Node n0, Node n1) {
			if (n1.fCost < n0.fCost) return 1;
			if (n1.fCost > n0.fCost) return -1;

			return 0;
		}

	};

	public static Level spawn = new SpawnLevel("/levels/spawn.png");

	// ****************************CONSTR._Y_GETTERS*****************
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		iTiles = new int[width * height];
		generateLevel();
	}

	public Level(String path) {
		loadLevel(path);
		generateLevel();

	}

	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	public List<Player> getPlayers() {
		return players;

	}

	// CONSIDDERAR ENLAZAR CON getEntities
	public List<Player> getPlayers(Entity e, int radius) { // MEJORAR_DISEÑO
		List<Player> result = new ArrayList<Player>();
		int ex = (int) e.getX();
		int ey = (int) e.getY();

		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			int x = (int) player.getX();
			int y = (int) player.getY();

			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);

			double distance = Math.sqrt(dx * dx + dy * dy); // +RAPIDO_QUE_MATH.POW
			if (distance <= radius) result.add(player);
		}

		return result;
	}

	public Player getPlayerAt(int index) {
		return players.get(index);
	}

	public Player getClientPlayer() {
		return players.get(0);
	}

	public List<Entity> getEntities(Entity e, int radius) {
		List<Entity> result = new ArrayList<Entity>();
		int ex = (int) e.getX();
		int ey = (int) e.getY();

		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			if (entity.equals(e)) continue;
			int x = (int) entity.getX();
			int y = (int) entity.getY();

			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);

			double distance = Math.sqrt(dx * dx + dy * dy); // +RAPIDO_QUE_MATH.POW
			if (distance <= radius) result.add(entity);
		}
		return result;
	}

	// *******************************MÉTODOS*************************
	protected void loadLevel(String path) {

	}

	protected void generateLevel() {

	}

	private void time() {

	}

	public void update() {
		for (int i = 0; i < entities.size(); i++)
			entities.get(i).update();
		for (int i = 0; i < projectiles.size(); i++)
			projectiles.get(i).update();
		for (int i = 0; i < particles.size(); i++)
			particles.get(i).update();
		for (int i = 0; i < players.size(); i++)
			players.get(i).update();
		remove();
	}

	public void onEvent(Event event) {
		getClientPlayer().onEvent(event);
	}

	private void remove() {
		for (int i = 0; i < entities.size(); i++)
			if (entities.get(i).isRemoved()) entities.remove(i);
		for (int i = 0; i < projectiles.size(); i++)
			if (projectiles.get(i).isRemoved()) projectiles.remove(i);
		for (int i = 0; i < particles.size(); i++)
			if (particles.get(i).isRemoved()) particles.remove(i);
		for (int i = 0; i < players.size(); i++)
			if (players.get(i).isRemoved()) players.remove(i);
	}

	public List<Node> findPath(Vector2i start, Vector2i goal) {
		List<Node> openList = new ArrayList<Node>();
		List<Node> closedList = new ArrayList<Node>();
		Node current = new Node(start, null, 0, getDistance(start, goal));
		openList.add(current);

		while (openList.size() > 0) {
			Collections.sort(openList, nodeSorter);
			current = openList.get(0);

			if (current.tile.equals(goal)) {
				List<Node> path = new ArrayList<Node>();
				while (current.parent != null) {
					path.add(current);
					current = current.parent;
				}
				openList.clear();
				closedList.clear();
				return path;
			}
			openList.remove(current);
			closedList.add(current);
			for (int i = 0; i < 9; i++) {
				if (i == 4) continue;
				int x = current.tile.getX();
				int y = current.tile.getY();
				int iX = (i % 3) - 1;
				int iY = (i / 3) - 1;
				Tile at = getTile(x + iX, y + iY);
				if (at == null) continue;
				if (at.solid()) continue;
				Vector2i a = new Vector2i(x + iX, y + iY);
				double gCost = current.gCost + (getDistance(current.tile, a));
				// == 1 ? 1 : 0.95); <-- ZIG ZAG
				double hCost = getDistance(current.tile, goal); // CONSIDERAR_SACAR
				Node node = new Node(a, current, gCost, hCost);
				if (vecInList(closedList, a) && gCost >= node.gCost) continue;
				if (!vecInList(openList, a) || gCost < node.gCost) openList.add(node);
			}
		}
		closedList.clear();
		return null;
	}

	private boolean vecInList(List<Node> list, Vector2i vector) {
		for (Node n : list)
			if (n.tile.equals(vector)) return true;

		return false;
	}

	private double getDistance(Vector2i tile, Vector2i goal) {
		double dx = tile.getX() - goal.getX();
		double dy = tile.getY() - goal.getY();

		return Math.sqrt(dx * dx + dy * dy);
	}

	public boolean tileCollision(double x, double y, double xA, double yA, int size) {
		boolean solid = false;

		if (spawn.getTile((int) ((x + xA) / 16), (int) ((y + yA) / 16)).solid()
				|| spawn.getTile((int) ((x + xA + size) / 16), (int) ((y + yA) / 16)).solid()
				|| spawn.getTile((int) ((x + xA) / 16), (int) ((y + yA + size) / 16)).solid()
				|| spawn.getTile((int) ((x + xA + size) / 16), (int) ((y + yA + size) / 16)).solid()) solid = true;

		return solid;
	}

	public void setScroll(int xScroll, int yScroll) {
		this.xScroll = xScroll;
		this.yScroll = yScroll;
	}

	// ESPECIFICA QUE TILE SE RECOLECTA
	public void render(Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4; // PIXEL PRECISION
		int x1 = (xScroll + screen.getWidth() + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.getHeight() + 16) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		for (int i = 0; i < entities.size(); i++)
			entities.get(i).render(screen);
		for (int i = 0; i < projectiles.size(); i++)
			projectiles.get(i).render(screen);
		for (int i = 0; i < particles.size(); i++)
			particles.get(i).render(screen);
		for (int i = 0; i < players.size(); i++)
			players.get(i).render(screen);

	}

	public void add(Entity e) {
		e.init(this);

		if (e instanceof Particle) particles.add((Particle) e);
		else if (e instanceof ParticleSpawner) particleSpawners.add((ParticleSpawner) e);
		else if (e instanceof Projectile) projectiles.add((Projectile) e);
		else if (e instanceof Player) players.add((Player) e);
		else entities.add(e);
	}

	// Grass = 0xFF0C3E0C
	// Grass2 = 0xFF8B9112
	// Flower = 0xFF700001
	// Rock = 0xFF7C8E80
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[Math.abs(x + y * width)] == Tile.col_spawn_grass) return Tile.spawn_grass;
		else if (tiles[Math.abs(x + y * width)] == Tile.col_spawn_grass_2) return Tile.spawn_grass_2;
		else if (tiles[Math.abs(x + y * width)] == Tile.col_spawn_grass_rock) return Tile.spawn_grass_rock;
		else if (tiles[Math.abs(x + y * width)] == Tile.col_spawn_grass_flower) return Tile.spawn_grass_flower;

		else if (tiles[Math.abs(x + y * width)] == Tile.col_spawn_lgrass) return Tile.spawn_lgrass;
		else if (tiles[Math.abs(x + y * width)] == Tile.col_spawn_lgrassS) return Tile.spawn_lgrassS;
		else if (tiles[Math.abs(x + y * width)] == Tile.col_spawn_lgrassT) return Tile.spawn_lgrassT;

		else if (tiles[Math.abs(x + y * width)] == Tile.col_spawn_wall) return Tile.spawn_wall;

		else if (tiles[Math.abs(x + y * width)] == Tile.col_spawn_water) return Tile.spawn_water;
		else return Tile.voidTile;
	}
}
