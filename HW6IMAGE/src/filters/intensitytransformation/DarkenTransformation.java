package filters.intensitytransformation;

import model.imaging.Color;
import filters.FilterClamp;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.PixelImpl;

/**
 * Class to represent a darkening filter on a pixel.
 */
public class DarkenTransformation extends AbstractIntensityTransformation {

  /**
   * Empty constructor for a DarkenTransformation filter.
   */
  public DarkenTransformation() {
    //Doesn't need any initializations.
  }

  /**
   * Applies a darkening on the given pixel.
   * If the RGB value is out of range 0-255, it will be clamped to the value.
   *
   * @param pixel pixel being transformed
   * @param val   the value that the pixel will be darkened by
   * @return the transformed pixel.
   */
  @Override
  protected IPixel intensityTransform(IPixel pixel, int val) {

    int changedRed = FilterClamp.clamp((int) (pixel.getColor().getRed() - val));
    int changedGreen = FilterClamp.clamp((int) (pixel.getColor().getGreen() - val));
    int changedBlue = FilterClamp.clamp((int) (pixel.getColor().getBlue() - val));

    return new PixelImpl(new Posn(pixel.getPosn().getX(), pixel.getPosn().getY()),
            new Color(changedRed,
                    changedGreen, changedBlue));
  }

}
