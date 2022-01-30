package filters.colortransformation.greyscale;

import filters.colortransformation.AbstractColorTransformation;
import model.imaging.Color;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.PixelImpl;

/**
 * Class to represent a pixel greyscale in terms of its green component.
 */
public class GreenComponent extends AbstractColorTransformation {

  /**
   * Empty constructor for GreenGreyscale.
   */
  public GreenComponent() {
    //Doesn't need any initializations.
  }

  /**
   * Applies the color transformation to the given pixel by updating its rgb values. Any out of
   * range rgb value is clamped to the minimum value of 0 or the maximum value of 255.
   *
   * @param pixel Pixel being transformed.
   * @return The transformed pixel.
   */
  protected IPixel colorTransform(IPixel pixel) {


    int green = pixel.getColor().getGreen();

    int changedRed = green;
    int changedBlue = green;

    return new PixelImpl(new Posn(pixel.getPosn().getX(), pixel.getPosn().getY()),
            new Color(changedRed,
                    green, changedBlue));

  }
}
