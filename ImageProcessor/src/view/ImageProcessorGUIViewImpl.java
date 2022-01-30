package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.graph.Line;
import view.graph.GraphPanel;


/**
 * Class to represent the implementation og a GUI view for and image processing program. The view
 * uses a listener to handle the high level events that are received from hitting buttons. Any
 * errors are represented as pop up boxes that the user sees.
 */
public class ImageProcessorGUIViewImpl extends JFrame implements ImageProcessorGUIView,
        ActionListener {

  private final JLabel imageLabel;
  private final List<JTextField> images;
  private final JMenuItem save;
  private final JMenu filters;
  private final JMenu transformations;
  private final JMenuItem deleteImage;
  private final JMenuItem showImage;
  private final JMenuItem hideImage;
  private final IViewListener listener;
  private final JLayeredPane labels;
  private GraphPanel graph;
  private BufferedImage topImage;
  private int val;

  /**
   * Constructs the initial instance of the GUI.
   *
   * @param listener Listener for this GUI
   */
  public ImageProcessorGUIViewImpl(IViewListener listener) {
    super();
    if (listener == null) {
      throw new IllegalArgumentException("Null parameter.");
    }
    this.listener = listener;
    this.topImage = null;
    setTitle("Image Processor");
    setSize(1800, 800);
    this.images = new ArrayList<>();

    JPanel mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BorderLayout());
    //scroll bars around this main panel
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    JMenuBar menuBar = new JMenuBar();

    JMenu file = new JMenu("File");
    file.getAccessibleContext().setAccessibleDescription(
            "File Menu");


    save = new JMenu("Save...");

    JMenuItem saveLayer = new JMenuItem("Save First Selected Image");
    saveLayer.getAccessibleContext().setAccessibleDescription("Save First Selected Image");
    saveLayer.setActionCommand("Save");
    saveLayer.addActionListener(this);
    save.add(saveLayer);

    save.setEnabled(false);
    file.add(save);

    menuBar.add(file);

    JMenu edit = new JMenu("Edit");
    edit.getAccessibleContext().setAccessibleDescription("Edit Menu");

    filters = new JMenu("Filters");
    filters.getAccessibleContext().setAccessibleDescription("Filters");
    filters.setEnabled(false);

    JMenuItem blur = new JMenuItem("Blur");
    blur.getAccessibleContext().setAccessibleDescription("Blur");
    blur.setActionCommand("Blur");
    blur.addActionListener(this);
    filters.add(blur);

    JMenuItem sharpen = new JMenuItem("Sharpen");
    sharpen.getAccessibleContext().setAccessibleDescription("Sharpen");
    sharpen.setActionCommand("Sharpen");
    sharpen.addActionListener(this);
    filters.add(sharpen);
    edit.add(filters);

    transformations = new JMenu("Transform");
    transformations.getAccessibleContext().setAccessibleDescription("Color Transformations");
    transformations.setEnabled(false);

    JMenuItem grayscale = new JMenuItem("Grayscale");
    grayscale.getAccessibleContext().setAccessibleDescription("Grayscale");
    grayscale.setActionCommand("Grayscale");
    grayscale.addActionListener(this);
    transformations.add(grayscale);

    JMenuItem sepia = new JMenuItem("Sepia");
    sepia.getAccessibleContext().setAccessibleDescription("Sepia");
    sepia.setActionCommand("Sepia");
    sepia.addActionListener(this);
    transformations.add(sepia);
    edit.add(transformations);

    JMenuItem redComponent = new JMenuItem("Red Component");
    redComponent.getAccessibleContext().setAccessibleDescription("Red Component");
    redComponent.setActionCommand("Red Component");
    redComponent.addActionListener(this);
    transformations.add(redComponent);

    JMenuItem blueComponent = new JMenuItem("Blue Component");
    blueComponent.getAccessibleContext().setAccessibleDescription("Blue Component");
    blueComponent.setActionCommand("Blue Component");
    blueComponent.addActionListener(this);
    transformations.add(blueComponent);

    JMenuItem greenComponent = new JMenuItem("Green Component");
    greenComponent.getAccessibleContext().setAccessibleDescription("Green Component");
    greenComponent.setActionCommand("Green Component\"");
    greenComponent.addActionListener(this);
    transformations.add(greenComponent);

    JMenuItem valueComponent = new JMenuItem("Value Component");
    valueComponent.getAccessibleContext().setAccessibleDescription("Value Component");
    valueComponent.setActionCommand("Value Component\"");
    valueComponent.addActionListener(this);
    transformations.add(valueComponent);

    JMenuItem intensityComponent = new JMenuItem("Intensity Component");
    intensityComponent.getAccessibleContext().setAccessibleDescription("Intensity Component");
    intensityComponent.setActionCommand("Intensity Component");
    intensityComponent.addActionListener(this);
    transformations.add(intensityComponent);

    JMenuItem darken = new JMenuItem("Darken");
    darken.getAccessibleContext().setAccessibleDescription("Darken");
    darken.setActionCommand("Darken");
    darken.addActionListener(this);
    transformations.add(darken);

    JMenuItem brighten = new JMenuItem("Brighten");
    brighten.getAccessibleContext().setAccessibleDescription("Brighten");
    brighten.setActionCommand("Brighten");
    brighten.addActionListener(this);
    transformations.add(brighten);

    JMenuItem verticalFlip = new JMenuItem("Vertical Flip");
    verticalFlip.getAccessibleContext().setAccessibleDescription("Vertical Flip");
    verticalFlip.setActionCommand("Vertical Flip");
    verticalFlip.addActionListener(this);
    transformations.add(verticalFlip);

    JMenuItem horizontalFLip = new JMenuItem("Horizontal Flip");
    horizontalFLip.getAccessibleContext().setAccessibleDescription("Horizontal Flip");
    horizontalFLip.setActionCommand("Horizontal Flip");
    horizontalFLip.addActionListener(this);
    transformations.add(horizontalFLip);

    val = 30;

    menuBar.add(edit);

    JMenu image = new JMenu("Images");
    image.getAccessibleContext().setAccessibleDescription("Layer Menu");

    JMenuItem addImage = new JMenu("Add Image...");
    addImage.getAccessibleContext().setAccessibleDescription("Add an image");

    JMenuItem loadLayer = new JMenuItem("Load a PPM/PNG/JPEG File");
    loadLayer.setActionCommand("Add Image");
    loadLayer.addActionListener(this);
    addImage.add(loadLayer);


    image.add(addImage);

    deleteImage = new JMenuItem("Delete Image");
    deleteImage.getAccessibleContext().setAccessibleDescription("Delete Current Image");
    deleteImage.setActionCommand("Delete Image");
    deleteImage.addActionListener(this);
    deleteImage.setEnabled(false);
    image.add(deleteImage);


    JSeparator s2 = new JSeparator();
    s2.setOrientation(SwingConstants.HORIZONTAL);
    image.add(s2);

    showImage = new JMenuItem("Show Image");
    showImage.getAccessibleContext().setAccessibleDescription("Show Selected Image");
    showImage.setActionCommand("Select Image");
    showImage.addActionListener(this);
    showImage.setEnabled(false);
    image.add(showImage);

    hideImage = new JMenuItem("Hide Image");
    hideImage.getAccessibleContext().setAccessibleDescription("Hide Selected Image");
    hideImage.setActionCommand("Hide Image");
    hideImage.addActionListener(this);
    hideImage.setEnabled(false);
    image.add(hideImage);

    menuBar.add(image);

    mainPanel.add(menuBar, BorderLayout.PAGE_START);

    JPanel imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Image Editor"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    mainPanel.add(imagePanel, BorderLayout.CENTER);

    imageLabel = new JLabel();
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(100, 600));
    imageLabel.setHorizontalAlignment(imageScrollPane.getWidth() / 2);
    imagePanel.add(imageScrollPane, BorderLayout.CENTER);

    JPanel operationsPanel = new JPanel();
    operationsPanel.setBorder(BorderFactory.createTitledBorder("Image Operations"));
    mainPanel.add(operationsPanel, BorderLayout.PAGE_END);

    // Panel for image operation commands.
    JPanel ioPanel = new JPanel();
    ioPanel.setLayout(new GridLayout(2, 4));
    operationsPanel.add(ioPanel);

    labels = new JLayeredPane();
    labels.setLayout(new FlowLayout());
    labels.setBorder(BorderFactory.createTitledBorder("Images"));
    labels.setPreferredSize(new Dimension(200, 200));

    JScrollPane labelScroll = new JScrollPane(labels);

    mainPanel.add(labelScroll, BorderLayout.WEST);


    graph = new GraphPanel();
    ScrollPane histogram = new ScrollPane();
    histogram.add(graph);

    JScrollPane histogramScroll = new JScrollPane(histogram);
    Dimension d = new Dimension(300, 600);
    histogramScroll.setBorder(BorderFactory.createTitledBorder("Image Histogram"));
    histogramScroll.setPreferredSize(d);
    mainPanel.add(histogramScroll, BorderLayout.EAST);

    //Buttons

    JButton blurButton = new JButton("Blur");
    blurButton.setActionCommand("Blur");
    blurButton.addActionListener(this);

    JButton sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("Sharpen");
    sharpenButton.addActionListener(this);

    JButton grayscaleButton = new JButton("Grayscale");
    grayscaleButton.setActionCommand("Grayscale");
    grayscaleButton.addActionListener(this);
    grayscaleButton.setPreferredSize(new Dimension(20, 20));

    JButton sepiaButton = new JButton("Sepia");
    sepiaButton.setActionCommand("Sepia");
    sepiaButton.addActionListener(this);

    JButton redButton = new JButton("Red Component");
    redButton.setActionCommand("Red Component");
    redButton.addActionListener(this);

    JButton greenButton = new JButton("Green Component");
    greenButton.setActionCommand("Green Component");
    greenButton.addActionListener(this);

    JButton blueButton = new JButton("Blue Component");
    blueButton.setActionCommand("Blue Component");
    blueButton.addActionListener(this);

    JButton valueButton = new JButton("Value Component");
    valueButton.setActionCommand("Value Component");
    valueButton.addActionListener(this);

    JButton intensityButton = new JButton("Intensity Component");
    intensityButton.setActionCommand("Intensity Component");
    intensityButton.addActionListener(this);

    JButton darkenButton = new JButton("Darken");
    darkenButton.setActionCommand("Darken");
    darkenButton.addActionListener(this);

    JButton brightenButton = new JButton("Brighten");
    brightenButton.setActionCommand("Brighten");
    brightenButton.addActionListener(this);

    JButton horizontalButton = new JButton("Horizontal Flip");
    horizontalButton.setActionCommand("Horizontal Flip");
    horizontalButton.addActionListener(this);

    JButton verticalButton = new JButton("Vertical Flip");
    verticalButton.setActionCommand("Vertical Flip");
    verticalButton.addActionListener(this);

    JButton showButton = new JButton("Show Image");
    showButton.setActionCommand("Show Image");
    showButton.addActionListener(this);

    JButton hideButton = new JButton("Hide Image");
    hideButton.setActionCommand("Hide Image");
    hideButton.addActionListener(this);

    ioPanel.add(blurButton);
    ioPanel.add(sharpenButton);
    ioPanel.add(grayscaleButton);
    ioPanel.add(sepiaButton);
    ioPanel.add(redButton);
    ioPanel.add(greenButton);
    ioPanel.add(blueButton);
    ioPanel.add(valueButton);
    ioPanel.add(intensityButton);
    ioPanel.add(brightenButton);
    ioPanel.add(darkenButton);
    ioPanel.add(verticalButton);
    ioPanel.add(horizontalButton);

    ioPanel.add(showButton);
    ioPanel.add(hideButton);


  }

  @Override
  public void runGUI() {
    ImageProcessorGUIViewImpl.setDefaultLookAndFeelDecorated(false);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  @Override
  public void renderMessage(String msg) throws IllegalArgumentException {
    JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void updateImage(BufferedImage image) {
    this.topImage = image;
    this.imageLabel.setIcon(new ImageIcon(this.topImage));
    save.setEnabled(true);
    repaint();
    revalidate();
  }


  @Override
  public void updateImages(String imageName) {
    JTextField layerLabel = new JTextField();
    layerLabel.setText(imageName);
    layerLabel.setPreferredSize(new Dimension(150, 50));
    layerLabel.setHorizontalAlignment(JLabel.CENTER);
    layerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    layerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    layerLabel.setEditable(false);

    this.images.add(layerLabel);
    // selectImage.setEnabled(true);

    layerLabel.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        listener.setCurrentImageEvent(imageName);
        deleteImage.setEnabled(true);
        showImage.setEnabled(true);
        hideImage.setEnabled(true);
        filters.setEnabled(true);
        transformations.setEnabled(true);

        for (JTextField jtf : images) {
          if (!jtf.equals(layerLabel)) {
            jtf.setBorder(BorderFactory.createLineBorder(Color.BLACK));
          } else {
            jtf.setBorder(BorderFactory.createLineBorder(Color.RED));
          }
        }
      }
    });
    labels.add(layerLabel, labels.getComponentCount() + 1, 0);
    repaint();
    revalidate();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Save":
        try {
          emitSaveEvent();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
        break;
      case "Blur":
        emitBlurLayerEvent();
        break;
      case "Sharpen":
        emitSharpenLayerEvent();
        break;
      case "Grayscale":
        emitGrayscaleLayerEvent();
        break;
      case "Sepia":
        emitSepiaLayerEvent();
        break;
      case "Red Component":
        emitRedComponentLayerEvent();
        break;
      case "Green Component":
        emitGreenComponentLayerEvent();
        break;
      case "Blue Component":
        emitBlueComponentLayerEvent();
        break;
      case "Value Component":
        emitValueComponentLayerEvent();
        break;
      case "Intensity Component":
        emitIntensityComponentLayerEvent();
        break;
      case "Darken":
        emitDarkenEvent(val);
        break;
      case "Brighten":
        emitBrightenEvent(val);
        break;
      case "Vertical Flip":
        emitVerticalFlipEvent();
        break;
      case "Horizontal Flip":
        emitHorizontalFlipEvent();
        break;
      case "Add Image":
        try {
          emitLoadImageEvent();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
        break;
      case "Delete Image":
        emitDeleteLayerEvent();
        break;
      case "Show Image":
        emitShowLayerEvent();
        break;
      case "Hide Image":
        emitHideLayerEvent();
        break;
      case "Save All":
        emitSaveAllEvent();
        break;
      default:
        throw new IllegalStateException("Could not retrieve command");
    }
  }



  /**
   * Tells the listener to save the top most visible layer image with the given file type at the
   * selected file path.
   */
  private void emitSaveEvent() throws IOException {
    if (!this.images.isEmpty()) {
      String[] optionsFileType = {"PPM", "PNG", "JPEG"};
      int filetypeValue = JOptionPane
              .showOptionDialog(this, "Choose the file type to save as", "File Types",
                      JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                      optionsFileType,
                      null);
      final JFileChooser fileChooser = new JFileChooser(".");
      if (filetypeValue != -1) {
        setFiletypeFilter(filetypeValue, fileChooser);
        int retvalue = fileChooser.showSaveDialog(this);

        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fileChooser.getSelectedFile();
          listener.handleSaveImageEvent(f.getAbsolutePath(), optionsFileType[filetypeValue]);
        }
      }
    } else {
      JOptionPane.showMessageDialog(null, "Add an image before saving");
    }
  }

  /**
   * Sets the filter for a file chooser based on the 3 values of either ppm. png. or jpeg.
   *
   * @param filetypeValue Value of the array to use for the filter.
   * @param fileChooser   File chooser to filter.
   */
  private void setFiletypeFilter(int filetypeValue, JFileChooser fileChooser) {
    if (filetypeValue == 0) {
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "PPM", "ppm");
      fileChooser.setFileFilter(filter);
    } else if (filetypeValue == 1) {
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "PNG", "png");
      fileChooser.setFileFilter(filter);
    } else if (filetypeValue == 2) {
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "JPEG", "jpeg");
      fileChooser.setFileFilter(filter);
    }


  }

  /**
   * Tells the listener to save the whole image with the specified type from the user and the
   * selected path from the user.
   */
  private void emitSaveAllEvent() {
    String[] optionsFileType = {"PPM", "PNG", "JPEG"};
    int filetypeValue = JOptionPane
            .showOptionDialog(this, "Choose the file type to save as", "File Types",
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                    optionsFileType,
                    null);
    final JFileChooser fileChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "TXT", "txt");
    fileChooser.setFileFilter(filter);
    int retvalue = fileChooser.showSaveDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fileChooser.getSelectedFile();
      listener.handleSaveAllImagesEvent(f.getAbsolutePath(), optionsFileType[filetypeValue]);
    }
  }

  /**
   * Tells the listener to blur the current layer in the image.
   */
  private void emitBlurLayerEvent() {
    listener.handleBlurEvent();
  }

  /**
   * Tells the listener to sharpen the current layer in the image.
   */
  private void emitSharpenLayerEvent() {
    listener.handleSharpenEvent();
  }

  /**
   * Tells the listener to grayscale the current layer in the image.
   */
  private void emitGrayscaleLayerEvent() {
    listener.handleGrayscaleEvent();
  }

  /**
   * Tells the listener to sepia the current layer in the image.
   */
  private void emitSepiaLayerEvent() {
    listener.handleSepiaEvent();
  }

  /**
   * Tells the listener to apply the red component to the current image.
   */
  private void emitRedComponentLayerEvent() {
    listener.handleRedEvent();
  }

  /**
   * Tells the listener to apply the green component to the current layer in the image.
   */
  private void emitGreenComponentLayerEvent() {
    listener.handleGreenEvent();
  }

  /**
   * Tells the listener to apply the blue component to the current layer in the image.
   */
  private void emitBlueComponentLayerEvent() {
    listener.handleBlueEvent();
  }


  /**
   * Tells the listener to apply the value component to the current layer in the image.
   */
  private void emitValueComponentLayerEvent() {
    listener.handleValueEvent();
  }

  /**
   * Tells the listener to apply the intensity component to the current layer in the image.
   */
  private void emitIntensityComponentLayerEvent() {
    listener.handleIntensityEvent();
  }

  /**
   * Tells the listener to apply a darken transformation with the given val to the current
   * layer in the image.
   */
  private void emitDarkenEvent(int val) {
    listener.handleDarkenEvent(val);
  }

  /**
   * Tells the listener to apply a brighten transformation with the given val to the current
   * layer in the image.
   */
  private void emitBrightenEvent(int val) {
    listener.handleBrightenEvent(val);
  }


  /**
   * Tells the listener to apply a vertical flip to the current layer in the image.
   */
  private void emitVerticalFlipEvent() {
    listener.handleVerticalFlipEvent();
  }

  /**
   * Tells the listener to apply a horizontal flip to the current layer in the image.
   */
  private void emitHorizontalFlipEvent() {
    listener.handleHorizontalFlipEvent();
  }


  /**
   * Tells the listener to remove the current layer. It then updates the list of layers in the GUI
   * with the new list of layers.
   */
  private void emitDeleteLayerEvent() {
    listener.removeLayerEvent();
    this.images.removeIf(jtf -> jtf.getText().equals(this.listener.getCurrentLayerID()));
    this.labels.removeAll();

    for (JTextField jtf : this.images) {
      this.labels.add(jtf);
    }

    this.deleteImage.setEnabled(false);
    this.filters.setEnabled(false);
    this.transformations.setEnabled(false);
    this.showImage.setEnabled(false);
    this.hideImage.setEnabled(false);

    repaint();
    revalidate();
  }

  /**
   * Tells the layer to set the layer with the given id from the user as the current layer. Also
   * sets the board of the layer in the list to red if it has been selected as the current.
   */
  private void emitSelectLayerEvent() {
    String layerName = JOptionPane.showInputDialog("Layer Name");
    if (!layerName.equals("")) {
      listener.setCurrentImageEvent(layerName);
      for (JTextField jtf : this.images) {
        if (!jtf.getText().equals(layerName)) {
          jtf.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
          jtf.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
      }
    }
    this.deleteImage.setEnabled(true);
    this.filters.setEnabled(true);
    this.transformations.setEnabled(true);
    this.showImage.setEnabled(true);
    this.hideImage.setEnabled(true);
  }

  /**
   * Tells the listener to set the current layer to visible.
   */
  private void emitShowLayerEvent() {
    listener.showEvent();
  }

  /**
   * Tells the listener to make the current layer invisible.
   */
  private void emitHideLayerEvent() {
    listener.hideEvent();
  }

  /**
   * Tells the listener to load in the image with the user selected file type and at the selected
   * file path as a layer in the program. The name of the layer is also taken from the user.
   */
  private void emitLoadImageEvent() throws IOException {
    String[] optionsFileType = {"PPM", "PNG", "JPEG"};
    int filetypeValue = JOptionPane
            .showOptionDialog(this, "Please choose the file type to import", "File Types",
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                    optionsFileType, null);

    if (filetypeValue != -1) {
      String layerName = JOptionPane.showInputDialog("Please enter the name of the layer.");

      if (layerName != null) {
        final JFileChooser fileChooser = new JFileChooser(".");

        setFiletypeFilter(filetypeValue, fileChooser);
        int retvalue = fileChooser.showOpenDialog(this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fileChooser.getSelectedFile();
          listener
                  .handleLoadImageEvent(f.getAbsolutePath(), optionsFileType[filetypeValue],
                          layerName);
        }
      }
    }
  }

  /**
   * Given a list of lines this method updates the histogram with the given lines.
   *
   * @param lines A list of lines that are drawn to make a histogram.
   */
  public void updateGraph(List<Line> lines) {
    this.repaint();
    this.graph.repaint();
    this.graph.setLines(lines);
  }


}
