package view;

import java.io.IOException;

/**
 * Interface to represent an Image Processing View.
 */
public interface ImageProcessorView {


  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message being transmitted
   * @throws IOException if transmission of the message to the data destination fails
   */
  void renderMessage(String message) throws IOException;


}