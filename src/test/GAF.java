package test;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class GAF {

 public static void main(String args[]) {
   GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
   Font[] fonts = ge.getAllFonts();
   for (int i=0;i<fonts.length;i++) {
    System.out.println("Fam="+fonts[i].getFamily()+
          ", Full="+ fonts[i].getFontName()+
        ", ps="+ fonts[i].getPSName()+", "+fonts[i]);
   }
 }
}