package filters;


/**
 * Class a kernel which may be used for image processing.
 */
public class Kernel implements IKernel {

  private int width;
  private int height;
  private double[][] values;

  /**
   * Constructs a kernel of given width, height, and matrix values.
   *
   * @param width  width of the 2D kernel array
   * @param height height of the 2D kernel array
   * @param values List of List of Doubles representing the values of the kernel
   */
  public Kernel(int width, int height, double[][] values) {
    if (width == 0 || height == 0 || values == null || values.length == 0) {
      throw new IllegalArgumentException("Invalid Kernel");
    }

    this.width = width;
    this.height = height;
    this.values = values;
  }

  /**
   * Alternative constructor that is empty.
   */
  public Kernel() {
    // default empty constructor
  }

  @Override
  public final int getHeight() {
    return height;
  }

  @Override
  public final int getWidth() {
    return width;
  }

  @Override
  public final double[][] getValues() {
    return values;
  }

  @Override
  public final double getValueAt(int x, int y) {
    try {
      return this.values[y][x];
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Index is out of bounds.");
    }
  }


}
