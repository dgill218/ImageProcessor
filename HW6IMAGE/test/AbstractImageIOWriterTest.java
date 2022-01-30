import controller.filewriting.IImageFileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import model.imagegenerating.ImageCreator;
import model.imaging.Color;
import model.imaging.PixelImage;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for image writing.
 */
public abstract class AbstractImageIOWriterTest {

  protected IImageFileWriter writer;
  protected PixelImage testCheckerboard;


  /**
   * Factory method for writer.
   *
   * @return The associated writer.
   */
  public abstract IImageFileWriter makeWriter();


  @Before
  public void setUp() throws Exception {
    writer = this.makeWriter();
    testCheckerboard = new ImageCreator(2, 2, new ArrayList<>(Arrays.asList(
        new Color(255, 0, 0), new Color(0, 255, 0)))).generateImage();
  }

  // testing a null file name
  @Test(expected = IllegalArgumentException.class)
  public void testNullFilename() throws IOException {
    writer.writeFile(null, testCheckerboard);
  }

  // testing a null image
  @Test(expected = IllegalArgumentException.class)
  public void testNullImage() throws IOException {
    writer.writeFile("hello", null);
  }
}