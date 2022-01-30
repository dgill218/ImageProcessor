import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;

import model.imagegenerating.ImageCreator;
import model.imaging.Color;
import model.imaging.PixelImage;
import org.junit.Test;

/**
 * Testing class for ImageImpl.
 */
public class ImageImplTest {

  private final PixelImage checkerboard = new ImageCreator(10, 10,
      new ArrayList<>(Arrays.asList(new Color(255, 0, 0),
          new Color(0, 255, 0)))).generateImage();


  @Test
  public void getPixelsCheckerboardRows() {
    assertEquals(this.checkerboard.getPixels().size(), 10);
  }

  @Test
  public void getPixelsCheckerboardColumns() {
    assertEquals(this.checkerboard.getPixels().get(0).size(), 10);
  }


  // testing two images not equal
  @Test
  public void testNotEqualsCheckerboard() {
    assertFalse(this.checkerboard.equals(new ImageCreator(5, 5,
        new ArrayList<>(Arrays.asList(new Color(255, 0, 0),
            new Color(0, 255, 0)))).generateImage()));
  }




}