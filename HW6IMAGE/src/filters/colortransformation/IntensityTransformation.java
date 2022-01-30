package filters.colortransformation;

import model.imaging.Color;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.PixelImpl;

/**
 * Class to represent an intensity change on a pixel.
 */
public class IntensityTransformation extends AbstractColorTransformation {

  /**
   * Empty constructor for IntensityChange.
   */
  public IntensityTransformation() {
    //Doesn't need any intializations.
  }

  /**
   * Applies the color transformation to the given pixel by updating its rgb values. Any out of
   * range rgb value is clamped to the minimum value of 0 or the maximum value of 255.
   *
   * @param pixel Pixel being transformed.
   * @return The transformed pixel.
   */
  @Override
  protected IPixel colorTransform(IPixel pixel) {

    int avg = clampValues(pixel.getColor().getRed()
            + pixel.getColor().getGreen()
            + pixel.getColor().getGreen()) / 3;

    return new PixelImpl(new Posn(pixel.getPosn().getX(), pixel.getPosn().getY()), new Color(avg,
            avg, avg));
  }

  /**
   * Clamps a value to 0 or 255 based on its current range.
   *
   * @param val value of the given RBG.
   * @return an integer representing the clamped RGB value.
   */
  private static int clampValues(int val) {
    if (val > 255) {
      return 255;
    } else if (val < 0) {
      return 0;
    }
    return val;
  }
}
