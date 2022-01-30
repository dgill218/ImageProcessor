import static org.junit.Assert.assertEquals;

import controller.filereading.IFileReader;
import controller.filereading.ImageIOFileReader;
import controller.filewriting.JPEGImageIOWriter;
import controller.filewriting.PNGImageIOWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import model.imagegenerating.ImageCreator;
import model.imaging.Color;
import model.imaging.PixelImage;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing class for ImageIOFileReader.
 */
public class ImageIOFileReaderTest {

  private IFileReader reader;

  @Before
  public void setUp() throws Exception {
    reader = new ImageIOFileReader();
  }

  // tests exception for null filename
  @Test(expected = IllegalArgumentException.class)
  public void nullFileName() throws IOException {
    reader.readImageFromFile(null);
  }

  // tests exception for  filename that does not exist
  @Test(expected = IllegalArgumentException.class)
  public void badFilename() throws IOException {
    reader.readImageFromFile("hello?.png");
  }

  // tests for an image file that is not supported by ImageIO
  @Test(expected = IllegalArgumentException.class)
  public void badFileType() throws IOException {
    reader.readImageFromFile("res\\bug.ppm");
  }

  //test reading in a png file
  @Test
  public void goodPNGFileTest() throws IOException {
    new PNGImageIOWriter()
        .writeFile("res\\checkerboard.png", new ImageCreator(2, 2, new ArrayList<>(
            Arrays.asList(new Color(255, 0, 0), new Color(255, 255, 255))))
            .generateImage());
    PixelImage board = reader.readImageFromFile("res\\checkerboard.png");
    assertEquals(2, board.getPixels().size());
    assertEquals(2, board.getPixels().get(0).size());
    assertEquals(new Color(255, 0, 0), board.getPixels().get(0).get(0).getColor());
  }

  //test reading in a jpeg file, Colors change due tp jpeg compression.
  @Test
  public void goodJPEGFileTest() throws IOException {
    new JPEGImageIOWriter()
        .writeFile("res\\checkerboard.jpeg", new ImageCreator(2, 2, new ArrayList<>(
            Arrays.asList(new Color(255, 0, 0), new Color(255, 255, 255))))
            .generateImage());
    PixelImage board = reader.readImageFromFile("res\\checkerboard.jpeg");
    assertEquals(2, board.getPixels().size());
    assertEquals(2, board.getPixels().get(0).size());
    assertEquals(new Color(161, 35, 36), board.getPixels().get(0).get(0).getColor());
  }
}