package thecherno.rain.graphics.ui;

import java.awt.Color;

public class UIButtonListener {

	// ********************************VARIABLES********************************

	// ****************************CONSTR._Y_GETTERS********************************

	// ********************************MÉTODOS**********************************

	public void entered(UIButton button) {
		button.color = new Color(0xffC96F10);
	}

	public void exited(UIButton button) {
		button.color = new Color(0xff94510A);
	}

	public void pressed(UIButton button) {
		button.color = new Color(0xff693A08);
	}

	public void realeased(UIButton button) {
		button.color = new Color(0xffC96F10);
	}
}
