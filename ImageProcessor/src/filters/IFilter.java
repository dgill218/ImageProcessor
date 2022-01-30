package filters;

import model.imaging.PixelImage;

/**
 * Interface to represent a filter that can be applied on an image.
 */
public interface IFilter {

  /**
   * Transforms the given image by using some filter.
   *
   * @param image The image being filtered.
   * @return The filtered image.
   * @throws IllegalArgumentException If image is null.
   */
  PixelImage transform(PixelImage image) throws IllegalArgumentException;
}
