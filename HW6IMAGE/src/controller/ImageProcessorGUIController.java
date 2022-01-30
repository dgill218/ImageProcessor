package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import controller.filereading.ImageIOFileReader;
import controller.filereading.PPMFileReader;
import controller.filewriting.JPEGImageIOWriter;
import controller.filewriting.MultiLayerImageWriter;
import controller.filewriting.PNGImageIOWriter;
import controller.filewriting.PPMFileWriter;
import model.IImageProcessorInstance;
import model.imaging.PixelImage;
import view.IViewListener;
import view.ImageProcessorGUIView;
import view.ImageProcessorGUIViewImpl;

/**
 * Class representing a controller for an image processing program that uses a GUI view. This
 * controller supports the multi layer model and uses the ImageProcessorGUIView in order to display
 * the required information. This also implements the IViewListener, allowing the controller to
 * receive high level events from the view and act on them.
 */
public class ImageProcessorGUIController implements ImageProcessorController, IViewListener {

  private final IImageProcessorInstance model;
  private final ImageProcessorGUIView view;
  private String current;

  /**
   * Constructor for the GUI controller.
   *
   * @param model Model for the image processor program.
   * @throws IllegalArgumentException If any argument is null.
   */
  public ImageProcessorGUIController(IImageProcessorInstance model)
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Null parameter.");
    }
    this.model = model;
    this.view = new ImageProcessorGUIViewImpl(this);
    this.current = null;
  }

  @Override
  public void start() throws IllegalStateException {
    this.view.updateImage(this.getTopVisibleLayer());
    this.view.runGUI();
  }


  @Override
  public String getCurrentLayerID() {
    return this.current;
  }


  @Override
  public void handleSaveImageEvent(String filename, String filetype) throws IOException {
    switch (filetype.toLowerCase()) {
      case "png":
        try {
          new PNGImageIOWriter().writeFile(filename + ".png",
                  this.model.getImage(getTopmostVisibleLayerID()));
        } catch (IllegalArgumentException e) {
          renderHandler(e.getMessage());
        } catch (IOException e) {
          throw new IllegalStateException();
        }
        break;
      case "jpeg":
        try {
          new JPEGImageIOWriter()
                  .writeFile(filename + ".jpeg", this.model.getImage(getTopmostVisibleLayerID()));
        } catch (IllegalArgumentException e) {
          renderHandler(e.getMessage());
        } catch (IOException e) {
          throw new IllegalStateException();
        }
        break;
      case "ppm":
        try {
          new PPMFileWriter()
                  .writeFile(filename + ".ppm", this.model.getImage(getTopmostVisibleLayerID()));
        } catch (IllegalArgumentException e) {
          renderHandler(e.getMessage());
        } catch (IOException e) {
          throw new IllegalStateException();
        }
        break;
      default:
        view.renderMessage("File type must be ppm/jpeg/png");
    }
  }

  private String getTopmostVisibleLayerID() throws IllegalArgumentException {
    for (Map.Entry<String, PixelImage> item : this.model.getLayers().entrySet()) {
      if (!this.model.getVisibility().contains(item.getKey())) {
        return item.getKey();
      }
    }
    throw new IllegalArgumentException("No visible images.");
  }

  @Override
  public void handleLoadImageEvent(String filename, String filetype, String layerName)
          throws IOException {
    switch (filetype.toLowerCase()) {
      case "png":
      case "jpeg":
        try {
          addHandler(layerName, new ImageIOFileReader().readImageFromFile(filename));
        } catch (IllegalArgumentException e) {
          renderHandler(e.getMessage());
        }
        break;
      case "ppm":
        try {
          addHandler(layerName, new PPMFileReader().readImageFromFile(filename));
        } catch (IllegalArgumentException e) {
          renderHandler(e.getMessage());
        }
        break;
      default:
        view.renderMessage("File type must be ppm/jpeg/png");
    }
  }

  @Override
  public void handleSaveAllImagesEvent(String fileName, String fileType)
          throws IllegalStateException {
    try {
      new MultiLayerImageWriter()
              .writeFile(fileName, fileType, this.model.getLayers(),
                      this.model.getVisibility());
    } catch (IllegalArgumentException e) {
      renderHandler(e.getMessage());
    } catch (IOException io) {
      throw new IllegalStateException();
    }

  }


  @Override
  public void handleBlurEvent() {
    if (this.current == null) {
      renderHandler("There is no current image selected.");
    } else {
      try {
        this.model.replaceImage(current, this.model.blur(current));
        this.view.updateImage(this.getTopVisibleLayer());
        this.updateHistogram();
      } catch (IllegalArgumentException e) {
        renderHandler(e.getMessage());
      }
    }
  }

  @Override
  public void handleSharpenEvent() {
    if (this.current == null) {
      renderHandler("There is no current image selected.");
    } else {
      try {
        this.model.replaceImage(current, this.model.sharpen(current));
        this.view.updateImage(this.getTopVisibleLayer());
        this.updateHistogram();

      } catch (IllegalArgumentException e) {
        renderHandler(e.getMessage());
      }
    }
  }

  @Override
  public void handleVerticalFlipEvent() {
    if (this.current == null) {
      renderHandler("There is no current image selected.");
    } else {
      try {
        this.model.replaceImage(current, this.model.verticalFlip(current));
        this.view.updateImage(this.getTopVisibleLayer());
        this.updateHistogram();

      } catch (IllegalArgumentException e) {
        renderHandler(e.getMessage());
      }
    }
  }

  @Override
  public void handleHorizontalFlipEvent() {
    if (this.current == null) {
      renderHandler("There is no current image selected.");
    } else {
      try {
        this.model.replaceImage(current, this.model.horizontalFlip(current));
        this.view.updateImage(this.getTopVisibleLayer());
        this.updateHistogram();

      } catch (IllegalArgumentException e) {
        renderHandler(e.getMessage());
      }
    }
  }

  @Override
  public void handleGrayscaleEvent() {
    if (this.current == null) {
      renderHandler("There is no current image selected.");
    } else {
      try {
        this.model.replaceImage(current, this.model.grayscale(current));
        this.view.updateImage(this.getTopVisibleLayer());
        this.updateHistogram();

      } catch (IllegalArgumentException e) {
        renderHandler(e.getMessage());
      }
    }
  }

  @Override
  public void handleSepiaEvent() {
    if (this.current == null) {
      renderHandler("There is no current image selected.");
    } else {
      try {
        this.model.replaceImage(current, this.model.sepia(current));
        this.view.updateImage(this.getTopVisibleLayer());
        this.updateHistogram();

      } catch (IllegalArgumentException e) {
        renderHandler(e.getMessage());
      }
    }
  }

  @Override
  public void handleRedEvent() {
    if (this.current == null) {
      renderHandler("There is no current image selected.");
    } else {
      try {
        this.model.replaceImage(current, this.model.redComponent(current));
        this.view.updateImage(this.getTopVisibleLayer());
        this.updateHistogram();

      } catch (IllegalArgumentException e) {
        renderHandler(e.getMessage());
      }
    }
  }

  @Override
  public void handleGreenEvent() {
    if (this.current == null) {
      renderHandler("There is no current image selected.");
    } else {
      try {
        this.model.replaceImage(current, this.model.greenComponent(current));
        this.view.updateImage(this.getTopVisibleLayer());
        this.updateHistogram();

      } catch (IllegalArgumentException e) {
        renderHandler(e.getMessage());
      }
    }
  }

  @Override
  public void handleBlueEvent() {
    if (this.current == null) {
      renderHandler("There is no current image selected.");
    } else {
      try {
        this.model.replaceImage(current, this.model.blueComponent(current));
        this.view.updateImage(this.getTopVisibleLayer());
        this.updateHistogram();

      } catch (IllegalArgumentException e) {
        renderHandler(e.getMessage());
      }
    }
  }

  @Override
  public void handleValueEvent() {
    if (this.current == null) {
      renderHandler("There is no current image selected.");
    } else {
      try {
        this.model.replaceImage(current, this.model.valueComponent(current));
        this.view.updateImage(this.getTopVisibleLayer());
        this.updateHistogram();

      } catch (IllegalArgumentException e) {
        renderHandler(e.getMessage());
      }
    }
  }

  @Override
  public void handleIntensityEvent() {
    if (this.current == null) {
      renderHandler("There is no current image selected.");
    } else {
      try {
        this.model.replaceImage(current, this.model.intensityComponent(current));
        this.view.updateImage(this.getTopVisibleLayer());
        this.updateHistogram();

      } catch (IllegalArgumentException e) {
        renderHandler(e.getMessage());
      }
    }
  }

  @Override
  public void handleDarkenEvent(int val) {
    if (this.current == null) {
      renderHandler("There is no current image selected.");
    } else {
      try {
        this.model.replaceImage(current, this.model.darken(current, val));
        this.view.updateImage(this.getTopVisibleLayer());
        this.updateHistogram();

      } catch (IllegalArgumentException e) {
        renderHandler(e.getMessage());
      }
    }
  }

  @Override
  public void handleBrightenEvent(int val) {
    if (this.current == null) {
      renderHandler("There is no current image selected.");
    } else {
      try {
        this.model.replaceImage(current, this.model.brighten(current, val));
        this.view.updateImage(this.getTopVisibleLayer());
        this.updateHistogram();

      } catch (IllegalArgumentException e) {
        renderHandler(e.getMessage());
      }
    }
  }

  @Override
  public void showEvent() {
    if (this.current == null) {
      renderHandler("There is no current image selected.");
    } else {
      try {
        this.model.showImage(current);
        this.view.updateImage(this.getTopVisibleLayer());
        this.updateHistogram();

      } catch (IllegalArgumentException e) {
        renderHandler(e.getMessage());
      }
    }
  }

  @Override
  public void hideEvent() {
    if (this.current == null) {
      renderHandler("There is no current image selected.");
    } else {
      try {
        this.model.hideImage(current);
        this.view.updateImage(this.getTopVisibleLayer());
      } catch (IllegalArgumentException e) {
        renderHandler(e.getMessage());
      }
    }
  }

  @Override
  public void removeLayerEvent() {
    if (this.current == null) {
      renderHandler("There is no current image selected.");
    } else {
      try {
        this.model.removeImage(this.current);
        this.view.updateImage(this.getTopVisibleLayer());
      } catch (IllegalArgumentException e) {
        renderHandler(e.getMessage());
      }
    }
  }

  @Override
  public void setCurrentImageEvent(String layerID) {
    if (this.model.getLayers().containsKey(layerID)) {
      this.current = layerID;
    } else {
      renderHandler("This image does not exist");
    }
  }


  /**
   * Generates a buffered image from the top visible layer in the model. If there is no visible
   * layers, returns a blank 1x1 image.
   *
   * @return Buffered image of the top visible layer in the model.
   */
  private BufferedImage getTopVisibleLayer() {
    for (Map.Entry<String, PixelImage> layer : this.model.getLayers().entrySet()) {
      if (!this.model.getVisibility().contains(layer.getKey())) {
        return this.generateImage(layer.getValue());
      }
    }
    return new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
  }

  /**
   * Generates a buffered image of the given image.
   *
   * @param image Image to turn into a buffered image.
   * @return Buffered image representation of the given image.
   */
  private BufferedImage generateImage(PixelImage image) {
    int height = image.getPixels().size();
    int width = image.getPixels().get(0).size();

    BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int red = image.getPixels().get(i).get(j).getColor().getRed();
        int green = image.getPixels().get(i).get(j).getColor().getGreen();
        int blue = image.getPixels().get(i).get(j).getColor().getBlue();

        java.awt.Color pixelColor = new java.awt.Color(red, green, blue);
        outputImage.setRGB(j, i, pixelColor.getRGB());
      }
    }
    return outputImage;
  }

  /**
   * Handles adding the given image to the model with the given name, and also updates the view with
   * the most recent top visible image and adds the layer to the list of layers in the view. Returns
   * any errors from the model as a popup in the view.
   *
   * @param layerName Name of the layer.
   * @param image     Image for the layer.
   */
  private void addHandler(String layerName, PixelImage image) {
    try {
      this.model.addImage(layerName, image);
      this.view.updateImage(this.getTopVisibleLayer());
      this.view.updateImages(layerName);
    } catch (IllegalArgumentException e) {
      renderHandler(e.getMessage());
    }
  }

  /**
   * Sends the given message as a pop-up in the view.
   *
   * @param msg Message to send.
   */
  private void renderHandler(String msg) {
    try {
      this.view.renderMessage(msg);
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  /**
   * Updates the histogram on the GUI.
   */
  private void updateHistogram() {
    this.view.updateGraph(this.model.getImage(current).getLines());
  }


}
