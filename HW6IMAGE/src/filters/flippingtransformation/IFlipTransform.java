package filters.flippingtransformation;

import model.imaging.PixelImage;

/**
 * Interface representing an image transformation of flipping.
 */
public interface IFlipTransform {

  /**
   * Applies some flip transformation on a given image.
   * @param image image being flipped.
   * @return the flipped image.
   * @throws IllegalArgumentException if the image is null/
   */
  PixelImage flipTransform(PixelImage image) throws IllegalArgumentException;

}