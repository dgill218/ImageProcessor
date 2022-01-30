package controller.filewriting;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import model.imaging.PixelImage;

/**
 * Function object to facilitate the writing of txt files and images representing a multi layer
 * image.
 */
public class MultiLayerImageWriter implements IMultiLayerImageWriter {

  @Override
  public void writeFile(String filename, String type, Map<String, PixelImage> layers,
      List<String> visibility) throws IllegalArgumentException, IOException {
    if (filename == null || type == null || layers == null || visibility == null) {
      throw new IllegalArgumentException("Null parameters.");
    }
    StringBuilder output = new StringBuilder().append(type).append("\n");
    for (Map.Entry<String, PixelImage> item : layers.entrySet()) {
      String imageFilename = "";
      switch (type.toLowerCase()) {
        case "png":
          imageFilename = filename + "\\" + item.getKey() + ".png";
          new PNGImageIOWriter().writeFile(imageFilename, item.getValue());
          output.append(imageFilename).append(" ").append(item.getKey()).append(" ")
              .append(this.visibilityStatus(item.getKey(), visibility));
          break;
        case "jpeg":
          imageFilename = filename + "\\" + item.getKey() + ".jpeg";
          new JPEGImageIOWriter().writeFile(imageFilename, item.getValue());
          output.append(imageFilename).append(" ").append(item.getKey()).append(" ")
              .append(this.visibilityStatus(item.getKey(), visibility));
          break;
        case "ppm":
          imageFilename = filename + "\\" + item.getKey() + ".ppm";
          new PPMFileWriter().writeFile(imageFilename, item.getValue());
          output.append(imageFilename).append(" ").append(item.getKey()).append(" ")
              .append(this.visibilityStatus(item.getKey(), visibility));
          break;
        default:
          throw new IllegalArgumentException("Invalid output type.");
      }
      output.append("\n");
    }
    String pattern = Pattern.quote(System.getProperty("file.separator"));
    String[] tokens = filename.split(pattern);
    File textFile = new File(filename + "\\" + tokens[tokens.length - 1] + ".txt");
    textFile.getParentFile().mkdirs();
    FileOutputStream stream = new FileOutputStream(textFile);
    stream.write(output.toString().getBytes());
    stream.close();


  }

  /**
   * Sets the status of visibility for the image.
   *
   * @param key        Id for the image.
   * @param visibility List of invisible image ids.
   * @return String representing the visibility status.
   */
  private String visibilityStatus(String key, List<String> visibility) {
    if (visibility.contains(key)) {
      return "invisible";
    } else {
      return "visible";
    }
  }
}
