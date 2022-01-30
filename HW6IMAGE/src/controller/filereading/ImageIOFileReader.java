package controller.filereading;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import model.imaging.Color;
import model.imaging.Image;
import model.imaging.PixelImage;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.PixelImpl;

/**
 * Class to represent a function object to read in file types supported by the ImageIO library.
 * Files are read in through a given file name.
 */
public class ImageIOFileReader implements IFileReader {

  @Override
  public PixelImage readImageFromFile(String filename)
      throws IllegalArgumentException {
    if (filename == null) {
      throw new IllegalArgumentException("Filename cannot be null.");
    }
    try {
      File imageFile = new File(filename);
      BufferedImage image = ImageIO.read(imageFile);
      if (image == null) {
        throw new IllegalArgumentException("File could not be read.");
      }
      int imageHeight = image.getHeight();
      int imageWidth = image.getWidth();

      List<ArrayList<IPixel>> pixels = new ArrayList<>();

      for (int i = 0; i < imageHeight; i++) {
        ArrayList<IPixel> row = new ArrayList<>();
        for (int j = 0; j < imageWidth; j++) {
          java.awt.Color pixelRGB = new java.awt.Color(image.getRGB(j, i));
          row.add(new PixelImpl(new Posn(j, i),
              new Color(pixelRGB.getRed(), pixelRGB.getGreen(), pixelRGB.getBlue())));
        }
        pixels.add(row);
      }

      return new Image(pixels);


    } catch (IOException e) {
      throw new IllegalArgumentException("File does not exist.");
    }

  }

  /**
   * Reads any images that are supported by the imageIO class, such as png and jpegs.
   * @param pathname The file path in string format.
   * @return A list of the pixels in the image
   */
  public static List readImage(String pathname) throws IOException {

    BufferedImage img = null;
    try {
      img = ImageIO.read(new File(pathname));
    } catch (IOException e) {
      throw new IOException("Image can't be read");
    }
    int height = img.getHeight();
    int width = img.getWidth();

    List pixels = new ArrayList();

    int rgb;
    int red;
    int green;
    int blue;
    for (int i = 0; i < height; i++) {
      List temp = new ArrayList();
      for (int j = 0; j < width; j++) {

        rgb = img.getRGB(j, i);
        red = (rgb >> 16) & 0xFF;
        green = (rgb >> 8) & 0xFF;
        blue = rgb & 0xFF;
        Color color = new Color(red, green, blue);
        temp.add(new PixelImpl(new Posn(j, i), color));
      }
      pixels.add(temp);
    }

    return pixels;

  }



}
