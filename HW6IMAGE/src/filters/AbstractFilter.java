package filters;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import model.imaging.Color;
import model.imaging.Image;
import model.imaging.PixelImage;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.PixelImpl;

/**
 * An abstract class to allow for a filter to be used on an image using a given kernel (must be
 * of odd dimensions).
 */
public abstract class AbstractFilter implements IFilter {

  protected final IKernel kernel;

  /**
   * Constructs an abstract filter with a given kernel.
   *
   * @param kernel kernel being used for filtering.
   * @throws IllegalArgumentException If kernel is null, not square, or not of odd dimensions.
   */
  protected AbstractFilter(IKernel kernel) throws IllegalArgumentException {
    if (kernel == null) {
      throw new IllegalArgumentException("Argument can't be null.");
    }
    if (kernel.getHeight() % 2 == 0 || kernel.getWidth() % 2 == 0) {
      throw new IllegalArgumentException("Kernel dimensions must be odd");
    }
    this.kernel = kernel;
  }

  @Override
  public PixelImage transform(PixelImage image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image can't be null.");
    }
    List<List<IPixel>> imagePixels = image.getPixels();

    return new Image(filtered(imagePixels, image));
  }

  /**
   * Returns the filtered 2D array of pixels of the image.
   *
   * @param pixels Pixels of the image being filtered.
   * @param image  Image being filtered.
   * @return The filtered 2D pixel array.
   */
  protected List<ArrayList<IPixel>> filtered(List<List<IPixel>> pixels,
                                             PixelImage image) {
    List<ArrayList<IPixel>> newPixels = new ArrayList<>();
    for (int i = 0; i < pixels.size(); i++) {
      ArrayList<IPixel> r = new ArrayList<>();
      for (int j = 0; j < pixels.get(0).size(); j++) {
        r.add(filter(pixels.get(i).get(j), image));
      }
      newPixels.add(r);
    }

    return newPixels;

  }

  /**
   * Applies a transformation to a given pixel by use of the given kernel. Values are clamped to 0
   * or 255.
   *
   * @param pixel pixel being filtered.
   * @param image image being filtered.
   * @return The filtered pixel
   */
  protected IPixel filter(IPixel pixel, PixelImage image) {

    List<List<IPixel>> imagePixels = image.getPixels();

    int peripherals = kernel.getHeight() / 2;

    int r = 0;
    int g = 0;
    int b = 0;

    for (int i = peripherals * -1; i <= peripherals; i++) {
      for (int j = peripherals * -1; j <= peripherals; j++) {
        try {
          double kVal = (double) Array
                  .get(Array.get(kernel.getValues(), i + peripherals), j + peripherals);

          int red = imagePixels.get(pixel.getPosn().getY() + i)
                  .get(pixel.getPosn().getX() + j).getColor().getRed();
          int green = imagePixels.get(pixel.getPosn().getY() + i)
                  .get(pixel.getPosn().getX() + j).getColor().getGreen();
          int blue = imagePixels.get(pixel.getPosn().getY() + i)
                  .get(pixel.getPosn().getX() + j).getColor().getBlue();

          red *= kVal;
          green *= kVal;
          blue *= kVal;

          r += red;
          g += green;
          b += blue;

        } catch (IndexOutOfBoundsException ignore) {
          r += 0;
          g += 0;
          b += 0;
        }
      }
    }

    r = FilterClamp.clamp(r);
    g = FilterClamp.clamp(g);
    b = FilterClamp.clamp(b);

    return new PixelImpl(new Posn(pixel.getPosn().getX(), pixel.getPosn().getY()),
            new Color(r, g, b));


  }


}