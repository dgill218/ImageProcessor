package model.imaging;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import model.graph.Histogram;
import model.graph.Line;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.PixelImpl;

/**
 * Class to represent an image made of pixels.
 */
public class Image implements PixelImage {
  private final List<ArrayList<IPixel>> pixels;
  private final Histogram histogram;

  /**
   * Constructs an image object from the given 2D list of pixels.
   *
   * @param pixels A 2d array containing pixels of the class IPixel.
   */
  public Image(List<ArrayList<IPixel>> pixels) {
    if (pixels == null) {
      throw new IllegalArgumentException("Pixels cannot be null!");
    }
    this.pixels = this.replicate(pixels);
    this.histogram = new Histogram(pixels);
  }


  /**
   * Replicates a 2D list of pixels.
   *
   * @param pixels the image's pixels
   * @return a 2D replicated list of the image's pixels.
   */
  private List<ArrayList<IPixel>> replicate(List<ArrayList<IPixel>> pixels) {
    List<ArrayList<IPixel>> tempPixels = new ArrayList<>();
    for (ArrayList<IPixel> row : pixels) {
      ArrayList<IPixel> rowCopy = new ArrayList<>();
      for (IPixel pixel : row) {
        rowCopy.add(new PixelImpl(pixel.getPosn(), pixel.getColor()));
      }
      tempPixels.add(rowCopy);
    }
    return tempPixels;
  }

  /**
   * Gets this images pixels.
   *
   * @return a 2D list of an image's pixels.
   */
  @Override
  public List<List<IPixel>> getPixels() {
    return new ArrayList<>(pixels);
  }

  /**
   * Saves an image as a PPM file given the name of the file to save it as.
   *
   * @param filename The name of the ppm file to create.
   * @throws IOException Thrown if the file output stream does not function correctly.
   */
  public void saveImageAsPPM(String filename) throws IOException {
    FileOutputStream outputStream = new FileOutputStream(filename);
    StringBuilder output = new StringBuilder();
    output.append("P3");
    output.append("\n" + this.pixels.get(1).size() + " " + this.pixels.size());
    output.append("\n255");
    for (List<IPixel> pixelList : this.pixels) {
      for (IPixel pixel : pixelList) {
        output.append("\n");
        output.append(pixel.getColor().getRed());
        output.append("\n");
        output.append(pixel.getColor().getGreen());
        output.append("\n");
        output.append(pixel.getColor().getBlue());
      }
    }
    outputStream.write(output.toString().getBytes());
    outputStream.close();
  }


  /**
   * Saves an image as a file type based on the name of the file to save it as.
   *
   * @param outputName The name of the file to create.
   * @throws IOException Thrown if the file output stream does not function correctly.
   */
  public void saveImageAs(String outputName) throws IOException {
    int width = this.pixels.get(1).size();
    int height = this.pixels.size();
    BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (List<IPixel> pixelList : this.pixels) {
      for (IPixel pixel : pixelList) {
        int red = pixel.getColor().getRed();
        int green = pixel.getColor().getGreen();
        int blue = pixel.getColor().getBlue();
        int value = (red << 16) + (green << 8) + blue;
        output.setRGB(pixel.getPosn().getX(), pixel.getPosn().getY(), value);
      }
    }
    /*
    description: Standard BMP Image Writer     format names: [bmp, BMP]
    description: Standard JPEG Image Writer    format names: [JPEG, jpeg, JPG, jpg]
    description: Standard WBMP Image Writer    format names: [wbmp, WBMP]
    description: Standard PNG image writer     format names: [png, PNG]
    description: Standard GIF image writer     format names: [gif, GIF]
    description: Standard TIFF image writer    format names: [tif, TIF, tiff, TIFF]
     */
    String[] splitAtFormat = outputName.split("\\.", 2);
    ImageIO.write(output, splitAtFormat[1], new File(outputName));
  }

  public List<Line> getLines() {
    return this.histogram.getLines();
  }
}

