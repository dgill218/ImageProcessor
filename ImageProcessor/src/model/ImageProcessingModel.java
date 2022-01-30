package model;

import filters.FilterBlur;
import filters.SharpenFilter;
import filters.colortransformation.GreyscaleTransformationMatrix;
import filters.colortransformation.SepiaTransformation;
import filters.colortransformation.greyscale.BlueComponent;
import filters.colortransformation.greyscale.GreenComponent;
import filters.colortransformation.greyscale.GreyscaleTransformation;
import filters.colortransformation.greyscale.RedComponent;
import filters.colortransformation.IntensityTransformation;
import filters.colortransformation.ValueTransformation;
import filters.flippingtransformation.FlipHorizontal;
import filters.flippingtransformation.FlipVertical;
import filters.intensitytransformation.BrightenTransformation;
import filters.intensitytransformation.DarkenTransformation;

import java.io.IOException;

import model.imaging.PixelImage;

/**
 * Class representing a model for an ImageProcessor.
 */
public class ImageProcessingModel implements ITextImageProcessingModel {

  private PixelImage image;

  /**
   * Constructor for an image processing model that uses a given image to set its image field.
   *
   * @param image An image which contains a 2d array of pixels.
   * @throws IllegalArgumentException if the image is null
   */
  public ImageProcessingModel(PixelImage image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    this.image = image;
  }

  /**
   * Brightens a given image.
   *
   * @param val value that the image will be brightened by.
   * @return the brightened image.
   * @throws IllegalArgumentException if the image is null
   */
  @Override
  public PixelImage brighten(int val) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new BrightenTransformation().applyTransformation(this.image, val);
  }

  /**
   * Darkens a given image.
   *
   * @param val value that the image will be darkened by.
   * @return the darkened image.
   * @throws IllegalArgumentException if the image is null
   */
  @Override
  public PixelImage darken(int val) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new DarkenTransformation().applyTransformation(this.image, val);
  }

  /**
   * Greyscale an image based on the red component.
   *
   * @return the greyscale image.
   * @throws IllegalArgumentException if the image is null
   */
  @Override
  public PixelImage redComponent() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new RedComponent().applyColorTransformation(this.image);
  }


  /**
   * Greyscale an image based on the green component.
   *
   * @return the greyscale image.
   * @throws IllegalArgumentException if the image is null
   */
  @Override
  public PixelImage greenComponent() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new GreenComponent().applyColorTransformation(this.image);
  }

  /**
   * Greyscale an image based on the blue component.
   *
   * @return the greyscale image.
   * @throws IllegalArgumentException if the image is null
   */
  @Override
  public PixelImage blueComponent() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new BlueComponent().applyColorTransformation(this.image);
  }

  /**
   * Alters an image's intensity.
   *
   * @return The intensified image.
   * @throws IllegalArgumentException if image is null
   */
  @Override
  public PixelImage intensity() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new IntensityTransformation().applyColorTransformation(this.image);
  }

  /**
   * Alters an image's pixels' value.
   *
   * @return The image with changed values.
   * @throws IllegalArgumentException if image is null
   */
  @Override
  public PixelImage valueImage() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new ValueTransformation().applyColorTransformation(this.image);
  }

  /**
   * Greyscale an image based on the luma of the components.
   *
   * @return the greyscale image.
   * @throws IllegalArgumentException if the image is null
   */
  @Override
  public PixelImage luma() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new GreyscaleTransformation().applyColorTransformation(this.image);
  }


  /**
   * Flips the image horizontally.
   *
   * @return The horizontally flipped image.
   * @throws IllegalArgumentException if the image is null.
   */
  @Override
  public PixelImage horizontalFlip() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }

    return new FlipHorizontal().flipTransform(this.image);
  }

  /**
   * Flips the image vertically.
   *
   * @return The vertically flipped image.
   * @throws IllegalArgumentException if the image is null.
   */
  @Override
  public PixelImage verticalFlip() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }

    return new FlipVertical().flipTransform(this.image);
  }

  /**
   * Filters the image by blurring the image.
   *
   * @return The blurred image.
   */
  @Override
  public PixelImage blur() {
    return new FilterBlur().transform(this.image);
  }

  /**
   * Filters the image by sharpening the image.
   *
   * @return The sharpened image.
   */
  @Override
  public PixelImage sharpen() {
    return new SharpenFilter().transform(this.image);
  }

  /**
   * Transforms the image into a sepia colored image.
   *
   * @return The transformed image.
   */
  @Override
  public PixelImage sepia() {
    return new SepiaTransformation().transform(this.image);
  }

  /**
   * Greyscale an image by using a matrix for component conversion.
   *
   * @return the greyscale image.
   */
  @Override
  public PixelImage greyscale() {
    if (image == null) {
      throw new IllegalArgumentException("Image can't be null");
    }
    return new GreyscaleTransformationMatrix().transform(this.image);
  }

  /**
   * Saves a file of the given filename as a PPM file.
   *
   * @param filename the file name of the image
   * @throws IOException if the filename is invalid
   */
  @Override
  public void saveImageAsPPM(String filename) throws IOException {
    this.image.saveImageAsPPM(filename);
  }

  /**
   * Saves a file of the given filename as the type specified in the outputName file.
   *
   * @param outputName the file name of the image
   * @throws IOException if the filename is invalid
   */
  public void saveImageAs(String outputName) throws IOException {
    this.image.saveImageAs(outputName);
  }

}
