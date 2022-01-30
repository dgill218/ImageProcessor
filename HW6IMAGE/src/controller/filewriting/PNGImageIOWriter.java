package controller.filewriting;

/**
 * Function object to facilitate the writing of png files.
 */
public class PNGImageIOWriter extends AbstractImageIOWriter {

  /**
   * Creates a new instance of the object using the "png" filetype string.
   */
  public PNGImageIOWriter() {
    super("png");
  }
}
