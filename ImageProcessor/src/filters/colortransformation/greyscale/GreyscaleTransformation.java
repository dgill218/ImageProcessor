package filters.colortransformation.greyscale;

import java.util.ArrayList;
import java.util.List;

import filters.colortransformation.IColorTransform;
import filters.FilterClamp;
import model.imaging.Color;
import model.imaging.Image;
import model.imaging.PixelImage;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.PixelImpl;

/**
 * Class representing a grey scale filter on a pixel.
 */
public class GreyscaleTransformation implements IColorTransform {
  public double[][] lumaVals;

  /**
   * Constructor for a luma greyscale filter.
   */
  public GreyscaleTransformation() {
    this.lumaVals = new double[][]{{0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}};
  }

  /**
   * Applies a color transformation to a given image.
   *
   * @param image Image being transformed
   * @return the image transformed
   * @throws IllegalArgumentException if the image is null
   */
  public PixelImage applyColorTransformation(PixelImage image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image can't be null.");
    }
    List<List<IPixel>> imagePixels = image.getPixels();
    return new Image(transform(image, imagePixels));
  }

  protected List<ArrayList<IPixel>> transform(PixelImage image,
                                              List<List<IPixel>> imagePixels) {
    List<ArrayList<IPixel>> updatedPixels = new ArrayList<>();
    for (List<IPixel> l : imagePixels) {
      ArrayList<IPixel> row = new ArrayList<>();
      for (IPixel p : l) {
        row.add(colorTransform(p));
      }
      updatedPixels.add(row);
    }
    return updatedPixels;
  }

  /**
   * Applies the color transformation to the given pixel. Any out of
   * range rgb value is clamped to the minimum value of 0 or the maximum value of 255.
   *
   * @param pixel pixel being transformed.
   * @return The transformed pixel.
   */
  protected IPixel colorTransform(IPixel pixel) {

    int changedRed = FilterClamp.clamp((int) (pixel.getColor().getRed() * this.lumaVals[0][0]
            + pixel.getColor().getGreen() * this.lumaVals[0][1]
            + pixel.getColor().getBlue() * this.lumaVals[0][2]));
    int changedGreen = FilterClamp.clamp((int) (pixel.getColor().getRed() * this.lumaVals[1][0]
            + pixel.getColor().getGreen() * this.lumaVals[1][1]
            + pixel.getColor().getBlue() * this.lumaVals[1][2]));
    int changedBlue = FilterClamp.clamp((int) (pixel.getColor().getRed() * this.lumaVals[2][0]
            + pixel.getColor().getGreen() * this.lumaVals[2][1]
            + pixel.getColor().getBlue() * this.lumaVals[2][2]));

    return new PixelImpl(new Posn(pixel.getPosn().getX(), pixel.getPosn().getY()),
            new Color(changedRed,
                    changedGreen, changedBlue));

  }
}