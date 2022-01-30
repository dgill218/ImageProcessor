package model.imaging;

import java.util.Objects;

/**
 * Class representing a color object.
 */
public class Color implements IColor {

  private final int red;
  private final int green;
  private final int blue;


  /**
   * Constructor for a color object.
   *
   * @param r red component of the color
   * @param g green component of the color
   * @param b blue component of the color
   * @throws IllegalArgumentException thrown if a value is outside (0,255).
   */
  public Color(int r, int g, int b) throws IllegalArgumentException {
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("Values must be between 0-255");
    }
    red = r;
    green = g;
    blue = b;
  }

  /**
   * Gets the red component of this color.
   *
   * @return the red value of this color.
   */
  @Override
  public int getRed() {
    return red;
  }

  /**
   * Gets the green component of this color.
   *
   * @return the green value of this color.
   */
  @Override
  public int getGreen() {
    return green;
  }

  /**
   * Gets the blue component of this color.
   *
   * @return the blue value of this color.
   */
  @Override
  public int getBlue() {
    return blue;
  }

  /**
   * Finds whether a color is equivalent to a given color.
   *
   * @param o the given object to be checked as a color
   * @return true of the colors are equals, false if otherwise
   */
  public boolean equals(Object o) {
    try {
      Color color = (Color) o;
      return (color.getRed() == this.red) && (color.getGreen() == this.green)
              && (color.getBlue() == this.blue);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("This object is not an RGB Pixel");
    }
  }

  /**
   * Finds the hash of the color's variables.
   *
   * @return an integer representation of the give color variable's hash.
   */
  @Override
  public int hashCode() {
    return Objects.hash(red, green, blue);
  }

}
