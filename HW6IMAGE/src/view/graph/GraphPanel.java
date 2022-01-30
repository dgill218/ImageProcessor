package view.graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import model.graph.Line;
import model.graph.Position2D;

/**
 * The graph panel view class. Allows the GUI to show the histogram of the imgage
 * that is being manipulated.
 */
public class GraphPanel extends JPanel {

  private List<Line> lines;
  //the rectangle within which all lines lie
  private Position2D minD;
  private Position2D maxD;

  /**
   * Constructs a graph panel with the sizes and colors.
   */
  public GraphPanel() {
    super();
    lines = new ArrayList<Line>();
    this.setBackground(Color.WHITE);
    minD = new Position2D(0, 0);
    maxD = new Position2D(0, 0);
    this.setAutoscrolls(true);
    this.setSize(300,400);
    this.setPreferredSize(new Dimension(1000,3000));
  }


  /**
   * Draws the lines of the histogram, with the heights being the frequencies of the RGB pixels.
   * @param lines The lines to be drawn.
   */
  public void setLines(List<Line> lines) {
    this.lines = new ArrayList<Line>(lines);

    List<Position2D> points = new ArrayList<Position2D>();
    for (Line l : this.lines) {
      points.add(new Position2D(l.start));
      points.add(new Position2D(l.end));
    }
    if (points.size() > 0) {

      minD = points.get(0);
      maxD = points.get(1);

      for (Position2D p : points) {
        if (p.getX() < minD.getX()) {
          minD = new Position2D(p.getX(), minD.getY());
        }
        if (p.getY() > minD.getY()) {
          minD = new Position2D(minD.getX(), p.getY());
        }
      }

      for (Position2D p : points) {
        if (p.getX() > maxD.getX()) {
          maxD = new Position2D(p.getX(), maxD.getY());
        }
        if (p.getY() > maxD.getY()) {
          maxD = new Position2D(maxD.getX(), p.getY());
        }
      }
    }



  }


  /**
   * Overrides the paintComponent method of the JPanel.
   *
   * @param g The graphics object that is needed to draw the image.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    AffineTransform originalTransform = g2d.getTransform(); // Moves the origin to bottom left

    g2d.translate(0, this.getPreferredSize().getHeight());
    g2d.scale(1, -1);

    for (Line l : lines) {
      if (Color.RED.equals(l.color)) {
        g2d.setColor(Color.RED);
      } else if (Color.GREEN.equals(l.color)) {
        g2d.setColor(Color.GREEN);
      } else if (Color.BLACK.equals(l.color)) {
        g2d.setColor(Color.BLACK);
      } else if (Color.BLUE.equals(l.color)) {
        g2d.setColor(Color.BLUE);
      }

      Position2D start = l.start;
      Position2D end = l.end;
      g2d.drawLine((int) start.getX(), (int) start.getY(),
          (int) end.getX(), (int) end.getY());
    }
    g2d.setTransform(originalTransform);
  }
}

