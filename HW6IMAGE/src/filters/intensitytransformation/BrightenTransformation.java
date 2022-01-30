package filters.intensitytransformation;

import filters.FilterClamp;
import model.imaging.Color;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.PixelImpl;
import model.imaging.Posn;

/**
 * Class to represent a brightening filter on a pixel.
 */
public class BrightenTransformation extends AbstractIntensityTransformation {

  /**
   * Empty constructor for a BrightenTransformation filter.
   */
  public BrightenTransformation() {
    //Doesn't need any initializations.
  }

  /**
   * Applies a brightening on a pixel.
   * If the RGB value is out of range 0-255, it will be clamped to the value.
   *
   * @param pixel pixel being transformed
   * @param val   the value that the pixel will be brightened by
   * @return the transformed pixel.
   */
  @Override
  protected IPixel intensityTransform(IPixel pixel, int val) {

    int changedRed = FilterClamp.clamp((int) (pixel.getColor().getRed() + val));
    int changedGreen = FilterClamp.clamp((int) (pixel.getColor().getGreen() + val));
    int changedBlue = FilterClamp.clamp((int) (pixel.getColor().getBlue() + val));

    return new PixelImpl(new Posn(pixel.getPosn().getX(), pixel.getPosn().getY()),
            new Color(changedRed,
                    changedGreen, changedBlue));
  }


}
