package filters;

/**
 * Class representing the filter for clamping which sets the color value to 0 or 255 if out of
 * generic range.
 */
public class FilterClamp {


  /**
   * Clamps a value to 0 or 255 based on its current range.
   *
   * @param val value of the given RBG.
   * @return an integer representing the clamped RGB value.
   */
  public static int clamp(int val) {
    if (val > 255) {
      return 255;
    } else if (val < 0) {
      return 0;
    }
    return val;
  }
}
