package model.graph;

import java.awt.Color;
import java.util.Objects;

/**
 * Class for a straight line with a start and endpoint.
 */
public final class Line {
  public final Position2D start;
  public final Position2D end;
  public final Color color;

  /**
   * Constructs a line with the gi en start point, end point, and color.
   * @param start The starting position of the line.
   * @param end The ending position of the line.
   * @param color The color of the line.
   */
  public Line(Position2D start, Position2D end, Color color) {
    this.start = start;
    this.end = end;
    this.color = color;
  }

  /**
   * Gets the starting position of the line.
   * @return The starting position of the line.
   */
  public Position2D getStart() {
    return this.start;
  }


  /**
   * Gets the ending position of the line.
   * @return Returns the ending point of the line.
   */
  public Position2D getEnd() {
    return this.end;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Line)) {
      return false;
    }

    Line line = (Line) o;

    return (this.start.equals(line.start) && this.end.equals(line.end))
            || (this.end.equals(line.start) && this.start.equals(line.end));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.start, this.end);
  }
}
