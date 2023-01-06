import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.controller.ConfirmInputs;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;

/**
 * a test for MarbleSolitaireTextView.
 */
public class MarbleSolitaireTextViewTest {
  EnglishSolitaireModel board;
  MarbleSolitaireView view;
  EnglishSolitaireModel customArmLengthBoard;
  MarbleSolitaireView view2;
  MarbleSolitaireView mockView;

  private void init() {
    board = new EnglishSolitaireModel();
    view = new MarbleSolitaireTextView(board);
    customArmLengthBoard = new EnglishSolitaireModel(0, 2);
    view2 = new MarbleSolitaireTextView(customArmLengthBoard);
  }

  @Test
  public void testMarbleSolitaireTextView() {
    try {
      view = new MarbleSolitaireTextView(null);
      fail("Exception is not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("The provided model cannot be null", e.getMessage());
    }
  }

  @Test
  public void testToString() {
    init();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", view.toString());

    assertEquals("    _ O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", view2.toString());

    init();
    board.move(1, 3, 3, 3);
    assertEquals("    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", view.toString());
  }

  @Test
  public void testRenderMessage() throws IOException {
    StringBuilder log = new StringBuilder();
    ConfirmInputs confirm = new ConfirmInputs(log);
    MarbleSolitaireTextView mockView = new MarbleSolitaireTextView(confirm);
    mockView.renderMessage("what");

    assertEquals("what", log.toString());
  }

  @Test
  public void testRenderBoard() throws IOException {
    StringBuilder log = new StringBuilder();
    ConfirmInputs confirm = new ConfirmInputs(log);
    MarbleSolitaireTextView mockView = new MarbleSolitaireTextView(confirm);

    try {
      mockView.renderBoard();
      fail("Exception is not thrown");
    } catch (StringIndexOutOfBoundsException e) {
      assertEquals("begin 0, end -1, length 0", e.getMessage());
    }

  }
}
