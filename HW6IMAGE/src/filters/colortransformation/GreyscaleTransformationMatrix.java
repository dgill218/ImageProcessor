package filters.colortransformation;

import filters.Kernel;

/**
 * Represents a Greyscale Transformation which creates a kernel matrix that contains weighted sums
 * for application.
 */
public class GreyscaleTransformationMatrix extends AbstractMatrixColorTransformation {

  /**
   * Constructs a GreyscaleTransformation with luma values that will allow for a pixel to be
   * greyscale.
   */
  public GreyscaleTransformationMatrix() {
    super(new Kernel(3, 3, new double[][]{{0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}}));
  }
}