
import java.io.IOException;

import controller.filereading.IFileReader;
import controller.filereading.PPMFileReader;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing class for PPMFileReader.
 */
public class PPMFileReaderTest {

  private IFileReader reader;

  @Before
  public void setUp() throws Exception {
    reader = new PPMFileReader();
  }

  //tests for exception when the given filename is null.
  @Test(expected = IllegalArgumentException.class)
  public void testNullFileName() throws IOException {
    reader.readImageFromFile(null);
  }

  // tests for exception when the given filename cannot be found
  @Test(expected = IllegalArgumentException.class)
  public void testCannotFindFileName() throws IOException {
    reader.readImageFromFile("hello?.ppm");
  }

  //test for exception when the given file is not a valid PPM ASCII file
  @Test(expected = IllegalArgumentException.class)
  public void testBadPPMFile() throws IOException {
    reader.readImageFromFile("test\\testreaderfiles\\badfile.txt");
  }
}