import javax.swing.*;

public class Fotag {

	public Fotag() {
		// Set crossplatform look and feel
		try {
    		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    	} catch (Exception ex) {
    		System.err.println("Cross platform look an feel not found.");
    	}

    	// Create the main frame
    	JFrame fotag = new JFrame();
    	// Create appropriate layout
    	fotag.setLayout(new BorderLayout());

    	// Create a model for the application
    	Model model = new Model();

    	// Create childs views for the main application window
    	MenuView menu = new MenuView(model);

    	// Add corresponding views to model
    	model.addObserver(menu);

    	// Add subviews to the application window
    	fotag.add(menu, BorderLayout.NORTH);
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
