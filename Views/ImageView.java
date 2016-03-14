package Views;

import Resources.GlobalConstants;
import Model.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.UUID;

public class ImageView extends JPanel {
	ImageIcon icon;
	String meta;
	JLabel thumbnail;
	JLabel metaLabel;
	JPanel rankings;
	Border borderImage;
	Border borderView;
	JLabel star1;
	JLabel star2;
	JLabel star3;
	JLabel star4;
	JLabel star5;
	RankController controller;
	UUID imageId;
	Model model;

	public ImageView (Model _model, ImageIcon img, String metaData, UUID id) {
		controller = new RankController();
		model = _model;
		icon = img;
		meta = metaData;
		imageId = id;
		thumbnail = new JLabel();
		metaLabel = new JLabel();
		rankings = new JPanel();
		star1 = new JLabel(GlobalConstants.STAR);
		star2 = new JLabel(GlobalConstants.STAR);
		star3 = new JLabel(GlobalConstants.STAR);
		star4 = new JLabel(GlobalConstants.STAR);
		star5 = new JLabel(GlobalConstants.STAR);

		rankings.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		rankings.setLayout(new GridLayout(0, 5));
		createStar(star1);
		createStar(star2);
		createStar(star3);
		createStar(star4);
		createStar(star5);
		borderImage = BorderFactory.createLineBorder(Color.BLACK);
		borderView = new EmptyBorder(GlobalConstants.BORDER_PADDING, GlobalConstants.BORDER_PADDING,
					GlobalConstants.BORDER_PADDING, GlobalConstants.BORDER_PADDING);
		rankings.setMaximumSize(new Dimension(GlobalConstants.THUMBNAIL_WIDTH, 15));
		thumbnail.setHorizontalAlignment(JLabel.CENTER);
		thumbnail.setVerticalAlignment(JLabel.CENTER);
		thumbnail.setIcon(icon);
		thumbnail.setBorder(borderImage);
		metaLabel.setText(meta);
		super.setLayout(new BorderLayout());
		super.setBorder(borderView);
		super.setBackground(Color.WHITE);
		super.add(metaLabel, BorderLayout.SOUTH);
		super.add(thumbnail, BorderLayout.NORTH);
		super.add(rankings, BorderLayout.CENTER);
	}

	private void createStar(JLabel label) {
		label.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		label.setHorizontalAlignment(JLabel.HORIZONTAL);
		rankings.add(label);
		label.addMouseListener(controller);
	}

	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		thumbnail.setIcon(icon);
		thumbnail.setBorder(borderImage);
		metaLabel.setText(meta);
	}

	private class RankController extends MouseInputAdapter implements MouseListener{
		@Override
		public void mouseClicked (MouseEvent e) {
			JLabel source = (JLabel) e.getSource();

			if (star1.equals(source)) {
				if (star1.getText().equals(GlobalConstants.STAR)) {
					star1.setText(GlobalConstants.FILLED_STAR);
				} else {
					star2.setText(GlobalConstants.STAR);
					star3.setText(GlobalConstants.STAR);
					star4.setText(GlobalConstants.STAR);
					star5.setText(GlobalConstants.STAR);
				}
				model.rankImage(imageId, 1);
			} else if (star2.equals(source)) {
				if (star2.getText().equals(GlobalConstants.STAR)) {
					star1.setText(GlobalConstants.FILLED_STAR);
					star2.setText(GlobalConstants.FILLED_STAR);
				} else {
					star3.setText(GlobalConstants.STAR);
					star4.setText(GlobalConstants.STAR);
					star5.setText(GlobalConstants.STAR);
				}
				model.rankImage(imageId, 2);
			} else if (star3.equals(source)) {
				if (star3.getText().equals(GlobalConstants.STAR)) {
					star1.setText(GlobalConstants.FILLED_STAR);
					star2.setText(GlobalConstants.FILLED_STAR);
					star3.setText(GlobalConstants.FILLED_STAR);
				} else {
					star4.setText(GlobalConstants.STAR);
					star5.setText(GlobalConstants.STAR);
				}
				model.rankImage(imageId, 3);
			} else if (star4.equals(source)) {
				if (star4.getText().equals(GlobalConstants.STAR)) {
					star1.setText(GlobalConstants.FILLED_STAR);
					star2.setText(GlobalConstants.FILLED_STAR);
					star3.setText(GlobalConstants.FILLED_STAR);
					star4.setText(GlobalConstants.FILLED_STAR);
				} else {
					star5.setText(GlobalConstants.STAR);
				}
				model.rankImage(imageId, 4);
			} else if (star5.equals(source)) {
				if (star5.getText().equals(GlobalConstants.STAR)) {
					star1.setText(GlobalConstants.FILLED_STAR);
					star2.setText(GlobalConstants.FILLED_STAR);
					star3.setText(GlobalConstants.FILLED_STAR);
					star4.setText(GlobalConstants.FILLED_STAR);
					star5.setText(GlobalConstants.FILLED_STAR);

					model.rankImage(imageId, 5);
				} else {
					star1.setText(GlobalConstants.STAR);
					star2.setText(GlobalConstants.STAR);
					star3.setText(GlobalConstants.STAR);
					star4.setText(GlobalConstants.STAR);
					star5.setText(GlobalConstants.STAR);


					model.rankImage(imageId, 0);
				}
			}
		}
	}


}