import controller.ImageProcessorController;
import controller.ImageControllerImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.ImageProcessorGUIController;
import model.IImageProcessorInstance;
import model.ImageProcessingModel;
import model.ImageProcessorTextInstance;
import model.MultipleImageProcessorInstance;
import model.imaging.Color;
import model.imaging.Image;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.PixelImpl;
import view.ImageProcessorTextView;
import view.ImageProcessorView;

/**
 * Class representing the main processing unit that handles the MVC Image Processor.
 */
public final class ImageProcessor {

  /**
   * The main function that runs the controller for an ImageProcessor.
   *
   * @param args the client's given inputs
   * @throws IOException if the client gives inputs invalid to the ImageProcessor.
   */
  public static void main(String[] args) throws IOException {
    if (args.length == 2) {
      if (args[0].equals("-file")) {
        String fileName = args[1];
        File file = new File(fileName);
        Readable rd = new InputStreamReader(System.in);

        ArrayList<IPixel> pixelArray = new ArrayList<>();
        pixelArray.add(new PixelImpl(new Posn(0, 0), new Color(0, 0, 0)));
        List<ArrayList<IPixel>> list = new ArrayList<>();
        list.add(pixelArray);

        Image startingImage = new Image(list);
        ImageProcessorTextInstance instance = new ImageProcessorTextInstance();
        ImageProcessorView view =
                new ImageProcessorTextView(new ImageProcessingModel(startingImage),
                        System.out);

        ImageProcessorController controller =
                new ImageControllerImpl(instance, view, new Scanner(file));
        controller.start();
      }
    } else if (args.length == 1 && args[0].equals("-text")) {
      Readable rd = new InputStreamReader(System.in);

      ArrayList<IPixel> pixelArray = new ArrayList<>();
      pixelArray.add(new PixelImpl(new Posn(0, 0), new Color(0, 0, 0)));
      List<ArrayList<IPixel>> list = new ArrayList<>();
      list.add(pixelArray);

      Image startingImage = new Image(list);
      ImageProcessorTextInstance instance = new ImageProcessorTextInstance();
      ImageProcessorView view = new ImageProcessorTextView(new ImageProcessingModel(startingImage),
              System.out);

      ImageProcessorController controller = new ImageControllerImpl(instance, view, rd);
      controller.start();
    } else if (args.length == 0) {
      IImageProcessorInstance testModel = new MultipleImageProcessorInstance();
      ImageProcessorController controller = new ImageProcessorGUIController(testModel);
      controller.start();
    } else {
      System.out.println("Bad arguments for program.");
    }
  }
}

