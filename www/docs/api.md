## Substance look and feel - API

### Working with skins

`public static Map<String, SkinInfo> getAllSkins()`

Returns all available skins.

`public static boolean setSkin(String skinClassName)`

Sets the specified skin.

`public static boolean setSkin(SubstanceSkin skin)`

Sets the specified skin.

`public static SubstanceSkin getCurrentSkin(Component c)`

Returns the current skin for the specified component.

`public static void registerSkinChangeListener(
SkinChangeListener skinChangeListener)`

Registers a new listener on skin change.

`public static void unregisterSkinChangeListener(
SkinChangeListener skinChangeListener)`

Unregisters a listener on skin change.

### Working with fonts

`public static FontPolicy getFontPolicy()`

Looks up and retrieves the font policy used by the Substance family.

`public static void setFontPolicy(FontPolicy fontPolicy)`

Sets the font policy to be used with Substance family.

### Working with tab closing

`public static Set<BaseTabCloseListener> getAllTabCloseListeners()`

Returns the set of all listeners registered on tab-close events on all tabbed panes.

`public static Set<BaseTabCloseListener> getAllTabCloseListeners(
JTabbedPane tabbedPane)`

Returns all listeners registered on tab closing of the specified tabbed pane.

`public static void registerTabCloseChangeListener(
BaseTabCloseListener tabCloseListener)`

Registers the specified listener on tab-close events on all tabbed panes.

`public static void registerTabCloseChangeListener(JTabbedPane tabbedPane,
BaseTabCloseListener tabCloseListener)`

Registers the specified listener on tab-close events on the specified tabbed pane.

`public static void unregisterTabCloseChangeListener(
BaseTabCloseListener tabCloseListener)`

Unregisters the specified listener on tab-close events on all tabbed panes.

`public static void unregisterTabCloseChangeListener(JTabbedPane tabbedPane,
BaseTabCloseListener tabCloseListener)`

Unregisters the specified listener on tab-close events on the specified tabbed pane.

### Additional APIs

`public static void setWidgetVisible(JRootPane rootPane, boolean visible,
SubstanceWidgetType... substanceWidgets)`

Sets the visibility of the specified widget kind(s).

`public static void setToUseConstantThemesOnDialogs(
boolean toUseConstantThemesOnDialogs)`

Sets the new setting for the icons of the `JOptionPane`s created with predefined message types.

`public static DecorationAreaType getDecorationType(Component comp)`

Returns the decoration area type of the specified component

`public static void setDecorationType(JComponent comp,
DecorationAreaType type)`

Sets the decoration type of the specified component and all its children.
