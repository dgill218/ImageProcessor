package filters.colortransformation;

import java.util.ArrayList;
import java.util.List;

import model.imaging.Image;
import model.imaging.PixelImage;
import model.imaging.pixel.IPixel;

/**
 * Abstract class for any Color transformations.
 */
public abstract class AbstractColorTransformation implements IColorTransform {

  /**
   * Applies a color transformation to a given image.
   * @param image Image being transformed
   * @return the image transformed
   * @throws IllegalArgumentException if the image is null
   */
  public PixelImage applyColorTransformation(PixelImage image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image can't be null.");
    }
    List<List<IPixel>> imagePixels = image.getPixels();
    return new Image(transform(imagePixels));
  }

  /**
   * Applies the given transformation to each pixel in the given image.
   *
   * @param imagePixels the image's pixels.
   * @return a list of list of transformed pixels
   */
  protected List<ArrayList<IPixel>> transform(List<List<IPixel>> imagePixels) {
    List<ArrayList<IPixel>> updatedPixels = new ArrayList<>();
    for (List<IPixel> l : imagePixels) {
      ArrayList<IPixel> row = new ArrayList<>();
      for (IPixel p : l) {
        row.add(colorTransform(p));
      }
      updatedPixels.add(row);
    }
    return updatedPixels;
  }

  protected abstract IPixel colorTransform(IPixel p);




}

