package test.check;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.SubstanceSkin;
import org.pushingpixels.substance.api.renderers.SubstanceDefaultComboBoxRenderer;
import org.pushingpixels.substance.api.skin.SkinInfo;

public class SubstanceSkinComboSelector extends JComboBox {
	public SubstanceSkinComboSelector() {
		// populate the combobox
		super(new ArrayList<SkinInfo>(SubstanceLookAndFeel.getAllSkins()
				.values()).toArray());
		// set the current skin as the selected item
		SubstanceSkin currentSkin = SubstanceLookAndFeel.getCurrentSkin();
		for (SkinInfo skinInfo : SubstanceLookAndFeel.getAllSkins().values()) {
			if (skinInfo.getDisplayName().compareTo(
					currentSkin.getDisplayName()) == 0) {
				this.setSelectedItem(skinInfo);
				break;
			}
		}
		// set custom renderer to show the skin display name
		this.setRenderer(new SubstanceDefaultComboBoxRenderer(this) {
			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				return super.getListCellRendererComponent(list,
						((SkinInfo) value).getDisplayName(), index, isSelected,
						cellHasFocus);
			}
		});
		// add an action listener to change skin based on user selection
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						SubstanceLookAndFeel
								.setSkin(((SkinInfo) SubstanceSkinComboSelector.this
										.getSelectedItem()).getClassName());
					}
				});
			}
		});
	}
}
