/*
 * Copyright (c) 2005-2010 Substance Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of Substance Kirill Grouchnikov nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.pushingpixels.substance.internal.utils;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

import org.pushingpixels.lafwidget.LafWidgetUtilities;
import org.pushingpixels.substance.api.*;
import org.pushingpixels.substance.internal.animation.StateTransitionTracker;
import org.pushingpixels.substance.internal.animation.TransitionAwareUI;
import org.pushingpixels.substance.internal.animation.StateTransitionTracker.ModelStateInfo;
import org.pushingpixels.substance.internal.painter.BackgroundPaintingUtils;
import org.pushingpixels.substance.internal.ui.SubstanceSplitPaneUI;
import org.pushingpixels.substance.internal.utils.icon.TransitionAwareIcon;

/**
 * Split pane divider in <code>Substance</code> look and feel.
 * 
 * @author Kirill Grouchnikov
 */
public class SubstanceSplitPaneDivider extends BasicSplitPaneDivider implements
		TransitionAwareUI {
	/**
	 * Listener for transition animations.
	 */
	private RolloverControlListener substanceRolloverListener;

	protected StateTransitionTracker stateTransitionTracker;

	/**
	 * Listener on property change events.
	 */
	private PropertyChangeListener substancePropertyChangeListener;
	/**
	 * Surrogate button model for tracking the thumb transitions.
	 */
	private ButtonModel gripModel;

	/**
	 * Simple constructor.
	 * 
	 * @param ui
	 *            Associated UI.
	 */
	public SubstanceSplitPaneDivider(SubstanceSplitPaneUI ui) {
		super(ui);
		this.setLayout(new SubstanceDividerLayout());
	}

	@Override
	public void setBasicSplitPaneUI(BasicSplitPaneUI newUI) {
		if (this.splitPane != null) {
			// fix for defect 358 - multiple listeners were installed
			// on the same split pane
			this.uninstall();
		}

		if (newUI != null) {
			// installing
			this.splitPane = newUI.getSplitPane();

			this.gripModel = new DefaultButtonModel();
			this.gripModel.setArmed(false);
			this.gripModel.setSelected(false);
			this.gripModel.setPressed(false);
			this.gripModel.setRollover(false);
			this.gripModel.setEnabled(this.splitPane.isEnabled());

			this.stateTransitionTracker = new StateTransitionTracker(
					this.splitPane, this.gripModel);

			// fix for defect 109 - memory leak on changing skin
			this.substanceRolloverListener = new RolloverControlListener(this,
					this.gripModel);
			this.addMouseListener(this.substanceRolloverListener);
			this.addMouseMotionListener(this.substanceRolloverListener);

			this.substancePropertyChangeListener = new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					if ("enabled".equals(evt.getPropertyName())) {
						boolean isEnabled = splitPane.isEnabled();
						gripModel.setEnabled(isEnabled);
						if (leftButton != null)
							leftButton.setEnabled(isEnabled);
						if (rightButton != null)
							rightButton.setEnabled(isEnabled);
						setEnabled(isEnabled);
					}
				}
			};
			// System.out.println("Registering " + this.hashCode() + ":"
			// + this.substancePropertyChangeListener.hashCode() + " on "
			// + this.splitPane.hashCode());
			this.splitPane
					.addPropertyChangeListener(this.substancePropertyChangeListener);

			this.stateTransitionTracker.registerModelListeners();
		} else {
			uninstall();
		}
		super.setBasicSplitPaneUI(newUI);
	}

	/**
	 * Uninstalls this divider.
	 */
	private void uninstall() {
		// uninstalling
		// fix for defect 109 - memory leak on changing skin
		this.removeMouseListener(this.substanceRolloverListener);
		this.removeMouseMotionListener(this.substanceRolloverListener);
		this.substanceRolloverListener = null;

		if (this.substancePropertyChangeListener != null) {
			// System.out.println("Unregistering " + this.hashCode() + ":"
			// + this.substancePropertyChangeListener.hashCode()
			// + " from " + this.splitPane.hashCode());
			this.splitPane
					.removePropertyChangeListener(this.substancePropertyChangeListener);
			this.substancePropertyChangeListener = null;
		}

		this.stateTransitionTracker.unregisterModelListeners();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Component#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		if (SubstanceCoreUtilities.hasFlatAppearance(this.splitPane, true)) {
			BackgroundPaintingUtils.updateIfOpaque(g, this.splitPane);
		}

		Graphics2D graphics = (Graphics2D) g.create();

		ModelStateInfo modelStateInfo = this.stateTransitionTracker
				.getModelStateInfo();
		ComponentState currState = modelStateInfo.getCurrModelState();
		Map<ComponentState, StateTransitionTracker.StateContributionInfo> activeStates = modelStateInfo
				.getStateContributionMap();

		float alpha = SubstanceColorSchemeUtilities.getAlpha(this.splitPane,
				currState);

		// compute the grip handle dimension
		int minSizeForGripPresence = SubstanceSizeUtils.getAdjustedSize(
				SubstanceSizeUtils.getComponentFontSize(this), 30, 1, 2, false);
		int maxGripSize = SubstanceSizeUtils.getAdjustedSize(SubstanceSizeUtils
				.getComponentFontSize(this), 40, 1, 3, false);
		if (this.splitPane.getOrientation() == JSplitPane.HORIZONTAL_SPLIT) {
			int thumbHeight = this.getHeight();
			if (thumbHeight >= minSizeForGripPresence) {
				int gripHeight = thumbHeight / 4;
				if (gripHeight > maxGripSize)
					gripHeight = maxGripSize;

				int thumbWidth = this.getWidth();

				int gripX = 0;
				int gripY = (thumbHeight - gripHeight) / 2;

				// draw the grip bumps
				for (Map.Entry<ComponentState, StateTransitionTracker.StateContributionInfo> activeEntry : activeStates
						.entrySet()) {
					float contribution = activeEntry.getValue()
							.getContribution();
					if (contribution == 0.0f)
						continue;

					ComponentState activeState = activeEntry.getKey();
					graphics.setComposite(LafWidgetUtilities.getAlphaComposite(
							this.splitPane, alpha * contribution, g));
					SubstanceImageCreator.paintSplitDividerBumpImage(graphics,
							this, gripX, gripY, thumbWidth, gripHeight, false,
							SubstanceColorSchemeUtilities.getColorScheme(this,
									ColorSchemeAssociationKind.MARK,
									activeState));
				}
			}
		} else {
			int thumbWidth = this.getWidth();
			if (thumbWidth >= minSizeForGripPresence) {
				int gripWidth = thumbWidth / 4;
				if (gripWidth > maxGripSize)
					gripWidth = maxGripSize;

				int thumbHeight = this.getHeight();
				// int gripHeight = thumbHeight * 2 / 3;

				int gripX = (thumbWidth - gripWidth) / 2;
				int gripY = 1;

				// draw the grip bumps
				for (Map.Entry<ComponentState, StateTransitionTracker.StateContributionInfo> activeEntry : activeStates
						.entrySet()) {
					float contribution = activeEntry.getValue()
							.getContribution();
					if (contribution == 0.0f)
						continue;

					ComponentState activeState = activeEntry.getKey();
					graphics.setComposite(LafWidgetUtilities.getAlphaComposite(
							this.splitPane, alpha * contribution, g));
					SubstanceImageCreator.paintSplitDividerBumpImage(graphics,
							this, gripX, gripY, gripWidth, thumbHeight, true,
							SubstanceColorSchemeUtilities.getColorScheme(this,
									ColorSchemeAssociationKind.MARK,
									activeState));
				}
			}
		}

		graphics.dispose();

		super.paint(g);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.plaf.basic.BasicSplitPaneDivider#createLeftOneTouchButton()
	 */
	@Override
	protected JButton createLeftOneTouchButton() {
		JButton oneTouchButton = new JButton() {
			// Don't want the button to participate in focus traversable.
			@Override
			public boolean isFocusable() {
				return false;
			}
		};
		Icon verticalSplit = new TransitionAwareIcon(oneTouchButton,
				new TransitionAwareIcon.Delegate() {
					public Icon getColorSchemeIcon(SubstanceColorScheme scheme) {
						int fontSize = SubstanceSizeUtils
								.getComponentFontSize(splitPane);
						return SubstanceImageCreator.getArrowIcon(
								SubstanceSizeUtils
										.getSplitPaneArrowIconWidth(fontSize),
								SubstanceSizeUtils
										.getSplitPaneArrowIconHeight(fontSize),
								SubstanceSizeUtils
										.getArrowStrokeWidth(fontSize),
								SwingConstants.NORTH, scheme);
					}
				}, "substance.splitPane.left.vertical");
		Icon horizontalSplit = new TransitionAwareIcon(oneTouchButton,
				new TransitionAwareIcon.Delegate() {
					public Icon getColorSchemeIcon(SubstanceColorScheme scheme) {
						int fontSize = SubstanceSizeUtils
								.getComponentFontSize(splitPane);
						return SubstanceImageCreator.getArrowIcon(
								SubstanceSizeUtils
										.getSplitPaneArrowIconWidth(fontSize),
								SubstanceSizeUtils
										.getSplitPaneArrowIconHeight(fontSize),
								SubstanceSizeUtils
										.getArrowStrokeWidth(fontSize),
								SwingConstants.WEST, scheme);
					}
				}, "substance.splitPane.left.horizontal");
		oneTouchButton
				.setIcon(this.splitPane.getOrientation() == JSplitPane.VERTICAL_SPLIT ? verticalSplit
						: horizontalSplit);

		oneTouchButton.putClientProperty(
				SubstanceLookAndFeel.BUTTON_PAINT_NEVER_PROPERTY, Boolean.TRUE);
		// fix for issue 281 - set empty border so that the arrow
		// icon is not cropped
		oneTouchButton.setBorder(new EmptyBorder(0, 0, 0, 0));

		oneTouchButton.setRequestFocusEnabled(false);
		oneTouchButton
				.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		oneTouchButton.setFocusPainted(false);
		oneTouchButton.setBorderPainted(false);
		return oneTouchButton;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.plaf.basic.BasicSplitPaneDivider#createRightOneTouchButton()
	 */
	@Override
	protected JButton createRightOneTouchButton() {
		JButton oneTouchButton = new JButton() {
			// Don't want the button to participate in focus traversable.
			@Override
			public boolean isFocusable() {
				return false;
			}
		};
		Icon verticalSplit = new TransitionAwareIcon(oneTouchButton,
				new TransitionAwareIcon.Delegate() {
					public Icon getColorSchemeIcon(SubstanceColorScheme scheme) {
						int fontSize = SubstanceSizeUtils
								.getComponentFontSize(splitPane);
						return SubstanceImageCreator.getArrowIcon(
								SubstanceSizeUtils
										.getSplitPaneArrowIconWidth(fontSize),
								SubstanceSizeUtils
										.getSplitPaneArrowIconHeight(fontSize),
								SubstanceSizeUtils
										.getArrowStrokeWidth(fontSize),
								SwingConstants.SOUTH, scheme);
					}
				}, "substance.splitPane.right.vertical");
		Icon horizontalSplit = new TransitionAwareIcon(oneTouchButton,
				new TransitionAwareIcon.Delegate() {
					public Icon getColorSchemeIcon(SubstanceColorScheme scheme) {
						int fontSize = SubstanceSizeUtils
								.getComponentFontSize(splitPane);
						return SubstanceImageCreator.getArrowIcon(
								SubstanceSizeUtils
										.getSplitPaneArrowIconWidth(fontSize),
								SubstanceSizeUtils
										.getSplitPaneArrowIconHeight(fontSize),
								SubstanceSizeUtils
										.getArrowStrokeWidth(fontSize),
								SwingConstants.EAST, scheme);
					}
				}, "substance.splitPane.right.horizontal");
		oneTouchButton
				.setIcon(this.splitPane.getOrientation() == JSplitPane.VERTICAL_SPLIT ? verticalSplit
						: horizontalSplit);

		oneTouchButton.putClientProperty(
				SubstanceLookAndFeel.BUTTON_PAINT_NEVER_PROPERTY, Boolean.TRUE);
		// fix for issue 281 - set empty border so that the arrow
		// icon is not cropped
		oneTouchButton.setBorder(new EmptyBorder(0, 0, 0, 0));

		oneTouchButton
				.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		oneTouchButton.setFocusPainted(false);
		oneTouchButton.setBorderPainted(false);
		oneTouchButton.setRequestFocusEnabled(false);
		// b.setOpaque(false);
		return oneTouchButton;
	}

	/**
	 * Updates the one-touch buttons.
	 * 
	 * @param orientation
	 *            Split pane orientation.
	 */
	public void updateOneTouchButtons(int orientation) {
		if (orientation == JSplitPane.VERTICAL_SPLIT) {
			if (this.leftButton != null) {
				this.leftButton.setIcon(new TransitionAwareIcon(
						this.leftButton, new TransitionAwareIcon.Delegate() {
							public Icon getColorSchemeIcon(
									SubstanceColorScheme scheme) {
								int fontSize = SubstanceSizeUtils
										.getComponentFontSize(splitPane);
								return SubstanceImageCreator
										.getArrowIcon(
												SubstanceSizeUtils
														.getSplitPaneArrowIconWidth(fontSize),
												SubstanceSizeUtils
														.getSplitPaneArrowIconHeight(fontSize),
												SubstanceSizeUtils
														.getArrowStrokeWidth(fontSize),
												SwingConstants.NORTH, scheme);
							}
						}, "substance.splitPane.left.vertical"));
			}
			if (this.rightButton != null) {
				this.rightButton.setIcon(new TransitionAwareIcon(
						this.rightButton, new TransitionAwareIcon.Delegate() {
							public Icon getColorSchemeIcon(
									SubstanceColorScheme scheme) {
								int fontSize = SubstanceSizeUtils
										.getComponentFontSize(splitPane);
								return SubstanceImageCreator
										.getArrowIcon(
												SubstanceSizeUtils
														.getSplitPaneArrowIconWidth(fontSize),
												SubstanceSizeUtils
														.getSplitPaneArrowIconHeight(fontSize),
												SubstanceSizeUtils
														.getArrowStrokeWidth(fontSize),
												SwingConstants.SOUTH, scheme);
							}
						}, "substance.splitPane.right.vertical"));
			}
		} else {
			if (this.leftButton != null) {
				this.leftButton.setIcon(new TransitionAwareIcon(
						this.leftButton, new TransitionAwareIcon.Delegate() {
							public Icon getColorSchemeIcon(
									SubstanceColorScheme scheme) {
								int fontSize = SubstanceSizeUtils
										.getComponentFontSize(splitPane);
								return SubstanceImageCreator
										.getArrowIcon(
												SubstanceSizeUtils
														.getSplitPaneArrowIconWidth(fontSize),
												SubstanceSizeUtils
														.getSplitPaneArrowIconHeight(fontSize),
												SubstanceSizeUtils
														.getArrowStrokeWidth(fontSize),
												SwingConstants.WEST, scheme);
							}
						}, "substance.splitPane.left.horizontal"));
			}
			if (this.rightButton != null) {
				this.rightButton.setIcon(new TransitionAwareIcon(
						this.rightButton, new TransitionAwareIcon.Delegate() {
							public Icon getColorSchemeIcon(
									SubstanceColorScheme scheme) {
								int fontSize = SubstanceSizeUtils
										.getComponentFontSize(splitPane);
								return SubstanceImageCreator
										.getArrowIcon(
												SubstanceSizeUtils
														.getSplitPaneArrowIconWidth(fontSize),
												SubstanceSizeUtils
														.getSplitPaneArrowIconHeight(fontSize),
												SubstanceSizeUtils
														.getArrowStrokeWidth(fontSize),
												SwingConstants.EAST, scheme);
							}
						}, "substance.splitPane.right.horizontal"));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pushingpixels.substance.utils.Trackable#isInside(java.awt.event.
	 * MouseEvent)
	 */
	public boolean isInside(MouseEvent me) {
		// entire area is sensitive
		return true;
	}

	@Override
	public StateTransitionTracker getTransitionTracker() {
		return this.stateTransitionTracker;
	}

	/**
	 * Layout manager for the split pane divider.
	 * 
	 * @author Kirill Grouchnikov
	 */
	protected class SubstanceDividerLayout extends DividerLayout {
		@Override
		public void layoutContainer(Container c) {
			if (leftButton != null && rightButton != null
					&& c == SubstanceSplitPaneDivider.this) {
				if (splitPane.isOneTouchExpandable()) {
					Insets insets = getInsets();

					if (orientation == JSplitPane.VERTICAL_SPLIT) {
						int extraX = (insets != null) ? insets.left : 0;
						int blockSize = getHeight();

						if (insets != null) {
							blockSize -= (insets.top + insets.bottom);
							blockSize = Math.max(blockSize, 0);
						}

						int y = (c.getSize().height - blockSize) / 2;

						int offset = SubstanceSizeUtils
								.getSplitPaneButtonOffset(SubstanceSizeUtils
										.getComponentFontSize(splitPane));
						leftButton.setBounds(extraX + offset, y, leftButton
								.getPreferredSize().width * 2 / 3, blockSize);
						rightButton.setBounds(leftButton.getX()
								+ leftButton.getWidth(), y, rightButton
								.getPreferredSize().width * 2 / 3, blockSize);
					} else {
						int extraY = (insets != null) ? insets.top : 0;
						int blockSize = getWidth();

						if (insets != null) {
							blockSize -= (insets.left + insets.right);
							blockSize = Math.max(blockSize, 0);
						}

						int x = (c.getSize().width - blockSize) / 2;

						int offset = SubstanceSizeUtils
								.getSplitPaneButtonOffset(SubstanceSizeUtils
										.getComponentFontSize(splitPane));
						leftButton.setBounds(x, extraY + offset, blockSize,
								leftButton.getPreferredSize().height * 2 / 3);
						rightButton.setBounds(x, leftButton.getY()
								+ leftButton.getHeight(), blockSize, leftButton
								.getPreferredSize().height * 2 / 3);
					}
				} else {
					leftButton.setBounds(-5, -5, 1, 1);
					rightButton.setBounds(-5, -5, 1, 1);
				}
			}
		}
	}
}
