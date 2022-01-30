
package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import model.imaging.Color;
import model.imaging.Posn;
import model.imaging.pixel.PixelImpl;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static void readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
      }
    }
  }

  /**
   * Static method that retrieves the pixels in a list of an image under the filename.
   *
   * @param filename name of the file.
   * @return A 2D list of pixels representing this image file.
   */
  public static List getPixels(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not Found");
    }

    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      try {
        if (s.charAt(0) != '#') {
          builder.append(s + System.lineSeparator());
        }
      } catch (StringIndexOutOfBoundsException e) {
        throw new IllegalArgumentException("Invalid String");
      }
    }

    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    //TODO NOT BEING USED
    int maxValue = sc.nextInt();

    List pixels = new ArrayList();

    for (int i = 0; i < height; i++) {
      List temp = new ArrayList();
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        temp.add(new PixelImpl(new Posn(j, i), new Color(r, g, b)));
      }
      pixels.add(temp);
    }
    return pixels;
  }

  /**
   * demo main function that loads images.
   *
   * @param args user inputs for loading images.
   */
  public static void main(String[] args) {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "sample.ppm";
    }

    ImageUtil.readPPM(filename);
  }


  /**
   * Static method that retrieves the pixels in a list of an image under the filename.
   *
   * @param pathname name of the file. The file cannot be a PPM file.
   * @return A 2D list of pixels representing this image file.
   */
  public static List readImage(String pathname) {

    BufferedImage img = null;
    try {
      img = ImageIO.read(new File(pathname));
    } catch (IOException e) {
      //TODO THROW SOMN
    }
    int height = img.getHeight();
    int width = img.getWidth();

    List pixels = new ArrayList();

    int rgb;
    int r;
    int g;
    int b;
    for (int i = 0; i < height; i++) {
      List temp = new ArrayList();
      for (int j = 0; j < width; j++) {

        rgb = img.getRGB(j, i);
        r = (rgb >> 16) & 0xFF;
        g = (rgb >> 8) & 0xFF;
        b = rgb & 0xFF;
        //   System.out.println(b);
        Color color = new Color(r, g, b);
        temp.add(new PixelImpl(new Posn(j, i), color));
      }
      pixels.add(temp);
    }

    return pixels;
  }

}

