package filters.colortransformation;

import filters.Kernel;

/**
 * Class to represent a sepia transformation on a pixel.
 */
public class SepiaTransformation extends AbstractMatrixColorTransformation {

  /**
   * Constructor for a sepia transformation with values that will allow for a pixel to be
   * sepia transformed.
   */
  public SepiaTransformation() {

    super(new Kernel(3, 3, new double[][]{{0.393, 0.769, 0.189},
        {0.349, 0.686, 0.168},
        {0.272, 0.534, 0.131}}));
  }


}
