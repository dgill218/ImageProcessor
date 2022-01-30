package view;

import model.ImageProcessingModel;

import java.io.IOException;

/**
 * Class to represent a textual view for the image program to display messages to the user.
 */
public class ImageProcessorTextView implements ImageProcessorView {

  private Appendable ap;

  /**
   * Creates a new view.
   *
   * @param ap Appendable for the view.
   * @throws IllegalArgumentException IF appendable is null.
   */
  public ImageProcessorTextView(Appendable ap)
      throws IllegalArgumentException {
    if (ap == null) {
      throw new IllegalArgumentException("Null parameter.");
    }
    this.ap = ap;

  }

  /**
   * Constructor that takes in both a model and an output source.
   *
   * @param model an IImageProcessingView model
   * @param ap Output source
   */
  public ImageProcessorTextView(ImageProcessingModel model, Appendable ap) {
    if (model == null || ap == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }

    this.ap = ap;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.ap.append(message);
  }
}
