## Substance look and feel - plugins

While the core library provides consistent visuals and animation effects for core Swing components, it also provides an extensible plugin architecture to extend the library functionality to third-party components - such as [Flamingo](https://github.com/kirill-grouchnikov/flamingo) and SwingX component suites.

### Creating a plugin

Substance plugin is a `jar` file that contains at least the following two files:
* `META-INF/substance-plugin.xml`
* A class that implements `org.pushingpixels.lafplugin.LafComponentPlugin` interface

The structure of `META-INF/substance-plugin.xml` descriptor is:

```xml
  <substance-plugin>
    <plugin-class>Class name</plugin-class>
  </substance-plugin>
```

The class name is a fully qualified name of a non-abstract class with a default zero-argument constructor that implements the `org.pushingpixels.lafplugin.LafComponentPlugin` interface.

The `org.pushingpixels.lafplugin.LafComponentPlugin` interface specifies the following methods which must be implemented by the plugin:

```java
  /**
   * Initializes <code>this</code> plugin.
   */
  public void initialize();

  /**
   * Unitializes <code>this</code> plugin.
   */
  public void uninitialize();

  /**
   * Retrieves a collection of custom settings based on the specified theme.
   * The entries in the array should be pairwise, odd being symbolic name of a
   * setting, and even being the setting value.
   *
   * @param themeInfo
   *            Theme information object. Can be {@link MetalTheme}, for
   *            instance or any other LAF-specific object.
   * @return Collection of custom settings based on the specified theme. The
   *         entries in the array should be pairwise, odd being symbolic name
   *         of a setting, and even being the setting value.
   */
  public Object[] getDefaults(Object themeInfo);
```  

### Reference plugins

There are two reference implementations of Substance plugins:

* Plugin for the [Flamingo component suite](https://github.com/kirill-grouchnikov/flamingo) available in the [Substance Flamingo plugin](https://github.com/kirill-grouchnikov/substance-flamingo) under BSD license.
* Plugin for the SwingX component suite available in the [Substance SwingX plugin](https://github.com/kirill-grouchnikov/substance-swingx) under LGPL license.

### Using plugins

In order to use a Substance plugin, just put it in the classpath (along with the `substance.jar` and `trident.jar`) and it will be picked up automatically.
