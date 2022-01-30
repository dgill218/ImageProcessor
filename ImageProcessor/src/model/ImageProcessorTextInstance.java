package model;

import java.io.IOException;
import java.util.HashMap;

import controller.filereading.ImageIOFileReader;
import model.imaging.Image;
import util.ImageUtil;

/**
 * Represents an image processing session where multiple images are loaded and manipulated.
 */
public class ImageProcessorTextInstance {

  private HashMap<String, ITextImageProcessingModel> images;

  /**
   * Initializes a new image processing session by making a hashmap to store images that are loaded
   * and manipulated.
   */
  public ImageProcessorTextInstance() {
    this.images = new HashMap<String, ITextImageProcessingModel>();
  }

  /**
   * Loads an image into the image processing instance.
   *
   * @param filepath  The location and name of the file.
   * @param modelName The name the file is stored under during the session.
   */
  public void load(String filepath, String modelName) throws IOException {

    String[] splitAtFormat = filepath.split("\\.", 2);
    if (splitAtFormat[1].equals("ppm")) {
      images.put(modelName,
              new ImageProcessingModel(new Image(ImageUtil.getPixels(filepath))));
    } else {
      images.put(modelName,
              new ImageProcessingModel(new Image(ImageIOFileReader.readImage(filepath))));
    }
  }

  /**
   * Saves an image from the image processing instance to a specified file output.
   *
   * @param saveLocation The name and location of the file to output.
   * @param modelName    The name of the image that will be saved.
   * @throws IOException if there is an issue writing to the output file.
   */
  public void save(String saveLocation, String modelName) throws IOException {
    if (this.images.containsKey(modelName)) {

      String[] splitAtFormat = saveLocation.split("\\.", 2);
      if (splitAtFormat[1].equals("ppm")) {
        this.images.get(modelName).saveImageAsPPM(saveLocation);
      } else {
        this.images.get(modelName).saveImageAs(saveLocation);
      }
    } else {
      throw new IllegalArgumentException("Invalid model name");
    }
  }

  /**
   * Given the name of the stored image and a name to save the image, this
   * method performs a RedTransformation on an image if the input is entered correctly.
   *
   * @param modelName The name of the image to be transformed.
   * @param newName The new name the image is being saved as
   */
  public void red(String modelName, String newName) {
    if (this.images.containsKey(modelName)) {
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).redComponent()));
    } else {
      throw new IllegalArgumentException("invalid inputs");
    }
  }

  /**
   * Given the name of the stored image and a name to save the image, this
   * method performs a GreenTransformation on an image if the input is entered correctly.
   *
   * @param modelName The name of the image to be transformed.
   * @param newName The new name the image is being saved as
   */
  public void green(String modelName, String newName) {
    if (this.images.containsKey(modelName)) {
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).greenComponent()));
    } else {
      throw new IllegalArgumentException("invalid inputs");
    }
  }

  /**
   * Given the name of the stored image and a name to save the image, this
   * method performs a BlueTransformation on an image if the input is entered correctly.
   *
   * @param modelName The name of the image to be transformed.
   * @param newName The new name the image is being saved as
   */
  public void blue(String modelName, String newName) {
    if (this.images.containsKey(modelName)) {
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).blueComponent()));
    } else {
      throw new IllegalArgumentException("invalid inputs");
    }
  }

  /**
   * Given the name of the stored image and a name to save the image, this
   * method performs a ValueTransformation on an image if the input is entered correctly.
   *
   * @param modelName The name of the image to be transformed.
   * @param newName The new name the image is being saved as
   */
  public void value(String modelName, String newName) {
    if (this.images.containsKey(modelName)) {
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).valueImage()));
    } else {
      throw new IllegalArgumentException("invalid inputs");
    }
  }

  /**
   * Given the name of the stored image and a name to save the image, this
   * method performs an IntensityTransformation on an image if the input is entered correctly.
   *
   * @param modelName The name of the image to be transformed.
   * @param newName The new name the image is being saved as
   */
  public void intensity(String modelName, String newName) {
    if (this.images.containsKey(modelName)) {
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).intensity()));
    } else {
      throw new IllegalArgumentException("invalid inputs");
    }
  }



  /**
   * Given the name of the stored image and a name to save the manipulation this method makes a
   * horizontally flipped image.
   *
   * @param modelName Name of image to be flipped.
   * @param newName   What the flipped image is stored as.
   */
  public void horizontalFlip(String modelName, String newName) {
    if (this.images.containsKey(modelName)) {
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).horizontalFlip()));
    } else {
      throw new IllegalArgumentException("invalid inputs");

    }
  }

  /**
   * Given the name of the stored image and a name to save the manipulation this method makes a
   * vertically flipped image.
   *
   * @param modelName Name of image to be flipped.
   * @param newName   What the flipped image is stored as.
   */
  public void verticalFlip(String modelName, String newName) {
    if (this.images.containsKey(modelName)) {
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).verticalFlip()));
    } else {
      throw new IllegalArgumentException("invalid inputs");
    }
  }

  /**
   * Given a value, a name of a stored image, and the name to store the manipulated image under this
   * method creates a brightened version of the image.
   *
   * @param value     The value to brighten the image by.
   * @param modelName The image to be brightened.
   * @param newName   The name of the brightened image made.
   */
  public void brighten(int value, String modelName, String newName) {
    if (this.images.containsKey(modelName)) {
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).brighten(value)));
    } else {
      throw new IllegalArgumentException("invalid inputs");
    }
  }


  /**
   * Given a value, a name of a stored image, and the name to store the manipulated image under this
   * method creates a darkened version of the image.
   *
   * @param value     The value to darken the image by.
   * @param modelName The image to be darkened.
   * @param newName   The name of the darkened image made.
   */
  public void darken(int value, String modelName, String newName) {
    if (this.images.containsKey(modelName)) {
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).darken(value)));
    } else {
      throw new IllegalArgumentException("invalid inputs");
    }
  }

  /**
   * Given the name of the stored image and a name to save the manipulation this method makes a
   * blurred image.
   *
   * @param modelName Name of image to be blurred.
   * @param newName   What the blurred image is stored as.
   */
  public void blur(String modelName, String newName) {
    if (this.images.containsKey(modelName)) {
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).blur()));
    } else {
      throw new IllegalArgumentException("invalid inputs");
    }
  }

  /**
   * Given the name of the stored image and a name to save the manipulation this method makes a
   * sharpened image.
   *
   * @param modelName Name of image to be sharpened.
   * @param newName   What the sharpened image is stored as.
   */
  public void sharpen(String modelName, String newName) {
    if (this.images.containsKey(modelName)) {
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).sharpen()));
    } else {
      throw new IllegalArgumentException("invalid inputs");
    }
  }

  /**
   * Given the name of the stored image and a name to save the manipulation this method makes a
   * greyscale image.
   *
   * @param modelName Name of image to be Greyscaled.
   * @param newName   What the greyScaled image is stored as.
   */
  public void greyscale(String modelName, String newName) {
    if (this.images.containsKey(modelName)) {
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).greyscale()));
    } else {
      throw new IllegalArgumentException("invalid inputs");
    }
  }

  /**
   * Given the name of the stored image and a name to save the manipulation this method makes an
   * image with sepia applied.
   *
   * @param modelName Name of image sepia is applied to.
   * @param newName   What the image after applying sepia is stored as.
   */
  public void sepia(String modelName, String newName) {
    if (this.images.containsKey(modelName)) {
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).sepia()));
    } else {
      throw new IllegalArgumentException("invalid inputs");
    }
  }
}
