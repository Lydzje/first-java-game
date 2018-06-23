package thecherno.rain.graphics.ui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class UIManager {

	// ********************************VARIABLES********************************

	private List<UIPanel> panels = new ArrayList<UIPanel>();

	// ****************************CONSTR._Y_GETTERS********************************

	public UIManager() {

	}

	// ********************************MÉTODOS**********************************

	public void update() {
		for (UIPanel panel : panels) {
			panel.update();
		}
	}

	public void render(Graphics g) {
		for (UIPanel panel : panels) {
			panel.render(g);
		}
	}

	public void addPanel(UIPanel panel) {
		panels.add(panel);
	}

}
