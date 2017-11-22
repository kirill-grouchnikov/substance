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

import java.awt.Component;
import java.awt.Font;
import java.awt.Window;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.JTextComponent;

import org.pushingpixels.substance.api.SubstanceSlices.AnimationFacet;
import org.pushingpixels.substance.api.SubstanceSlices.DecorationAreaType;
import org.pushingpixels.substance.api.SubstanceSlices.LocaleChangeListener;
import org.pushingpixels.substance.api.SubstanceSlices.SubstanceOptionPaneButtonOrder;
import org.pushingpixels.substance.api.SubstanceSlices.SubstanceWidgetType;
import org.pushingpixels.substance.api.font.FontPolicy;
import org.pushingpixels.substance.api.font.FontSet;
import org.pushingpixels.substance.api.font.SubstanceFontUtilities;
import org.pushingpixels.substance.api.iconpack.SubstanceDefaultIconPack;
import org.pushingpixels.substance.api.iconpack.SubstanceIconPack;
import org.pushingpixels.substance.api.painter.preview.DefaultPreviewPainter;
import org.pushingpixels.substance.api.painter.preview.PreviewPainter;
import org.pushingpixels.substance.api.password.PasswordStrengthChecker;
import org.pushingpixels.substance.api.skin.SkinChangeListener;
import org.pushingpixels.substance.api.skin.SkinInfo;
import org.pushingpixels.substance.api.tabbed.BaseTabCloseListener;
import org.pushingpixels.substance.internal.AnimationConfigurationManager;
import org.pushingpixels.substance.internal.SubstancePluginRepository;
import org.pushingpixels.substance.internal.SubstanceSynapse;
import org.pushingpixels.substance.internal.SubstanceWidgetRepository;
import org.pushingpixels.substance.internal.fonts.FontPolicies;
import org.pushingpixels.substance.internal.painter.DecorationPainterUtils;
import org.pushingpixels.substance.internal.ui.SubstanceRootPaneUI;
import org.pushingpixels.substance.internal.utils.LazyResettableHashMap;
import org.pushingpixels.substance.internal.utils.SubstanceCoreUtilities;
import org.pushingpixels.substance.internal.utils.SubstanceSizeUtils;
import org.pushingpixels.substance.internal.utils.SubstanceWidgetManager;
import org.pushingpixels.substance.internal.utils.TabCloseListenerManager;

/**
 * <p>
 * This class is the only officially-supported entry point into configuring the behavior of
 * Substance-powered UIs and for querying the state of such UIs. The API surface of this class is
 * broken into a number of scopes, with every scope applying at the specific granularity level of
 * control:
 * </p>
 * 
 * <ul>
 * <li>{@link GlobalScope} - configuring and querying the global state of the application.</li>
 * <li>{@link WindowScope} - configuring and querying state at the level of the application
 * {@link Window}s.</li>
 * <li>{@link ComponentScope} - configuring and querying state at the level of the application
 * {@link Component}s.</li>
 * </ul>
 * 
 * @since version 8.0
 * @author Kirill Grouchnikov
 */
public class SubstanceCortex {
    /**
     * APIs in this scope apply to the global state of the application.
     */
    public static final class GlobalScope {
        private final static String SUBSTANCE_FONT_POLICY_KEY = "substancelaf.fontPolicyKey";

        /**
         * Resource bundle for <b>Substance</b> labels.
         */
        private static ResourceBundle labelBundle = null;

        /**
         * Class loader for {@link #labelBundle}.
         */
        private static ClassLoader labelBundleClassLoader;

        /** The current Substance skin. */
        private static SubstanceSkin currentSkin = null;

        /**
         * List of all listeners on skin changes.
         */
        private final static Set<SkinChangeListener> skinChangeListeners = new HashSet<SkinChangeListener>();

        /**
         * List of all listeners on changing locales.
         */
        private final static Set<LocaleChangeListener> localeChangeListeners = new HashSet<LocaleChangeListener>();

        private static SubstanceIconPack iconPack;

        /**
         * Indicates whether option dialogs (error, question, warning, info) should use constant
         * color schemes for icon coloring. Note that since version 4.0, the default setting is
         * <code>true</code> (use constant color scheme). To use color scheme-consistent coloring,
         * call {@link #setToUseConstantThemesOnDialogs(boolean)} and pass <code>false</code>.
         * 
         * @see #isToUseConstantThemesOnDialogs()
         * @see #setToUseConstantThemesOnDialogs(boolean)
         */
        private static boolean toUseConstantThemesOnDialogs = true;

        private static SubstanceSlices.SubstanceOptionPaneButtonOrder optionPaneButtonOrder = SubstanceSlices.SubstanceOptionPaneButtonOrder.PLATFORM;

        private static SubstanceSlices.SubstanceOptionPaneButtonAlignment optionPaneButtonAlignment = SubstanceSlices.SubstanceOptionPaneButtonAlignment.PLATFORM;

        /**
         * Sets the specified skin. If the current look-and-feel is not Substance, this method will
         * create a new Substance look-and-feel based on the specified skin and set it on
         * {@link UIManager}. This method does not require Substance to be the current
         * look-and-feel.
         * 
         * @param newSkin
         *            Skin to set.
         * @param toUpdateWindows
         *            if <code>true</code>, the
         *            {@link SwingUtilities#updateComponentTreeUI(Component)} is called on all
         *            windows returned by {@link Window#getWindows()} API.
         * @return <code>true</code> if the specified skin has been set successfully,
         *         <code>false</code> otherwise.
         * @see #setSkin(SubstanceSkin)
         */
        static boolean setSkin(SubstanceSkin newSkin, boolean toUpdateWindows) {
            if (!SwingUtilities.isEventDispatchThread()) {
                throw new IllegalStateException(
                        "This method must be called on the Event Dispatch Thread");
            }

            if (!newSkin.isValid())
                return false;

            boolean isSubstance = (UIManager.getLookAndFeel() instanceof SubstanceLookAndFeel);
            if (!isSubstance) {
                class SkinDerivedLookAndFeel extends SubstanceLookAndFeel {
                    public SkinDerivedLookAndFeel(SubstanceSkin newSkin) {
                        super(newSkin);
                    }
                }

                LookAndFeel derived = new SkinDerivedLookAndFeel(newSkin);
                try {
                    UIManager.setLookAndFeel(derived);
                } catch (UnsupportedLookAndFeelException ulafe) {
                    return false;
                }
                if (!(UIManager.getLookAndFeel() instanceof SubstanceLookAndFeel)) {
                    return false;
                }
                for (Window window : Window.getWindows()) {
                    SwingUtilities.updateComponentTreeUI(window);
                }
                return true;
            }

            try {
                // Required skin settings must be non-null
                if (!newSkin.isValid()) {
                    return false;
                }

                // fix for defect 109 - memory leak on watermark switch
                if ((currentSkin != null) && (currentSkin.getWatermark() != null)) {
                    currentSkin.getWatermark().dispose();
                }
                if (newSkin.getWatermark() != null) {
                    if (!newSkin.getWatermark().updateWatermarkImage(newSkin)) {
                        return false;
                    }
                }

                UIDefaults lafDefaults = UIManager.getLookAndFeelDefaults();
                UIDefaults defaults = lafDefaults;
                // The table will be null when the skin is set using a custom
                // LAF
                if (defaults != null) {
                    initFontDefaults(lafDefaults, getFontPolicy().getFontSet("Substance", null));
                    newSkin.addCustomEntriesToTable(lafDefaults);
                    SubstancePluginRepository.getInstance()
                            .processAllDefaultsEntriesComponentPlugins(lafDefaults, newSkin);
                }

                // file chooser strings go to the main UIManager table
                ResourceBundle substanceBundle = getLabelBundle();
                Enumeration<String> keyEn = substanceBundle.getKeys();
                while (keyEn.hasMoreElements()) {
                    String key = keyEn.nextElement();
                    if (key.indexOf("FileChooser") != -1) {
                        String value = substanceBundle.getString(key);
                        UIManager.put(key, value);
                    }
                }

                if (isSubstance)
                    LazyResettableHashMap.reset();

                currentSkin = newSkin;

                if (toUpdateWindows) {
                    for (Window window : Window.getWindows()) {
                        SwingUtilities.updateComponentTreeUI(window);
                    }
                }

                // SwingUtilities.invokeLater(new Runnable() {
                // public void run() {
                for (SkinChangeListener skinChangeListener : skinChangeListeners)
                    skinChangeListener.skinChanged();
                // }
                // });
                return true;
            } catch (NoClassDefFoundError ncdfe) {
                // this may happen when a skin references some class
                // that can't be found in the classpath.
                ncdfe.printStackTrace(System.out);
                return false;
            } catch (Exception e) {
                e.printStackTrace(System.out);
                return false;
            }
        }

        static void unsetSkin() {
            currentSkin = null;
        }

        /**
         * Sets the specified skin. If the current look-and-feel is not Substance, this method will
         * create a new Substance look-and-feel based on the specified skin and set it on
         * {@link UIManager}. This method does not require Substance to be the current
         * look-and-feel. Calling this method will call
         * {@link SwingUtilities#updateComponentTreeUI(Component)} on all open top-level windows.
         * 
         * @param newSkin
         *            Skin to set.
         * @return <code>true</code> if the specified skin has been set successfully,
         *         <code>false</code> otherwise.
         * @throws IllegalStateException
         *             When called outside the Event Dispatch Thread.
         * @see #registerSkinChangeListener(SkinChangeListener)
         * @see #unregisterSkinChangeListener(SkinChangeListener)
         * @see SubstanceSkin#isValid()
         */
        public static boolean setSkin(SubstanceSkin newSkin) {
            return setSkin(newSkin, true);
        }

        /**
         * Sets the specified skin. If the current look-and-feel is not Substance, this method will
         * create a new Substance look-and-feel based on the specified skin and set it on
         * {@link UIManager}. This method does not require Substance to be the current
         * look-and-feel. Calling this method will call
         * {@link SwingUtilities#updateComponentTreeUI(Component)} on all open top-level windows.
         * 
         * @param skinClassName
         *            Skin to set.
         * @return <code>true</code> if the specified skin has been set successfully,
         *         <code>false</code> otherwise.
         * @throws IllegalStateException
         *             When called outside the Event Dispatch Thread.
         * @since version 3.1
         * @see #setSkin(SubstanceSkin)
         * @see #registerSkinChangeListener(SkinChangeListener)
         * @see #unregisterSkinChangeListener(SkinChangeListener)
         * @see SubstanceSkin#isValid()
         */
        public static boolean setSkin(String skinClassName) {
            try {
                Class<?> skinClass = Class.forName(skinClassName);
                if (skinClass == null) {
                    return false;
                }
                Object obj = skinClass.newInstance();
                if (obj == null) {
                    return false;
                }
                if (!(obj instanceof SubstanceSkin)) {
                    return false;
                }
                return setSkin((SubstanceSkin) obj);
            } catch (Exception exc) {
                exc.printStackTrace();
                return false;
            }
        }

        /**
         * Returns the current global skin. If the current look-and-feel is not Substance, this
         * method returns <code>null</code>. This method is for internal use only. Applications
         * should use the {@link #getCurrentSkin(Component)}.
         * 
         * @return Current global skin.
         * @see #getCurrentSkin(Component)
         */
        public static SubstanceSkin getCurrentSkin() {
            LookAndFeel current = UIManager.getLookAndFeel();
            if (current instanceof SubstanceLookAndFeel) {
                return currentSkin;
            }
            return null;
        }

        /**
         * Looks up the correct control font and sets it for all controls.
         * 
         * @param table
         *            The UI defaults table.
         */
        static void initFontDefaults(UIDefaults table) {
            FontSet substanceFontSet = getFontPolicy().getFontSet("Substance", null);
            initFontDefaults(table, substanceFontSet);
        }

        /**
         * Sets Fonts in the given FontSet as defaults for all known component types in the given
         * UIDefaults table.
         * 
         * @param table
         *            the UIDefaults table used to set fonts
         * @param fontSet
         *            describes the set of Fonts to be installed
         */
        private static void initFontDefaults(UIDefaults table, FontSet fontSet) {
            Font controlFont = fontSet.getControlFont();
            Font menuFont = fontSet.getMenuFont();
            Font messageFont = fontSet.getMessageFont();
            Font toolTipFont = fontSet.getSmallFont();
            Font titleFont = fontSet.getTitleFont();
            Font windowFont = fontSet.getWindowTitleFont();

            Object[] defaults = {

                            "Button.font", controlFont,

                            "CheckBox.font", controlFont,

                            "ColorChooser.font", controlFont,

                            "ComboBox.font", controlFont,

                            "EditorPane.font", controlFont,

                            "FormattedTextField.font", controlFont,

                            "Label.font", controlFont,

                            "List.font", controlFont,

                            "Panel.font", controlFont,

                            "PasswordField.font", controlFont,

                            "ProgressBar.font", controlFont,

                            "RadioButton.font", controlFont,

                            "ScrollPane.font", controlFont,

                            "Spinner.font", controlFont,

                            "TabbedPane.font", controlFont,

                            "Table.font", controlFont,

                            "TableHeader.font", controlFont,

                            "TextArea.font", controlFont,

                            "TextField.font", controlFont,

                            "TextPane.font", controlFont,

                            "ToolBar.font", controlFont,

                            "ToggleButton.font", controlFont,

                            "Tree.font", controlFont,

                            "Viewport.font", controlFont,

                            "InternalFrame.titleFont", windowFont,

                            "DesktopIcon.titleFont", windowFont,

                            "OptionPane.font", messageFont,

                            "OptionPane.messageFont", messageFont,

                            "OptionPane.buttonFont", messageFont,

                            "TitledBorder.font", titleFont,

                            "ToolTip.font", toolTipFont,

                            "CheckBoxMenuItem.font", menuFont,

                            "CheckBoxMenuItem.acceleratorFont", menuFont,

                            "Menu.font", menuFont,

                            "Menu.acceleratorFont", menuFont,

                            "MenuBar.font", menuFont,

                            "MenuItem.font", menuFont,

                            "MenuItem.acceleratorFont", menuFont,

                            "PopupMenu.font", menuFont,

                            "RadioButtonMenuItem.font", menuFont,

                            "RadioButtonMenuItem.acceleratorFont", menuFont, };
            table.putDefaults(defaults);
        }

        /**
         * Registers a new listener on skin change.
         * 
         * @param skinChangeListener
         *            New listener on skin change.
         * @see #setSkin(String)
         * @see #setSkin(SubstanceSkin)
         * @see #unregisterSkinChangeListener(SkinChangeListener)
         */
        public static void registerSkinChangeListener(SkinChangeListener skinChangeListener) {
            skinChangeListeners.add(skinChangeListener);
        }

        /**
         * Unregisters a listener on skin change.
         * 
         * @param skinChangeListener
         *            The listener to unregister.
         * @see #setSkin(String)
         * @see #setSkin(SubstanceSkin)
         * @see #registerSkinChangeListener(SkinChangeListener)
         */
        public static void unregisterSkinChangeListener(SkinChangeListener skinChangeListener) {
            skinChangeListeners.remove(skinChangeListener);
        }

        /**
         * Returns all available skins.
         * 
         * @return All available skins. Key - skin display name, value - skin information.
         */
        public static Map<String, SkinInfo> getAllSkins() {
            Map<String, SkinInfo> result = new TreeMap<String, SkinInfo>();
            for (SubstanceSkinPlugin skinPlugin : SubstancePluginRepository.getInstance()
                    .getSkinPlugins()) {
                for (SkinInfo skinInfo : ((SubstanceSkinPlugin) skinPlugin).getSkins()) {
                    result.put(skinInfo.getDisplayName(), skinInfo);
                }
            }
            return result;
        }

        public static void registerComponentPlugin(SubstanceComponentPlugin componentPlugin) {
            SubstancePluginRepository.getInstance().registerComponentPlugin(componentPlugin);
        }

        public static void registerSkinPlugin(SubstanceSkinPlugin skinPlugin) {
            SubstancePluginRepository.getInstance().registerSkinPlugin(skinPlugin);
        }

        public static void registerWidget(String widgetClassName, Class<?> supportedClass,
                boolean isExact) {
            SubstanceWidgetRepository.getRepository().registerWidget(widgetClassName,
                    supportedClass, isExact);
        }

        /**
         * Sets the {@link FontPolicy} to be used with Substance family. If the specified policy is
         * <code>null</code>, the default will be reset. This method does not require Substance to
         * be the current look-and-feel, and will cause Substance to be set as the current
         * application look-and-feel.
         * 
         * @param fontPolicy
         *            The {@link FontPolicy} to be used with Substance family, or <code>null</code>
         *            to reset to the default
         * 
         * @see #getFontPolicy()
         */
        public static void setFontPolicy(FontPolicy fontPolicy) {
            UIManager.put(SUBSTANCE_FONT_POLICY_KEY, fontPolicy);
            SubstanceSizeUtils.setControlFontSize(-1);
            SubstanceSizeUtils.resetPointsToPixelsRatio(fontPolicy);
            setSkin(getCurrentSkin());
        }

        /**
         * Looks up and retrieves the {@link FontPolicy} used by the Substance family. If a
         * {@link FontPolicy} has been set, it'll be returned. If no {@link FontPolicy} has been
         * set, the default Substance font policy will be returned.
         * 
         * @return the {@link FontPolicy} set for Substance, or the default Substance font policy.
         * 
         * @see #setFontPolicy
         * @see FontPolicies
         * @see FontPolicies#customSettingsPolicy(FontPolicy)
         */
        public static FontPolicy getFontPolicy() {
            FontPolicy policy = (FontPolicy) UIManager.get(SUBSTANCE_FONT_POLICY_KEY);
            if (policy != null)
                return policy;

            // return default policy
            return SubstanceFontUtilities.getDefaultFontPolicy();
        }

        /**
         * Registers the specified listener on tab-close events on <b>all</b> tabbed panes.
         * 
         * @param tabCloseListener
         *            Listener to register.
         * @see ComponentScope#registerTabCloseChangeListener(JTabbedPane, BaseTabCloseListener)
         * @see #unregisterTabCloseChangeListener(BaseTabCloseListener)
         * @see #getAllTabCloseListeners()
         */
        public static void registerTabCloseChangeListener(BaseTabCloseListener tabCloseListener) {
            TabCloseListenerManager.getInstance().registerListener(tabCloseListener);
        }

        /**
         * Unregisters the specified listener on tab-close events on <b>all</b> tabbed panes.
         * 
         * @param tabCloseListener
         *            Listener to unregister.
         * @see ComponentScope#unregisterTabCloseChangeListener(JTabbedPane, BaseTabCloseListener)
         * @see #registerTabCloseChangeListener(BaseTabCloseListener)
         * @see #getAllTabCloseListeners()
         */
        public static void unregisterTabCloseChangeListener(BaseTabCloseListener tabCloseListener) {
            TabCloseListenerManager.getInstance().unregisterListener(tabCloseListener);
        }

        /**
         * Returns the set of all listeners registered on tab-close events on <b>all</b> tabbed
         * panes.
         * 
         * @return Set of all listeners registered on tab-close events on <b>all</b> tabbed panes.
         * @see #registerTabCloseChangeListener(BaseTabCloseListener)
         * @see #unregisterTabCloseChangeListener(BaseTabCloseListener)
         */
        public static Set<BaseTabCloseListener> getAllTabCloseListeners() {
            return TabCloseListenerManager.getInstance().getListeners();
        }

        /**
         * Sets the {@link SubstanceIconPack} to be used with Substance.
         * 
         * @param iconPack
         *            The {@link SubstanceIconPack} to be used with Substance.
         * 
         * @see #getIconPack()
         */
        public static void setIconPack(SubstanceIconPack iconPack) {
            if (iconPack == null) {
                throw new IllegalArgumentException("Cannot pass null icon pack");
            }
            GlobalScope.iconPack = iconPack;
            LazyResettableHashMap.reset();
        }

        /**
         * Looks up and retrieves the {@link SubstanceIconPack} used by Substance.
         * 
         * @return the {@link SubstanceIconPack} set for Substance.
         * 
         * @see #setIconPack(SubstanceIconPack)
         */
        public static SubstanceIconPack getIconPack() {
            if (GlobalScope.iconPack == null) {
                GlobalScope.iconPack = new SubstanceDefaultIconPack();
            }
            return GlobalScope.iconPack;
        }

        /**
         * Allows animations of the specified facet on all controls.
         * 
         * @param animationFacet
         *            Animation facet to allow.
         */
        public static void allowAnimations(AnimationFacet animationFacet) {
            AnimationConfigurationManager.getInstance().allowAnimations(animationFacet);
        }

        /**
         * Allows animations of the specified facet on all controls of specified class.
         * 
         * @param animationFacet
         *            Animation facet to allow.
         * @param clazz
         *            Control class for allowing the animation facet.
         */
        public static void allowAnimations(AnimationFacet animationFacet, Class<?> clazz) {
            AnimationConfigurationManager.getInstance().allowAnimations(animationFacet, clazz);
        }

        /**
         * Allows animations of the specified facet on all controls of specified classes.
         * 
         * @param animationFacet
         *            Animation facet to allow.
         * @param clazz
         *            Control classes for allowing the animation facet.
         */
        public static void allowAnimations(AnimationFacet animationFacet, Class<?>[] clazz) {
            AnimationConfigurationManager.getInstance().allowAnimations(animationFacet, clazz);
        }

        /**
         * Disallows animations of the specified facet on all controls.
         * 
         * @param animationFacet
         *            Animation facet to disallow.
         */
        public static void disallowAnimations(AnimationFacet animationFacet) {
            AnimationConfigurationManager.getInstance().disallowAnimations(animationFacet);
        }

        /**
         * Disallows animations of the specified facet on all controls of specified class.
         * 
         * @param animationFacet
         *            Animation facet to disallow.
         * @param clazz
         *            Control class for disallowing the animation facet.
         */
        public static void disallowAnimations(AnimationFacet animationFacet, Class<?> clazz) {
            AnimationConfigurationManager.getInstance().disallowAnimations(animationFacet, clazz);
        }

        /**
         * Disallows animations of the specified facet on all controls of specified classes.
         * 
         * @param animationFacet
         *            Animation facet to disallow.
         * @param clazz
         *            Control classes for disallowing the animation facet.
         */
        public static void disallowAnimations(AnimationFacet animationFacet, Class<?>[] clazz) {
            AnimationConfigurationManager.getInstance().disallowAnimations(animationFacet, clazz);
        }

        /**
         * Checks whether the specified animation facet is allowed on the specified component.
         * 
         * @param animationFacet
         *            Animation facet.
         * @return <code>true</code> if the specified animation facet is allowed globally,
         *         <code>false</code> otherwise.
         */
        public static boolean isAnimationAllowed(AnimationFacet animationFacet) {
            return AnimationConfigurationManager.getInstance().isAnimationAllowed(animationFacet,
                    null);
        }

        public static void setTimelineDuration(long timelineDuration) {
            AnimationConfigurationManager.getInstance().setTimelineDuration(timelineDuration);
        }

        public static long getTimelineDuration() {
            return AnimationConfigurationManager.getInstance().getTimelineDuration();
        }

        /**
         * Registers a new listener on locale change.
         * 
         * @param localeListener
         *            New listener on locale change.
         */
        public static void registerLocaleChangeListener(LocaleChangeListener localeListener) {
            localeChangeListeners.add(localeListener);
        }

        /**
         * Unregisters a listener on locale change.
         * 
         * @param localeListener
         *            The listener to unregister.
         */
        public static void unregisterLocaleChangeListener(LocaleChangeListener localeListener) {
            localeChangeListeners.remove(localeListener);
        }

        /**
         * Returns all listeners registered on locale change.
         * 
         * @return All listeners registered on locale change.
         */
        public static Set<LocaleChangeListener> getLocaleChangeListeners() {
            return Collections.unmodifiableSet(localeChangeListeners);
        }

        /**
         * Retrieves the current label bundle.
         * 
         * @return The current label bundle.
         * @see #resetLabelBundle()
         */
        public static synchronized ResourceBundle getLabelBundle() {
            if (labelBundle == null) {
                // fix for RFE 157 (allowing custom class loader for
                // resource bundles which can remove server calls
                // in applets)
                if (labelBundleClassLoader == null) {
                    labelBundle = ResourceBundle.getBundle(
                            "org.pushingpixels.substance.internal.resources.Labels",
                            Locale.getDefault());
                } else {
                    labelBundle = ResourceBundle.getBundle(
                            "org.pushingpixels.substance.internal.resources.Labels",
                            Locale.getDefault(), labelBundleClassLoader);
                }
                for (LocaleChangeListener lcl : SubstanceCortex.GlobalScope
                        .getLocaleChangeListeners())
                    lcl.localeChanged();
            }
            return labelBundle;
        }

        /**
         * Retrieves the label bundle for the specified locale.
         * 
         * @param locale
         *            Locale.
         * @return The label bundle for the specified locale.
         */
        public static synchronized ResourceBundle getLabelBundle(Locale locale) {
            // fix for RFE 157 (allowing custom class loader for
            // resource bundles which can remove server calls
            // in applets)
            if (labelBundleClassLoader == null) {
                return ResourceBundle
                        .getBundle("org.pushingpixels.substance.internal.resources.Labels", locale);
            } else {
                return ResourceBundle.getBundle(
                        "org.pushingpixels.substance.internal.resources.Labels", locale,
                        labelBundleClassLoader);
            }
        }

        /**
         * Resets the current label bundle. Useful when the application changes Locale at runtime.
         * 
         * @see #getLabelBundle()
         */
        public static synchronized void resetLabelBundle() {
            labelBundle = null;
        }

        /**
         * Sets the class loader for {@link #labelBundle}.
         * 
         * @param labelBundleClassLoader
         *            Class loader for {@link #labelBundle}.
         * @since version 3.1
         */
        public static void setLabelBundleClassLoader(ClassLoader labelBundleClassLoader) {
            GlobalScope.labelBundleClassLoader = labelBundleClassLoader;
        }

        public static ClassLoader getLabelBundleClassLoader() {
            return labelBundleClassLoader;
        }

        /**
         * Checks whether the <code>JOptionPane</code>s created with predefined message types should
         * use constant color schemes for the icons.
         * 
         * @return <code>true</code> if the <code>JOptionPane</code>s created with predefined
         *         message types should use constant color schemes for the icons, <code>false</code>
         *         otherwise.
         * @see #setToUseConstantThemesOnDialogs(boolean)
         */
        public static boolean isToUseConstantThemesOnDialogs() {
            return toUseConstantThemesOnDialogs;
        }

        /**
         * Sets the new setting for the icons of the <code>JOptionPane</code>s created with
         * predefined message types.
         * 
         * @param toUseConstantThemesOnDialogs
         *            if <code>true</code>, the <code>JOptionPane</code>s created with predefined
         *            message types should use constant color schemes for the icons.
         * @see #isToUseConstantThemesOnDialogs()
         */
        public static void setToUseConstantThemesOnDialogs(boolean toUseConstantThemesOnDialogs) {
            GlobalScope.toUseConstantThemesOnDialogs = toUseConstantThemesOnDialogs;
            SwingUtilities.invokeLater(() -> {
                for (Window window : Window.getWindows()) {
                    SwingUtilities.updateComponentTreeUI(window);
                }
            });
        }

        /**
         * Returns the currently set button order for all <code>JOptionPane</code>s.
         * 
         * @return The currently set button order for all <code>JOptionPane</code>s.
         * @see #setOptionPaneButtonOrder(SubstanceOptionPaneButtonOrder)
         */
        public static SubstanceSlices.SubstanceOptionPaneButtonOrder getOptionPaneButtonOrder() {
            return optionPaneButtonOrder;
        }

        /**
         * Sets the button order for all <code>JOptionPane</code>s.
         * 
         * @param optionPaneButtonOrder
         *            The new button order for all <code>JOptionPane</code>s. The value cannot be
         *            null.
         * @see #getOptionPaneButtonOrder()
         */
        public static void setOptionPaneButtonOrder(
                SubstanceSlices.SubstanceOptionPaneButtonOrder optionPaneButtonOrder) {
            if (optionPaneButtonOrder == null) {
                throw new IllegalArgumentException("Cannot pass null. Did you mean PLATFORM?");
            }
            GlobalScope.optionPaneButtonOrder = optionPaneButtonOrder;
            SwingUtilities.invokeLater(() -> {
                for (Window window : Window.getWindows()) {
                    SwingUtilities.updateComponentTreeUI(window);
                }
            });
        }

        /**
         * Returns the currently set button alignment for all <code>JOptionPane</code>s.
         * 
         * @return The currently set button alignment for all <code>JOptionPane</code>s.
         * @see #setOptionPaneButtonAlignment(org.pushingpixels.substance.api.SubstanceSlices.SubstanceOptionPaneButtonAlignment)
         */
        public static SubstanceSlices.SubstanceOptionPaneButtonAlignment getOptionPaneButtonAlignment() {
            return optionPaneButtonAlignment;
        }

        /**
         * Sets the button alignment for all <code>JOptionPane</code>s.
         * 
         * @param optionPaneButtonAlignment
         *            The new button alignment for all <code>JOptionPane</code>s. The value cannot
         *            be null.
         * @see #getOptionPaneButtonAlignment()
         */
        public static void setOptionPaneButtonAlignment(
                SubstanceSlices.SubstanceOptionPaneButtonAlignment optionPaneButtonAlignment) {
            if (optionPaneButtonAlignment == null) {
                throw new IllegalArgumentException("Cannot pass null. Did you mean PLATFORM?");
            }
            GlobalScope.optionPaneButtonAlignment = optionPaneButtonAlignment;
            SwingUtilities.invokeLater(() -> {
                for (Window window : Window.getWindows()) {
                    SwingUtilities.updateComponentTreeUI(window);
                }
            });
        }

        /**
         * Sets the visibility of the specified widget kind(s). This call applies to all root panes.
         * This method should not be called from inside the initialization sequence of your window.
         * If the specific widget needs to be visible when the window is shown, wrap the call with
         * {@link SwingUtilities#invokeLater(Runnable)}.
         * 
         * @param visible
         *            Visibility indication.
         * @param substanceWidgets
         *            Widget types.
         * @since version 5.0
         */
        public static void setWidgetVisible(boolean visible,
                SubstanceWidgetType... substanceWidgets) {
            SubstanceWidgetManager.getInstance().register(null, visible, substanceWidgets);
            for (Window window : Window.getWindows()) {
                JRootPane root = SwingUtilities.getRootPane(window);
                SwingUtilities.updateComponentTreeUI(root);
            }
        }

        /**
         * Specifies global visibility of the lock icon on non-editable text components.
         * 
         * @param visible
         *            If <code>true</code>, all non-editable text components not explicitly
         *            configured with {@link ComponentScope#setLockIconVisible(JComponent, Boolean)}
         *            will show a lock icon. Pass <code>null</code> to reset to the default
         *            behavior.
         * @see #resetLockIconVisibility()
         * @see ComponentScope#setLockIconVisible(JComponent, Boolean)
         * @since 8.0
         */
        public static void setLockIconVisible(Boolean visible) {
            UIManager.put(SubstanceSynapse.HAS_LOCK_ICON, visible);
        }

        /**
         * Specifies global preview painter to be used for showing preview thumbnails. Default
         * implementation is available in the {@link DefaultPreviewPainter}.
         * 
         * @param previewPainter
         *            Global preview painter. Can be <code>null</code>.
         * @see ComponentOrParentScope#setComponentPreviewPainter(JComponent, PreviewPainter)
         * @since 8.0
         */
        public static void setComponentPreviewPainter(PreviewPainter previewPainter) {
            UIManager.put(SubstanceSynapse.COMPONENT_PREVIEW_PAINTER, previewPainter);
        }

        /**
         * Specifies whether the contents of text components should be selected on focus gain.
         * 
         * @param selectTextOnFocus
         *            If <code>true</code>, the contents of text components will be selected on
         *            focus gain. Pass <code>null</code> to reset to the default behavior.
         * @see ComponentOrParentChainScope#setSelectTextOnFocus(JComponent, Boolean)
         * @since 8.0
         */
        public static void setSelectTextOnFocus(Boolean selectTextOnFocus) {
            UIManager.put(SubstanceSynapse.TEXT_SELECT_ON_FOCUS, selectTextOnFocus);
        }

        /**
         * Specifies whether text components should have the edit context menu (with Cut / Copy /
         * Paste / ... menu items)
         * 
         * @param hasEditContextMenu
         *            If <code>true</code>, text components will have the edit context menu (with
         *            Cut / Copy / Paste / ... menu items). Pass <code>null</code> to reset to the
         *            default behavior.
         * @see ComponentScope#setTextEditContextMenuPresence(JTextComponent, Boolean)
         * @since 8.0
         */
        public static void setTextEditContextMenuPresence(Boolean hasEditContextMenu) {
            UIManager.put(SubstanceSynapse.TEXT_EDIT_CONTEXT_MENU, hasEditContextMenu);
        }

        /**
         * Specifies whether trees should have should have automatic drag and drop support.
         * 
         * @param hasAutomaticDragAndDropSupport
         *            If <code>true</code>, trees will have automatic drag and drop support. Pass
         *            <code>null</code> to reset to the default behavior.
         * @see ComponentScope#setAutomaticDragAndDropSupportPresence(JTree, Boolean)
         * @since 8.0
         */
        public static void setAutomaticDragAndDropSupportPresence(
                Boolean hasAutomaticDragAndDropSupport) {
            UIManager.put(SubstanceSynapse.TREE_AUTO_DND_SUPPORT, hasAutomaticDragAndDropSupport);
        }

        /**
         * Specifies whether scroll panes should have have auto-scroll support invoked on mouse
         * button click that triggers popups.
         * 
         * @param hasAutomaticScroll
         *            If <code>true</code>, scroll panes will have have auto-scroll support invoked
         *            on mouse button click that triggers popups. Pass <code>null</code> to reset to
         *            the default behavior.
         * @see ComponentScope#setAutomaticScrollPresence(JScrollPane, Boolean)
         * @since 8.0
         */
        public static void setAutomaticScrollPresence(Boolean hasAutomaticScroll) {
            UIManager.put(SubstanceSynapse.AUTO_SCROLL, hasAutomaticScroll);
        }
    }

    /**
     * APIs in this scope apply to individual application {@link Component}s.
     */
    public static final class ComponentScope {
        /**
         * Returns the current skin for the specified component. If the current look-and-feel is not
         * Substance, this method returns <code>null</code>.
         * 
         * @param c
         *            Component. May be <code>null</code> - in this case the global current
         *            Substance skin will be returned.
         * @return Current skin for the specified component.
         * @see #SKIN_PROPERTY
         * @see #getCurrentSkin()
         */
        public static SubstanceSkin getCurrentSkin(Component c) {
            return SubstanceCoreUtilities.getSkin(c);
        }

        /**
         * Registers the specified listener on tab-close events on <b>the specified</b> tabbed pane.
         * 
         * @param tabbedPane
         *            Tabbed pane. Must be not <code>null</code>.
         * @param tabCloseListener
         *            Listener to register.
         * @see GlobalScope#registerTabCloseChangeListener(BaseTabCloseListener)
         * @see #unregisterTabCloseChangeListener(JTabbedPane, BaseTabCloseListener)
         * @see #getAllTabCloseListeners(JTabbedPane)
         */
        public static void registerTabCloseChangeListener(JTabbedPane tabbedPane,
                BaseTabCloseListener tabCloseListener) {
            if (tabbedPane == null) {
                throw new IllegalArgumentException(
                        "Component scope APIs do not accept null components");
            }
            TabCloseListenerManager.getInstance().registerListener(tabbedPane, tabCloseListener);
        }

        /**
         * Unregisters the specified listener on tab-close events on <b>the specified</b> tabbed
         * pane.
         * 
         * @param tabbedPane
         *            Tabbed pane. Must be not <code>null</code>.
         * @param tabCloseListener
         *            Listener to unregister.
         * @see GlobalScope#unregisterTabCloseChangeListener(BaseTabCloseListener)
         * @see #registerTabCloseChangeListener(JTabbedPane, BaseTabCloseListener)
         * @see #getAllTabCloseListeners(JTabbedPane)
         */
        public static void unregisterTabCloseChangeListener(JTabbedPane tabbedPane,
                BaseTabCloseListener tabCloseListener) {
            if (tabbedPane == null) {
                throw new IllegalArgumentException(
                        "Component scope APIs do not accept null components");
            }
            TabCloseListenerManager.getInstance().unregisterListener(tabbedPane, tabCloseListener);
        }

        /**
         * Returns all listeners registered on tab closing of the specified tabbed pane.
         * 
         * @param tabbedPane
         *            A tabbed pane. Must be not <code>null</code>.
         * @return All listeners registered on tab closing of the specified tabbed pane.
         * @see #registerTabCloseChangeListener(JTabbedPane, BaseTabCloseListener)
         * @see #unregisterTabCloseChangeListener(JTabbedPane, BaseTabCloseListener)
         */
        public static Set<BaseTabCloseListener> getAllTabCloseListeners(JTabbedPane tabbedPane) {
            if (tabbedPane == null) {
                throw new IllegalArgumentException(
                        "Component scope APIs do not accept null components");
            }
            return TabCloseListenerManager.getInstance().getListeners(tabbedPane);
        }

        /**
         * Allows animations of the specified facet on the specified control.
         * 
         * @param animationFacet
         *            Animation facet to allow.
         * @param comp
         *            Control for allowing the animation facet.
         */
        public static void allowAnimations(Component comp, AnimationFacet animationFacet) {
            if (comp == null) {
                throw new IllegalArgumentException(
                        "Component scope APIs do not accept null components");
            }
            AnimationConfigurationManager.getInstance().allowAnimations(animationFacet, comp);
        }

        /**
         * Disallows animations of the specified facet on the specified control.
         * 
         * @param animationFacet
         *            Animation facet to disallow.
         * @param comp
         *            Control for disallowing the animation facet.
         */
        public static void disallowAnimations(Component comp, AnimationFacet animationFacet) {
            if (comp == null) {
                throw new IllegalArgumentException(
                        "Component scope APIs do not accept null components");
            }
            AnimationConfigurationManager.getInstance().disallowAnimations(animationFacet, comp);
        }

        /**
         * Checks whether the specified animation facet is allowed on the specified component.
         * 
         * @param animationFacet
         *            Animation facet.
         * @param comp
         *            Component.
         * @return <code>true</code> if the specified animation facet is allowed on the specified
         *         component, <code>false</code> otherwise.
         */
        public static boolean isAnimationAllowed(Component comp, AnimationFacet animationFacet) {
            if (comp == null) {
                throw new IllegalArgumentException(
                        "Component scope APIs do not accept null components");
            }
            return AnimationConfigurationManager.getInstance().isAnimationAllowed(animationFacet,
                    comp);
        }

        /**
         * Sets the decoration type of the specified component and all its children.
         * 
         * @param comp
         *            Component.
         * @param type
         *            Decoration type of the component and all its children.
         */
        public static void setDecorationType(JComponent comp, DecorationAreaType type) {
            if (comp == null) {
                throw new IllegalArgumentException(
                        "Component scope APIs do not accept null components");
            }
            DecorationPainterUtils.setDecorationType(comp, type);
        }

        /**
         * Returns the decoration area type of the specified component. The component and its
         * ancestor hierarchy are scanned for the registered decoration area type. If
         * {@link #setDecorationType(JComponent, DecorationAreaType)} has been called on the
         * specified component, the matching decoration type is returned. Otherwise, the component
         * hierarchy is scanned to find the closest ancestor that was passed to
         * {@link #setDecorationType(JComponent, DecorationAreaType)} - and its decoration type is
         * returned. If neither the component, nor any one of its parent components has been passed
         * to the setter method, {@link DecorationAreaType#NONE} is returned.
         * 
         * @param comp
         *            Component.
         * @return Decoration area type of the component.
         */
        public static DecorationAreaType getDecorationType(Component comp) {
            if (comp == null) {
                throw new IllegalArgumentException(
                        "Component scope APIs do not accept null components");
            }
            return DecorationPainterUtils.getDecorationType(comp);
        }

        /**
         * Returns the immediate decoration area type of the specified component. The component is
         * checked for the registered decoration area type. If
         * {@link #setDecorationType(JComponent, DecorationAreaType, boolean)} was not called on
         * this component, this method returns <code>null</code>.
         * 
         * @param comp
         *            Component.
         * @return Immediate decoration area type of the component.
         */
        public static DecorationAreaType getImmediateDecorationType(Component comp) {
            if (comp == null) {
                throw new IllegalArgumentException(
                        "Component scope APIs do not accept null components");
            }
            return DecorationPainterUtils.getImmediateDecorationType(comp);
        }

        /**
         * Specifies component-level visibility of the lock icon on the specific component.
         * 
         * @param comp
         *            Component.
         * @param visible
         *            If <code>true</code>, the specific text component will show a lock icon when
         *            it is in non-editable mode. Pass <code>null</code> to reset to the default
         *            behavior.
         * @see GlobalScope#setLockIconVisible(Boolean)
         * @see #resetLockIconVisibility(JComponent)
         * @since 8.0
         */
        public static void setLockIconVisible(JComponent comp, Boolean visible) {
            comp.putClientProperty(SubstanceSynapse.HAS_LOCK_ICON, visible);
        }

        /**
         * Specifies password strength checker for the specific password field.
         * 
         * @param passwordField
         *            Password field.
         * @param passwordStrengthChecker
         *            Password strength checker
         * @since 8.0
         */
        public static void setPasswordStrengthChecker(JPasswordField passwordField,
                PasswordStrengthChecker passwordStrengthChecker) {
            passwordField.putClientProperty(SubstanceSynapse.PASSWORD_STRENGTH_CHECKER,
                    passwordStrengthChecker);
        }

        /**
         * Specifies whether the text component contents should flip selection on ESCAPE key press.
         * 
         * @param comp
         *            Text component.
         * @param flipTextSelectionOnEscape
         *            If <code>true</code>, the contents of the specified text component will flip
         *            selection on ESCAPE key press. Pass <code>null</code> to reset to the default
         *            behavior.
         * @since 8.0
         */
        public static void setFlipTextSelectionOnEscape(JTextComponent comp,
                Boolean flipTextSelectionOnEscape) {
            comp.putClientProperty(SubstanceSynapse.TEXT_FLIP_SELECT_ON_ESCAPE,
                    flipTextSelectionOnEscape);
        }

        /**
         * Specifies whether the text component should have the edit context menu (with Cut / Copy /
         * Paste / ... menu items).
         * 
         * @param comp
         *            Text component.
         * @param hasEditContextMenu
         *            If <code>true</code>, the text component will have the edit context menu (with
         *            Cut / Copy / Paste / ... menu items). Pass <code>null</code> to reset to the
         *            default behavior.
         * @see GlobalScope#setTextEditContextMenuPresence(Boolean)
         * @since 8.0
         */
        public static void setTextEditContextMenuPresence(JTextComponent comp,
                Boolean hasEditContextMenu) {
            comp.putClientProperty(SubstanceSynapse.TEXT_EDIT_CONTEXT_MENU, hasEditContextMenu);
        }

        /**
         * Specifies whether the tree should have automatic drag and drop support.
         * 
         * @param tree
         *            Tree component.
         * @param hasAutomaticDragAndDropSupport
         *            If <code>true</code>, the tree will have automatic drag and drop support. Pass
         *            <code>null</code> to reset to the default behavior.
         * @see GlobalScope#setAutomaticDragAndDropSupportPresence(Boolean)
         * @since 8.0
         */
        public static void setAutomaticDragAndDropSupportPresence(JTree tree,
                Boolean hasAutomaticDragAndDropSupport) {
            tree.putClientProperty(SubstanceSynapse.TREE_AUTO_DND_SUPPORT,
                    hasAutomaticDragAndDropSupport);
        }

        /**
         * Specifies whether the scroll pane should have have auto-scroll support invoked on mouse
         * button click that triggers popups.
         * 
         * @param scrollPane
         *            Scroll pane component.
         * @param hasAutomaticScroll
         *            If <code>true</code>, the scroll pane will have have auto-scroll support
         *            invoked on mouse button click that triggers popups. Pass <code>null</code> to
         *            reset to the default behavior.
         * @see GlobalScope#setAutomaticScrollPresence(Boolean)
         * @since 8.0
         */
        public static void setAutomaticScrollPresence(JScrollPane scrollPane,
                Boolean hasAutomaticScroll) {
            scrollPane.putClientProperty(SubstanceSynapse.AUTO_SCROLL, hasAutomaticScroll);
        }
    }

    /**
     * APIs in this scope apply to individual application {@link Component}s or all immediate child
     * components of a container.
     */
    public static final class ComponentOrParentScope {
        /**
         * Specifies preview painter to be used for showing preview of the specific component or its
         * immediate children. Default implementation is available in the
         * {@link DefaultPreviewPainter}.
         * 
         * @param previewPainter
         *            Preview painter. Can be <code>null</code>.
         * @see GlobalScope#setComponentPreviewPainter(PreviewPainter)
         * @since 8.0
         */
        public static void setComponentPreviewPainter(JComponent comp,
                PreviewPainter previewPainter) {
            comp.putClientProperty(SubstanceSynapse.COMPONENT_PREVIEW_PAINTER, previewPainter);
        }
    }

    /**
     * APIs in this scope apply to individual application {@link Component}s or all nested child
     * components of a container.
     */
    public static final class ComponentOrParentChainScope {
        /**
         * Specifies whether the contents of the specified text component or its nested children
         * should be selected on focus gain.
         * 
         * @param selectTextOnFocus
         *            If <code>true</code>, the contents of the specified text component or its
         *            nested children will be selected on focus gain. Pass <code>null</code> to
         *            reset to the default behavior.
         * @see GlobalScope#setSelectTextOnFocus(boolean)
         * @since 8.0
         */
        public static void setSelectTextOnFocus(JComponent comp, Boolean selectTextOnFocus) {
            comp.putClientProperty(SubstanceSynapse.TEXT_SELECT_ON_FOCUS, selectTextOnFocus);
        }
    }

    /**
     * APIs in this scope apply to individual application {@link Window}s.
     */
    public static final class WindowScope {
        /**
         * Sets the visibility of the specified widget kind(s). This method should not be called
         * from inside the initialization sequence of your window. If the specific widget needs to
         * be visible when the window is shown, wrap the call with
         * {@link SwingUtilities#invokeLater(Runnable)}.
         * 
         * @param rootPane
         *            Root pane. May be <code>null</code>.
         * @param visible
         *            Visibility indication.
         * @param substanceWidgets
         *            Widget types.
         * @since version 5.0
         */
        public static void setWidgetVisible(Window window, boolean visible,
                SubstanceWidgetType... substanceWidgets) {
            if (window == null) {
                throw new IllegalArgumentException("Window scope APIs do not accept null windows");
            }
            JRootPane rootPane = SwingUtilities.getRootPane(window);
            if (rootPane != null) {
                SubstanceWidgetManager.getInstance().register(rootPane, visible, substanceWidgets);
                SwingUtilities.updateComponentTreeUI(rootPane);
            }
        }

        /**
         * Returns the title pane of the specified top-level window.
         * 
         * @param window
         *            Top-level window.
         * @return If the parameter is either {@link JFrame} or {@link JDialog} and has custom
         *         decorations, the result is the title pane, <code>null</code> otherwise.
         * @since version 3.1
         */
        public static JComponent getTitlePaneComponent(Window window) {
            if (window == null) {
                throw new IllegalArgumentException("Window scope APIs do not accept null windows");
            }
            JRootPane rootPane = SwingUtilities.getRootPane(window);
            if (rootPane != null) {
                SubstanceRootPaneUI ui = (SubstanceRootPaneUI) rootPane.getUI();
                return ui.getTitlePane();
            }
            return null;
        }

    }
}
