package filters.colortransformation;

import model.imaging.Color;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.PixelImpl;

/**
 * Class to represent a value change on a pixel.
 */
public class ValueTransformation extends AbstractColorTransformation {


  /**
   * Empty constructor for a ValueChange filter.
   */
  public ValueTransformation() {
    //No initialization for empty constructor
  }

  /**
   * Applies the color transformation to the given pixel by updating its rgb values. Any out of
   * range rgb value is clamped to the minimum value of 0 or the maximum value of 255.
   *
   * @param pixel Pixel being transformed.
   * @return The transformed pixel.
   */
  protected IPixel colorTransform(IPixel pixel) {

    int largest = getMaxColor(pixel.getColor().getRed(),
            pixel.getColor().getGreen(),
            pixel.getColor().getBlue());

    return new PixelImpl(new Posn(pixel.getPosn().getX(), pixel.getPosn().getY()),
            new Color(largest, largest, largest));

  }

  /**
   * Determines the max RGB value in a pixel.
   * @return The maximum value of the pixel's RGB.
   */
  private int getMaxColor(int r, int g, int b) {
    return Math.max(r, Math.max(g, b));
  }
}
