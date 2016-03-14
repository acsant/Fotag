package Views;

import Model.Model;
import Resources.GlobalConstants;
import sun.net.ResourceManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MenuView extends JMenuBar implements Observer {
	// Menu view with all the menus
	Model model;
	// Declare components for the menu view
	JButton load = new JButton();
	ButtonGroup viewGroup = new ButtonGroup();
	JPanel viewChangePanel = new JPanel();
	JToggleButton listView = new JToggleButton();
	JToggleButton gridView = new JToggleButton();
	JFileChooser fileChooser = new JFileChooser();
	MenuController controller;
	// Menu icons
    ImageIcon loadIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
            ResourceManager.class.getResource(GlobalConstants.RESOURCES_PATH + GlobalConstants.LOAD_ICON)));

	ImageIcon listIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
			ResourceManager.class.getResource(GlobalConstants.RESOURCES_PATH + GlobalConstants.LIST_VIEW_ICON)));

	ImageIcon gridIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
			ResourceManager.class.getResource(GlobalConstants.RESOURCES_PATH + GlobalConstants.GRID_VIEW_ICON)));

	public MenuView (Model _model) {
		super.setLayout(new BorderLayout());
		controller = new MenuController();
		makeTransparent(load);
		model = _model;
		load.addActionListener(controller);
		load.setIcon(loadIcon);
		viewGroup.add(listView);
		viewGroup.add(gridView);
		viewChangePanel.add(listView);
		viewChangePanel.add(gridView);
		viewChangePanel.setBackground(Color.WHITE);
		listView.setIcon(listIcon);
		listView.setBackground(Color.WHITE);
		listView.setForeground(Color.WHITE);
		listView.setBorder(BorderFactory.createRaisedBevelBorder());
		gridView.setIcon(gridIcon);
		gridView.setBorder(BorderFactory.createLoweredBevelBorder());
		gridView.setForeground(Color.WHITE);
		gridView.setBackground(Color.WHITE);
		listView.addItemListener(controller);
		gridView.addItemListener(controller);
		fileChooser.setMultiSelectionEnabled(true);
		// Add the component to the menu
		super.add(load, BorderLayout.WEST);
		super.add(viewChangePanel, BorderLayout.EAST);
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

	private class MenuController implements ActionListener, ItemListener {
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

		@Override
		public void itemStateChanged(ItemEvent e) {
			JToggleButton source = (JToggleButton) e.getSource();
			if (e.getStateChange() == ItemEvent.SELECTED) {
				if (source.equals(listView)) {
					listView.setBorder(BorderFactory.createLoweredBevelBorder());
					gridView.setBorder(BorderFactory.createRaisedBevelBorder());
					model.setGridView(false);
				} else {
					gridView.setBorder(BorderFactory.createLoweredBevelBorder());
					listView.setBorder(BorderFactory.createRaisedBevelBorder());
					model.setGridView(true);
				}
			}
		}
	}
}