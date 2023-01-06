import org.junit.Test;

import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;

/**
 * Test the view of Triangle Solitaire Marble.
 */
public class TriangleSolitaireTextViewTest {
  TriangleSolitaireModel board;
  TriangleSolitaireTextView view;

  private void init() {
    board = new TriangleSolitaireModel();
    view = new TriangleSolitaireTextView(board);
  }

  @Test
  public void testValidTriangleConstructor() {
    init();
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", view.toString());

    board = new TriangleSolitaireModel(2, 2);
    view = new TriangleSolitaireTextView(board);
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O _\n" +
            " O O O O\n" +
            "O O O O O", view.toString());

    board = new TriangleSolitaireModel(7);
    view = new TriangleSolitaireTextView(board);
    assertEquals("      _\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O O\n" +
            "  O O O O O\n" +
            " O O O O O O\n" +
            "O O O O O O O", view.toString());

    board = new TriangleSolitaireModel(6, 4, 3);
    view = new TriangleSolitaireTextView(board);
    assertEquals("     O\n" +
            "    O O\n" +
            "   O O O\n" +
            "  O O O O\n" +
            " O O O _ O\n" +
            "O O O O O O", view.toString());

    board = new TriangleSolitaireModel(2, 0, 0);
    view = new TriangleSolitaireTextView(board);
    assertEquals(" _\n" +
            "O O", view.toString());
  }

  @Test
  public void testValidMove() {
    // up-left
    board = new TriangleSolitaireModel(7, 2, 0);
    view = new TriangleSolitaireTextView(board);
    board.move(4, 2, 2, 0);
    assertEquals("      O\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O _ O O\n" +
            "  O O _ O O\n" +
            " O O O O O O\n" +
            "O O O O O O O", view.toString());

    // up-right
    board = new TriangleSolitaireModel(7, 2, 2);
    view = new TriangleSolitaireTextView(board);
    board.move(4, 2, 2, 2);
    assertEquals("      O\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O _ O\n" +
            "  O O _ O O\n" +
            " O O O O O O\n" +
            "O O O O O O O", view.toString());

    // left
    board = new TriangleSolitaireModel(7, 4, 0);
    view = new TriangleSolitaireTextView(board);
    board.move(4, 2, 4, 0);
    assertEquals("      O\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O O\n" +
            "  O _ _ O O\n" +
            " O O O O O O\n" +
            "O O O O O O O", view.toString());

    // right
    board = new TriangleSolitaireModel(7, 4, 4);
    view = new TriangleSolitaireTextView(board);
    board.move(4, 2, 4, 4);
    assertEquals("      O\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O O\n" +
            "  O O _ _ O\n" +
            " O O O O O O\n" +
            "O O O O O O O", view.toString());

    // down-left
    board = new TriangleSolitaireModel(7, 6, 2);
    view = new TriangleSolitaireTextView(board);
    board.move(4, 2, 6, 2);
    assertEquals("      O\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O O\n" +
            "  O O _ O O\n" +
            " O O _ O O O\n" +
            "O O O O O O O", view.toString());

    // down-right
    board = new TriangleSolitaireModel(7, 6, 5);
    view = new TriangleSolitaireTextView(board);
    board.move(4, 2, 6, 5);
    assertEquals("      O\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O O\n" +
            "  O O _ O O\n" +
            " O O O _ O O\n" +
            "O O O O O O O", view.toString());
  }
}