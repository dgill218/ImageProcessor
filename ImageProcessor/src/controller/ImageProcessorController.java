package controller;


import java.io.IOException;

/**
 * Interface representing an image controller.
 */
public interface ImageProcessorController {

  /**
   * Runs the controller that handles the Processing session, view, and input to conduct the Image
   * Processing.
   * @throws IOException if given inputs fail to transmit inside the Image Processor.
   */
  void start() throws IOException;

}