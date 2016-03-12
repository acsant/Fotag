package Views;

import Model.*;
import Resources.*;
import java.util.Observable;
import sun.net.ResourceManager;
import java.util.Observer;
import java.util.ArrayList;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Observer;
import java.io.*;

public class MenuView extends JMenuBar implements Observer {
	// Menu view with all the menus
	Model model;
	// Declare components for the menu view
	JButton load = new JButton();
	JFileChooser fileChooser = new JFileChooser();
	MenuController controller;
	// Menu icons
    ImageIcon loadIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
            ResourceManager.class.getResource(GlobalConstants.RESOURCES_PATH + GlobalConstants.LOAD_ICON)));

	public MenuView (Model _model) {
		controller = new MenuController();
		makeTransparent(load);
		model = _model;
		load.addActionListener(controller);
		load.setIcon(loadIcon);
		fileChooser.setMultiSelectionEnabled(true);
		// Add the component to the menu
		super.add(load);
	}

	/**
	* Helper function to make all the buttons transparent
	*/
	private void makeTransparent(JButton button) {
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
	}

	@Override
	public void update (Observable o, Object arg) {

	}

	private class MenuController implements ActionListener {
		@Override
		public void actionPerformed (ActionEvent e) {
			JButton source = (JButton) e.getSource();
			ArrayList<String> filePaths = new ArrayList<String>();
			File[] files;
			if (source.equals(load)) {
				fileChooser.showOpenDialog(null);
				files = fileChooser.getSelectedFiles();
				for (File f : files) {
					filePaths.add(f.getAbsolutePath());
				}
				model.addFilePaths(filePaths);
			}
		}
	}
}