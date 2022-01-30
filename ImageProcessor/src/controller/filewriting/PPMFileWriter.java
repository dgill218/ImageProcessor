package controller.filewriting;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import model.imaging.PixelImage;
import model.imaging.pixel.IPixel;

/**
 * Class to write PPM ASCII image files from ImageInterface data.
 */
public class PPMFileWriter implements IImageFileWriter {

  @Override
  public void writeFile(String filename, PixelImage image)
      throws IOException, IllegalArgumentException {
    if (filename == null || image == null) {
      throw new IllegalArgumentException("Argument is null.");
    }
    String ppmString = this.generatePPM(image);
    File file = new File(filename);
    File parent = file.getParentFile();
    if (parent != null) {
      parent.mkdirs();
    }
    FileOutputStream fileStream = new FileOutputStream(file);
    fileStream.write(ppmString.getBytes());
    fileStream.close();
  }

  /**
   * Generates the string in the format of a PPM ASCII image for the given image data.
   *
   * @param image Image to generate the string from.
   * @return The generated PPM ASCII string.
   */
  private String generatePPM(PixelImage image) {
    List<List<IPixel>> imagePixels = image.getPixels();
    StringBuilder ppmString = new StringBuilder().append("P3\n")
        .append(imagePixels.get(0).size()).append(" ")
        .append(imagePixels.size()).append("\n255\n");
    for (int i = 0; i < imagePixels.size(); i++) {
      for (int j = 0; j < imagePixels.get(0).size(); j++) {
        IPixel currentPixel = imagePixels.get(i).get(j);
        ppmString.append(currentPixel.getColor().getRed()).append(" ")
            .append(currentPixel.getColor().getGreen()).append(" ")
            .append(currentPixel.getColor().getBlue()).append(" ");
      }
      ppmString.append("\n");
    }
    return ppmString.toString();
  }
}
