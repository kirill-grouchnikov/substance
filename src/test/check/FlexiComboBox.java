package test.check;

import java.awt.Color;
import java.awt.Component;

import javax.swing.*;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.renderers.SubstanceDefaultComboBoxRenderer;

public abstract class FlexiComboBox<T> extends JComboBox {

	public FlexiComboBox(T... items) {
		super(items);
	}

	@Override
	public void updateUI() {
		if (SubstanceLookAndFeel.isCurrentLookAndFeel()) {
			setRenderer(new SubstanceDefaultComboBoxRenderer(this) {
				@Override
				public Component getListCellRendererComponent(JList list,
						Object value, int index, boolean isSelected,
						boolean cellHasFocus) {
					Component result = super.getListCellRendererComponent(list,
							getCaption((T) value), index, isSelected,
							cellHasFocus);
					Color color = getItemColor((T) value);
					if (color != null)
						result.setBackground(color);
					if (result instanceof JLabel) {
						((JLabel) result).setIcon(getItemIcon((T) value));
					}
					return result;
				}
			});
		} else {
			setRenderer(new DefaultListCellRenderer() {
				@Override
				public Component getListCellRendererComponent(JList list,
						Object value, int index, boolean isSelected,
						boolean cellHasFocus) {
					Component result = super.getListCellRendererComponent(list,
							getCaption((T) value), index, isSelected,
							cellHasFocus);
					Color color = getItemColor((T) value);
					if (color != null)
						result.setBackground(color);
					if (result instanceof JLabel) {
						((JLabel) result).setIcon(getItemIcon((T) value));
					}
					return result;
				}
			});
		}
		super.updateUI();
	}

	public abstract String getCaption(T item);

	public Color getItemColor(T item) {
		return null;
	}

	public Icon getItemIcon(T item) {
		return null;
	}

}
