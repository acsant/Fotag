import javax.swing.*;
import java.awt.*;

import java.util.Observer;

public class MenuView implements Observer {
	// Menu view with all the menus
	Model model;
	// Declare components for the menu view
	JMenu file = new Menu();

	public MenuView (Model _model) {
		model = _model;
	}

	@Override
	public void update (Observable o, Object arg) {

	}
}