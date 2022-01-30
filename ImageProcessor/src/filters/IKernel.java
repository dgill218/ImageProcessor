package filters;

/**
 * Interface representing a kernel which is used for image filters.
 */
public interface IKernel {

  /**
   * Returns the height (number of rows) of the kernel.
   *
   * @return integer representing the height of the array
   */
  int getHeight();

  /**
   * Returns the width (length of row) of the kernel.
   *
   * @return integer representing the width of the array
   */
  int getWidth();

  /**
   * Gets the values of the matrix of values of the kernel.
   *
   * @return 2D matrix representing the kernel's values.
   */
  double[][] getValues();

  /**
   * Returns the matrix value at an x and y index of the kernel.
   *
   * @param x index of the array representing the row
   * @param y index of the array representing the column
   */
  double getValueAt(int x, int y);
}
