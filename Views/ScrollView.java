package Views;

import Model.Model;
import Resources.GlobalConstants;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.util.Observable;
import java.util.Observer;

public class ScrollView extends JScrollPane implements Observer {
	// Model
	Model model;
	Border border;
	public ScrollView (Model _model) {
		model = _model;
		border = new EtchedBorder(EtchedBorder.LOWERED);
		super.setPreferredSize(GlobalConstants.FULL_SIZE);
		super.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		super.setBorder(border);
	}

	@Override
	public void update (Observable o, Object arg) {

	}
}