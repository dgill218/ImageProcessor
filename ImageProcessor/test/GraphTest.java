import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import model.graph.Histogram;
import model.graph.Line;
import model.graph.Position2D;
import model.imaging.IColor;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.PixelImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A test class that ensures that a histogram can b e created correctly.
 */
public class GraphTest {


  @Test
  public void testPosition2D() {
    Position2D pos = new Position2D(33, 5);
    assertEquals(33, (int) pos.getX());
    assertEquals(5, (int) pos.getY());
  }

  @Test
  public void testLine() {
    Position2D start = new Position2D(0, 0);
    Position2D end = new Position2D(50, 52);
    Line line = new Line(start, end, Color.BLACK);
    assertEquals(end, line.getEnd());
    assertEquals(start, line.getStart());
    assertEquals(Color.black, line.color);
  }

  @Test
  public void testHistogram() {
    List<ArrayList<IPixel>> listPixels = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      ArrayList<IPixel> pixels = new ArrayList<>();
      IColor test = new model.imaging.Color(i, i, i);
      IPixel pixel1 = new PixelImpl(new Posn(i, i), test);
      pixels.add(pixel1);
      listPixels.add(pixels);
    }
    Histogram graph = new Histogram(listPixels);

    List<Line> expected;
    expected = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      expected.add(new Line(new Position2D(i, 1), new Position2D(i + 1, 1), Color.RED));
      expected.add(new Line(new Position2D(i, 1), new Position2D(i + 1, 1), Color.GREEN));
      expected.add(new Line(new Position2D(i, 1), new Position2D(i + 1, 1), Color.BLUE));
      expected.add(new Line(new Position2D(i, 1), new Position2D(i + 1, 1), Color.BLACK));
    }

    List<Line> actual = graph.getLines();
    for (int i = 0; i < 5; i++) {
      assertEquals(expected.get(i).getStart(), actual.get(i).getStart());
      assertEquals(expected.get(i).getEnd(), actual.get(i).getEnd());
      assertEquals(expected.get(i).color, actual.get(i).color);
    }
  }
}

