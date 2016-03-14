package Views;

import Model.ImageModel;
import Model.Model;
import Resources.GlobalConstants;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class ImageCollectionView extends JPanel implements Observer {
	// Set the model for the view
	Model model;

	public ImageCollectionView (Model _model) {
		model = _model;
		super.setBackground(new Color(110, 123, 139));
		super.setPreferredSize(GlobalConstants.MINIMUM_SIZE);
		super.setLayout(new FlowLayout());
	}

	@Override
	public void update (Observable o, Object arg) {
		HashMap<ImageModel, ImageIcon> selectedImages = model.getSelectedImages();
		this.removeAll();
		for (HashMap.Entry<ImageModel, ImageIcon> entry : selectedImages.entrySet()) {
			System.out.println("Adding image: \n" + entry.getKey().toString() + "\n");

			this.add(new ImageView(model, entry.getValue(), entry.getKey().htmlString(), entry.getKey().getImageId()));
		}
		repaint();
		Dimension preferredDim = model.getCollectionSize();
		this.setPreferredSize(preferredDim);
		revalidate();
	}
}