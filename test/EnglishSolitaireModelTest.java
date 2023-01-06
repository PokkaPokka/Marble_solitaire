import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * a test for EnglishSolitaireModel constructor.
 */
public class EnglishSolitaireModelTest {
  EnglishSolitaireModel board;
  EnglishSolitaireModel customSizeBoard;

  private void init() {
    this.board = new EnglishSolitaireModel();
    this.customSizeBoard = new EnglishSolitaireModel(5);
  }

  @Test
  public void testEnglishSolitaireModel() {
    // test the default constructor
    this.board = new EnglishSolitaireModel();
    assertEquals(7, board.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, board.getSlotAt(3, 3));

    // test the second constructor
    try {
      EnglishSolitaireModel s1 = new EnglishSolitaireModel(1, 0);
      fail("Exception is not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (1, 0)", e.getMessage());
    }

    try {
      EnglishSolitaireModel s2 = new EnglishSolitaireModel(7, 9);
      fail("Exception is not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (7, 9)", e.getMessage());
    }

    try {
      EnglishSolitaireModel s3 = new EnglishSolitaireModel(5, 0);
      fail("Exception is not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (5, 0)", e.getMessage());
    }

    try {
      EnglishSolitaireModel s4 = new EnglishSolitaireModel(1, 5);
      fail("Exception is not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (1, 5)", e.getMessage());
    }

    // test the third constructor
    try {
      EnglishSolitaireModel t1 = new EnglishSolitaireModel(4);
      fail("Exception is not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid Arm Length", e.getMessage());
    }

    try {
      EnglishSolitaireModel t2 = new EnglishSolitaireModel(-99);
      fail("Exception is not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid Arm Length", e.getMessage());
    }

    // test the fourth constructor
    try {
      EnglishSolitaireModel f1 = new EnglishSolitaireModel(9, 1, 1);
      fail("Exception is not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (1, 1)", e.getMessage());
    }

    // test the fourth constructor
    try {
      EnglishSolitaireModel f1 = new EnglishSolitaireModel(-9, 1, 1);
      fail("Exception is not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid Arm Length", e.getMessage());
    }
  }

  //********************************************//

  @Test
  public void testMove() {
    // test the condition that the from and to positions are invalid
    try {
      EnglishSolitaireModel board = new EnglishSolitaireModel();
      board.move(2, 2, 6, 2);
      fail("Exception is not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid move", e.getMessage());
    }

    try {
      EnglishSolitaireModel board = new EnglishSolitaireModel();
      board.move(3, 6, 3, 3);
      fail("Exception is not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid move", e.getMessage());
    }

    // test the condition that there isn't a marble at the from position
    try {
      init();
      board.move(3, 1, 3, 3);
      board.move(3, 4, 3, 2);
      board.move(3, 1, 3, 3);
      fail("Exception is not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("There isn't a marble at the from position", e.getMessage());
    }

    // test the condition that the to position has a marble
    try {
      init();
      board.move(3, 0, 3, 2);
      fail("Exception is not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("The destined slot has a marble", e.getMessage());
    }

    // test the condition that there isn't a marble between from and to positions
    try {
      EnglishSolitaireModel board = new EnglishSolitaireModel();
      board.move(3, 1, 3, 3);
      board.move(3, 3, 3, 1);
      fail("Exception is not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("A marble is needed between the from position " +
              "and the to position", e.getMessage());
    }

    // test the condition that there is a diagonal move
    try {
      EnglishSolitaireModel board = new EnglishSolitaireModel();
      board.move(2, 2, 3, 3);
      fail("Exception is not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid move", e.getMessage());
    }

    try {
      init();
      board.move(-2, -2, 3, 3);
      fail(("Exception is not thrown"));
    } catch (IllegalArgumentException e) {
      assertEquals("The position is out of board", e.getMessage());
    }

    try {
      init();
      board.move(0, 3, 0, 5);
      fail(("Exception is not thrown"));
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot move to an invalid position", e.getMessage());
    }

    // test the valid case
    init();
    board.move(3, 1, 3, 3);
    assertEquals(board.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(board.getSlotAt(3, 2), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(board.getSlotAt(3, 1), MarbleSolitaireModelState.SlotState.Empty);

    init();
    board.move(3, 5, 3, 3);
    assertEquals(board.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(board.getSlotAt(3, 4), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(board.getSlotAt(3, 5), MarbleSolitaireModelState.SlotState.Empty);

    init();
    board.move(1, 3, 3, 3);
    assertEquals(board.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(board.getSlotAt(2, 3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(board.getSlotAt(1, 3), MarbleSolitaireModelState.SlotState.Empty);

    init();
    board.move(5, 3, 3, 3);
    assertEquals(board.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(board.getSlotAt(4, 3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(board.getSlotAt(5, 3), MarbleSolitaireModelState.SlotState.Empty);
  }

  //********************************************//

  @Test
  public void testGetSlotAt() {
    init();
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, board.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, board.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, board.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, board.getSlotAt(2, 3));

    try {
      init();
      board.getSlotAt(99, 99);
      fail("Exception is not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid slot", e.getMessage());
    }
  }

  //********************************************//

  @Test
  public void testGetScore() {
    init();
    assertEquals(32, board.getScore());
    board.move(3, 1, 3, 3);
    assertEquals(31, board.getScore());
    board.move(3, 4, 3, 2);
    assertEquals(30, board.getScore());
  }

  //********************************************//

  @Test
  public void testIsGameOver() {
    init();
    board.move(3, 1, 3, 3);
    board.move(5, 2, 3, 2);
    board.move(4, 0, 4, 2);
    board.move(4, 3, 4, 1);
    board.move(4, 5, 4, 3);
    board.move(6, 4, 4, 4);
    board.move(3, 4, 5, 4);
    board.move(6, 2, 6, 4);
    board.move(6, 4, 4, 4);
    assertEquals(false, board.isGameOver());
    board.move(2, 2, 4, 2);
    board.move(0, 2, 2, 2);
    assertEquals(false, board.isGameOver());
    board.move(1, 4, 3, 4);
    board.move(3, 4, 5, 4);
    board.move(5, 4, 5, 2);
    board.move(5, 2, 3, 2);
    board.move(3, 2, 1, 2);
    assertEquals(false, board.isGameOver());
    board.move(2, 0, 4, 0);
    board.move(4, 0, 4, 2);
    board.move(4, 2, 4, 4);
    assertEquals(false, board.isGameOver());
    board.move(2, 6, 2, 4);
    board.move(2, 3, 2, 5);
    board.move(4, 6, 2, 6);
    board.move(2, 6, 2, 4);
    board.move(0, 4, 0, 2);
    board.move(0, 2, 2, 2);
    assertEquals(board.getSlotAt(2, 1), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(board.getSlotAt(2, 2), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(false, board.isGameOver());
    board.move(2, 1, 2, 3);
    board.move(2, 3, 2, 5);
    board.move(2, 5, 4, 5);
    board.move(4, 5, 4, 3);
    board.move(4, 3, 2, 3);
    assertEquals(false, board.isGameOver());
    board.move(1, 3, 3, 3);
    assertEquals(true, board.isGameOver());
  }

  //********************************************//

  @Test
  public void testGetBoardSize() {
    init();
    assertEquals(7, board.getBoardSize());
    assertEquals(13, customSizeBoard.getBoardSize());
  }
}

