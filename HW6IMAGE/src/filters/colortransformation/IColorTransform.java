package filters.colortransformation;

import model.imaging.PixelImage;

/**
 * Interface to represent a color transformation action.
 */
public interface IColorTransform {

  /**
   * Applies a transformation on the color of a given image.
   * @param image image being transformed.
   * @return The transformed image.
   * @throws IllegalArgumentException If the image is null.
   */
  PixelImage applyColorTransformation(PixelImage image);

}

