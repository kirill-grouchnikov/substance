/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pushingpixels.substance.internal.contrib.intellij;

import java.awt.AWTError;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.registry.Registry;
import java.util.WeakHashMap;

import org.pushingpixels.lafwidget.utils.LookUtils;

/**
 * @author max
 */
public class UIUtil {
  /**
   * Utility class for retina routine
   */
  private final static class DetectRetinaKit {

    private final static WeakHashMap<GraphicsDevice, Boolean> devicesToRetinaSupportCacheMap = new WeakHashMap<GraphicsDevice, Boolean>();

    /**
     * The best way to understand whether we are on a retina device is [NSScreen backingScaleFactor]
     * But we should not invoke it from any thread. We do not have access to the AppKit thread
     * on the other hand. So let's use a dedicated method. It is rather safe because it caches a
     * value that has been got on AppKit previously.
     */
    private static boolean isOracleMacRetinaDevice (GraphicsDevice device) {

      if (LookUtils.IS_VENDOR_APPLE) return false;

      Boolean isRetina  = devicesToRetinaSupportCacheMap.get(device);

      if (isRetina != null){
        return isRetina;
      }

      Method getScaleFactorMethod = null;
      try {
        getScaleFactorMethod = Class.forName("sun.awt.CGraphicsDevice").getMethod("getScaleFactor");
      } catch (ClassNotFoundException e) {
      } catch (NoSuchMethodException e) {
      }

      try {
        isRetina =  getScaleFactorMethod == null || (Integer)getScaleFactorMethod.invoke(device) != 1;
      } catch (IllegalAccessException e) {
        isRetina = false;
      } catch (InvocationTargetException e) {
        isRetina = false;
      } catch (IllegalArgumentException e) {
        isRetina = false;
      }

      devicesToRetinaSupportCacheMap.put(device, isRetina);

      return isRetina;
    }

    /*
      Could be quite easily implemented with [NSScreen backingScaleFactor]
      and JNA
     */
    //private static boolean isAppleRetina (Graphics2D g2d) {
    //  return false;
    //}

    /**
     * For JDK6 we have a dedicated property which does not allow to understand anything
     * per device but could be useful for image creation. We will get true in case
     * if at least one retina device is present.
     */
    private static boolean hasAppleRetinaDevice() {
      return (Float)Toolkit.getDefaultToolkit()
        .getDesktopProperty(
          "apple.awt.contentScaleFactor") != 1.0f;
    }

    /**
     * This method perfectly detects retina Graphics2D for jdk7+
     * For Apple JDK6 it returns false.
     * @param g graphics to be tested
     * @return false if the device of the Graphics2D is not a retina device,
     * jdk is an Apple JDK or Oracle API has been changed.
     */
    private static boolean isMacRetina(Graphics2D g) {
      GraphicsDevice device = g.getDeviceConfiguration().getDevice();
      return isOracleMacRetinaDevice(device);
    }

    /**
     * Checks that at least one retina device is present.
     * Do not use this method if your are going to make decision for a particular screen.
     * isRetina(Graphics2D) is more preferable
     *
     * @return true if at least one device is a retina device
     */
    private static boolean isRetina() {
      if (LookUtils.IS_VENDOR_APPLE) {
        return hasAppleRetinaDevice();
      }

      // Oracle JDK

      if (LookUtils.IS_OS_MAC) {
        GraphicsEnvironment e
          = GraphicsEnvironment.getLocalGraphicsEnvironment();

        GraphicsDevice[] devices = e.getScreenDevices();

        //now get the configurations for each device
        for (GraphicsDevice device : devices) {
          if (isOracleMacRetinaDevice(device)) {
            return true;
          }
        }
      }

      return false;
    }
  }

  public static boolean isRetina (Graphics2D graphics) {
    if (LookUtils.IS_OS_MAC && LookUtils.IS_JAVA_7_OR_LATER) {
      return DetectRetinaKit.isMacRetina(graphics);
    } else {
      return isRetina();
    }
  }

  //public static boolean isMacRetina(Graphics2D g) {
  //  return DetectRetinaKit.isMacRetina(g);
  //}


  private static final Ref<Boolean> ourRetina = Ref.create(LookUtils.IS_OS_MAC ? null : false);

  public static boolean isRetina() {
    if (GraphicsEnvironment.isHeadless()) return false;

    //Temporary workaround for HiDPI on Windows/Linux
    if ("true".equalsIgnoreCase(System.getProperty("is.hidpi"))) {
      return true;
    }

//    if (Registry.is("new.retina.detection")) {
      return DetectRetinaKit.isRetina();
//    } else {
//      synchronized (ourRetina) {
//        if (ourRetina.isNull()) {
//          ourRetina.set(false); // in case HiDPIScaledImage.drawIntoImage is not called for some reason
//
//          if (SystemInfo.isJavaVersionAtLeast("1.6.0_33") && SystemInfo.isAppleJvm) {
//            if (!"false".equals(System.getProperty("ide.mac.retina"))) {
//              ourRetina.set(IsRetina.isRetina());
//              return ourRetina.get();
//            }
//          }
//          else if (SystemInfo.isJavaVersionAtLeast("1.7.0_40") /*&& !SystemInfo.isOracleJvm*/) {
//            try {
//              GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
//              final GraphicsDevice device = env.getDefaultScreenDevice();
//              Integer scale = ReflectionUtil.getField(device.getClass(), device, int.class, "scale");
//              if (scale != null && scale.intValue() == 2) {
//                ourRetina.set(true);
//                return true;
//              }
//            }
//            catch (AWTError ignore) {
//            }
//            catch (Exception ignore) {
//            }
//          }
//          ourRetina.set(false);
//        }
//
//        return ourRetina.get();
//      }
//    }
  }
}
