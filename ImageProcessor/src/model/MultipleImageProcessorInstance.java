package model;

import filters.FilterBlur;
import filters.SharpenFilter;
import filters.colortransformation.GreyscaleTransformationMatrix;
import filters.colortransformation.IntensityTransformation;
import filters.colortransformation.SepiaTransformation;
import filters.colortransformation.ValueTransformation;
import filters.colortransformation.greyscale.BlueComponent;
import filters.colortransformation.greyscale.GreenComponent;
import filters.colortransformation.greyscale.RedComponent;
import filters.flippingtransformation.FlipHorizontal;
import filters.flippingtransformation.FlipVertical;
import filters.intensitytransformation.BrightenTransformation;
import filters.intensitytransformation.DarkenTransformation;
import model.imaging.PixelImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class representing the implementation of an image processing model for when we want to load
 * multiple images at once, and hide/show them in something like a GUI. One model contains
 * only one multi layer image, and can have as many layers as it wants. Layers are given names, and
 * the names are then assigned to images in the delegate. The ids of invisible layers are stored in
 * another list.
 */
public class MultipleImageProcessorInstance implements IImageProcessorInstance {

  // private final ImageProcessorModel delegate;
  private final List<String> layers;
  private final List<String> hidden;
  private final Map<String, PixelImage> images;

  /**
   * Creates an instance of the multi layer model.
   *
   * @param layers List of ids of the layers.
   * @param hidden List of ids of invisible layers.
   * @throws IllegalArgumentException If any argument is null.
   */
  public MultipleImageProcessorInstance(List<String> layers,
                                        List<String> hidden) throws IllegalArgumentException {
    if (layers == null || hidden == null) {
      throw new IllegalArgumentException("Null parameter.");
    }
    //  this.delegate = delegate;
    this.layers = layers;
    this.hidden = hidden;
    this.images = new HashMap<>();
  }

  /**
   * Convenience constructor for the model.
   */
  public MultipleImageProcessorInstance() {
    // this.delegate = new ImageProcessorModelImpl();
    this.layers = new ArrayList<>();
    this.hidden = new ArrayList<>();
    this.images = new HashMap<>();
  }

  @Override
  public void addImage(String id, PixelImage image) throws IllegalArgumentException {
    if (id == null || image == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }
    if (this.layers.contains(id)) {
      throw new IllegalArgumentException("Id is already contained.");
    }
    // this.sameDimensions(image);
    this.layers.add(id);
    //  this.delegate.addImage(id, image);
    if (this.images.containsKey(id)) {
      throw new IllegalArgumentException("Image with id already exits.");
    }

    this.images.putIfAbsent(id, image);
  }

  @Override
  public PixelImage getImage(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }
    // return this.delegate.getImage(id);
    if (!this.images.containsKey(id)) {
      throw new IllegalArgumentException("Image with id does not exist.");
    }

    return this.images.get(id);
  }

  @Override
  public void replaceImage(String id, PixelImage image) throws IllegalArgumentException {
    if (id == null || image == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }
    if (!this.layers.contains(id)) {
      throw new IllegalArgumentException("Id is not contained.");
    }
    // this.sameDimensions(image);
    // this.delegate.replaceImage(id, image);
    if (!this.images.containsKey(id)) {
      throw new IllegalArgumentException("No such image is contained");
    }

    this.images.replace(id, image);

  }

  /**
   * Checks if the given image has the same dimensions as the first added layer.
   *
   * @param image Image to check.
   * @throws IllegalArgumentException If the image does not have the same dimensions.
   */
  private void sameDimensions(PixelImage image) throws IllegalArgumentException {
    if (!this.layers.isEmpty() && (
            image.getPixels().size() != this.images.get(this.layers.get(0)).getPixels().size() ||
                    image.getPixels().get(0).size() != this.images.get(this.layers.get(0))
                            .getPixels().get(0).size())) {
      throw new IllegalArgumentException("Layers must all be the same dimensions.");
    }
  }

  @Override
  public PixelImage blur(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }
    return new FilterBlur().transform(this.getImage(id));
  }

  @Override
  public PixelImage sharpen(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }
    return new SharpenFilter().transform(this.getImage(id));
  }

  @Override
  public PixelImage blueComponent(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new BlueComponent().applyColorTransformation(this.getImage(id));
  }

  @Override
  public PixelImage redComponent(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new RedComponent().applyColorTransformation(this.getImage(id));
  }

  @Override
  public PixelImage greenComponent(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new GreenComponent().applyColorTransformation(this.getImage(id));
  }

  @Override
  public PixelImage valueComponent(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new ValueTransformation().applyColorTransformation(this.getImage(id));
  }

  @Override
  public PixelImage intensityComponent(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new IntensityTransformation().applyColorTransformation(this.getImage(id));
  }


  @Override
  public PixelImage grayscale(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new GreyscaleTransformationMatrix().transform(this.getImage(id));
  }

  @Override
  public PixelImage sepia(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new SepiaTransformation().transform(this.getImage(id));
  }


  @Override
  public PixelImage darken(String id, int val) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new DarkenTransformation().applyTransformation(this.getImage(id), val);
  }

  @Override
  public PixelImage brighten(String id, int val) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new BrightenTransformation().applyTransformation(this.getImage(id), val);
  }

  @Override
  public PixelImage horizontalFlip(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new FlipHorizontal().flipTransform(this.getImage(id));
  }

  @Override
  public PixelImage verticalFlip(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new FlipVertical().flipTransform(this.getImage(id));
  }


  @Override
  public void removeImage(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("String cannot be null.");
    }
    if (!this.layers.contains(id)) {
      throw new IllegalArgumentException("Layer with id does not exist.");
    }
    if (!this.images.containsKey(id)) {
      throw new IllegalArgumentException("No such image is contained");
    }
    this.layers.remove(id);
    this.hidden.remove(id);
    this.images.remove(id);
  }


  @Override
  public void showImage(String id) {
    if (id == null) {
      throw new IllegalArgumentException("String cannot be null.");
    }
    if (!this.hidden.contains(id)) {
      throw new IllegalArgumentException("Image with id is already visible.");
    }
    if (!this.layers.contains(id)) {
      throw new IllegalArgumentException("Id does not exist.");
    }
    this.hidden.remove(id);
  }

  @Override
  public void hideImage(String id) {
    if (id == null) {
      throw new IllegalArgumentException("String cannot be null.");
    }
    if (this.hidden.contains(id)) {
      throw new IllegalArgumentException("Image with id is already hidden.");
    }
    if (!this.layers.contains(id)) {
      throw new IllegalArgumentException("Id does not exist.");
    }
    this.hidden.add(id);
  }


  @Override
  public void addMultipleImages(Map<String, PixelImage> images, List<String> invisibleLayers)
          throws IllegalArgumentException {
    if (images == null || invisibleLayers == null) {
      throw new IllegalArgumentException("Null parameters.");
    }
    for (String id : this.layers) {
      if (id == null) {
        throw new IllegalArgumentException("Arguments cannot be null.");
      }
      if (!this.images.containsKey(id)) {
        throw new IllegalArgumentException("No such image is contained");
      }
      this.images.remove(id);
    }
    this.layers.clear();
    this.hidden.clear();
    for (Map.Entry<String, PixelImage> item : images.entrySet()) {
      this.addImage(item.getKey(), item.getValue());
    }
    this.hidden.addAll(invisibleLayers);

  }

  @Override
  public List<String> getVisibility() {
    return new ArrayList<>(this.hidden);
  }

  @Override
  public Map<String, PixelImage> getLayers() throws IllegalArgumentException {
    Map<String, PixelImage> layersMap = new HashMap<>();
    for (String id : this.layers) {
      layersMap.put(id, this.getImage(id));
    }
    return layersMap;
  }

}
