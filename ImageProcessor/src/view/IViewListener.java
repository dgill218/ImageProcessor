package view;

import java.io.IOException;

/**
 * Interface to represent a listener to the GUI view for an image processing program that can react
 * to high level events received by the view.
 */
public interface IViewListener {

  /**
   * Returns the current layer of the image processing program from the model.
   *
   * @return String id of the current layer in the program.
   */
  String getCurrentLayerID();

  /**
   * Handles the logic of saving the topmost layer. Saves it as an image at the specified filepath
   * and of the given file type, one of PNG, JPEG, or PPM.
   *
   * @param fileName Path of the file to be created.
   * @param fileType Type of image to create.
   */
  void handleSaveImageEvent(String fileName, String fileType) throws IOException;

  /**
   * Handles the logic of loading a given image file as a layer into the program. Files loaded can
   * be PNG, JPEG, or PPM.
   *
   * @param fileName  Path of the file to be loaded in.
   * @param fileType  Type of file to be loaded.
   * @param layerName Name of the layer to be placed into the model.
   */
  void handleLoadImageEvent(String fileName, String fileType, String layerName) throws IOException;

  /**
   * Handles saving all of the layers to a folder with a text file containing the information of the
   * image.
   *
   * @param fileName File path for the new folder where the image will be placed.
   * @param fileType Type of image file for the images to be exported too.
   */
  void handleSaveAllImagesEvent(String fileName, String fileType);


  /**
   * Handles blurring the current image in the program.
   */
  void handleBlurEvent();

  /**
   * Handles an event that calls the sharpen the current image.
   */
  void handleSharpenEvent();

  /**
   * Handles an event that calls the grayscale the current image.
   */
  void handleGrayscaleEvent();

  /**
   * Handles an event that calls the sepia the current image.
   */
  void handleSepiaEvent();

  /**
   * Handles an event that calls the red component of the current image.
   */
  void handleRedEvent();

  /**
   * Handles an event that calls the green component of the current image.
   */
  void handleGreenEvent();

  /**
   * Handles an event that calls the blue component of the current image.
   */
  void handleBlueEvent();

  /**
   * Handles an event that calls the value component of the current image.
   */
  void handleValueEvent();

  /**
   * Handles an event that calls the intensity component of the current image.
   */
  void handleIntensityEvent();

  /**
   * Handles an event that darkens the current image.
   */
  void handleDarkenEvent(int val);

  /**
   * Handles an event that brightens the current image.
   */
  void handleBrightenEvent(int val);

  void handleVerticalFlipEvent();

  void handleHorizontalFlipEvent();


  /**
   * Handles an event that tells the listener to show the current image.
   */
  void showEvent();

  /**
   * Handles an event that tells the listener to hide the current image in the program.
   */
  void hideEvent();

  /**
   * Handles the logic for an event that tells the listener to remove the current layer.
   */
  void removeLayerEvent();

  /**
   * Handles the logic for an event that tells the listener to set the current layer to the
   * specified id.
   *
   * @param layerID Id for the current layer.
   */
  void setCurrentImageEvent(String layerID);

}
