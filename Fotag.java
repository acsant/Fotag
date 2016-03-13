

import Model.Model;
import Resources.GlobalConstants;
import Views.ImageCollectionView;
import Views.MenuView;
import Views.FilterView;
import Views.ScrollView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
        FilterView filterView = new FilterView(model);
        ImageCollectionView imgCollectionView = new ImageCollectionView(model);
        ScrollView scrollable = new ScrollView(model);

        // Collection view setting
        scrollable.setViewportView(imgCollectionView);

    	// Add corresponding views to model
    	model.addObserver(menu);
        model.addObserver(filterView);
        model.addObserver(imgCollectionView);
        model.addObserver(scrollable);

    	// Add subviews to the application window
        groupPanel.add(filterView, BorderLayout.NORTH);
        groupPanel.add(scrollable, BorderLayout.CENTER);
    	fotag.add(menu, BorderLayout.NORTH);
        fotag.add(groupPanel, BorderLayout.CENTER);
        fotag.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fotag.setMinimumSize(GlobalConstants.MINIMUM_SIZE);
        fotag.setVisible(true);
        model.setCollectionSize(fotag.getSize());
        fotag.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized (ComponentEvent e) {
                JFrame source = (JFrame) e.getSource();
                System.out.println(source.getSize());
                model.setCurrentSize(source.getSize());
                if (fotag.getSize().getWidth() <= GlobalConstants.MAX_NAME_DISPLAY) {
                    model.setShowName(false);
                } else if (!model.isShowName()) {
                    model.setShowName(true);
                }
            }
        });
    
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
