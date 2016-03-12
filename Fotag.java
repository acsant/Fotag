

import javax.swing.*;
import java.awt.*;
import Views.*;
import Resources.*;
import Model.*;

public class Fotag {

	public Fotag() {
		// Set crossplatform look and feel
		/*try {
    		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    	} catch (Exception ex) {
    		System.err.println("Cross platform look an feel not found.");
    	}*/

    	// Create the main frame
    	JFrame fotag = new JFrame(GlobalConstants.APPLICATION_NAME);
        // Grouping panel
        JPanel groupPanel = new JPanel();
    	// Create appropriate layout
    	fotag.setLayout(new BorderLayout());
        fotag.setSize(GlobalConstants.SCREEN_SIZE);
        groupPanel.setLayout(new BorderLayout());

    	// Create a model for the application
		Model model = new Model();

    	// Create childs views for the main application window
    	MenuView menu = new MenuView(model);
        RankView rankView = new RankView(model);
        ImageCollectionView imgCollectionView = new ImageCollectionView(model);

    	// Add corresponding views to model
    	model.addObserver(menu);
        model.addObserver(imgCollectionView);

    	// Add subviews to the application window
        groupPanel.add(rankView, BorderLayout.NORTH);
        groupPanel.add(imgCollectionView, BorderLayout.CENTER);
    	fotag.add(menu, BorderLayout.NORTH);
        fotag.add(groupPanel, BorderLayout.CENTER);
        fotag.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fotag.setVisible(true);
	}

    public static void main(String[] args) {

    	SwingUtilities.invokeLater(new Runnable() {
    		@Override
    		public void run() {
        		System.out.println("Starting application");
    			Fotag fotag_app = new Fotag();
    		}
    	});
    }
}
