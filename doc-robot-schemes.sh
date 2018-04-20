export JAVA_HOME=`/usr/libexec/java_home -v 9`
alias JAVA="java"

SUBSTANCE_VERSION=8.1.00-dev
TRIDENT_VERSION=1.5.00
CLASSPATH=../substance-tools/drop/$SUBSTANCE_VERSION/substance-tools-$SUBSTANCE_VERSION.jar:../substance-demo/drop/$SUBSTANCE_VERSION/substance-demo-$SUBSTANCE_VERSION.jar:./drop/$SUBSTANCE_VERSION/substance-$SUBSTANCE_VERSION.jar:../substance-extras/drop/$SUBSTANCE_VERSION/substance-extras-$SUBSTANCE_VERSION.jar:./lib/trident-$TRIDENT_VERSION.jar:../substance-demo/lib/build/*:../substance-demo/lib/test/*:../substance-swingx/lib/swingx-all-1.6.4.jar

JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.AquaScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.BarbyPinkScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.BottleGreenScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.BrownScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.CharcoalScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.CremeScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.DarkVioletScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.EbonyScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.JadeForestScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.LightAquaScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.LimeGreenScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.OliveScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.OrangeScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.PurpleScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.RaspberryScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.SepiaScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.SteelBlueScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.SunGlareScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.SunsetScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.TerracottaScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.UltramarineScheme

JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.DerivedSaturatedScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.DerivedDesaturatedScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.DerivedInvertedScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.DerivedNegatedScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.DerivedHueShiftedScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.DerivedShadedScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.DerivedTintedScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.DerivedTonedScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.DerivedShiftedScheme
JAVA -cp $CLASSPATH org.pushingpixels.tools.substance.main.docrobot.RobotMain org.pushingpixels.tools.substance.main.docrobot.schemes.DerivedShiftedBackgroundScheme
