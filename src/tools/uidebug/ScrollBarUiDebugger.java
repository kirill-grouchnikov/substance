package tools.uidebug;

import java.awt.event.*;

import javax.swing.*;

import org.pushingpixels.lafwidget.LafWidgetAdapter;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.SubstanceConstants.ScrollPaneButtonPolicyKind;

public class ScrollBarUiDebugger extends LafWidgetAdapter<JScrollBar> {
	protected MouseListener substanceDebugUiListener;

	@Override
	public boolean requiresCustomLafSupport() {
		return false;
	}

	@Override
	public void installListeners() {

		this.substanceDebugUiListener = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				this.process(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				this.process(e);
			}

			protected void process(MouseEvent e) {
				if (e.isPopupTrigger()) {
					JPopupMenu popup = new JPopupMenu();
					JMenuItem policyNone = new JMenuItem("Empty policy");
					policyNone.addActionListener(new PolicyChanger(
							ScrollPaneButtonPolicyKind.NONE));
					popup.add(policyNone);
					JMenuItem policyOpposite = new JMenuItem("Opposite policy");
					policyOpposite.addActionListener(new PolicyChanger(
							ScrollPaneButtonPolicyKind.OPPOSITE));
					popup.add(policyOpposite);
					JMenuItem policyAdjacent = new JMenuItem("Adjacent policy");
					policyAdjacent.addActionListener(new PolicyChanger(
							ScrollPaneButtonPolicyKind.ADJACENT));
					popup.add(policyAdjacent);
					JMenuItem policyMultiple = new JMenuItem("Multiple policy");
					policyMultiple.addActionListener(new PolicyChanger(
							ScrollPaneButtonPolicyKind.MULTIPLE));
					popup.add(policyMultiple);
					JMenuItem policyMultipleBoth = new JMenuItem(
							"Multiple both policy");
					policyMultipleBoth.addActionListener(new PolicyChanger(
							ScrollPaneButtonPolicyKind.MULTIPLE_BOTH));
					popup.add(policyMultipleBoth);
					popup.show(jcomp, e.getX(), e.getY());
				}
			}
		};
		this.jcomp.addMouseListener(this.substanceDebugUiListener);
	}

	@Override
	public void uninstallListeners() {
		if (this.substanceDebugUiListener != null) {
			this.jcomp.removeMouseListener(this.substanceDebugUiListener);
			this.substanceDebugUiListener = null;
		}

	}

	/**
	 * Listener on policy change menu items in debug UI mode.
	 * 
	 * @author Kirill Grouchnikov
	 */
	protected class PolicyChanger implements ActionListener {
		/**
		 * Policy to set.
		 */
		protected ScrollPaneButtonPolicyKind newPolicy;

		/**
		 * Creates a new policy change listener.
		 * 
		 * @param newPolicy
		 *            Policy to set.
		 */
		public PolicyChanger(ScrollPaneButtonPolicyKind newPolicy) {
			super();
			this.newPolicy = newPolicy;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					((JScrollPane) jcomp.getParent()).putClientProperty(
							SubstanceLookAndFeel.SCROLL_PANE_BUTTONS_POLICY,
							PolicyChanger.this.newPolicy);
					jcomp.getParent().doLayout();
					jcomp.getParent().repaint();
				}
			});
		}
	}
}
