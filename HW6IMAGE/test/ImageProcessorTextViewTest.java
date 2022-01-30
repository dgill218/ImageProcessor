import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.Test;
import view.ImageProcessorTextView;
import view.ImageProcessorView;

/**
 * Test class for the text view.
 */
public class ImageProcessorTextViewTest {

  private ImageProcessorView view;


  // tests writing to appendable.
  @Test
  public void testRenderMessage() throws IOException {
    Appendable output = new StringBuilder();
    view = new ImageProcessorTextView(output);
    view.renderMessage("Hello world.");
    assertEquals("Hello world.", output.toString());
  }

  // tests writing null appendable
  @Test(expected = IllegalArgumentException.class)
  public void renderMNessageNull() throws IOException {
    Appendable output = new StringBuilder();
    view = new ImageProcessorTextView(null);
    view.renderMessage("hi");
  }

  // tests if writing fails.
  @Test(expected = IOException.class)
  public void renderMessageBadAppendable() throws IOException {
    Appendable output = new FakeAppendable();
    view = new ImageProcessorTextView(output);
    view.renderMessage("hi");
  }
}