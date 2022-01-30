import controller.ImageProcessorController;

import view.IViewListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

/**
 * Mock controller for testing GUI.
 */
public class ControllerMock implements ImageProcessorController, IViewListener, ActionListener {

  private final Appendable log;

  /**
   * Constructs mock controller.
   *
   * @param log The appendable object to log the output.
   */
  public ControllerMock(Appendable log) {
    this.log = Objects.requireNonNull(log);

  }


  @Override
  public String getCurrentLayerID() {
    return null;
  }

  @Override
  public void handleSaveImageEvent(String fileName, String fileType) {
    try {
      this.log.append("handleSaveImageEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void handleLoadImageEvent(String fileName, String fileType, String layerName) {
    try {
      this.log.append("handleLoadLayerEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void handleSaveAllImagesEvent(String fileName, String fileType) {
    try {
      this.log.append("handleSaveAllImageEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @Override
  public void handleBlurEvent() {
    try {
      this.log.append("handleBlurEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void handleSharpenEvent() {
    try {
      this.log.append("handleSharpenEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void handleGrayscaleEvent() {
    try {
      this.log.append("handleGrayscaleEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void handleSepiaEvent() {
    try {
      this.log.append("handleSepiaEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void handleRedEvent() {
    try {
      this.log.append("handleRedEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void handleGreenEvent() {
    try {
      this.log.append("handleGreenEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void handleBlueEvent() {
    try {
      this.log.append("handleBlueEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void handleValueEvent() {
    try {
      this.log.append("handleValueEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void handleIntensityEvent() {
    try {
      this.log.append("handleIntensityEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void handleDarkenEvent(int val) {
    try {
      this.log.append("handleDarkenEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void handleBrightenEvent(int val) {
    try {
      this.log.append("handleBrightenEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void handleVerticalFlipEvent() {
    try {
      this.log.append("handleVerticalFlipEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void handleHorizontalFlipEvent() {
    try {
      this.log.append("handleHorizontalFlipEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void showEvent() {
    try {
      this.log.append("showEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void hideEvent() {
    try {
      this.log.append("hideEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void removeLayerEvent() {
    try {
      this.log.append("removeLayerEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setCurrentImageEvent(String layerID) {
    // not needed for testing
  }

  @Override
  public void start() throws IOException {
    // not needed for testing
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // not needed for testing
  }
}
