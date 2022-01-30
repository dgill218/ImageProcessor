

import model.graph.Line;
import view.IViewListener;
import view.ImageProcessorGUIView;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Mock view that can be used to test how events are received by the listener.
 */
public class ViewMock implements ImageProcessorGUIView {

  private final IViewListener listener;
  private final Appendable ap;


  /**
   * Constructs a View mock with a listener and appendable object.
   *
   * @param listener The listener for the mock object.
   * @param ap       Appendable object to append output to.
   */
  public ViewMock(IViewListener listener, Appendable ap) {
    this.listener = Objects.requireNonNull(listener);
    this.ap = ap;

  }


  @Override
  public void runGUI() {
    // don't need to test this method.... we will know if it runs when running the program.
  }

  @Override
  public void updateImage(BufferedImage image) {
    try {
      ap.append("updated image ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void updateImages(String layerName) {
    try {
      ap.append("updated layers ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void updateGraph(List<Line> lines) {
    // updating graph doesn't need to be tested.
  }

  @Override
  public void renderMessage(String msg) throws IllegalArgumentException, IOException {
    ap.append("rendered msg ");
  }

  public void emitBlurLayerEvent() {
    listener.handleBlurEvent();
  }

  public void emitSharpenLayerEvent() {
    listener.handleSharpenEvent();
  }

  public void emitRedEvent() {
    listener.handleRedEvent();
  }

  public void emitGreenEvent() {
    listener.handleGreenEvent();
  }

  public void emitBlueEvent() {
    listener.handleBlueEvent();
  }

  public void emitGrayscaleLayerEvent() {
    listener.handleGrayscaleEvent();
  }

  public void emitSepiaLayerEvent() {
    listener.handleSepiaEvent();
  }

  public void emitDeleteLayerEvent() {
    listener.removeLayerEvent();
  }

  public void emitShowLayerEvent() {
    listener.showEvent();
  }

  public void emitHideLayerEvent() {
    listener.hideEvent();
  }


}
