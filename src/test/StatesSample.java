package test;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.pushingpixels.substance.api.ColorSchemeAssociationKind;
import org.pushingpixels.substance.api.ComponentState;
import org.pushingpixels.substance.api.DecorationAreaType;
import org.pushingpixels.substance.api.SubstanceConstants;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.OfficeSilver2007Skin;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

public class StatesSample extends JFrame {
	public StatesSample() {
		super("States");

		setIconImage(SubstanceLogo
				.getLogoImage(SubstanceLookAndFeel.getCurrentSkin(
						this.getRootPane())
						.getColorScheme(DecorationAreaType.PRIMARY_TITLE_PANE,
								ColorSchemeAssociationKind.FILL,
								ComponentState.ENABLED)));

		FormLayout layout = new FormLayout("right:pref, 4dlu, fill:pref:grow",
				"");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		builder.setDefaultDialogBorder();

		JButton regular = new JButton("sample");
		builder.append("Regular", regular);

		JButton rollover = new JButton("sample");
		rollover.getModel().setRollover(true);
		builder.append("Rollover", rollover);

		JButton selected = new JButton("sample");
		selected.getModel().setSelected(true);
		builder.append("Selected", selected);

		JButton rolloverSelected = new JButton("sample");
		rolloverSelected.getModel().setRollover(true);
		rolloverSelected.getModel().setSelected(true);
		builder.append("Rollover selected", rolloverSelected);

		JButton pressed = new JButton("sample");
		pressed.getModel().setArmed(true);
		pressed.getModel().setPressed(true);
		builder.append("Pressed", pressed);

		JButton pressedSelected = new JButton("sample");
		pressedSelected.getModel().setArmed(true);
		pressedSelected.getModel().setPressed(true);
		pressedSelected.getModel().setSelected(true);
		builder.append("Pressed selected", pressedSelected);

		this.add(builder.getPanel());

		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				UIManager.put(SubstanceLookAndFeel.FOCUS_KIND,
						SubstanceConstants.FocusKind.NONE);
				SubstanceLookAndFeel.setSkin(new OfficeSilver2007Skin());
				new StatesSample().setVisible(true);
			}
		});
	}

}
