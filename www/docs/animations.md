## Substance look and feel - animations

### Basics

To bring richer user experience to Swing applications, Substance look and feel uses the [Trident animation library](https://github.com/kirill-grouchnikov/trident). This library provides support for animating core and custom Swing components.

While the default animation settings (which transitions to animate, the animation speed, the set of components to animate) were selected to provide a consistent and pleasant out-of-the-box visual experience with zero application configuration, some applications might need to configure the animation settings.

### Using the AnimationConfigurationManager API

The `org.pushingpixels.lafwidget.animation.AnimationConfigurationManager` provides APIs to programmatically disable / enable core and custom animations. You can use the various `allowAnimations` and `disallowAnimations` on all components, on all components of the specified class or on a specific component. For example, here is how you can remove rollover and selection animations from the specific list:

```java
	JList list = ...; // create the list
	AnimationConfigurationManager.getInstance().disallowFades(AnimationFacet.ROLLOVER, list);
	AnimationConfigurationManager.getInstance().disallowFades(AnimationFacet.SELECTION, list);
```

where `org.pushingpixels.lafwidget.animation.AnimationFacet` is an instance of a core or custom animation kind.
