package controller.filewriting;

/**
 * Function object to facilitate the writing of jpeg images.
 */
public class JPEGImageIOWriter extends AbstractImageIOWriter {

  /**
   * Creates a new instance of the object using the type "jpeg".
   */
  public JPEGImageIOWriter() {
    super("jpeg");
  }
}
