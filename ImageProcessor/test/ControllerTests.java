import static org.junit.Assert.assertEquals;

import org.junit.Test;
import view.IViewListener;

/**
 * Testing class to make sure that the controller transmits to the view correctly based
 * on user input.
 */
public class ControllerTests {



  @Test
  public void testBlurButtonClick() {
    Appendable out = new StringBuilder();
    IViewListener controller = new ControllerMock(out);
    ViewMock mockView = new ViewMock(controller, new StringBuilder());
    mockView.emitBlurLayerEvent();

    assertEquals(out.toString(), "handleBlurEvent");

  }

  @Test
  public void testSharpenButtonClick() {
    Appendable out = new StringBuilder();
    IViewListener controller = new ControllerMock(out);
    ViewMock mockView = new ViewMock(controller, new StringBuilder());
    mockView.emitSharpenLayerEvent();

    assertEquals(out.toString(), "handleSharpenEvent");

  }

  @Test
  public void testGrayscaleButtonClick() {
    Appendable out = new StringBuilder();
    IViewListener controller = new ControllerMock(out);
    ViewMock mockView = new ViewMock(controller, new StringBuilder());
    mockView.emitGrayscaleLayerEvent();

    assertEquals(out.toString(), "handleGrayscaleEvent");

  }

  @Test
  public void testRedButtonClick() {
    Appendable out = new StringBuilder();
    IViewListener controller = new ControllerMock(out);
    ViewMock mockView = new ViewMock(controller, new StringBuilder());
    mockView.emitRedEvent();
    assertEquals(out.toString(), "handleRedEvent");

  }


  @Test
  public void testGreenButtonClick() {
    Appendable out = new StringBuilder();
    IViewListener controller = new ControllerMock(out);
    ViewMock mockView = new ViewMock(controller, new StringBuilder());
    mockView.emitGreenEvent();
    assertEquals(out.toString(), "handleGreenEvent");

  }

  @Test
  public void testBlueButtonClick() {
    Appendable out = new StringBuilder();
    IViewListener controller = new ControllerMock(out);
    ViewMock mockView = new ViewMock(controller, new StringBuilder());
    mockView.emitBlueEvent();
    assertEquals(out.toString(), "handleBlueEvent");

  }


  @Test
  public void testSepiaButtonClick() {
    Appendable out = new StringBuilder();
    IViewListener controller = new ControllerMock(out);
    ViewMock mockView = new ViewMock(controller, new StringBuilder());
    mockView.emitSepiaLayerEvent();

    assertEquals(out.toString(), "handleSepiaEvent");

  }

  @Test
  public void testDeleteLayerButtonClick() {
    Appendable out = new StringBuilder();
    IViewListener controller = new ControllerMock(out);
    ViewMock mockView = new ViewMock(controller, new StringBuilder());
    mockView.emitDeleteLayerEvent();

    assertEquals(out.toString(), "removeLayerEvent");

  }


  @Test
  public void testShowLayerButtonClick() {
    Appendable out = new StringBuilder();
    IViewListener controller = new ControllerMock(out);
    ViewMock mockView = new ViewMock(controller, new StringBuilder());
    mockView.emitShowLayerEvent();

    assertEquals(out.toString(), "showEvent");

  }

  @Test
  public void testHideLayerButtonClick() {
    Appendable out = new StringBuilder();
    IViewListener controller = new ControllerMock(out);
    ViewMock mockView = new ViewMock(controller, new StringBuilder());
    mockView.emitHideLayerEvent();

    assertEquals(out.toString(), "hideEvent");

  }


}
