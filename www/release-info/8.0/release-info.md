## Substance 8.0 Wyoming release notes

### Unified API surface (Project Cerebrum)

The API surface for controlling the visual appearance and behavior of various parts of Substance has grown organically over the years. Part of this growth process has been experimenting with various ways to express this control, from client properties to VM flags to APIs on a number of classes.

Starting with 8.0, the only officially supported entry point into configuring the behavior of Substance-powered UIs and for querying the state of such UIs is via the `org.pushingpixels.substance.api.SubstanceCortex` class. The API surface of this class is broken into a number of scopes, with every scope applying at the specific granularity level of control:

* `GlobalScope` - configuring and querying the global state of the application.
* `WindowScope` - configuring and querying state at the level of the application `Window`s.
* `RootPaneScope` - configuring and querying state at the level of the application `JRootPane`s.
* `ComponentScope` - configuring and querying state at the level of the application `Component`s.
* `ComponentOrParentScope` - configuring and querying state at the level of individual application `Component`s or all immediate child components of a container.
* `ComponentOrParentChainScope` - configuring and querying state at the level of individual application `Component`s or all nested child components of a container.

All APIs previously exposed on `SubstanceLookAndFeel` - as either methods or client properties - have been migrated into the relevant scopes of `SubstanceCortex`. Note that if a certain behavior is relevant for multiple scopes (for example `setFocusKind`), it will appear in more than one scope.

### Cleaning up API package names

Package names under the `org.pushingpixels.substance.api` packages have been cleaned for consistency.

* `fonts` is now `font`
* `inputmaps` is now `inputmap`
* `renderers` is now `renderer`

In addition, a number of classes have moved from `api` to `api.colorscheme` package. Note that this cleanup did not change the class names and method signatures of the APIs themselves, and the only change you need to do in your application code that is using these APIs is to fix the imports.

### Configurable and custom title pane content (Project Visor)

Substance 8.0 provides a number of APIs to configure the layout in and around the title pane area of application windows.

`SubstanceCortex.GlobalScope.configureTitleContentGravity` is the API to globally configure the gravity (edge alignment) of title pane content - title text, control buttons (minimize, maximize, close) and app icon.

The following APIs on the `SubstanceCortex.WindowScope` scope allow apps to extend the main content view into the title pane area:

* `extendContentIntoTitlePane(Window, SubstanceSlices.HorizontalGravity, SubstanceSlices.VerticalGravity)` to marks the specified window to have its content extend vertically into the title pane area.
* `getTitlePaneControlInsets(Window)` to query the insets that should be
reserved for the main control buttons - close / maximize / minimize.
* `setPreferredTitlePaneHeight(Window, int)` to increase the preferred height of the title pane area in case the content you extend into that area is taller than the main control buttons.
* `createTitlePaneControlButton(Window)` to get a button that has consistent visual appearance and preferred size with the main control buttons.

Calling `JFrame.setDefaultLookAndFeelDecorated(true)` on the specific window is the mandatory pre-requisite to be extend the window content into the title pane area with `SubstanceCortex.WindowScope.extendContentIntoTitlePane` API. See the [skeleton demo apps](https://github.com/kirill-grouchnikov/substance-samples/tree/master/src/org/pushingpixels/samples/substance) for sample code on how to use these APIs.

### No more laf-plugin and laf-widget (Project Corpora)

These two projects were envisioned when the landscape of third party look-and-feels in particular, and Swing in general, was more vibrant. The goal was:

* For `laf-plugin` to provide a common mechanism for specifying look-and-feel plugins for components libraries
* For `laf-widget` to provide a collection of widgets that enhance the visual appearance and behavior of specific Swing components

The functionality of these two projects has now been folded into the main Substance codebase. The APIs for configuring animations and widgets are now part of the `SubstanceCortex` class.

There are three widgets for `JTabbedPane` component that have been moved into the [substance-extras](https://github.com/kirill-grouchnikov/substance-extras) project. These are `TabPagerWidget`, `TabHoverPreviewWidget` and `TabOverviewDialogWidget`. To enable one or more of these widgets:

* Call `SubstanceCortex.GlobalScope.registerWidget` on each one of the widgets you want to enable
* Call `SubstanceExtrasCortex.ComponentScope.setTabPanePreviewPainter` to configure the preview painter for the specific `JTabbedPane`

### No more automatic discovery of Substance plugins

Up until version 8.0, Substance has supported defining and seamlessly loading plugins for third-party components. This was done at the initialization time by looking up the `META-INF/substance-plugin.xml` descriptor files in the classpath.

While this has provided an easy mechanism for working with these plugins, it has also provided a way for injecting unintended behaviors into Substance-powered applications by simply adding a malicious or misbehaving plugin to the classpath.

Starting with version 8.0, your application will have to make explicit calls to `SubstanceCortex.GlobalScope.registerComponentPlugin` API to make Substance aware of the specific component plugin, such as [Substance plugin](https://github.com/kirill-grouchnikov/substance-flamingo) for [Flamingo components](https://github.com/kirill-grouchnikov/flamingo)

### Good bye Tango, hello Material (icons)

Starting with version 8.0, Substance has switched to [Material Design icon pack](https://material.io/icons/) for icons used on Swing components such as `JOptionPane`, `JFileChooser`, `JColorChooser`, and a few other places like text component edit context menus. The original icon content is in SVG format, and [project Ibis](https://github.com/kirill-grouchnikov/ibis) is used offline to create transcoded Java2D-powered classes that encapsulate the content as resizable icons that are automatically scaled on high-DPI screens.

In addition, the newly added `api.icon.SubstanceIconPack` interface and `SubstanceCortex.GlobalScope.setIconPack` API can be used to configure your application to use a consistent set of icons - such as the previously used [Project Tango](https://commons.wikimedia.org/wiki/Tango_icons).

### Better support for fractional scaling factors

Hi-DPI support introduced in 7.0 and refined in 7.1 has received an overhaul for better visuals under fractional desktop scaling factors.
