package view;

import java.awt.image.BufferedImage;
import java.util.List;
import model.graph.Line;

/**
 * Interface to represent the view for an image processing programs that uses an interactive GUI to
 * receive user inputs. GUI exposes all features of the program to the user, which can be activated
 * using these buttons.
 */
public interface ImageProcessorGUIView extends ImageProcessorView {

  /**
   * Makes the GUI visible to the user.
   */
  void runGUI();

  /**
   * Sets the viewable image in the GUI to the given buffered image.
   *
   * @param image The image that is being used for the view.
   */
  void updateImage(BufferedImage image);

  /**
   * Adds the given image name to the list of images contained in the GUI.
   * the list of images that are contained in the GUI consist of all of the images
   * that the user has loaded so far.
   * @param imageName Name of the image to add.
   */
  void updateImages(String imageName);

  /**
   * Given a list of lines this method updates the histogram with the given lines.
   * @param lines A list of lines that are drawn to make a histogram.
   */
  public void updateGraph(List<Line> lines);

}
