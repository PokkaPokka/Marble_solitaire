import org.junit.Test;

import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;

/**
 * Test view for European Solitaire Marble.
 */
public class EuropeanMarbleSolitaireTextViewTest {
  EuropeanSolitaireModel board;
  MarbleSolitaireView view;

  private void init() {
    board = new EuropeanSolitaireModel(3);
    view = new MarbleSolitaireTextView(board);
  }

  @Test
  public void testEuropeanToString() {
    init();
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", view.toString());

    MarbleSolitaireModel board11 = new EuropeanSolitaireModel(1, 1);
    MarbleSolitaireView view11 = new MarbleSolitaireTextView(board11);
    assertEquals("    O O O\n" +
            "  _ O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", view11.toString());

    MarbleSolitaireModel board02 = new EuropeanSolitaireModel(3,0, 2);
    MarbleSolitaireView view02 = new MarbleSolitaireTextView(board02);
    assertEquals("    _ O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", view02.toString());

    MarbleSolitaireModel board55 = new EuropeanSolitaireModel(5, 5);
    MarbleSolitaireView view55 = new MarbleSolitaireTextView(board55);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O _\n" +
            "    O O O", view55.toString());

    MarbleSolitaireModel board5910 = new EuropeanSolitaireModel(5, 9, 10);
    MarbleSolitaireView view5910 = new MarbleSolitaireTextView(board5910);
    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O _ O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", view5910.toString());

    MarbleSolitaireModel board5 = new EuropeanSolitaireModel(5);
    MarbleSolitaireView view5 = new MarbleSolitaireTextView(board5);
    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", view5.toString());

    MarbleSolitaireModel board23 = new EuropeanSolitaireModel(2 , 3);
    MarbleSolitaireView view23 = new MarbleSolitaireTextView(board23);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", view23.toString());

    MarbleSolitaireModel board551 = new EuropeanSolitaireModel(5, 5, 1);
    MarbleSolitaireView view551 = new MarbleSolitaireTextView(board551);
    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O _ O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", view551.toString());
  }

  @Test
  public void testValidMove() {
    init();
    board.move(3, 1, 3, 3);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", view.toString());

    init();
    board.move(1, 3, 3, 3);
    assertEquals("    O O O\n" +
            "  O O _ O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", view.toString());

    init();
    board.move(3, 5, 3, 3);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", view.toString());

    init();
    board.move(5, 3, 3, 3);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "  O O _ O O\n" +
            "    O O O", view.toString());
  }
}