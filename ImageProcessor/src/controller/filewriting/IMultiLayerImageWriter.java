package controller.filewriting;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import model.imaging.PixelImage;

/**
 * Interface to represent a file writer for a multi layer image. Information is stored in a text
 * file which contains the file type of all images, the paths of all layer images, their associated
 * id, and their visibility status.
 */
public interface IMultiLayerImageWriter {

  /**
   * Writes the txt file and image files representing the given multi layer image. Valid file types
   * are "png", "jpeg", and ppm.
   *
   * @param filename   Name of the txt file.
   * @param type       File type for the images to be exported into.
   * @param layers     Map of ids to images for the layers in the image.
   * @param visibility List of ids of invisible images.
   * @throws IllegalArgumentException If any argument is null, or the given file type is not
   *                                  supported.
   * @throws IOException              If writing fails at any time.
   */
  void writeFile(String filename, String type, Map<String, PixelImage> layers,
      List<String> visibility)
      throws IllegalArgumentException, IOException;

}
