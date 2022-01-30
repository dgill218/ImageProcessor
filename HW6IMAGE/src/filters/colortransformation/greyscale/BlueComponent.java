package filters.colortransformation.greyscale;

import filters.colortransformation.AbstractColorTransformation;
import model.imaging.Color;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.PixelImpl;

/**
 * Class to represent a pixel being greyscale in terms of its blue component.
 */
public class BlueComponent extends AbstractColorTransformation {
  /**
   * Empty constructor for BlueGreyscale.
   */
  public BlueComponent() {
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

    int blue = pixel.getColor().getBlue();

    int changedRed = blue;
    int changedGreen = blue;


    return new PixelImpl(new Posn(pixel.getPosn().getX(), pixel.getPosn().getY()),
            new Color(changedRed,
                    changedGreen, blue));

  }

}
