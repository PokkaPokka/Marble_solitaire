import org.junit.Test;

import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * a test for TriangleSolitaireModel constructor.
 */
public class TriangleSolitaireModelTest {
  TriangleSolitaireModel board;

  private void init() {
    board = new TriangleSolitaireModel();
  }

  //#####################################################################################//

  @Test
  public void testInvalidTriangleConstructor() {
    // non-positive board dimension
    try {
      board = new TriangleSolitaireModel(-3);
      fail("Not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Board dimension must be positive", e.getMessage());
    }

    // empty position at an invalid position
    try {
      board = new TriangleSolitaireModel(2, 3);
      fail("Not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell (2, 3)", e.getMessage());
    }

    // non-positive empty position
    try {
      board = new TriangleSolitaireModel(-1, 3);
      fail("Not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell (-1, 3)", e.getMessage());
    }

    // 3 cases above at a fully-custom constructor
    // non-positive board dimension
    try {
      board = new TriangleSolitaireModel(-3, 2, 2);
      fail("Not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell (2, 2)", e.getMessage());
    }

    // empty position at an invalid position
    try {
      board = new TriangleSolitaireModel(5, 2, 3);
      fail("Not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell (2, 3)", e.getMessage());
    }

    // non-positive empty position
    try {
      board = new TriangleSolitaireModel(5, -1, 3);
      fail("Not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell (-1, 3)", e.getMessage());
    }
  }

  //********************************************//

  @Test
  public void testInvalidMove() {
    //out of board
    try {
      init();
      board.move(2, 2, 3, 5);
      fail("Not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("The position is out of board", e.getMessage());
    }

    try {
      init();
      board.move(2, 3, -3, 5);
      fail("Not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("The position is out of board", e.getMessage());
    }

    // invalid move
    try {
      init();
      board.move(2, 2, 3, 1);
      fail("Not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid move", e.getMessage());
    }

    try {
      init();
      board.move(2, 2, 3, 3);
      fail("Not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid move", e.getMessage());
    }

    try {
      init();
      board.move(2, 1, 0, 0);
      fail("Not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid move", e.getMessage());
    }

    // The destined position has a marble
    try {
      init();
      board.move(2, 2, 4, 4);
      fail("Not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("The destined slot has a marble", e.getMessage());
    }

    try {
      init();
      board.move(3, 0, 3, 2);
      fail("Not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("The destined slot has a marble", e.getMessage());
    }

    // Move to an invalid position
    try {
      init();
      board.move(3, 2, 3, 4);
      fail("Not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot move to an invalid position", e.getMessage());
    }

    // No marble at the from position
    try {
      init();
      board.move(0, 0, 2, 2);
      fail("Not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("There isn't a marble at the from position", e.getMessage());
    }

    // No marble in between
    try {
      init();
      board.move(2, 2, 0, 0);
      board.move(3, 3, 1, 1);
      fail("Not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("A marble is needed between the from position and the to position",
              e.getMessage());
    }
  }

  @Test
  public void testIsGameOver() {
    init();
    board.move(2, 0, 0, 0);
    assertEquals(false, board.isGameOver());
    board.move(2, 2, 2, 0);
    assertEquals(false, board.isGameOver());
    board.move(0, 0, 2, 2);
    assertEquals(false, board.isGameOver());
    board.move(3, 0, 1, 0);
    assertEquals(false, board.isGameOver());
    board.move(4, 2, 2, 0);
    assertEquals(false, board.isGameOver());
    board.move(3, 3, 3, 1);
    assertEquals(false, board.isGameOver());
    board.move(1, 0, 3, 0);
    assertEquals(false, board.isGameOver());
    board.move(3, 0, 3, 2);
    assertEquals(false, board.isGameOver());
    board.move(4, 4, 4, 2);
    assertEquals(false, board.isGameOver());
    board.move(4, 1, 4, 3);
    assertEquals(false, board.isGameOver());
    board.move(2, 2, 4, 2);
    assertEquals(false, board.isGameOver());
    board.move(4, 3, 4, 1);
    assertEquals(false, board.isGameOver());
    board.move(4, 0, 4, 2);
    assertEquals(true, board.isGameOver());
  }
}