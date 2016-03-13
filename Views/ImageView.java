package Views;

import Resources.*;
import Model.*;
import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public class ImageView extends JPanel {
	ImageIcon image;
	String meta;
	JLabel thumbnail;
	JLabel metaLabel;
	Border borderImage;
	Border borderView;

	public ImageView (ImageIcon img, String metaData) {
		image = img;
		meta = metaData;
		thumbnail = new JLabel();
		metaLabel = new JLabel();
		borderImage = BorderFactory.createLineBorder(Color.BLACK);
		borderView = new EmptyBorder(GlobalConstants.BORDER_PADDING, GlobalConstants.BORDER_PADDING,
					GlobalConstants.BORDER_PADDING, GlobalConstants.BORDER_PADDING);
		
		thumbnail.setHorizontalAlignment(JLabel.CENTER);
		thumbnail.setVerticalAlignment(JLabel.CENTER);
		thumbnail.setIcon(image);
		thumbnail.setBorder(borderImage);
		metaLabel.setText(meta);
		super.setLayout(new BorderLayout());
		super.setBorder(borderView);
		super.add(metaLabel, BorderLayout.SOUTH);
		super.add(thumbnail, BorderLayout.CENTER);
	}

	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		thumbnail.setIcon(image);
		thumbnail.setBorder(borderImage);
		metaLabel.setText(meta);
	}
}