package model.graph;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.imaging.pixel.IPixel;

/**
 * Class to represent an image histogram. Gets the frequencies of the RGB values and creates the
 * lines for the view to plot.
 */
public class Histogram {
  private List<ArrayList<IPixel>> pixels;
  private List<Line> lines;
  private Map<Integer, Integer> red;
  private Map<Integer, Integer> blue;
  private Map<Integer, Integer> green;
  private Map<Integer, Integer> intensity;

  /**
   * Creates a Histogram with the 2d list of pixels that will be used for the histogram.
   * Initializes the frequency maps for each of the channels to empty maps.
   * @param pixels The 2d list of pixels to be used.
   */
  public Histogram(List<ArrayList<IPixel>> pixels) {
    this.pixels = pixels;
    lines = new ArrayList<Line>();

    this.red = new HashMap<>();
    this.green = new HashMap<>();
    this.blue = new HashMap<>();
    this.intensity = new HashMap<>();
    //initialize maps for storing values
    for (int i = 0; i < 256; i++) {
      red.put(i, 0);
      blue.put(i, 0);
      green.put(i, 0);
      intensity.put(i, 0);
    }
    this.getFrequencies();
    this.addLines();
  }

  //fill all of the maps with corresponding frequencies.
  private void getFrequencies() {
    for (List<IPixel> list : this.pixels) {
      for (IPixel pixel : list) {
        int red = pixel.getColor().getRed();
        int blue = pixel.getColor().getBlue();
        int green = pixel.getColor().getGreen();
        int intensity = (red + green + blue) / 3;

        this.red.put(red, this.red.get(red) + 1);
        this.blue.put(blue, this.blue.get(blue) + 1);
        this.green.put(green, this.green.get(green) + 1);
        this.intensity.put(intensity, this.intensity.get(intensity) + 1);
      }
    }
  }

  // Adds all of the lines to the maps for the colors.
  private void addLines() {
    //create lines
    for (int i = 0; i < 255; i++) {
      Line redLine = new Line(new Position2D(i, red.get(i)),
          new Position2D(i + 1, red.get(i + 1)), Color.RED);

      Line greenLine = new Line(new Position2D(i, green.get(i)),
          new Position2D(i + 1, green.get(i + 1)), Color.GREEN);

      Line blueLine = new Line(new Position2D(i, blue.get(i)),
          new Position2D(i + 1, blue.get(i + 1)), Color.BLUE);

      Line intensityLine = new Line(new Position2D(i, intensity.get(i)),
          new Position2D(i + 1, intensity.get(i + 1)), Color.BLACK);
      this.lines.add(redLine);
      this.lines.add(greenLine);
      this.lines.add(blueLine);
      this.lines.add(intensityLine);
    }
  }

  public List<Line> getLines() {
    return this.lines;
  }
}
