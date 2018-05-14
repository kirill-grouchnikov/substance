## Substance look and feel

### Getting started

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/substance/master/www/images/walkthrough/all.png" width="279" height="324" border=0 align="right">

If you are new to Swing, start with the general [Swing tutorial](http://java.sun.com/docs/books/tutorial/uiswing/). If you are new to look-and-feels, read the [getting started](www/docs/getting-started.md) first.

To use Substance in your applications, use one of the following options:

* Call `UIManager.setLookAndFeel()` with one of the skin-based look-and-feel classes ([light](www/docs/skins/toneddown.md) or [dark](www/docs/skins/dark.md)).
* Extend the `SubstanceLookAndFeel` class and pass a `SubstanceSkin` instance to the super constructor.
* Use the static `setSkin(SubstanceSkin)` or `setSkin(String)` APIs in `SubstanceCortex.GlobalScope` to set the specific skin.

In any case, add the latest `substance-X.Y.ZZ.jar` and the matching `trident-L.M.NN.jar` to your classpath.

The main demo application is in the [substance-demo](https://github.com/kirill-grouchnikov/substance-demo) uses the [FormLayout](http://www.jgoodies.com/freeware/libraries/forms/) and components from SwingLabs. In addition, [substance-samples](https://github.com/kirill-grouchnikov/substance-samples) has a collection of self-contained samples that show more advanced usage of Substance APIs for custom application skinning.

Have a question? See the [FAQ](www/docs/faq.md) and the list of [known issues](www/docs/known-issues.md), and if you don't see an answer there, [file a bug](https://github.com/kirill-grouchnikov/substance/issues) or [get in touch](http://www.pushing-pixels.org/about-kirill).

### Adding Substance to your application

If you've skipped [getting started](www/docs/getting-started.md) page, to add Substance to your app follow these steps:
* Go to the [main project page](https://github.com/kirill-grouchnikov/substance).
* Navigate to the **drop** folder. This should take you to the sub-folder that contains the downloads for the latest / earlier releases.
* Click on the specific *X.Y.ZZ* row. It's highly recommended to always use the latest stable release which is 8.0.0 at the present.
* Download *substance-X.Y.ZZ.jar* file from there.
* Also download *trident-L.M.NN.jar* file from the same folder - this is the [Trident animation library](https://github.com/kirill-grouchnikov/trident) that is a required runtime dependency.

### Configure and customize

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/substance/master/www/images/screenshots/skins/nebulabrickwall1.png" width="340" height="254" border=0 align="left">
It is easy to customize and extend Substance to fit your visual needs.

Substance comes with an extensive set of [APIs](www/docs/api.md) to add custom functionality to your components. The overall visuals are controlled by [skins](www/docs/skins/overview.md), with each skin defined by [color scheme bundles](www/docs/skins/colorschemebundles.md) and [painters](www/docs/painters/overview.md). 

The [skinning primer](www/docs/painters/custom-skinning.md) has a few tips on how to use the different Substance painters in applications that wish to paint custom components in a way that is consistent with other Substance visuals. The [substance-samples](https://github.com/kirill-grouchnikov/substance-samples) project provides additional self-contained mini-applications that show more advanced usage of Substance APIs for pixel-perfect skinning.

### Plugins

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/substance/master/www/images/learn/ribbon.png" width="340" height="210" border=0 align="right">

While the core Swing components cover the base UI functionality that most applications require, modern UIs need modern UI components. To address this need, Substance comes with a built-in support to define and load plugins that provide consistent appearance and interaction patterns for third-party components.

During the initialization of your application in the `main()` method - before creating your first window - use these `SubstanceCortex.GlobalScope` APIs to register plugins:
* `registerComponentPlugin()` for registering component plugins
* `registerSkinPlugin()` for registering skin plugins

[Substance plugin](https://github.com/kirill-grouchnikov/substance-flamingo) for [Flamingo components](https://github.com/kirill-grouchnikov/flamingo) is the only actively developed and fully supported first-party component plugin.

[Substance plugin](https://github.com/kirill-grouchnikov/substance-swingx) for SwingX components is being kept in sync with the latest core Substance library. However, since the core SwingX project is effectively dead (last official 1.6.4 release is from July 2013), there is no more active new development in that plugin.

[Substance Extras pack](https://github.com/kirill-grouchnikov/substance-extras) provides additional watermarks, color schemes and skins and is another example of a Substance plugin.
