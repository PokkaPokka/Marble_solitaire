import org.junit.Test;

import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class for MarbleSolitaireControllerImpl.
 */
public class MarbleSolitaireControllerImplTest {
  MarbleSolitaireModel model;
  MarbleSolitaireView view;
  Readable rd;
  MarbleSolitaireController controller;
  Appendable app;

  /**
   * Initialize the game for later tests.
   */
  private void init() {
    model = new EnglishSolitaireModel();
    view = new MarbleSolitaireTextView(model);
    rd = new StringReader("2 4 4 4 q");
    controller = new MarbleSolitaireControllerImpl(model, view, rd);
  }

  @Test
  public void testMarbleSolitaireControllerImplConstructor() {
    try {
      init();
      MarbleSolitaireController conFail = new MarbleSolitaireControllerImpl(null, view, rd);
      fail("Exception is not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Model cannot be null", e.getMessage());
    }

    try {
      init();
      MarbleSolitaireController conFail = new MarbleSolitaireControllerImpl(model, null, rd);
      fail("Exception is not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("View cannot be null", e.getMessage());
    }

    try {
      init();
      MarbleSolitaireController conFail =
              new MarbleSolitaireControllerImpl(model, view, null);
      fail("Exception is not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Input cannot be null", e.getMessage());
    }
  }

  @Test
  public void testPlayGameValid() throws IllegalStateException {
    // valid move once
    init();
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", view.toString());

    // valid move twice
    model = new EnglishSolitaireModel();
    view = new MarbleSolitaireTextView(model);
    rd = new StringReader("4 2 4 4 4 5 4 3 q");
    controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O _ _ O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", view.toString());

    // game completely over
    model = new EnglishSolitaireModel();
    view = new MarbleSolitaireTextView(model);
    rd = new StringReader("4 2 4 4 6 3 4 3 5 1 5 3 5 4 5 2 5 6 5 4 7 5 5 " +
            "5 4 5 6 5 7 3 7 5 " +
            "7 5 5 5 4 5 6 5 7 3 7 5 7 5 5 5 3 3 5 3 1 3 3 3 2 5 4 5 4 5 6 5 6 " +
            "5 6 3 6 3 4 3 4 3 2 3 3 1 5 1 5 1 5 3 5 3 5 5 3 7 " +
            "3 5 3 4 3 6 5 7 3 7 3 7 3 5 1 5 1 3 1 3 " +
            " 3 3 3 2 3 4 3 4 3 6 3 6 5 6 5 6 5 4 5 4 3 4 2 4 4 4 q");
    controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _", view.toString());

    // quit straight ahead
    model = new EnglishSolitaireModel();
    view = new MarbleSolitaireTextView(model);
    rd = new StringReader("q");
    controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", view.toString());
  }

  @Test
  public void testPlayGameInvalid() {
    // invalid move once
    model = new EnglishSolitaireModel();
    view = new MarbleSolitaireTextView(model);
    rd = new StringReader("4 3 4 4 q");
    controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", view.toString());

    // negative invalid move once
    model = new EnglishSolitaireModel();
    view = new MarbleSolitaireTextView(model);
    rd = new StringReader("-3 2 2 4 4 3 4 4 q");
    controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", view.toString());

    // false amount of input
    model = new EnglishSolitaireModel();
    view = new MarbleSolitaireTextView(model);
    rd = new StringReader("-3 2 3 2 4 4 3 4 4 q");
    controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", view.toString());

    // letter input
    model = new EnglishSolitaireModel();
    view = new MarbleSolitaireTextView(model);
    rd = new StringReader("l 2 3 2 4 f 3 4 4 q");
    controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", view.toString());

    // one valid one invalid
    model = new EnglishSolitaireModel();
    view = new MarbleSolitaireTextView(model);
    rd = new StringReader("4 2 4 4 2 3 4 1 q");
    controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", view.toString());

    // one invalid one valid
    model = new EnglishSolitaireModel();
    view = new MarbleSolitaireTextView(model);
    rd = new StringReader("3 2 1 5 4 2 4 4 q");
    controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", view.toString());

    // only 3 inputs
    model = new EnglishSolitaireModel();
    view = new MarbleSolitaireTextView(model);
    rd = new StringReader("3 2 1 q");
    controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", view.toString());
  }

  @Test
  public void testNoSuchException() {
    try {
      model = new EnglishSolitaireModel();
      view = new MarbleSolitaireTextView(model);
      rd = new StringReader("3 2 1 ");
      controller = new MarbleSolitaireControllerImpl(model, view, rd);
      controller.playGame();
      fail("Exception is not thrown");
    } catch (IllegalStateException e) {
      assertEquals("Runs out of inputs", e.getMessage());
    }
  }

  @Test
  public void testMoveMessage() {
    model = new EnglishSolitaireModel();
    app = new StringBuilder();
    view = new MarbleSolitaireTextView(model, app);
    rd = new StringReader("3 2 1 2 q");
    controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again. Cannot move to an invalid position.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32", app.toString());

    model = new EnglishSolitaireModel();
    app = new StringBuilder();
    view = new MarbleSolitaireTextView(model, app);
    rd = new StringReader("-1 2 3 4 q");
    controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again. The position is out of board.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32", app.toString());

    model = new EnglishSolitaireModel();
    app = new StringBuilder();
    view = new MarbleSolitaireTextView(model, app);
    rd = new StringReader("4 2 4 4 4 4 2 4 q");
    controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Invalid move. Play again. The destined slot has a marble.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31", app.toString());

    model = new EnglishSolitaireModel();
    app = new StringBuilder();
    view = new MarbleSolitaireTextView(model, app);
    rd = new StringReader("4 2 4 4 4 2 4 2 q");
    controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Invalid move. Play again. Invalid move.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31", app.toString());

    model = new EnglishSolitaireModel();
    app = new StringBuilder();
    view = new MarbleSolitaireTextView(model, app);
    rd = new StringReader("4 2 4 4 4 2 99 2 q");
    controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Invalid move. Play again. The position is out of board.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31", app.toString());

    model = new EnglishSolitaireModel();
    app = new StringBuilder();
    view = new MarbleSolitaireTextView(model, app);
    rd = new StringReader("4 2 4 4 4 4 4 2 q");
    controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Invalid move. Play again. A marble is needed between " +
            "the from position and the to position.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31", app.toString());

    model = new EnglishSolitaireModel();
    app = new StringBuilder();
    view = new MarbleSolitaireTextView(model, app);
    rd = new StringReader("3 3 4 4 q");
    controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again. Invalid move.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32", app.toString());
  }
}
