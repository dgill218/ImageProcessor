import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import model.imaging.Color;
import model.imaging.IColor;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing class for ColorImpl.
 */
public class ColorTest {

  private IColor white = new Color(0, 0, 0);

  private IColor black = new Color(255, 255, 255);

  private IColor red = new Color(255, 0, 0);

  private IColor green = new Color(0, 255, 0);

  private IColor blue = new Color(0, 0, 255);

  @Before
  public void init() {
    this.white = new Color(0, 0, 0);

    this.black = new Color(255, 255, 255);

    this.red = new Color(255, 0, 0);

    this.green = new Color(0, 255, 0);

    this.blue = new Color(0, 0, 255);
  }

  // CONSTRUCTOR TESTS

  // -----------------------------------------------------------------------------------------------


  /*
        -----------------------------
       | Constructor Exception Tests |
        -----------------------------
  */

  // red over 255 exception
  @Test(expected = IllegalArgumentException.class)
  public void redOver255Ex() {
    new Color(256, 0, 0);
  }

  // red under 0 exception
  @Test(expected = IllegalArgumentException.class)
  public void redUnder0Ex() {
    new Color(-1, 0, 0);
  }

  // green over 255 exception
  @Test(expected = IllegalArgumentException.class)
  public void greenOver255Ex() {
    new Color(0, 256, 0);
  }

  // green under 0 exception
  @Test(expected = IllegalArgumentException.class)
  public void greenUnder0Ex() {
    new Color(0, -1, 0);
  }

  // blue over 255 exception
  @Test(expected = IllegalArgumentException.class)
  public void blueOver255Ex() {
    new Color(0, 0, 256);
  }

  // blue under 0 exception
  @Test(expected = IllegalArgumentException.class)
  public void blueUnder0Ex() {
    new Color(0, 0, -1);
  }

  // testing all over 255 ex
  @Test(expected = IllegalArgumentException.class)
  public void allOver255Ex() {
    new Color(256, 256, 256);
  }

  // testing all under 0 ex
  @Test(expected = IllegalArgumentException.class)
  public void allUnder0Ex() {
    new Color(-1, -1, -1);
  }

  // GENERAL TESTS

  // -----------------------------------------------------------------------------------------------


  /*
        --------------
       | getRed Tests |
        --------------
  */


  // test for getting the red value from the color White
  @Test
  public void getRedWhite() {
    assertEquals(this.white.getRed(), 0);
  }

  // test for getting the red value from the color Black

  @Test
  public void getRedBlack() {
    assertEquals(this.black.getRed(), 255);
  }

  // test for getting the red value from the color Red
  @Test
  public void getRedRed() {
    assertEquals(this.red.getRed(), 255);
  }

  // test for getting the red value from the color Green
  @Test
  public void getRedGreen() {
    assertEquals(this.green.getRed(), 0);
  }

  // test for getting the red value from the color Blue
  @Test
  public void getRedBlue() {
    assertEquals(this.blue.getRed(), 0);
  }

  // -----------------------------------------------------------------------------------------------


  /*
        --------------
       | getGreen Tests |
        --------------
  */


  // test for getting the green value from the color White
  @Test
  public void getGreenWhite() {
    assertEquals(this.white.getGreen(), 0);
  }

  // test for getting the green value from the color Black
  @Test
  public void getGreenBlack() {
    assertEquals(this.black.getGreen(), 255);
  }

  // test for getting the green value from the color Red
  @Test
  public void getGreenRed() {
    assertEquals(this.red.getGreen(), 0);
  }

  // test for getting the green value from the color Green
  @Test
  public void getGreenGreen() {
    assertEquals(this.green.getGreen(), 255);
  }

  // test for getting the green value from the color Blue
  @Test
  public void getGreenBlue() {
    assertEquals(this.blue.getGreen(), 0);
  }

  // -----------------------------------------------------------------------------------------------


  /*
        --------------
       | getBlue Tests |
        --------------
  */


  // test for getting the blue value from the color White
  @Test
  public void getBlueWhite() {
    assertEquals(this.white.getBlue(), 0);
  }

  // test for getting the blue value from the color Black
  @Test
  public void getBlueBlack() {
    assertEquals(this.black.getBlue(), 255);
  }

  // test for getting the blue value from the color Red
  @Test
  public void getBlueRed() {
    assertEquals(this.red.getBlue(), 0);
  }

  // test for getting the blue value from the color Green
  @Test
  public void getBlueGreen() {
    assertEquals(this.green.getBlue(), 0);
  }

  // test for getting the blue value from the color Blue
  @Test
  public void getBlueBlue() {
    assertEquals(this.blue.getBlue(), 255);
  }

  // -----------------------------------------------------------------------------------------------


  /*
        --------------
       | equals Tests |
        --------------
  */


  // testing equals with color White
  @Test
  public void testEqualsWhite() {
    assertTrue(this.white.equals(new Color(0, 0, 0)));
  }

  // testing equals with color White
  @Test
  public void testEqualsBlack() {
    assertTrue(this.black.equals(new Color(255, 255, 255)));
  }

  // testing equals with color White
  @Test
  public void testEqualsRed() {
    assertTrue(this.red.equals(new Color(255, 0, 0)));
  }

  // testing equals with color White
  @Test
  public void testEqualsGreen() {
    assertTrue(this.green.equals(new Color(0, 255, 0)));
  }

  // testing equals with color White
  @Test
  public void testEqualsBlue() {
    assertTrue(this.blue.equals(new Color(0, 0, 255)));
  }

  // testing that two colors are not equal
  @Test
  public void testNotEqualWhiteBlack() {
    assertFalse(this.white.equals(this.black));
  }

  // -----------------------------------------------------------------------------------------------


  /*
        ----------------
       | hashCode Tests |
        ----------------
  */


  // testing the hashCode of White
  @Test
  public void testHashCodeWhite() {
    assertEquals(this.white.hashCode(), 29791);
  }

  // testing the hashCode of Black
  @Test
  public void testHashCodeBlack() {
    assertEquals(this.black.hashCode(), 283006);
  }

  // testing the hashCode of Red
  @Test
  public void testHashCodeRed() {
    assertEquals(this.red.hashCode(), 274846);
  }

  // testing the hashCode of Green
  @Test
  public void testHashCodeGreen() {
    assertEquals(this.green.hashCode(), 37696);
  }

  // testing the hashCode of Blue
  @Test
  public void testHashCodeBlue() {
    assertEquals(this.blue.hashCode(), 30046);
  }

  // test to make sure that two different colors have different hashCodes
  @Test
  public void testHashCodesdifferent() {
    assertNotEquals(this.blue.hashCode(), this.white.hashCode());
  }

}