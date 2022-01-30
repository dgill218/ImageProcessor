package filters.flippingtransformation;

import java.util.ArrayList;
import java.util.List;

import model.imaging.Image;
import model.imaging.PixelImage;
import model.imaging.pixel.IPixel;

/**
 * Class to represent an image flipped horizontally down the middle.
 */
public class FlipHorizontal implements IFlipTransform {

  /**
   * Applies a horizontal flip on the image by rearranging its pixels.
   *
   * @param image Image being flipped.
   * @return The transformed image.
   * @throws IllegalArgumentException If the image is null.
   */
  @Override
  public PixelImage flipTransform(PixelImage image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image can't be null");
    }
    List<List<IPixel>> imagePixels = new ArrayList<>(image.getPixels());
    List<ArrayList<IPixel>> newImagePixels = new ArrayList<>();

    for (int i = 0; i < imagePixels.size(); i++) {
      List<IPixel> oldPixels = imagePixels.get(i);
      ArrayList<IPixel> newPixels = new ArrayList<>();
      for (int j = oldPixels.size() - 1; j >= 0; j--) {
        newPixels.add(oldPixels.get(j));
      }
      newImagePixels.add(newPixels);
    }
    return new Image(newImagePixels);
  }

}