package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

//#####################################################################################//

/**
 * to visualize the marble solitaire game.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  private final MarbleSolitaireModelState printBoard;
  protected Appendable des;

  /**
   * a constructor for MarbleSolitaireTextView to take in the Marble Solitaire.
   *
   * @param printBoard the Marble Solitaire we need to visualize
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState printBoard) {
    if (printBoard == null) {
      throw new IllegalArgumentException("The provided model cannot be null");
    }

    this.printBoard = printBoard;
    this.des = System.out;
  }

  /**
   * The constructor for the view class that accept the state and appendable.
   *
   * @param printBoard the state of the game
   * @param des        output
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState printBoard, Appendable des) {
    if (des == null) {
      throw new IllegalArgumentException("Appendable is null");
    } else if (printBoard == null) {
      throw new IllegalArgumentException("MarbleSolitaireModelState is null");
    }

    this.printBoard = printBoard;
    this.des = des;
  }

  //#####################################################################################//

  @Override
  public String toString() {

    String board = "";
    int arraySize = printBoard.getBoardSize();
    int arm = (arraySize + 2) / 3;

    for (int row = 0; row < arraySize; row++) {
      for (int col = 0; col < arraySize; col++) {
        if (printBoard.getSlotAt(row, col).equals(MarbleSolitaireModelState.SlotState.Invalid)) {
          if ((row > arm + 1 && (col > arm + 1))
                  || (row < arm - 1 && (col > arm + 1))) {
            continue;
          }
          board = board.concat("  ");
        } else if (printBoard.getSlotAt(row, col)
                .equals(MarbleSolitaireModelState.SlotState.Marble)) {
          board = board.concat("O ");
        } else if (printBoard.getSlotAt(row, col)
                .equals(MarbleSolitaireModelState.SlotState.Empty)) {
          board = board.concat("_ ");
        }
      }
      board = board.substring(0, board.length() - 1);
      board = board.concat("\n");
    }
    board = board.substring(0, board.length() - 1);
    return board;
  }

  @Override
  public void renderBoard() throws IOException {
    try {
      this.des.append(this.toString());
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.des.append(message);
    } catch (IOException e) {
      throw new IOException();
    }
  }
}

