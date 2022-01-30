package controller.filewriting;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import model.imaging.PixelImage;

/**
 * Abstract class representing a file writer that uses file types from the ImageIO library. Holds
 * the file type to designate what kind of file to write.
 */
public abstract class AbstractImageIOWriter implements IImageFileWriter {

  protected final String fileType;

  /**
   * Creates a new instance of the class with the given file type.
   *
   * @param fileType String of the file type.
   * @throws IllegalArgumentException If the filetype is null or not supported by ImageIO.
   */
  protected AbstractImageIOWriter(String fileType) throws IllegalArgumentException {
    if (fileType == null) {
      throw new IllegalArgumentException("Null file type.");
    }
    if (!Arrays.asList(ImageIO.getWriterFileSuffixes()).contains(fileType)) {
      throw new IllegalArgumentException("File extension is not a valid file suffix for an image.");
    }
    this.fileType = fileType;
  }

  /**
   * Writes an image file of the desired type of this object using the given filename and image.
   *
   * @param filename File path to be used for the file.
   * @param image    Image to be written to an image file.
   * @throws IOException              If writing fails.
   * @throws IllegalArgumentException If any argument is null.
   */
  public void writeFile(String filename, PixelImage image)
      throws IOException, IllegalArgumentException {
    if (filename == null || image == null) {
      throw new IllegalArgumentException("Argument cannot be null.");
    }
    File file = new File(filename);
    File parent = file.getParentFile();
    if (parent != null) {
      parent.mkdirs();
    }
    FileOutputStream output = new FileOutputStream(file);

    int height = image.getPixels().size();
    int width = image.getPixels().get(0).size();

    BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int red = image.getPixels().get(i).get(j).getColor().getRed();
        int green = image.getPixels().get(i).get(j).getColor().getGreen();
        int blue = image.getPixels().get(i).get(j).getColor().getBlue();

        Color pixelColor = new Color(red, green, blue);
        outputImage.setRGB(j, i, pixelColor.getRGB());
      }
    }

    ImageIO.write(outputImage, this.fileType, output);
    output.close();

  }
}
