import static org.junit.Assert.assertEquals;

import controller.filereading.PPMFileReader;
import controller.filewriting.IImageFileWriter;
import controller.filewriting.PPMFileWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import filters.FilterBlur;
import filters.colortransformation.greyscale.GreyscaleTransformation;
import model.imagegenerating.ImageCreator;
import model.imaging.Color;
import model.imaging.PixelImage;
import model.imaging.Posn;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing class for PPMFileWriter.
 */
public class PPMFileWriterTest {

  private IImageFileWriter writer;
  private PixelImage testCheckerboard;

  @Before
  public void setUp() throws Exception {
    writer = new PPMFileWriter();
    testCheckerboard = new ImageCreator(2, 2, new ArrayList<>(Arrays.asList(
        new Color(255, 0, 0), new Color(0, 255, 0)))).generateImage();
  }

  // test that exception is thrown when the filename is null
  @Test(expected = IllegalArgumentException.class)
  public void testNullFileName() throws IOException {
    writer.writeFile(null, testCheckerboard);
  }

  // tests that exception is thrown when the image is null
  @Test(expected = IllegalArgumentException.class)
  public void testNullImage() throws IOException {
    writer.writeFile("res\\test.ppm", null);
  }

  // tests that file written from the image will return
  // the same information after being read in again as a PPM file.
  @Test
  public void testWritingToFileAsPPM() throws IOException {
    writer.writeFile("test\\testreaderfiles\\writtenfile.txt", testCheckerboard);
    PixelImage testImage = new PPMFileReader()
        .readImageFromFile("test\\testreaderfiles\\writtenfile.txt");
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

  // tests writing a filtered image
  @Test
  public void testWritingToFileAsPPMFiltered() throws IOException {
    PixelImage blurredCheckerboard = new FilterBlur().transform(testCheckerboard);
    writer.writeFile("test\\testreaderfiles\\writtenfile1.txt", blurredCheckerboard);
    PixelImage testImage = new PPMFileReader()
        .readImageFromFile("test\\testreaderfiles\\writtenfile1.txt");
    assertEquals(new Color(78, 62, 0), testImage.getPixels().get(0).get(0).getColor());
    assertEquals(new Color(62, 78, 0), testImage.getPixels().get(0).get(1).getColor());
    assertEquals(new Color(62, 78, 0), testImage.getPixels().get(1).get(0).getColor());
    assertEquals(new Color(78, 62, 0), testImage.getPixels().get(1).get(1).getColor());

    for (int i = 0; i < testImage.getPixels().size(); i++) {
      for (int j = 0; j < testImage.getPixels().get(0).size(); j++) {
        assertEquals(new Posn(j, i), testImage.getPixels().get(i).get(j).getPosn());
      }
    }
  }

  // tests writing a transformed image
  @Test
  public void testWritingToFileAsPPMGray() throws IOException {
    PixelImage grayCheckerboard = new GreyscaleTransformation()
        .applyColorTransformation(testCheckerboard);
    writer.writeFile("test\\testreaderfiles\\writtenfile2.txt", grayCheckerboard);
    PixelImage testImage = new PPMFileReader()
        .readImageFromFile("test\\testreaderfiles\\writtenfile2.txt");
    assertEquals(new Color(54, 54, 54), testImage.getPixels().get(0).get(0).getColor());
    assertEquals(new Color(182, 182, 182), testImage.getPixels().get(0).get(1).getColor());
    assertEquals(new Color(182, 182, 182), testImage.getPixels().get(1).get(0).getColor());
    assertEquals(new Color(54, 54, 54), testImage.getPixels().get(1).get(1).getColor());

    for (int i = 0; i < testImage.getPixels().size(); i++) {
      for (int j = 0; j < testImage.getPixels().get(0).size(); j++) {
        assertEquals(new Posn(j, i), testImage.getPixels().get(i).get(j).getPosn());
      }
    }
  }
}