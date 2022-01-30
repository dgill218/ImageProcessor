import static org.junit.Assert.assertEquals;

import java.io.IOException;

import controller.filereading.ImageIOFileReader;
import controller.filewriting.IImageFileWriter;
import controller.filewriting.JPEGImageIOWriter;
import model.imaging.Color;
import model.imaging.PixelImage;
import model.imaging.Posn;
import org.junit.Test;

/**
 * Test class for the imageio writer class.
 */
public class JPEGImageIOWriterTest extends AbstractImageIOWriterTest {

  @Override
  public IImageFileWriter makeWriter() {
    return new JPEGImageIOWriter();
  }

  // tests writing to a jpeg file and reading it back in
  @Test
  public void testWritingToJPEG() throws IOException {
    writer.writeFile("test\\testreaderfiles\\writtenJPEG.jpeg", testCheckerboard);
    PixelImage testImage = new ImageIOFileReader()
        .readImageFromFile("test\\testreaderfiles\\writtenJPEG.jpeg");
    assertEquals(new Color(98, 99, 0), testImage.getPixels().get(0).get(0).getColor());
    assertEquals(new Color(148, 149, 22), testImage.getPixels().get(0).get(1).getColor());
    assertEquals(new Color(148, 149, 22), testImage.getPixels().get(0).get(1).getColor());
    assertEquals(new Color(98, 99, 0), testImage.getPixels().get(0).get(0).getColor());

    for (int i = 0; i < testImage.getPixels().size(); i++) {
      for (int j = 0; j < testImage.getPixels().get(0).size(); j++) {
        assertEquals(new Posn(j, i), testImage.getPixels().get(i).get(j).getPosn());
      }
    }
  }
}