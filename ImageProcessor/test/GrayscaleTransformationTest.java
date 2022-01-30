import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import filters.colortransformation.greyscale.GreyscaleTransformation;
import model.imagegenerating.ImageCreator;
import model.imaging.Color;
import model.imaging.PixelImage;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing class for GrayScaleTransformation function objects.
 */
public class GrayscaleTransformationTest {

  private PixelImage blackRedCheckerBoard;
  private PixelImage greenRedCheckerBoard;
  private GreyscaleTransformation testGrayscale;

  @Before
  public void setUp() throws Exception {
    blackRedCheckerBoard = new ImageCreator(2, 2,
            new ArrayList<>(Arrays.asList(new Color(255, 0, 0), new Color(0, 0, 0))))
            .generateImage();
    greenRedCheckerBoard = new ImageCreator(2, 2,
            new ArrayList<>(Arrays.asList(new Color(255, 0, 0), new Color(0, 255, 0))))
            .generateImage();
    testGrayscale = new GreyscaleTransformation();
  }

  // tests for exception with null image
  @Test(expected = IllegalArgumentException.class)
  public void testNullImage() {
    testGrayscale.applyColorTransformation(null);
  }


  // tests running a grayscale transformation on a 4x4 black red checkerboard.
  // Checks if the rgb values are good.
  @Test
  public void testGrayscaleBlackRedCheckerboard() {
    PixelImage grayCheckerboard = testGrayscale.applyColorTransformation(blackRedCheckerBoard);
    assertEquals(54, grayCheckerboard.getPixels().get(0).get(0).getColor().getRed());
    assertEquals(54, grayCheckerboard.getPixels().get(0).get(0).getColor().getGreen());
    assertEquals(54, grayCheckerboard.getPixels().get(0).get(0).getColor().getBlue());

    assertEquals(0, grayCheckerboard.getPixels().get(0).get(1).getColor().getRed());
    assertEquals(0, grayCheckerboard.getPixels().get(0).get(1).getColor().getGreen());
    assertEquals(0, grayCheckerboard.getPixels().get(0).get(1).getColor().getBlue());

    assertEquals(0, grayCheckerboard.getPixels().get(1).get(0).getColor().getRed());
    assertEquals(0, grayCheckerboard.getPixels().get(1).get(0).getColor().getGreen());
    assertEquals(0, grayCheckerboard.getPixels().get(1).get(0).getColor().getBlue());

    assertEquals(54, grayCheckerboard.getPixels().get(1).get(1).getColor().getRed());
    assertEquals(54, grayCheckerboard.getPixels().get(1).get(1).getColor().getGreen());
    assertEquals(54, grayCheckerboard.getPixels().get(1).get(1).getColor().getBlue());

  }

  // tests running a grayscale transformation on a 4x4 green red checkerboard.
  // Checks if the rgb values are good.
  @Test
  public void testGrayscaleGreenRedCheckerboard() {
    PixelImage grayCheckerboard = testGrayscale.applyColorTransformation(greenRedCheckerBoard);
    assertEquals(54, grayCheckerboard.getPixels().get(0).get(0).getColor().getRed());
    assertEquals(54, grayCheckerboard.getPixels().get(0).get(0).getColor().getGreen());
    assertEquals(54, grayCheckerboard.getPixels().get(0).get(0).getColor().getBlue());

    assertEquals(182, grayCheckerboard.getPixels().get(0).get(1).getColor().getRed());
    assertEquals(182, grayCheckerboard.getPixels().get(0).get(1).getColor().getGreen());
    assertEquals(182, grayCheckerboard.getPixels().get(0).get(1).getColor().getBlue());

    assertEquals(182, grayCheckerboard.getPixels().get(1).get(0).getColor().getRed());
    assertEquals(182, grayCheckerboard.getPixels().get(1).get(0).getColor().getGreen());
    assertEquals(182, grayCheckerboard.getPixels().get(1).get(0).getColor().getBlue());

    assertEquals(54, grayCheckerboard.getPixels().get(1).get(1).getColor().getRed());
    assertEquals(54, grayCheckerboard.getPixels().get(1).get(1).getColor().getGreen());
    assertEquals(54, grayCheckerboard.getPixels().get(1).get(1).getColor().getBlue());

  }

  //tests that stacking grayscale on top of each other and that it stays the same.
  @Test
  public void testGrayscaleStacking() {
    PixelImage grayCheckerboard = testGrayscale
            .applyColorTransformation(testGrayscale.applyColorTransformation(blackRedCheckerBoard));
    assertEquals(54, grayCheckerboard.getPixels().get(0).get(0).getColor().getRed());
    assertEquals(54, grayCheckerboard.getPixels().get(0).get(0).getColor().getGreen());
    assertEquals(54, grayCheckerboard.getPixels().get(0).get(0).getColor().getBlue());

    assertEquals(0, grayCheckerboard.getPixels().get(0).get(1).getColor().getRed());
    assertEquals(0, grayCheckerboard.getPixels().get(0).get(1).getColor().getGreen());
    assertEquals(0, grayCheckerboard.getPixels().get(0).get(1).getColor().getBlue());

    assertEquals(0, grayCheckerboard.getPixels().get(1).get(0).getColor().getRed());
    assertEquals(0, grayCheckerboard.getPixels().get(1).get(0).getColor().getGreen());
    assertEquals(0, grayCheckerboard.getPixels().get(1).get(0).getColor().getBlue());

    assertEquals(54, grayCheckerboard.getPixels().get(0).get(0).getColor().getRed());
    assertEquals(54, grayCheckerboard.getPixels().get(0).get(0).getColor().getGreen());
    assertEquals(54, grayCheckerboard.getPixels().get(0).get(0).getColor().getBlue());


  }
}