package Views;

import Model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public class ImageView extends JPanel {
	ImageIcon image;
	String meta;
	JLabel thumbnail;
	JLabel metaLabel;

	public ImageView (ImageIcon img, String metaData) {
		image = img;
		meta = metaData;
		thumbnail = new JLabel();
		metaLabel = new JLabel();
		thumbnail.setIcon(image);
		metaLabel.setText(meta);
		super.setLayout(new BorderLayout());
		super.add(metaLabel, BorderLayout.SOUTH);
		super.add(thumbnail, BorderLayout.CENTER);
	}

	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		thumbnail.setIcon(image);
		metaLabel.setText(meta);
	}
}