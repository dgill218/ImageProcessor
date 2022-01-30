package model.imaging.pixel;

import java.util.Objects;

import model.imaging.IColor;
import model.imaging.Posn;

/**
 * Class representing a standard pixel that uses a combination or red, blue, and green
 * color values to represent it.
 */
public class PixelImpl implements IPixel {

  private final Posn posn;
  private final IColor color;

  /**
   * Constructs a pixel object given a position and a color.
   * @param position Posn of the pixel.
   * @param color color of the given pixel.
   */
  public PixelImpl(Posn position, IColor color) {

    if (position == null || color == null) {
      throw new IllegalArgumentException("Argument cannot be null.");
    }

    this.posn = position;

    this.color = color;
  }

  @Override
  public IColor getColor() {
    return color;
  }

  @Override
  public Posn getPosn() {
    return new Posn(posn.getX(), posn.getY());
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PixelImpl)) {
      return false;
    }

    PixelImpl other = (PixelImpl) o;
    return this.color.equals(other.color) && this.posn.equals(other.posn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(color, posn);
  }

}
