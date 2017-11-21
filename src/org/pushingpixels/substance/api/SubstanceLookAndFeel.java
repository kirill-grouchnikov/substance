/*
 * Copyright (c) 2005-2017 Substance Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.substance.api;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.KeyboardFocusManager;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicLookAndFeel;

import org.pushingpixels.substance.api.SubstanceSlices.MenuGutterFillKind;
import org.pushingpixels.substance.api.colorscheme.SubstanceColorScheme;
import org.pushingpixels.substance.api.combo.ComboPopupPrototypeCallback;
import org.pushingpixels.substance.api.hidpi.HiDpiAwareIcon;
import org.pushingpixels.substance.api.shaper.ClassicButtonShaper;
import org.pushingpixels.substance.api.shaper.StandardButtonShaper;
import org.pushingpixels.substance.api.shaper.SubstanceButtonShaper;
import org.pushingpixels.substance.api.tabbed.TabCloseCallback;
import org.pushingpixels.substance.internal.SubstancePluginRepository;
import org.pushingpixels.substance.internal.contrib.intellij.UIUtil;
import org.pushingpixels.substance.internal.contrib.jgoodies.looks.common.ShadowPopupFactory;
import org.pushingpixels.substance.internal.utils.LazyResettableHashMap;
import org.pushingpixels.substance.internal.utils.MemoryAnalyzer;
import org.pushingpixels.substance.internal.utils.SubstanceColorSchemeUtilities;
import org.pushingpixels.substance.internal.utils.SubstanceCoreUtilities;
import org.pushingpixels.substance.internal.utils.SubstanceImageCreator;
import org.pushingpixels.substance.internal.utils.SubstanceTitlePane;

/**
 * <p>
 * Main class for <b>Substance </b> look and feel. <b>All</b> static methods in
 * this class should be called when Substance is the currently set look and feel
 * unless explicitly stated otherwise.
 * </p>
 * 
 * <p>
 * Since version 5.0 this class is abstract. There are three options to use
 * Substance:
 * </p>
 * 
 * <ul>
 * <li>Use one of the core skin-based look-and-feels in the
 * <code>org.pushingpixels.substance.api.skin</code> package.</li>
 * <li>Extend this class and pass a skin instance to the
 * {@link SubstanceLookAndFeel#SubstanceLookAndFeel(SubstanceSkin)} constructor.
 * </li>
 * <li>Call {@link SubstanceCortex.GlobalScope#setSkin(String)} or
 * {@link SubstanceCortex.GlobalScope#setSkin(SubstanceSkin)} static methods. These
 * methods do not require Substance to be the current look-and-feel.</li>
 * </ul>
 * 
 * @author Kirill Grouchnikov
 */
public abstract class SubstanceLookAndFeel extends BasicLookAndFeel {

	/**
	 * Change listener on keyboard focus manager - fix for defect 208.
	 */
	private PropertyChangeListener focusOwnerChangeListener;

	/**
	 * The current keyboard focus manager - fix for defect 208.
	 */
	private KeyboardFocusManager currentKeyboardFocusManager;
	
	/**
	 * Client property name for requesting that watermark should be painted on
	 * the component and its descendants. This property can be set either as
	 * client property on some component or as global property on
	 * {@link UIManager}. The value should be either {@link Boolean#TRUE} or
	 * {@link Boolean#FALSE}.
	 * 
	 * <p>
	 * In order to compute whether the current watermark should be painted on a
	 * given component, its hierarchy is traversed bottom up. The first
	 * component that has this property set defines the watermark visibility. If
	 * neither component nor its ancestors define this property, the global
	 * setting on {@link UIManager} is checked. If there is no global setting,
	 * the watermark is <b>not</b> ignored (it is painted).
	 * </p>
	 * 
	 * <p>
	 * There is special default setting for trees, tables, lists and text
	 * components. These show watermark only when this property is explicitly
	 * set to {@link Boolean#TRUE} on the component itself, one of its ancestors
	 * or the {@link UIManager}.
	 * </p>
	 * 
	 * @since version 5.0
	 */
	public static final String WATERMARK_VISIBLE = "substancelaf.watermark.visible";

	/**
	 * Client property name for ignoring the default (minimum) dimension for a
	 * single button. This property can be set either on the specific button or
	 * as a global setting on {@link UIManager}. The value should be either
	 * {@link Boolean#TRUE} or {@link Boolean#FALSE}.
	 * <p>
	 * Note that {@link SubstanceButtonShaper} implementations are not required
	 * to respect this property. The current implementations of the default
	 * {@link StandardButtonShaper} and {@link ClassicButtonShaper} respect this
	 * property.
	 * </p>
	 * 
	 * <p>
	 * Example of marking a button to ignore minimum dimension settings:
	 * </p>
	 * <code>
	 * JButton button = new JButton("text");<br>
	 * button.putClientProperty(SubstanceLookAndFeel.BUTTON_NO_MIN_SIZE_PROPERTY, <br>
	 * &nbsp;&nbsp;Boolean.TRUE);
	 * </code>
	 * <p>
	 * Example of marking all application buttons to ignore minimum dimension
	 * settings:
	 * </p>
	 * <code>
	 * UIManager.put(SubstanceLookAndFeel.BUTTON_NO_MIN_SIZE_PROPERTY, <br>
	 * &nbsp;&nbsp;Boolean.TRUE);
	 * </code>
	 * 
	 * @since version 2.1
	 */
	public static final String BUTTON_NO_MIN_SIZE_PROPERTY = "substancelaf.buttonnominsize";

	/**
	 * Client property name for specifying that a single button / all
	 * application buttons should not paint the background. This property can be
	 * set on the specific button, its parent or as a global setting on
	 * {@link UIManager}. The value should be either {@link Boolean#TRUE} or
	 * {@link Boolean#FALSE}. Note that unlike the {@link #FLAT_PROPERTY}, a
	 * button marked with this property will <b>never</b> show the background
	 * (will always be painted flat).
	 * 
	 * <p>
	 * Example of marking a button to never paint background:
	 * </p>
	 * <code>
	 * JButton button = new JButton("text");<br>
	 * button.putClientProperty(SubstanceLookAndFeel.BUTTON_PAINT_NEVER_PROPERTY, <br>
	 * &nbsp;&nbsp;Boolean.TRUE);
	 * </code>
	 * 
	 * <p>
	 * Example of marking all application buttons to never paint background:
	 * </p>
	 * <code>
	 * UIManager.put(SubstanceLookAndFeel.BUTTON_PAINT_NEVER_PROPERTY, <br>
	 * &nbsp;&nbsp;Boolean.TRUE);
	 * </code>
	 * 
	 * @since version 2.3
	 * @see #FLAT_PROPERTY
	 */
	public static final String BUTTON_PAINT_NEVER_PROPERTY = "substancelaf.buttonpaintnever";

	/**
	 * Client property name for specifying a straight side for a single button.
	 * This property must be set on the specific button. The value can be:
	 * 
	 * <p>
	 * <ul>
	 * <li>A value in {@link SubstanceSlices.Side} enum.
	 * <li>Set of values in {@link SubstanceSlices.Side} enum.
	 * </ul>
	 * 
	 * <p>
	 * Note that the {@link SubstanceButtonShaper} implementations are not
	 * required to respect this property. The default
	 * {@link StandardButtonShaper} and {@link ClassicButtonShaper} respect this
	 * property.
	 * </p>
	 * 
	 * <p>
	 * Example of marking a button to have straight north side:
	 * </p>
	 * <code>
	 * JButton button = new JButton("text");<br>
	 * button.putClientProperty(SubstanceLookAndFeel.BUTTON_SIDE_PROPERTY,<br>
	 * &nbsp;&nbsp;SubstanceSlices.Side.RIGHT);
	 * </code>
	 * 
	 * @since version 2.1
	 * @see #BUTTON_OPEN_SIDE_PROPERTY
	 */
	public static final String BUTTON_SIDE_PROPERTY = "substancelaf.buttonside";

	/**
	 * Client property name for specifying an open side for a single button.
	 * This property must be set on the specific button. The value can be:
	 * 
	 * <p>
	 * <ul>
	 * <li>A value in {@link SubstanceSlices.Side} enum.
	 * <li>Set of values in {@link SubstanceSlices.Side} enum.
	 * </ul>
	 * </p>
	 * <p>
	 * Example of marking a button to have open top and west sides:
	 * </p>
	 * <code>
	 * JButton button = new JButton("text");<br>
	 * Set<Side> openSides = EnumSet.of(Side.TOP, Side.WEST);<br>
	 * button.putClientProperty(SubstanceLookAndFeel.BUTTON_OPEN_SIDE_PROPERTY, <br>
	 * &nbsp;&nbsp;openSides);
	 * </code>
	 * 
	 * @since version 3.1
	 * @see #BUTTON_SIDE_PROPERTY
	 */
	public static final String BUTTON_OPEN_SIDE_PROPERTY = "substancelaf.buttonopenSide";

	/**
	 * Client property name for specifying the corner radius for buttons.
	 * Currently, this property is respected only on toolbar buttons. This
	 * property can be set on the specific toolbar button, on the specific
	 * toolbar (will hold for all buttons in the toolbar) or as a global setting
	 * on {@link UIManager}. The value should be a positive {@link Float}.
	 * 
	 * <p>
	 * Example of specifying a (toolbar) button to have corner radius of 5
	 * pixels:
	 * </p>
	 * <code>
	 * JButton button = new JButton("text");<br>
	 * button.putClientProperty(SubstanceLookAndFeel.CORNER_RADIUS, <br>
	 * &nbsp;&nbsp;Float.valueOf(5.0f));
	 * </code>
	 * 
	 * <p>
	 * Example of specifying all buttons of a toolbar to have corner radius of 3
	 * pixels:
	 * </p>
	 * <code>
	 * JToolBar toolbar = new JToolBar("toolbar");<br>
	 * toolbar.putClientProperty(SubstanceLookAndFeel.CORNER_RADIUS, <br>
	 * &nbsp;&nbsp;Float.valueOf(3.0f));
	 * </code>
	 * 
	 * <p>
	 * Example of specifying all toolbar buttons to have corner radius of 0
	 * pixels:
	 * </p>
	 * <code>
	 * UIManager.put(SubstanceLookAndFeel.CORNER_RADIUS, Float.valueOf(0.0f));
	 * </code>
	 * 
	 * @since version 3.0
	 */
	public static final String CORNER_RADIUS = "substancelaf.cornerRadius";

	/**
	 * Property name for specifying that the component should be painted flat
	 * (no background / border) when it's inactive. This property should be
	 * specified on a specific component or its parent and must have either
	 * {@link Boolean#TRUE} or {@link Boolean#FALSE} value.
	 * 
	 * <p>
	 * Example how to mark a button to appear flat:
	 * </p>
	 * 
	 * <code>
	 * JButton button = new JButton("text");<br>
	 * button.putClientProperty(SubstanceLookAndFeel.FLAT_PROPERTY, <br>
	 * &nbsp;&nbsp;Boolean.TRUE);
	 * </code>
	 * 
	 * @since version 3.0
	 * @see #BUTTON_PAINT_NEVER_PROPERTY
	 */
	public static final String FLAT_PROPERTY = "substancelaf.componentFlat";

	/**
	 * VM property name for specifying the heap status trace file. The trace
	 * file will contain information on the status of heap. The property value
	 * is used as a filename for tracing the heap status. Example for specifying
	 * the trace file name:
	 * 
	 * <p>
	 * <code>
	 * -Dsubstancelaf.heapStatusTraceFile=C:/temp/myApp.heap.log
	 * </code>
	 * </p>
	 * 
	 * @since version 5.0
	 */
	public static final String HEAP_STATUS_TRACE_FILE = "substancelaf.heapStatusTraceFile";

	/**
	 * Client property name for specifying that contents of a frame, dialog,
	 * internal frame, desktop icon or tab have been modified and not saved. The
	 * property can be set on:
	 * <p>
	 * <ul>
	 * <li>{@link JRootPane} - the <b>close</b> button of the title pane of the
	 * matching frame / dialog will be animated (in case that the frame / dialog
	 * have decorated title pane). In case the root pane belongs to a
	 * {@link JInternalFrame} and that frame is iconified (to a
	 * {@link JInternalFrame.JDesktopIcon}), the close button of the its desktop
	 * icon is animated as well.</li>
	 * <li>{@link JComponent} in a {@link JTabbedPane}. Based on the
	 * {@link #TABBED_PANE_CLOSE_BUTTONS_MODIFIED_ANIMATION} property presence,
	 * either the entire tab or its close button area is animated. In this case,
	 * this property must be set on the tab component itself, <b>not</b> on one
	 * of its child components.</li>
	 * </ul>
	 * </p>
	 * <p>
	 * The animation cycles between red, orange and yellow color schemes. In
	 * most cases (all but tabs not marked with
	 * {@link #TABBED_PANE_CLOSE_BUTTONS_MODIFIED_ANIMATION} property), the
	 * animation will be visible only when the mouse hovers over the close
	 * button of the matching container (frame, dialog, internal frame, desktop
	 * icon, tab). The tooltip of the close button is changed as well to reflect
	 * that the container contents are marked as modified.
	 * </p>
	 * 
	 * <p>
	 * Here is a sample text editing application that illustrates the use of
	 * this property. Once the contents of the text pane are changed, the frame
	 * is marked as modified. The <b>Save</b> button marks the frame as
	 * not-modified. In the real application, the listener on this button will
	 * need to persist the changes as well.
	 * </p>
	 * 
	 * <code>
	 * public class Changer extends JFrame {<br>
	 * &nbsp;&nbsp;public Changer() {<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;super("Changer");<br>
	 * <br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;this.setLayout(new BorderLayout());<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;JTextPane textArea = new JTextPane();<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;this.add(textArea, BorderLayout.CENTER);<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;textArea.getDocument().addDocumentListener(new
	 * DocumentListener() {<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;private void handleChange() {<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;getRootPane().putClientProperty(<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;SubstanceLookAndFeel.WINDOW_MODIFIED,
	 * Boolean.TRUE);<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>
	 * <br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;public void
	 * changedUpdate(DocumentEvent e) {<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;handleChange();<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>
	 * <br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;public void
	 * insertUpdate(DocumentEvent e) {<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;handleChange();<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>
	 * <br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;public void
	 * removeUpdate(DocumentEvent e) {<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;handleChange();<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;});<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;JPanel buttons = new JPanel(new
	 * FlowLayout(FlowLayout.RIGHT));<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;JButton saveButton = new JButton("Save");<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;saveButton.addActionListener(new ActionListener() {<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;public void
	 * actionPerformed(ActionEvent e) {<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;getRootPane().putClientProperty(<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;SubstanceLookAndFeel.WINDOW_MODIFIED,
	 * Boolean.FALSE);<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;});<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;buttons.add(saveButton);<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;this.add(buttons, BorderLayout.SOUTH);<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);<br>
	 * &nbsp;&nbsp;}<br>
	 * <br>
	 * &nbsp;&nbsp;public static void main(String ... args) {<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;try {<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;UIManager.setLookAndFeel(new
	 * SubstanceLookAndFeel());<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;}<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;catch (Exception exc) {}<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;JFrame.setDefaultLookAndFeelDecorated(true);<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;Changer ch = new Changer();<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;ch.setPreferredSize(new Dimension(200, 200));<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;ch.setSize(ch.getPreferredSize());<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;ch.setLocationRelativeTo(null);<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;ch.setVisible(true);<br>
	 * &nbsp;&nbsp;}<br> }
	 * </code>
	 * 
	 * @since version 2.1
	 */
	public final static String WINDOW_MODIFIED = "windowModified";

	/**
	 * Client property name for adding close buttons on tabs. This property can
	 * be specified on a single tab component, on a {@link JTabbedPane} itself
	 * (will hold for all tab components that don't define this property) or on
	 * {@link UIManager}. The value should be either {@link Boolean#TRUE} or
	 * {@link Boolean#FALSE}. By default, the close buttons are not displayed.
	 * 
	 * <p>
	 * Example of setting that all tabs in the application will have close
	 * buttons:
	 * </p>
	 * <code>
	 * UIManager.put(SubstanceLookAndFeel.TABBED_PANE_CLOSE_BUTTONS_PROPERTY, <br>
	 * &nbsp;&nbsp;Boolean.TRUE);
	 * </code>
	 * 
	 * <p>
	 * A more complex example:
	 * </p>
	 * <code>
	 * UIManager.put(SubstanceLookAndFeel.TABBED_PANE_CLOSE_BUTTONS_PROPERTY, <br>
	 * &nbsp;&nbsp;Boolean.TRUE);<br>
	 * JTabbedPane jtpMain = new JTabbedPane();<br>
	 * JTabbedPane jtpSecondary = new JTabbedPane();<br>
	 * jtpSecondary.putClientProperty(SubstanceLookAndFeel.TABBED_PANE_CLOSE_BUTTONS_PROPERTY, <br>
	 * &nbsp;&nbsp;Boolean.FALSE);<br>
	 * JPanel panelSecondary = new JPanel();<br>
	 * jtpMain.addTab(jtpSecondary);<br>
	 * jtpMain.addTab(panelSecondary);<br>
	 * JPanel tab1 = new JPanel();<br>
	 * JPanel tab2 = new JPanel();<br>
	 * tab2.putClientProperty(SubstanceLookAndFeel.TABBED_PANE_CLOSE_BUTTONS_PROPERTY, <br>
	 * &nbsp;&nbsp;Boolean.TRUE);<br>
	 * jtpSecondary.addTab(tab1);<br>
	 * jtpSecondary.addTab(tab2);
	 * </code>
	 * 
	 * <p>
	 * In the example above, the first first-level child (<b>jtpSecondary</b>)
	 * doesn't have the close button (since it overrides the global setting).
	 * The second first-level child tab (<b>panelSecondary</b>) has close button
	 * (from the global <b>UIManager</b> setting). The first second-level tab
	 * doesn't have the close button (setting inherited from the parent
	 * <b>jtpSecondary</b> tab that overrides the global setting). The second
	 * second-level tab has the close button (since its setting overrides the
	 * parent setting).
	 * </p>
	 * 
	 * @since version 2.1
	 * @see #TABBED_PANE_CLOSE_BUTTONS_MODIFIED_ANIMATION
	 * @see #TABBED_PANE_CLOSE_CALLBACK
	 */
	public final static String TABBED_PANE_CLOSE_BUTTONS_PROPERTY = "substancelaf.tabbedpanehasclosebuttons";

	/**
	 * Client property name for specifying that only the close button of a
	 * marked-as-modified tab component should pulsate. This property can be
	 * specified on a single tab component, on a {@link JTabbedPane} itself
	 * (will hold for all tab components that don't define this property) or on
	 * {@link UIManager}. The value should be either {@link Boolean#TRUE} or
	 * {@link Boolean#FALSE}. By default, the animation on modified tabs is on
	 * the entire tab rectangle. Note that this setting is only relevant for
	 * tabs marked with {@link #WINDOW_MODIFIED} property.
	 * 
	 * <p>
	 * Example of setting that all tabs in the application will have modified
	 * animation on close button:
	 * </p>
	 * <code>
	 * UIManager.put(SubstanceLookAndFeel.TABBED_PANE_CLOSE_BUTTONS_MODIFIED_ANIMATION, <br>
	 * &nbsp;&nbsp;Boolean.TRUE);
	 * </code>
	 * 
	 * <p>
	 * A more complex example:
	 * </p>
	 * <code>
	 * UIManager.put(SubstanceLookAndFeel.TABBED_PANE_CLOSE_BUTTONS_MODIFIED_ANIMATION, <br>
	 * &nbsp;&nbsp;Boolean.TRUE);<br>
	 * JTabbedPane jtpMain = new JTabbedPane();<br>
	 * JTabbedPane jtpSecondary = new JTabbedPane();<br>
	 * jtpSecondary.putClientProperty(SubstanceLookAndFeel.TABBED_PANE_CLOSE_BUTTONS_MODIFIED_ANIMATION, <br>
	 * &nbsp;&nbsp;Boolean.FALSE);<br>
	 * JPanel panelSecondary = new JPanel();<br>
	 * jtpMain.addTab(jtpSecondary);<br>
	 * jtpMain.addTab(panelSecondary);<br>
	 * JPanel tab1 = new JPanel();<br>
	 * JPanel tab2 = new JPanel();<br>
	 * tab2.putClientProperty(SubstanceLookAndFeel.TABBED_PANE_CLOSE_BUTTONS_MODIFIED_ANIMATION, <br>
	 * &nbsp;&nbsp;Boolean.TRUE);<br>
	 * jtpSecondary.addTab(tab1);<br>
	 * jtpSecondary.addTab(tab2);
	 * </code>
	 * 
	 * <p>
	 * In the example above, the first first-level child (<b>jtpSecondary</b>)
	 * has the animation on the entire tab (since it overrides the global
	 * setting). The second first-level child tab (<b>panelSecondary</b>) has
	 * animation on the close button (from the global <b>UIManager</b> setting).
	 * The first second-level tab has the animation on the entire tab (setting
	 * inherited from the parent <b>jtpSecondary</b> tab that overrides the
	 * global setting). The second second-level tab has animation on the close
	 * button (since its setting overrides the parent setting).
	 * </p>
	 * 
	 * @since version 2.2
	 * @see #TABBED_PANE_CLOSE_BUTTONS_PROPERTY
	 * @see #TABBED_PANE_CLOSE_CALLBACK
	 */
	public final static String TABBED_PANE_CLOSE_BUTTONS_MODIFIED_ANIMATION = "substancelaf.tabbedpaneclosebuttonsmodifiedanimation";

	/**
	 * Client property name for specifying the callback for deciding on the tab
	 * close type. This property can be specified on a single tab component, on
	 * a {@link JTabbedPane} itself (will hold for all tab components that don't
	 * define this property) or on {@link UIManager}. The value should be an
	 * instance of {@link TabCloseCallback}. Note that this setting is only
	 * relevant for tabs marked with {@link #TABBED_PANE_CLOSE_BUTTONS_PROPERTY}
	 * property.
	 * 
	 * <p>
	 * Example of custom tab close callback set on a tabbed pane:
	 * </p>
	 * <code>
	 * TabCloseCallback closeCallback = new TabCloseCallback() {<br>
	 * &nbsp;&nbsp;public TabCloseKind onAreaClick(JTabbedPane tabbedPane,<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;int tabIndex, MouseEvent mouseEvent) {<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;if (mouseEvent.getButton() != MouseEvent.BUTTON3)<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return TabCloseKind.NONE;<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;if (mouseEvent.isShiftDown()) {<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return TabCloseKind.ALL;<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;}<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;return TabCloseKind.THIS;<br>
	 * &nbsp;&nbsp;}<br>
	 * <br>
	 * &nbsp;&nbsp;public TabCloseKind onCloseButtonClick(JTabbedPane tabbedPane,<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;int tabIndex, MouseEvent mouseEvent) {<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;if (mouseEvent.isAltDown()) {<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return TabCloseKind.ALL_BUT_THIS;<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;}<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;if (mouseEvent.isShiftDown()) {<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return TabCloseKind.ALL;<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;}<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;return TabCloseKind.THIS;<br>
	 * &nbsp;&nbsp;}<br>
	 * <br>
	 * &nbsp;&nbsp;public String getAreaTooltip(JTabbedPane tabbedPane, int tabIndex) {<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;return null;<br>
	 * &nbsp;&nbsp;}<br>
	 * <br>
	 * &nbsp;&nbsp;public String getCloseButtonTooltip(JTabbedPane tabbedPane,<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;int tabIndex) {<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;StringBuffer result = new StringBuffer();<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;result.append("&lt;html&gt;&lt;body&gt;");<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;result.append("Mouse click closes &lt;b&gt;"<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ tabbedPane.getTitleAt(tabIndex) + "&lt;/b&gt; tab");<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;result.append("&lt;br&gt;&lt;b&gt;Alt&lt;/b&gt;-Mouse click closes all tabs but &lt;b&gt;"<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ tabbedPane.getTitleAt(tabIndex) + "&lt;/b&gt; tab");<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;result.append("&lt;br&gt;&lt;b&gt;Shift&lt;/b&gt;-Mouse click closes all tabs");<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;result.append("&lt;/body&gt;&lt;/html&gt;");<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;return result.toString();<br>
	 * &nbsp;&nbsp;}<br>
	 * };<br>
	 * 
	 * JTabbedPane jtp = new JTabbedPane();<br>
	 * jtp.putClientProperty(<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;SubstanceLookAndFeel.TABBED_PANE_CLOSE_CALLBACK, <br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;closeCallback);
	 * </code>
	 * 
	 * @since version 2.3
	 */
	public final static String TABBED_PANE_CLOSE_CALLBACK = "substancelaf.tabbedpanecloseCallback";

	/**
	 * Client property name for specifying the content pane border kind. This
	 * property can be specified either on a single {@link JTabbedPane} or on
	 * {@link UIManager}. The value should be one of
	 * {@link SubstanceSlices.TabContentPaneBorderKind} enum. By default, the
	 * border kind is
	 * {@link SubstanceSlices.TabContentPaneBorderKind#DOUBLE_PLACEMENT}.
	 * 
	 * <p>
	 * Example of setting that all tabbed panes in the application have single
	 * full border (default setting prior to version 4.1):
	 * </p>
	 * <code>
	 * UIManager.put(SubstanceLookAndFeel.TABBED_PANE_CONTENT_BORDER_KIND, <br>
	 * &nbsp;&nbsp;TabContentPaneBorderKind.SINGLE_FULL);
	 * </code>
	 * 
	 * <p>
	 * Example of specifying that the specific tabbed pane has single full
	 * border (default setting prior to version 4.1):
	 * </p>
	 * <code>
	 * JTabbedPane jtpMain = new JTabbedPane();<br>
	 * jtpMain.putClientProperty(SubstanceLookAndFeel.TABBED_PANE_CONTENT_BORDER_KIND, <br>
	 * &nbsp;&nbsp;TabContentPaneBorderKind.SINGLE_FULL);
	 * </code>
	 * 
	 * @since version 4.1
	 */
	public final static String TABBED_PANE_CONTENT_BORDER_KIND = "substancelaf.tabbedPaneContentBorderKind";

	/**
	 * Client property name for specifying combo popup flyout orientation. This
	 * property can be set on either a specific {@link JComboBox} or globally on
	 * {@link UIManager}. The value should be one of the {@link Integer}s below:
	 * 
	 * <p>
	 * <ul>
	 * <li>The default {@link SwingConstants#SOUTH} - the popup is displayed
	 * directly below the combo aligned to the left.
	 * <li>{@link SwingConstants#NORTH} - the popup is displayed directly above
	 * the combo aligned to the left.
	 * <li>{@link SwingConstants#EAST} - the popup is displayed to the left of
	 * the combo aligned to the top.
	 * <li>{@link SwingConstants#WEST} - the popup is displayed to the right of
	 * the combo aligned to the top.
	 * <li>{@link SwingConstants#CENTER} - the popup is displayed centered
	 * vertically over the combo aligned to the left.
	 * </ul>
	 * </p>
	 * 
	 * <p>
	 * Note that the combo arrow changes in accordance with the combo popup
	 * flyout orientation. Example of setting a combobox with a custom flyout
	 * orientation:
	 * </p>
	 * <code>
	 * JComboBox cb = new JComboBox(<br>
	 * &nbsp;&nbsp;new Object[] { "Ester", "Jordi", "Jordina", "Jorge", "Sergi" });<br>
	 * cb.putClientProperty(SubstanceLookAndFeel.COMBO_BOX_POPUP_FLYOUT_ORIENTATION, <br>
	 * &nbsp;&nbsp;SwingConstants.CENTER);
	 * </code>
	 * 
	 * @since version 2.3
	 * @see #COMBO_POPUP_PROTOTYPE
	 */
	public final static String COMBO_BOX_POPUP_FLYOUT_ORIENTATION = "substancelaf.comboboxpopupFlyoutOrientation";

	/**
	 * Property name for specifying that extra UI elements (such as menu items
	 * in system menu or lock borders) should be shown. This property can be set
	 * as a global setting on {@link UIManager} or as a client property on a
	 * specific component. The value should be either {@link Boolean#TRUE} or
	 * {@link Boolean#FALSE}.
	 * 
	 * <p>
	 * Example of setting this property on {@link UIManager}:
	 * </p>
	 * <code>
	 * UIManager.put(SubstanceLookAndFeel.SHOW_EXTRA_WIDGETS, Boolean.TRUE);
	 * SwingUtilities.updateComponentTree(myFrame);
	 * </code>
	 * 
	 * @since version 5.0
	 */
	public final static String SHOW_EXTRA_WIDGETS = "substancelaf.addWidgets";

	/**
	 * Property name for specifying menu gutter fill kind. Menu gutter is the
	 * part of the menu where checkmarks and icons are painted. The value should
	 * be one of {@link MenuGutterFillKind} enum. This property can be set
	 * globally on the {@link UIManager}. The default value is
	 * {@link MenuGutterFillKind#HARD_FILL}.
	 * 
	 * <p>
	 * Example of setting soft fill kind:
	 * </p>
	 * <code>
	 * UIManager.put(SubstanceLookAndFeel.MENU_GUTTER_FILL_KIND, MenuGutterFillKind.SOFT);
	 * </code>
	 * 
	 * @since version 3.2
	 */
	public final static String MENU_GUTTER_FILL_KIND = "substancelaf.menuGutterFillKind";

	/**
	 * Client property name for specifying the kind of focus indication on
	 * buttons, check boxes and radio buttons. The value should be one of
	 * {@link SubstanceSlices.FocusKind} enum. This property can be set
	 * either on the specific component or as global property on
	 * {@link UIManager}.
	 * 
	 * <p>
	 * In order to compute the kind of focus indication for some component, the
	 * component's hierarchy is traversed bottom up. The first component that
	 * has this property set, defines the focus indication kind. If neither
	 * component nor its ancestors define this property, the global setting on
	 * {@link UIManager} is checked. If there is no global setting, the default
	 * {@link SubstanceSlices.FocusKind#ALL_INNER} is used. Here is an
	 * example to illustrate the above:
	 * </p>
	 * 
	 * <code>
	 * &nbsp;&nbsp;JPanel topPanel = new JPanel();<br>
	 * &nbsp;&nbsp;topPanel.putClientProperty(SubstanceLookAndFeel.FOCUS_KIND, FocusKind.UNDERLINE);<br>
	 * &nbsp;&nbsp;JPanel panel1 = new JPanel();<br>
	 * &nbsp;&nbsp;JButton b1 = new JButton("button1");<br>
	 * &nbsp;&nbsp;b1.putClientProperty(SubstanceLookAndFeel.FOCUS_KIND, FocusKind.TEXT);<br>
	 * &nbsp;&nbsp;JButton b2 = new JButton("button2");<br>
	 * &nbsp;&nbsp;JButton b3 = new JButton("button3");<br>
	 * &nbsp;&nbsp;b3.putClientProperty(SubstanceLookAndFeel.FOCUS_KIND, FocusKind.ALL_INNER);<br>
	 * &nbsp;&nbsp;panel1.add(b1);<br>
	 * &nbsp;&nbsp;panel1.add(b2);<br>
	 * &nbsp;&nbsp;topPanel.add(panel1);<br>
	 * &nbsp;&nbsp;topPanel.add(b3);<br>
	 * </code>
	 * 
	 * <p>
	 * In the code above:
	 * </p>
	 * <ul>
	 * <li>Button <b>b1</b> will have {@link SubstanceSlices.FocusKind#NONE}
	 * focus kind which is set directly on the button.
	 * <li>Button <b>b2</b> will have
	 * {@link SubstanceSlices.FocusKind#UNDERLINE} focus kind which is
	 * inherited from its <b>topPanel</b> parent.
	 * <li>Button <b>b3</b> will have
	 * {@link SubstanceSlices.FocusKind#ALL_INNER} focus kind which is set
	 * directly on the button.
	 * </ul>
	 * 
	 * @since 2.2
	 * @see SubstanceSlices.FocusKind
	 */
	public final static String FOCUS_KIND = "substancelaf.focusKind";

	/**
	 * Property name for specifying the combobox popup prototype display value
	 * which is used to compute the width of the popup at runtime. The property
	 * value should be one of:
	 * 
	 * <p>
	 * <ul>
	 * <li>{@link ComboPopupPrototypeCallback} - will provide
	 * application-specific logic at runtime.
	 * <li>{@link Object} - will point to the prototype entry itself.
	 * </ul>
	 * </p>
	 * 
	 * <p>
	 * This property can be set either on a specific {@link JComboBox} or
	 * globally on {@link UIManager}.
	 * </p>
	 * 
	 * <p>
	 * Here is an example of combo popup prototype set to a model element:
	 * </p>
	 * <code>
	 * 	JComboBox comboProto1 = new JComboBox(new Object[] { "aa", "aaaaa",<br>
	 * 	&nbsp;&nbsp;"aaaaaaaaaa", "this one is the one", "aaaaaaaaaaaaaaaaaaaaa" });<br>
	 * 	comboProto1.setPrototypeDisplayValue("aaaaa");<br>
	 * 	comboProto1.putClientProperty(SubstanceLookAndFeel.COMBO_POPUP_PROTOTYPE,<br>
	 * 	&nbsp;&nbsp;"this one is the one");
	 * </code>
	 * 
	 * <p>
	 * Here is an example of combo popup prototype set to a dynamic callback.
	 * This callback always returns the last model element:
	 * </p>
	 * <code>
	 *  JComboBox comboProto3 = new JComboBox(new Object[] { "aa", "aaaaa",<br>
	 * 	&nbsp;&nbsp;"this is not", "this one is not it",<br>
	 * 	&nbsp;&nbsp;"this one is it that is for the popup" });<br>
	 * 	comboProto3.setPrototypeDisplayValue("aaaaa");<br>
	 * 	comboProto3.putClientProperty(SubstanceLookAndFeel.COMBO_POPUP_PROTOTYPE,<br>
	 * 	&nbsp;&nbsp;new ComboPopupPrototypeCallback() {<br>
	 * 	&nbsp;&nbsp;&nbsp;&nbsp;public Object getPopupPrototypeDisplayValue(JComboBox jc) {<br>
	 * 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return jc.getModel().getElementAt(<br>
	 * 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;jc.getModel().getSize() - 1);<br>
	 * 	&nbsp;&nbsp;&nbsp;&nbsp;}<br>
	 * 	&nbsp;&nbsp;});
	 *  </code>
	 * 
	 * @since version 3.0
	 * @see #COMBO_BOX_POPUP_FLYOUT_ORIENTATION
	 */
	public final static String COMBO_POPUP_PROTOTYPE = "substancelaf.comboPopupPrototype";

	/**
	 * VM property name for specifying the trace file. The trace file will
	 * contain output of the memory analyser which can be used to pinpoint the
	 * memory leaks. The property value is used as a filename for tracing the
	 * memory allocations. Example for specifying the trace file name:
	 * 
	 * <p>
	 * <code>
	 * -Dsubstancelaf.traceFile=C:/temp/myApp.substance.log
	 * </code>
	 * </p>
	 * 
	 * @since version 2.0
	 */
	public final static String TRACE_FILE = "substancelaf.traceFile";

	/**
	 * Client property name for specifying the number of echo characters for
	 * each password character. The value should be an instance of
	 * {@link Integer}, otherwise will be ignored. This property can be set
	 * either on a specific {@link JPasswordField} or globally on
	 * {@link UIManager}.
	 * 
	 * <p>
	 * Example of having all password fields echo 3 characters per each typed
	 * user character:
	 * </p>
	 * <code>
	 * UIManager.put(SubstanceLookAndFeel.PASSWORD_ECHO_PER_CHAR, <br>
	 * &nbsp;&nbsp;new Integer(3));
	 * </code>
	 * 
	 * <p>
	 * Example of having a specific password field echo 2 characters per each
	 * typed user character:
	 * </p>
	 * <code>
	 * JPasswordField jpf = new JPasswordField();<br>
	 * jpf.putClientProperty(SubstanceLookAndFeel.PASSWORD_ECHO_PER_CHAR, <br>
	 * &nbsp;&nbsp;new Integer(2));
	 * </code>
	 * 
	 * @since version 2.2
	 */
	public final static String PASSWORD_ECHO_PER_CHAR = "substancelaf.passwordEchoPerChar";

	/**
	 * <p>
	 * Client property name for specifying that icons on controls such as
	 * buttons, toggle buttons, labels, tabs and menu items should match the
	 * color of the current color scheme when they are in default state. The
	 * control is in default state when it's not pressed, not selected, not
	 * armed and not rolled over. The value should be an instance of
	 * {@link Boolean}. By default, all controls show regular (full-color
	 * original) icons. The value can be set globally on {@link UIManager}.
	 * </p>
	 * 
	 * @since version 3.3
	 */
	public final static String USE_THEMED_DEFAULT_ICONS = "substancelaf.useThemedDefaultIcons";

	/**
	 * <p>
	 * Client property name for specifying the colorization amount applied to
	 * the background and foreground of the current color scheme and the
	 * specific control. By default, when the application does not use any
	 * custom colors, all the controls are painted with the colors of the
	 * current color scheme / skin. The colors coming from the look-and-feel
	 * implement the marker {@link UIResource} interface which allows the UI
	 * delegates to differentiate between application-specific colors which are
	 * not changed, and the LAF-provide colors that are changed on LAF switch.
	 * </p>
	 * 
	 * <p>
	 * This new client property installs the "smart colorization" mode which
	 * uses the colors of the current color scheme and the custom background /
	 * foreground colors (when installed by application) to colorize the
	 * relevant portions of the control. For example, on checkbox the custom
	 * background color will be used to colorize the check box itself, while the
	 * custom foreground color will be applied to the check box text and the
	 * check mark.
	 * </p>
	 * 
	 * <p>
	 * The value of this property specifies the actual colorization amount.
	 * Value of 0.0 results in Substance completely <strong>ignoring</strong>
	 * the custom application background and foreground colors set on the
	 * components - no colorization. Values closer to 1.0 result in almost full
	 * usage of the custom application background and foreground colors set on
	 * the components. Note that in order to maintain the gradients (fill,
	 * border, etc), even value of 1.0 does not result in full custom color
	 * being applied to the relevant visuals of the control.
	 * </p>
	 * 
	 * <p>
	 * This property can be specified globally on {@link UIManager}, applying on
	 * all controls, or on the specific component / container. In the later
	 * case, the value will be applied to the component / container itself and
	 * all its children that do not specify a custom value for this property.
	 * </p>
	 * 
	 * <p>
	 * The default colorization amount (when this property is not set at all) is
	 * 0.5. This means that applications that install custom background /
	 * foreground colors on their UI controls will see them colorized with 50%
	 * "strength", even without setting this property.
	 * </p>
	 * 
	 * <p>
	 * The value should be an instance of {@link Double} in 0.0-1.0 range.
	 * </p>
	 * 
	 * <p>
	 * Example of marking a button to have a custom background color and
	 * colorizing it with 40%:
	 * </p>
	 * <code>
	 * JButton jb = new JButton("sample", myIcon);<br>
	 * jb.setBackground(Color.red);<br>
	 * jb.putClientProperty(SubstanceLookAndFeel.COLORIZATION_FACTOR, <br>
	 * &nbsp;&nbsp;new Double(0.4));
	 * </code>
	 * 
	 * <p>
	 * Note that components in decoration areas registered on the current skin
	 * will ignore the colorization on custom background color. The background
	 * of such components is always painted by the skin's decoration painter to
	 * ensure consistent background painting of the relevant decoration area.
	 * </p>
	 * 
	 * @since version 4.2
	 * @see Component#setBackground(Color)
	 * @see Component#setForeground(Color)
	 */
	public final static String COLORIZATION_FACTOR = "substancelaf.colorizationFactor";

	/**
	 * Property name for specifying outline shaper. This property is used a
	 * client property that can be set on a specific control.
	 * 
	 * <p>
	 * The value must be a {@link SubstanceButtonShaper} object.
	 * </p>
	 * 
	 * <p>
	 * Example of using a {@link SubstanceButtonShaper} object as client
	 * property value:
	 * </p>
	 * <code>
	 * JButton b = new JButton("text");<br>
	 * b.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, <br>
	 * &nbsp;&nbsp;new ClassicButtonShaper());
	 * </code>
	 * 
	 * @since version 2.1
	 */
	public static final String BUTTON_SHAPER_PROPERTY = "substancelaf.buttonShaper";

	/**
	 * Property name for specifying a skin to be used on the specific root pane.
	 * This property can only be installed on a {@link JRootPane} and will
	 * affect all the controls in that root pane. The value must be an instance
	 * of {@link SubstanceSkin}. After setting this property, call
	 * {@link SwingUtilities#updateComponentTreeUI(Component)} on the matching
	 * window.
	 * 
	 * @since version 5.0
	 * @see #getCurrentSkin(Component)
	 */
	public static final String SKIN_PROPERTY = "substancelaf.skin";


	/**
	 * The skin of this look-and-feel instance.
	 */
	protected SubstanceSkin skin;

	/**
	 * The name of this look-and-feel instance.
	 */
	protected String name;

	/**
	 * Creates a new skin-based Substance look-and-feel. 
	 * 
	 * @param skin
	 *            Skin.
	 */
	protected SubstanceLookAndFeel(SubstanceSkin skin) {
		this.skin = skin;
		this.name = "Substance " + skin.getDisplayName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.LookAndFeel#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Substance Look and Feel by Kirill Grouchnikov";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.LookAndFeel#getID()
	 */
	@Override
	public String getID() {
		return this.name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.LookAndFeel#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.LookAndFeel#isNativeLookAndFeel()
	 */
	@Override
	public boolean isNativeLookAndFeel() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.LookAndFeel#isSupportedLookAndFeel()
	 */
	@Override
	public boolean isSupportedLookAndFeel() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.plaf.basic.BasicLookAndFeel#initClassDefaults(javax.swing
	 * .UIDefaults)
	 */
	@Override
	protected void initClassDefaults(UIDefaults table) {
		super.initClassDefaults(table);

		String UI_CLASSNAME_PREFIX = "org.pushingpixels.substance.internal.ui.Substance";
		Object[] uiDefaults = {

		"ButtonUI", UI_CLASSNAME_PREFIX + "ButtonUI",

		"CheckBoxUI", UI_CLASSNAME_PREFIX + "CheckBoxUI",

		"ComboBoxUI", UI_CLASSNAME_PREFIX + "ComboBoxUI",

		"CheckBoxMenuItemUI", UI_CLASSNAME_PREFIX + "CheckBoxMenuItemUI",

		"DesktopIconUI", UI_CLASSNAME_PREFIX + "DesktopIconUI",

		"DesktopPaneUI", UI_CLASSNAME_PREFIX + "DesktopPaneUI",

		"EditorPaneUI",
				UI_CLASSNAME_PREFIX + "EditorPaneUI",

				"FileChooserUI",
				UI_CLASSNAME_PREFIX + "FileChooserUI",

				// "FileChooserUI", "javax.swing.plaf.metal.MetalFileChooserUI",

				"FormattedTextFieldUI",
				UI_CLASSNAME_PREFIX + "FormattedTextFieldUI",

				"InternalFrameUI", UI_CLASSNAME_PREFIX + "InternalFrameUI",

				"LabelUI", UI_CLASSNAME_PREFIX + "LabelUI",

				"ListUI", UI_CLASSNAME_PREFIX + "ListUI",

				"MenuUI", UI_CLASSNAME_PREFIX + "MenuUI",

				"MenuBarUI", UI_CLASSNAME_PREFIX + "MenuBarUI",

				"MenuItemUI", UI_CLASSNAME_PREFIX + "MenuItemUI",

				"OptionPaneUI", UI_CLASSNAME_PREFIX + "OptionPaneUI",

				"PanelUI", UI_CLASSNAME_PREFIX + "PanelUI",

				"PasswordFieldUI", UI_CLASSNAME_PREFIX + "PasswordFieldUI",

				"PopupMenuUI", UI_CLASSNAME_PREFIX + "PopupMenuUI",

				"PopupMenuSeparatorUI",
				UI_CLASSNAME_PREFIX + "PopupMenuSeparatorUI",

				"ProgressBarUI", UI_CLASSNAME_PREFIX + "ProgressBarUI",

				"RadioButtonUI", UI_CLASSNAME_PREFIX + "RadioButtonUI",

				"RadioButtonMenuItemUI",
				UI_CLASSNAME_PREFIX + "RadioButtonMenuItemUI",

				"RootPaneUI", UI_CLASSNAME_PREFIX + "RootPaneUI",

				"ScrollBarUI", UI_CLASSNAME_PREFIX + "ScrollBarUI",

				"ScrollPaneUI", UI_CLASSNAME_PREFIX + "ScrollPaneUI",

				"SeparatorUI", UI_CLASSNAME_PREFIX + "SeparatorUI",

				"SliderUI", UI_CLASSNAME_PREFIX + "SliderUI",

				"SpinnerUI", UI_CLASSNAME_PREFIX + "SpinnerUI",

				"SplitPaneUI", UI_CLASSNAME_PREFIX + "SplitPaneUI",

				"TabbedPaneUI", UI_CLASSNAME_PREFIX + "TabbedPaneUI",

				"TableUI", UI_CLASSNAME_PREFIX + "TableUI",

				"TableHeaderUI", UI_CLASSNAME_PREFIX + "TableHeaderUI",

				"TextAreaUI", UI_CLASSNAME_PREFIX + "TextAreaUI",

				"TextFieldUI", UI_CLASSNAME_PREFIX + "TextFieldUI",

				"TextPaneUI", UI_CLASSNAME_PREFIX + "TextPaneUI",

				"ToggleButtonUI", UI_CLASSNAME_PREFIX + "ToggleButtonUI",

				"ToolBarUI", UI_CLASSNAME_PREFIX + "ToolBarUI",

				"ToolBarSeparatorUI",
				UI_CLASSNAME_PREFIX + "ToolBarSeparatorUI",

				"ToolTipUI", UI_CLASSNAME_PREFIX + "ToolTipUI",

				"TreeUI", UI_CLASSNAME_PREFIX + "TreeUI",

				"ViewportUI", UI_CLASSNAME_PREFIX + "ViewportUI",

		};
		table.putDefaults(uiDefaults);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.plaf.basic.BasicLookAndFeel#initComponentDefaults(javax.swing
	 * .UIDefaults)
	 */
	@Override
	protected void initComponentDefaults(UIDefaults table) {
		super.initComponentDefaults(table);

		SubstanceCortex.GlobalScope.initFontDefaults(table);
		this.skin.addCustomEntriesToTable(table);
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.basic.BasicLookAndFeel#getDefaults()
	 */
	@Override
	public UIDefaults getDefaults() {
		UIDefaults table = super.getDefaults();

		SubstancePluginRepository.getInstance().processAllDefaultsEntriesComponentPlugins(table,
                this.skin);
		return table;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.basic.BasicLookAndFeel#initialize()
	 */
	@Override
	public void initialize() {
		super.initialize();
		ShadowPopupFactory.install();

		SubstanceCortex.GlobalScope.setSkin(this.skin, false);
		//setSkin(this.skin, false);

		// tracer for memory analysis
		String paramTraceFile = SubstanceCoreUtilities
				.getVmParameter(SubstanceLookAndFeel.TRACE_FILE);
		if (paramTraceFile != null) {
			MemoryAnalyzer.commence(1000, paramTraceFile);
			for (SubstanceComponentPlugin plugin : SubstancePluginRepository.getInstance().getComponentPlugins())
				MemoryAnalyzer.enqueueUsage("Has plugin '"
						+ plugin.getClass().getName() + "'");
		}

		// to show heap status panel in title pane?
		String heapStatusPanelParam = SubstanceCoreUtilities
				.getVmParameter(SubstanceLookAndFeel.HEAP_STATUS_TRACE_FILE);
		SubstanceTitlePane.setHeapStatusLogfileName(heapStatusPanelParam);

		// initialize component plugins
		SubstancePluginRepository.getInstance().initializeAllComponentPlugins();

        // fix for defect 208 - tracking changes to focus owner
        // and repainting the default button
        this.focusOwnerChangeListener = (PropertyChangeEvent evt) -> {
            if ("focusOwner".equals(evt.getPropertyName())) {
                Component newFocusOwner = (Component) evt.getNewValue();
                if (newFocusOwner != null) {
                    JRootPane rootPane = SwingUtilities.getRootPane(newFocusOwner);
                    if (rootPane == null)
                        return;
                    JButton defaultButton = rootPane.getDefaultButton();
                    if (defaultButton == null)
                        return;
                    defaultButton.repaint();
                }
            }
            if ("managingFocus".equals(evt.getPropertyName())) {
                if (Boolean.FALSE.equals(evt.getNewValue())) {
                    // new keyboard focus manager has been installed
                    currentKeyboardFocusManager
                            .removePropertyChangeListener(focusOwnerChangeListener);
                    currentKeyboardFocusManager = KeyboardFocusManager
                            .getCurrentKeyboardFocusManager();
                    currentKeyboardFocusManager.addPropertyChangeListener(focusOwnerChangeListener);
                }
            }
        };
		this.currentKeyboardFocusManager = KeyboardFocusManager
				.getCurrentKeyboardFocusManager();
		this.currentKeyboardFocusManager
				.addPropertyChangeListener(this.focusOwnerChangeListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.basic.BasicLookAndFeel#uninitialize()
	 */
	@Override
	public void uninitialize() {
		super.uninitialize();

		SubstanceCortex.GlobalScope.unsetSkin();
//		SubstanceLookAndFeel.currentSkin = null;

		ShadowPopupFactory.uninstall();

		SubstanceCoreUtilities.stopThreads();

		// fix for defect 109 - memory leak on watermarks
		if (this.skin.getWatermark() != null)
			this.skin.getWatermark().dispose();

		// uninitialize component plugins
		SubstancePluginRepository.getInstance().uninitializeAllComponentPlugins();

		// clear caches
		LazyResettableHashMap.reset();

		this.currentKeyboardFocusManager
				.removePropertyChangeListener(this.focusOwnerChangeListener);
		this.focusOwnerChangeListener = null;
		this.currentKeyboardFocusManager = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.LookAndFeel#getSupportsWindowDecorations()
	 */
	@Override
	public boolean getSupportsWindowDecorations() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.LookAndFeel#getDisabledIcon(javax.swing.JComponent,
	 * javax.swing.Icon)
	 */
	@Override
	public Icon getDisabledIcon(JComponent component, Icon icon) {
		if (icon == null)
			return null;
		SubstanceColorScheme colorScheme = SubstanceColorSchemeUtilities
				.getColorScheme(component, ComponentState.DISABLED_UNSELECTED);
		BufferedImage result = SubstanceImageCreator.getColorSchemeImage(
				component, icon, colorScheme, 0.5f);
		float alpha = SubstanceColorSchemeUtilities.getAlpha(component,
				ComponentState.DISABLED_UNSELECTED);
		if (alpha < 1.0f) {
			BufferedImage intermediate = SubstanceCoreUtilities.getBlankImage(
					icon.getIconWidth(), icon.getIconHeight());
			Graphics2D g2d = intermediate.createGraphics();
			g2d.setComposite(AlphaComposite.SrcOver.derive(alpha));
			int scaleFactor = UIUtil.getScaleFactor();
			g2d.drawImage(result, 0, 0, result.getWidth() / scaleFactor, 
					result.getHeight() / scaleFactor, null);
			g2d.dispose();
			result = intermediate;
		}

		return new HiDpiAwareIcon(result);
	}
}
