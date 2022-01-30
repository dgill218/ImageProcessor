import java.io.IOException;

/**
 * Appendable class to simulate an appendable failing transmission.
 */
public class FakeAppendable implements Appendable {

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("IO Error.");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("IO Error.");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("IO Error.");
  }
}
