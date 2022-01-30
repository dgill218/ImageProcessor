package filters;

/**
 * Class to represent a sharpening filter on an image.
 */
public class SharpenFilter extends AbstractFilter {

  /**
   * Constructor for a sharpening transformation with a matrix of values that will allow for a pixel
   * to be sharpened.
   */
  public SharpenFilter() {

    super(new Kernel(5, 5, new double[][]{{-0.125, -0.125, -0.125, -0.125, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, 0.25, 1, 0.25, -0.12},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, -0.125, -0.125, -0.125, -0.125}}));
  }
}
