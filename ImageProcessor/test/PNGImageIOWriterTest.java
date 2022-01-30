import static org.junit.Assert.assertEquals;

import controller.filereading.ImageIOFileReader;
import controller.filewriting.IImageFileWriter;
import controller.filewriting.PNGImageIOWriter;

import java.io.IOException;

import model.imaging.Color;
import model.imaging.PixelImage;
import model.imaging.Posn;
import org.junit.Test;

/**
 * Test class for the png writer test.
 */
public class PNGImageIOWriterTest extends AbstractImageIOWriterTest {

  @Override
  public IImageFileWriter makeWriter() {
    return new PNGImageIOWriter();
  }

  // tests writing to a png
  @Test
  public void testWritingPNG() throws IOException {
    writer.writeFile("test\\testreaderfiles\\writtenPNG.png", testCheckerboard);
    PixelImage testImage = new ImageIOFileReader()
            .readImageFromFile("test\\testreaderfiles\\writtenPNG.png");
    assertEquals(new Color(255, 0, 0), testImage.getPixels().get(0).get(0).getColor());
    assertEquals(new Color(0, 255, 0), testImage.getPixels().get(0).get(1).getColor());
    assertEquals(new Color(0, 255, 0), testImage.getPixels().get(1).get(0).getColor());
    assertEquals(new Color(255, 0, 0), testImage.getPixels().get(1).get(1).getColor());

    for (int i = 0; i < testImage.getPixels().size(); i++) {
      for (int j = 0; j < testImage.getPixels().get(0).size(); j++) {
        assertEquals(new Posn(j, i), testImage.getPixels().get(i).get(j).getPosn());
      }
    }
  }
}