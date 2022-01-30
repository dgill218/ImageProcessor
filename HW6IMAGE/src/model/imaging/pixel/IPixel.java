package model.imaging.pixel;

import model.imaging.IColor;
import model.imaging.Posn;

/**
 * Interface representing a pixel object.
 */
public interface IPixel {

  /**
   * Gets the color of this pixel.
   *
   * @return the color of this pixel
   */
  IColor getColor();

  /**
   * Gets the position of the pixel.
   *
   * @return
   */
  Posn getPosn();
}
