package Views;

import Model.*;
import Resources.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.border.*;
import java.util.Observer;
import java.util.Observable;

public class ImageCollectionView extends JPanel implements Observer {
	// Set the model for the view
	Model model;
	Border border;

	public ImageCollectionView (Model _model) {
		model = _model;
		border = new CompoundBorder(new EmptyBorder(GlobalConstants.BORDER_PADDING, 
			GlobalConstants.BORDER_PADDING, GlobalConstants.BORDER_PADDING, GlobalConstants.BORDER_PADDING),
			new EtchedBorder(EtchedBorder.LOWERED));

		super.setLayout(new FlowLayout());
		super.setBorder(border);
	}

	@Override
	public void update (Observable o, Object arg) {
		HashMap<ImageModel, ImageIcon> selectedImages = model.getSelectedImages();
		System.out.println("Updating collection.");
		for (HashMap.Entry<ImageModel, ImageIcon> entry : selectedImages.entrySet()) {
			System.out.println("Adding image: \n" + entry.getKey().toString() + "\n");
			this.add(new ImageView(entry.getValue(), entry.getKey().htmlString()));
		}
		repaint();
	}
}