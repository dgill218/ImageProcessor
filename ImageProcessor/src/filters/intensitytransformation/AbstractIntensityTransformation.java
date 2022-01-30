package filters.intensitytransformation;

import java.util.ArrayList;
import java.util.List;


import model.imaging.Image;
import model.imaging.PixelImage;
import model.imaging.pixel.IPixel;

/**
 * Abstract class for an intensity transformation on an image.
 */
public abstract class AbstractIntensityTransformation implements IIntensityTransform {

  /**
   * Empty constructor for an abstract intensity transformation.
   */
  protected AbstractIntensityTransformation() {
    //Doesn't need any initializations.
  }

  /**
   * Applies the intensity transformation to the given pixel. If the RGB value is out of range
   * 0-255, it will be clamped to the value.
   *
   * @param pixel the pixel being transformed
   * @return the transformed pixel
   */
  protected abstract IPixel intensityTransform(IPixel pixel, int val);

  /**
   * Applies some transformation on the intensity of a given image.
   *
   * @param image image being transformed.
   * @return The transformed image.
   * @throws IllegalArgumentException If the image is null.
   */
  public PixelImage applyTransformation(PixelImage image, int val)
          throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image can't' be null.");
    }
    List<List<IPixel>> imagePixels = image.getPixels();
    return new Image(transform(imagePixels, val));
  }

  /**
   * Applies the intensity transformation on a given image.
   *
   * @param imageOfPixels the image's pixels.
   * @param val           the value that the image is being intensified by.
   * @return the list of transformed pixels.
   */
  protected List transform(List<List<IPixel>> imageOfPixels,
                           int val) {
    List<ArrayList<IPixel>> updated = new ArrayList<>();
    for (List<IPixel> r : imageOfPixels) {
      ArrayList<IPixel> row = new ArrayList<>();
      for (IPixel p : r) {
        row.add(intensityTransform(p, val));
      }
      updated.add(row);
    }
    return updated;
  }


}

