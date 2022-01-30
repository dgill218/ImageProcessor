package model;

import model.imaging.PixelImage;

import java.util.List;
import java.util.Map;

/**
 * Interface to represent the model of an image program that can edit a multi layered image. Model
 * can hold exactly one multi layer image at a time, and can add, remove, or replace layers. Each
 * layer is represented with a string name and an image interface image. Layers can also be marked
 * as either invisible or visible.
 */
public interface IImageProcessorInstance {

  /**
   * Gets the image represented by the id and marks it as visible.
   *
   * @param id String of image to mark.
   * @throws IllegalArgumentException If the id is null, the id is already shown, or no layer with
   *                                  the id exists.
   */
  void showImage(String id) throws IllegalArgumentException;

  /**
   * Gets the image represented by the given id and marks it as invisible.
   *
   * @param id String of image to mark.
   * @throws IllegalArgumentException If the id is null, the id is already hidden,  or no layer with
   *                                  the id exists.
   */
  void hideImage(String id) throws IllegalArgumentException;

  /**
   * Adds multiple images at the same time to this model.
   *
   * @param images          Strings and images to make up the layers of the image.
   * @param invisibleLayers List containing the ids of invisible layers for this multi layer image.
   * @throws IllegalArgumentException If any argument is null, if all layers do not have the same
   *                                  dimensions, or layers share the same id.
   */
  void addMultipleImages(Map<String, PixelImage> images, List<String> invisibleLayers)
          throws IllegalArgumentException;

  /**
   * Returns the list of ids of invisible images for this multi layer image.
   *
   * @return The list of invisible image ids.
   */
  List<String> getVisibility();

  /**
   * Gets a map assigning the image ids to the image represented by it.
   *
   * @return Map of all ids and their respective images.
   */
  Map<String, PixelImage> getLayers();


  /**
   * Replaces the image associated with the given id with the given image.
   *
   * @param id    Id of image to be replaced.
   * @param image Image to replace with.
   * @throws IllegalArgumentException If either argument is null or there is no such id in the map.
   */
  void replaceImage(String id, PixelImage image) throws IllegalArgumentException;

  /**
   * Adds the given image to the map with the given id as the key.
   *
   * @param id    Key for the image.
   * @param image Image to be added.
   * @throws IllegalArgumentException If either argument is null or the id is already contained.
   */
  void addImage(String id, PixelImage image) throws IllegalArgumentException;

  /**
   * Returns the image associated with the given id.
   *
   * @param id Key to grab the image.
   * @return The image associated with the id.
   * @throws IllegalArgumentException If the id is null, or there is no image associated with that
   *                                  id.
   */
  PixelImage getImage(String id) throws IllegalArgumentException;

  /**
   * Applies the blur filter to the image in the map associated with the given id.
   *
   * @param id Id for image to filter.
   * @return The filtered image with blur applied.
   * @throws IllegalArgumentException If any argument is null or the id is invalid.
   */
  PixelImage blur(String id) throws IllegalArgumentException;

  /**
   * Applies the sharpen filter to the image in the map associated with the given id.
   *
   * @param id Id for image to filter.
   * @return The filtered image with sharpen applied.
   * @throws IllegalArgumentException If any argument is null or the id is invalid.
   */
  PixelImage sharpen(String id) throws IllegalArgumentException;

  /**
   * Applies the grayscale color transformation to the image associated with the id.
   *
   * @param id Id for the image to be transformed.
   * @return The transformed image in grayscale.
   * @throws IllegalArgumentException If any argument is null or the id is not contained in the
   *                                  map.
   */
  PixelImage grayscale(String id)
          throws IllegalArgumentException;

  /**
   * Applies the sepia color transformation to the image associated with the id.
   *
   * @param id Id for the image to be transformed.
   * @return The transformed image in sepia.
   * @throws IllegalArgumentException If any argument is null or the id is not contained in the
   *                                  map.
   */
  PixelImage sepia(String id)
          throws IllegalArgumentException;

  /**
   * Creates an image that visualizes the blue component of the image.
   *
   * @return the transformed greyscale image.
   * @throws IllegalArgumentException If the image is null
   */
  PixelImage blueComponent(String id) throws IllegalArgumentException;

  /**
   * Creates an image that visualizes the red component of the image.
   *
   * @return the transformed greyscale image.
   * @throws IllegalArgumentException If the image is null
   */
  PixelImage redComponent(String id) throws IllegalArgumentException;

  /**
   * Creates an image that visualizes the green component of the image.
   *
   * @return the transformed greyscale image.
   * @throws IllegalArgumentException If the image is null
   */

  PixelImage greenComponent(String id) throws IllegalArgumentException;

  /**
   * Creates an image that visualizes the value component of the image.
   *
   * @return the transformed greyscale image.
   * @throws IllegalArgumentException If the image is null
   */

  PixelImage valueComponent(String id) throws IllegalArgumentException;

  /**
   * Creates an image that visualizes the intensity component of the image.
   *
   * @return the transformed greyscale image.
   * @throws IllegalArgumentException If the image is null
   */

  PixelImage intensityComponent(String id) throws IllegalArgumentException;


  /**
   * Creates an image that is darkened by the given value.
   *
   * @return the transformed greyscale image.
   * @throws IllegalArgumentException If the image is null
   */

  PixelImage darken(String id, int val) throws IllegalArgumentException;

  /**
   * Creates an image that is brightened by the given value.
   *
   * @return the transformed greyscale image.
   * @throws IllegalArgumentException If the image is null
   */

  PixelImage brighten(String id, int val) throws IllegalArgumentException;

  /**
   * Flips an image horizontally.
   *
   * @return the flipped image.
   * @throws IllegalArgumentException If the image is null
   */
  PixelImage horizontalFlip(String id) throws IllegalArgumentException;

  /**
   * Flips an image vertically.
   *
   * @return the flipped image.
   * @throws IllegalArgumentException If the image is null
   */
  PixelImage verticalFlip(String id) throws IllegalArgumentException;



  /**
   * Removes the given image associated with the string id from the model.
   *
   * @param id String of image to remove.
   * @throws IllegalArgumentException If the string is null, or it is not contained in the model.
   */
  void removeImage(String id) throws IllegalArgumentException;


}
