package Views;

import Model.Model;
import Resources.GlobalConstants;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

public class FilterView extends JPanel implements Observer {
	// Set the model
	Model model;
	JLabel applicationName;
	JButton star1, star2, star3, star4, star5;
	JPanel rankPanel;
	Border border;
	RankingController controller;

	public FilterView(Model _model) {
		model = _model;
		controller = new RankingController();
		applicationName = new JLabel(GlobalConstants.APPLICATION_NAME);
		rankPanel = new JPanel();
		star1 = new JButton(GlobalConstants.STAR);
		star2 = new JButton(GlobalConstants.STAR);
		star3 = new JButton(GlobalConstants.STAR);
		star4 = new JButton(GlobalConstants.STAR);
		star5 = new JButton(GlobalConstants.STAR);

		// Add component controllers
		star1.addMouseListener(controller);
		star2.addMouseListener(controller);
		star3.addMouseListener(controller);
		star4.addMouseListener(controller);
		star5.addMouseListener(controller);

		applicationName.setFont(new Font("Courier New", Font.BOLD, GlobalConstants.TITLE_FONT_SIZE));
		border = new EmptyBorder(GlobalConstants.BORDER_PADDING,
			GlobalConstants.BORDER_PADDING, GlobalConstants.BORDER_PADDING, GlobalConstants.BORDER_PADDING);

		// Design the stars
		rankPanel.setLayout(new GridLayout(0,5));
		createStar(star1);
		createStar(star2);
		createStar(star3);
		createStar(star4);
		createStar(star5);

		rankPanel.add(star1);
		rankPanel.add(star2);
		rankPanel.add(star3);
		rankPanel.add(star4);
		rankPanel.add(star5);
		rankPanel.setBackground(Color.WHITE);
		super.setBackground(Color.WHITE);
		super.setBorder(border);
		super.setLayout(new BorderLayout());
		super.add(applicationName, BorderLayout.WEST);
		super.add(rankPanel, BorderLayout.EAST);

	}

	@Override
	public void update (Observable o, Object arg) {
		if (model.isShowName()) {
			applicationName.setVisible(true);
			this.remove(rankPanel);
			this.add(rankPanel, BorderLayout.EAST);
		} else {
			applicationName.setVisible(false);
			this.remove(rankPanel);
			this.add(rankPanel, BorderLayout.CENTER);
		}
	}

	private void createStar(JButton button) {
		button.setBackground(Color.WHITE);
		button.setOpaque(false); 
		button.setFont(new Font("Plain", Font.BOLD, GlobalConstants.STAR_SIZE));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
	}

	private class RankingController extends MouseInputAdapter {
		public RankingController () {

		}

		@Override
		public void mouseEntered (MouseEvent e) {
			JButton source = (JButton) e.getSource();
			star1.setText(GlobalConstants.FILLED_STAR);

			if (star2.equals(source) || star3.equals(source) || star4.equals(source) || star5.equals(source)) {
				star2.setText(GlobalConstants.FILLED_STAR);
			}

			if (star3.equals(source) || star4.equals(source) || star5.equals(source)) {
				star3.setText(GlobalConstants.FILLED_STAR);
			}

			if (star4.equals(source) || star5.equals(source)) {
				star4.setText(GlobalConstants.FILLED_STAR);
			}

			if (star5.equals(source)) {
				star5.setText(GlobalConstants.FILLED_STAR);
			}
		}

		@Override
		public void mouseExited (MouseEvent e) {
			star1.setText(GlobalConstants.STAR);
			star2.setText(GlobalConstants.STAR);
			star3.setText(GlobalConstants.STAR);
			star4.setText(GlobalConstants.STAR);
			star5.setText(GlobalConstants.STAR);
		}
	}
}