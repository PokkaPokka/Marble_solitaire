package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * To visualize the triangle marble solitaire game.
 */
public class TriangleSolitaireTextView extends MarbleSolitaireTextView
        implements MarbleSolitaireView {
  private final MarbleSolitaireModelState printBoard;

  /**
   * a constructor for MarbleSolitaireTextView to take in the Marble Solitaire.
   *
   * @param printBoard the Marble Solitaire we need to visualize
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState printBoard) {
    super(printBoard);

    this.printBoard = printBoard;
    this.des = System.out;
  }

  /**
   * The constructor for the view class that accept the state and appendable.
   *
   * @param printBoard the state of the game
   * @param des        output
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState printBoard, Appendable des) {
    super(printBoard, des);

    this.printBoard = printBoard;
    this.des = des;
  }

  //#####################################################################################//

  @Override
  public String toString() {

    String board = "";
    int arraySize = printBoard.getBoardSize();

    for (int row = 0; row < arraySize; row++) {
      for (int col = 0; col < arraySize; col++) {
        if (printBoard.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Marble) {
          if (col == 0) {
            for (int space = 0; space < arraySize - row - 1; space++) {
              board = board.concat(" ");
            }
          }
          board = board.concat("O ");
        } else if (printBoard.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Empty) {
          if (col == 0) {
            for (int space = 0; space < arraySize - row - 1; space++) {
              board = board.concat(" ");
            }
          }
          board = board.concat("_ ");
        }
      }
      board = board.substring(0, board.length() - 1);
      board = board.concat("\n");
    }
    board = board.substring(0, board.length() - 1);
    return board;
  }
}
