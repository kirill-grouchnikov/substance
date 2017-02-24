## Substance look and feel

### Getting started

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/substance/master/www/images/walkthrough/all.png" width="279" height="324" border=0 align="right">

If you are new to Swing, start with the general [Swing tutorial](http://java.sun.com/docs/books/tutorial/uiswing/). If you are new to look-and-feels, read the [getting started first](docs/getting-started.md).

To use Substance in your applications, choose one of the following options:

* Use one of the skin-based look-and-feel classes ([light](docs/skins/toneddown.md) or [dark](docs/skins/dark.md)).
* Extend the `SubstanceLookAndFeel` class and pass a `SubstanceSkin` instance to the super constructor.
* Use the static `setSkin(SubstanceSkin)` or `setSkin(String)` APIs to set the specific skin.

In any case, add the latest `substance.jar` to your classpath.

If you wish to build Substance from the sources, you will need to also get [laf-plugin](https://github.com/kirill-grouchnikov/laf-plugin) and [laf-widget](https://github.com/kirill-grouchnikov/laf-widgetn) (binaries or sources). The best way is to sync to the latest and use the `build.xml` script. The main test application uses the FormLayout and components from SwingLabs.

Have a question? See the [FAQ](docs/faq.md) and the list of [known issues](docs/known_issues.md), and if you don't see an answer there, [file a bug](https://github.com/kirill-grouchnikov/substance/issues) or [get in touch](http://www.pushing-pixels.org/about-me).

### Configure and customize

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/substance/master/www/images/screenshots/skins/nebulabrickwall1.png" width="340" height="254" border=0 align="left">
You can use Substance-specific settings and APIs to add custom functionality to your components. See the list of [API calls](docs/api.md), [client properties](docs/client-properties.md) and [VM flags](docs/vmflags.md) for more information. To configure the animations, consult the [animation overview](docs/animations.md).

Substance can be customized to fit your visual needs. The overall visuals are controlled by [skins](docs/skins/overview.md), with each skin defined by [color scheme bundles](docs/skins/colorschemebundles.md) and [painters](docs/painters/overview.md). The [skinning primer](docs/painters/custom-skinning.md) has a few tips on how to use the different Substance painters in applications that wish to paint custom components in a way that is consistent with other Substance visuals. The Substance samples project provides a detailed walktrough that analyzes a complex UI mockup and uses different Substance APIs to implement it.

### Plugins

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/substance/master/www/images/learn/ribbon.png" width="340" height="210" border=0 align="right">

While the core Swing components cover the basic UI functionality found in most applications, modern UIs need modern UI components. To address this need, Substance comes with a built-in support to allow defining and seamlessly loading plugins that provide consistent appearance and interaction for third-party components.

During the initialization, Substance looks in the classpath for the `META-INF/substance-plugin.xml` descriptor files. The `<component-plugin-class>` element should contain the fully qualified name of a class that implements the `LafComponentPlugin` interface. This interface defines the lifecycle of a Substance plugin, with the main extension point that allows setting custom entries in the `UIManager` tables.

[Substance plugin](https://github.com/kirill-grouchnikov/substance-flamingo) for [Flamingo components](https://github.com/kirill-grouchnikov/flamingo) is the only actively developed and fully supported plugin.

[Substance plugin](https://github.com/kirill-grouchnikov/substance-swingx) for SwingX components has been aligned with the latest Substance sources. However, since the core SwingX project is effectively dead (last official 1.6.4 release is from July 2013), there is no more active development in that plugin.

Substance Extras pack provides additional watermarks, color schemes and skins and is another example of a Substance plugin.
