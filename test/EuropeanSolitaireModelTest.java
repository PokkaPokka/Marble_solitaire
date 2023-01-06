import org.junit.Assert;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test cases for European Solitaire Marble.
 */
public class EuropeanSolitaireModelTest {
  EuropeanSolitaireModel board;

  private void init() {
    board = new EuropeanSolitaireModel();
  }

  @Test
  public void testValidConstructor() {
    // default
    init();
    Assert.assertEquals(7, board.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, board.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, board.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, board.getSlotAt(0, 1));

    // custom armLength
    MarbleSolitaireModel armLengthBoard = new EuropeanSolitaireModel(5);
    assertEquals(13, armLengthBoard.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, armLengthBoard.getSlotAt(6, 6));

    // custom empty cell position
    MarbleSolitaireModel emptyBoard = new EuropeanSolitaireModel(4, 4);
    assertEquals(7, emptyBoard.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, emptyBoard.getSlotAt(4, 4));

    // custom all
    MarbleSolitaireModel allBoard = new EuropeanSolitaireModel(5, 4, 3);
    assertEquals(13, allBoard.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, allBoard.getSlotAt(4, 3));
  }

  @Test
  public void testInvalidConstructor() {
    // custom armLength
    try {
      MarbleSolitaireModel armLengthBoard = new EuropeanSolitaireModel(4);
      fail("Not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid Arm Length", e.getMessage());
    }

    // custom empty cell position
    try {
      MarbleSolitaireModel emptyBoard = new EuropeanSolitaireModel(8, 0);
      fail("Not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (8, 0)", e.getMessage());
    }

    // custom empty cell position
    try {
      MarbleSolitaireModel emptyBoard = new EuropeanSolitaireModel(0, 0);
      fail("Not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0, 0)", e.getMessage());
    }

    // custom all
    try {
      MarbleSolitaireModel allBoard = new EuropeanSolitaireModel(4, 4, 4);
      fail("Not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid Arm Length", e.getMessage());
    }

    // custom all
    try {
      MarbleSolitaireModel allBoard = new EuropeanSolitaireModel(5, 0, 0);
      fail("Not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0, 0)", e.getMessage());
    }

    // custom all
    try {
      MarbleSolitaireModel allBoard = new EuropeanSolitaireModel(3, 5, 0);
      fail("Not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (5, 0)", e.getMessage());
    }
  }

  @Test
  public void testInvalidMove() {
    // test the condition that the from and to positions are invalid
    try {
      EuropeanSolitaireModel board = new EuropeanSolitaireModel();
      board.move(2, 2, 6, 2);
      fail("Exception is not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid move", e.getMessage());
    }

    try {
      EuropeanSolitaireModel board = new EuropeanSolitaireModel();
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
      EuropeanSolitaireModel board = new EuropeanSolitaireModel();
      board.move(3, 1, 3, 3);
      board.move(3, 3, 3, 1);
      fail("Exception is not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("A marble is needed between the from position " +
              "and the to position", e.getMessage());
    }

    // test the condition that there is a diagonal move
    try {
      EuropeanSolitaireModel board = new EuropeanSolitaireModel();
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
  }

  //#####################################################################################//

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

  //#####################################################################################//

  @Test
  public void testGetScore() {
    init();
    Assert.assertEquals(36, board.getScore());
    board.move(3, 1, 3, 3);
    Assert.assertEquals(35, board.getScore());
    board.move(3, 4, 3, 2);
    Assert.assertEquals(34, board.getScore());
  }

  //#####################################################################################//

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
    Assert.assertEquals(false, board.isGameOver());
    board.move(2, 2, 4, 2);
    board.move(0, 2, 2, 2);
    Assert.assertEquals(false, board.isGameOver());
    board.move(1, 4, 3, 4);
    board.move(3, 4, 5, 4);
    board.move(5, 4, 5, 2);
    board.move(5, 2, 3, 2);
    board.move(3, 2, 1, 2);
    Assert.assertEquals(false, board.isGameOver());
    board.move(2, 0, 4, 0);
    board.move(4, 0, 4, 2);
    board.move(4, 2, 4, 4);
    Assert.assertEquals(false, board.isGameOver());
    board.move(2, 6, 2, 4);
    board.move(2, 3, 2, 5);
    board.move(4, 6, 2, 6);
    board.move(2, 6, 2, 4);
    board.move(0, 4, 0, 2);
    board.move(0, 2, 2, 2);
    assertEquals(board.getSlotAt(2, 1), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(board.getSlotAt(2, 2), MarbleSolitaireModelState.SlotState.Marble);
    Assert.assertEquals(false, board.isGameOver());
    board.move(2, 1, 2, 3);
    board.move(2, 3, 2, 5);
    board.move(2, 5, 4, 5);
    board.move(4, 5, 4, 3);
    board.move(4, 3, 2, 3);
    Assert.assertEquals(false, board.isGameOver());
    board.move(1, 3, 3, 3);
    Assert.assertEquals(true, board.isGameOver());
  }

  @Test
  public void testGetBoardSize() {
    init();
    Assert.assertEquals(7, board.getBoardSize());
  }
}