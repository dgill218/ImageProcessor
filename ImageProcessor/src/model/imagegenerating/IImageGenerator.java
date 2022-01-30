package model.imagegenerating;


import model.imaging.PixelImage;

/**
 * Interface to represent a function object that generates an ImageInterface programmatically.
 */
public interface IImageGenerator {

  /**
   * Creates the generate programmatic image.
   *
   * @return The generated image.
   */
  PixelImage generateImage();

}
