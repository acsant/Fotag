package Views;

import Model.*;
import Resources.*;
import javax.swing.*;
import java.awt.*;
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
		
		super.setBorder(border);
	}

	@Override
	public void update (Observable o, Object arg) {

	}
}