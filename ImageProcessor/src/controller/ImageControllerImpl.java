package controller;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import model.ImageProcessorTextInstance;
import view.ImageProcessorView;


/**
 * Represents an Image Controller that is used to run the image processing application given a view
 * and readable object.
 */
public class ImageControllerImpl implements ImageProcessorController {

  private ImageProcessorView view;
  private Scanner in;
  private String filepath;
  private String modelName;
  private String newName;
  private ImageProcessorTextInstance model;

  private static final String LOAD = "load";
  private static final String SAVE = "save";

  // component transformations
  private static final String RED = "red";
  private static final String GREEN = "green";
  private static final String BLUE = "blue";
  private static final String VALUE = "value";
  private static final String GREY = "greyscale";
  private static final String INTS = "intensity";
  private static final String SEPA = "sepia";


  // flip transformations
  private static final String HORI = "horizontal";
  private static final String VERT = "vertical";

  // brightness transformations
  private static final String BRTN = "brighten";
  private static final String DRKN = "darken";

  // Filters
  private static final String SHRP = "sharpen";
  private static final String BLUR = "blur";

  private static final String FILE = "-file";

  // exit
  private static final String EXIT = "exit";

  /**
   * Creates a new ImageControllerImpl given a session, view and readable object. Given a variety of
   * commands such as load, save, get-component (component), horizontal-flip, vertical-flip,
   * brighten, darken, and q to quite the controller creates and manipulates images.
   * @param model The model to be used for the controller.
   * @param view The view that will be transmitting the output
   * @param input The input of the application that is used to read in commands.
   * @throws IllegalArgumentException input is null.
   */
  public ImageControllerImpl(ImageProcessorTextInstance model, ImageProcessorView view,
                             Readable input)
          throws IllegalArgumentException {
    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("Parameters for controller cannot be null");
    }

    //initial view has no model, needed to display intro message
    //Not being used yet from rendering messages
    this.view = view;
    this.model = model;
    this.in = new Scanner(input);
  }

  /**
   * Creates a new ImageControllerImpl given a session, view and readable object. Given a variety of
   * commands such as load, save, get-component (component), horizontal-flip, vertical-flip,
   * brighten, darken, and q to quite the controller creates and manipulates images.
   *
   * @param model The input of the application that is used to read in commands.
   * @param view The view that will be transmitting the output
   * @param in The input stream that the controller will be using
   * @throws IllegalArgumentException input is null.
   */
  public ImageControllerImpl(ImageProcessorTextInstance model, ImageProcessorView view, Scanner in)
          throws IllegalArgumentException {
    if (model == null || view == null || in == null) {
      throw new IllegalArgumentException("Parameters for controller cannot be null");
    }

    this.in = in;
    //initial view has no model, needed to display intro message
    //Not being used yet from rendering messages
    this.view = view;
    this.model = model;
  }


  /**
   * Runs the controller that handles the Processing session, view, and input to conduct the Image
   * Processing.
   *
   * @throws IOException if given inputs fail to transmit inside the Image Processor.
   */
  @Override
  public void start() throws IOException {
    boolean over = false;

    view.renderMessage("Enter a command:");

    while (!over) {

      String option = in.next().toLowerCase(Locale.ROOT);

      switch (option) {
        case LOAD:
          loadCommand();
          break;
        case SAVE:
          saveCommand();
          break;
        case RED:
          redCommand();
          break;
        case BLUE:
          blueCommand();
          break;
        case GREEN:
          greenCommand();
          break;
        case VALUE:
          valueCommand();
          break;
        case INTS:
          intensityCommand();
          break;
        case BLUR:
          blurCommand();
          break;

        case SHRP:
          sharpenCommand();
          break;

        case GREY:
          greyscaleCommand();
          break;

        case SEPA:
          sepiaCommand();
          break;

        case HORI:
          horizontalCommand();
          break;

        case VERT:
          verticalCommand();
          break;

        case BRTN:
          int brightenValue;
          try {
            brightenValue = Integer.parseInt(in.next());
          } catch (NumberFormatException e) {
            this.view.renderMessage("\nMust enter an integer");
            break;
          }
          brightenCommand(brightenValue);
          break;

        case DRKN:
          int darkenValue;
          try {
            darkenValue = Integer.parseInt(in.next());
          } catch (NumberFormatException e) {
            this.view.renderMessage("\nMust enter an integer");
            break;
          }
          darkenCommand(darkenValue);
          break;

        case FILE:
          String fileName = in.next();
          File file = new File(fileName);
          this.in = new Scanner(file);
          break;

        case EXIT:
          over = true;
          break;
        default:
          view.renderMessage("\nInvalid input");
      }
    }

  }

  /**
   * Reads in the filepath and the model name. Used in load and save switch cases.
   */
  private void processFile() {
    this.filepath = in.next();
    this.modelName = in.next();

  }

  /**
   * Reads in the model name and the new model name. Used in image manipulation switch cases.
   */
  private void processModel() {
    this.modelName = in.next();
    this.newName = in.next();
  }

  /**
   * Loads the image if the command is inputted correctly.
   *
   * @throws IOException If the output cannot be transmitted.
   */
  private void loadCommand() throws IOException {
    this.processFile();
    try {
      this.model.load(this.filepath, this.modelName);
      this.view.renderMessage("\nImage Loaded");

    } catch (IllegalArgumentException e) {
      this.view.renderMessage("\n" + e.getMessage());
    }
  }

  /**
   * Saves the image if the command is inputted correctly.
   *
   * @throws IOException If the output cannot be transmitted.
   */
  private void saveCommand() throws IOException {
    this.processFile();
    try {
      this.model.save(this.filepath, this.modelName);
      this.view.renderMessage("\nImage saved");
    } catch (IllegalArgumentException | IOException e) {
      this.view.renderMessage("\n" + e.getMessage());
    }
  }

  /**
   * Performs a blur filter on an image if the input is entered correctly.
   *
   * @throws IOException If the output cannot be transmitted.
   */
  private void blurCommand() throws IOException {
    this.processModel();
    try {
      this.model.blur(this.modelName, this.newName);
      this.view.renderMessage("\nImage blurred");
    } catch (IllegalArgumentException e) {
      this.view.renderMessage("\n" + e.getMessage());
    }
  }


  /**
   * Performs a sharpen filter on an image if the input is entered correctly.
   *
   * @throws IOException If the output cannot be transmitted.
   */
  private void sharpenCommand() throws IOException {
    this.processModel();
    try {
      this.model.sharpen(this.modelName, this.newName);
      this.view.renderMessage("\nImage sharpened");
    } catch (IllegalArgumentException e) {
      this.view.renderMessage("\n" + e.getMessage());
    }
  }

  /**
   * Performs a RedTransformation on an image if the input is entered correctly.
   *
   * @throws IOException If the output cannot be transmitted.
   */
  private void redCommand() throws IOException {
    this.processModel();
    try {
      this.model.red(this.modelName, this.newName);
      this.view.renderMessage("\nComponent Image made");
    } catch (IllegalArgumentException e) {
      this.view.renderMessage("\n" + e.getMessage());
    }
  }

  /**
   * Performs a BlueTransformation on an image if the input is entered correctly.
   *
   * @throws IOException If the output cannot be transmitted.
   */
  private void blueCommand() throws IOException {
    this.processModel();
    try {
      this.model.blue(this.modelName, this.newName);
      this.view.renderMessage("\nComponent Image made");
    } catch (IllegalArgumentException e) {
      this.view.renderMessage("\n" + e.getMessage());
    }
  }

  /**
   * Performs a GreenTransformation on an image if the input is entered correctly.
   *
   * @throws IOException If the output cannot be transmitted.
   */
  private void greenCommand() throws IOException {
    this.processModel();
    try {
      this.model.green(this.modelName, this.newName);
      this.view.renderMessage("\nComponent Image made");
    } catch (IllegalArgumentException e) {
      this.view.renderMessage("\n" + e.getMessage());
    }
  }

  /**
   * Performs a ValueTransformation (Value Greyscale) on an image if the input is entered
   * correctly.
   *
   * @throws IOException If the output cannot be transmitted.
   */
  private void valueCommand() throws IOException {
    this.processModel();
    try {
      this.model.value(this.modelName, this.newName);
      this.view.renderMessage("\nComponent Image made");
    } catch (IllegalArgumentException e) {
      this.view.renderMessage("\n" + e.getMessage());
    }
  }

  /**
   * Performs an IntensityTransformation (Intensity greyscale) on an image if the input is entered
   * correctly.
   *
   * @throws IOException If the output cannot be transmitted.
   */
  private void intensityCommand() throws IOException {
    this.processModel();
    try {
      this.model.intensity(this.modelName, this.newName);
      this.view.renderMessage("\nComponent Image made");
    } catch (IllegalArgumentException e) {
      this.view.renderMessage("\n" + e.getMessage());
    }
  }

  /**
   * Performs a GreyscaleTransformation (Luma Greyscale) on an image if the input is entered
   * correctly.
   *
   * @throws IOException If the output cannot be transmitted.
   */
  private void greyscaleCommand() throws IOException {
    this.processModel();
    try {
      this.model.greyscale(this.modelName, this.newName);
      this.view.renderMessage("\nImage greyscaled");
    } catch (IllegalArgumentException e) {
      this.view.renderMessage("\n" + e.getMessage());
    }
  }

  /**
   * Performs a Sepia transformation on an image if the input is entered correctly.
   *
   * @throws IOException If the output cannot be transmitted.
   */
  private void sepiaCommand() throws IOException {
    this.processModel();
    try {
      this.model.sepia(this.modelName, this.newName);
      this.view.renderMessage("\nSepia image made");
    } catch (IllegalArgumentException e) {
      this.view.renderMessage("\n" + e.getMessage());
    }
  }

  /**
   * Performs a Horizontal flip on an image if the input is entered correctly.
   *
   * @throws IOException If the output cannot be transmitted.
   */
  private void horizontalCommand() throws IOException {
    this.processModel();
    try {
      this.model.horizontalFlip(this.modelName, this.newName);
      this.view.renderMessage("\nImage flipped");
    } catch (IllegalArgumentException e) {
      this.view.renderMessage("\n" + e.getMessage());
    }
  }


  /**
   * Performs a VerticalFlip on an image if the input is entered correctly.
   *
   * @throws IOException If the output cannot be transmitted.
   */
  private void verticalCommand() throws IOException {
    this.processModel();
    try {
      this.model.verticalFlip(this.modelName, this.newName);
      this.view.renderMessage("\nImage flipped");
    } catch (IllegalArgumentException e) {
      this.view.renderMessage("\n" + e.getMessage());
    }
  }


  /**
   * Handles the brighten command by darkening the image. Calls the model to apply the
   * transformation.
   *
   * @param brightenValue The value the image is being brightened by.
   * @throws IOException If the output can't be transmitted.
   */
  private void brightenCommand(int brightenValue) throws IOException {
    this.processModel();
    try {
      this.model.brighten(brightenValue, this.modelName, this.newName);
      this.view.renderMessage("\nImage brightened");
    } catch (IllegalArgumentException e) {
      this.view.renderMessage("\n" + e.getMessage());
    }
  }

  /**
   * Handles the darken command by darkening the image. Calls the model to apply the transformation.
   *
   * @param darkenValue The value the image is being darkened by.
   * @throws IOException If the output can't be transmitted.
   */
  private void darkenCommand(int darkenValue) throws IOException {
    this.processModel();
    try {
      this.model.darken(darkenValue, this.modelName, this.newName);
      this.view.renderMessage("\nImage darkened");
    } catch (IllegalArgumentException e) {
      this.view.renderMessage("\n" + e.getMessage());
    }
  }


}
