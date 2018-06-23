package thecherno.rain.level;

import thecherno.rain.utl.Vector2i;

public class Node {

	// ********************************VARIABLES********************************

	public Vector2i tile;
	public Node parent;
	public double fCost, gCost, hCost; // NPP1

	// ****************************CONSTR._Y_GETTERS********************************

	public Node(Vector2i tile, Node parent, double gCost, double hCost) {
		this.tile = tile;
		this.parent = parent;
		this.gCost = gCost;
		this.hCost = hCost;
		this.fCost = this.gCost + this.hCost;
	}

	// ********************************MÉTODOS**********************************

}

// -------------NOTAS_A_PIE_PÁGINA----------------------
/**
 * 1- gCost = coste de cada nodo de la ruta elegida
 * 2- hCost = coste de la ruta directa (NO NODOS)
 * 3- fCost = hcost + gCost // TOTAL COST
 */
