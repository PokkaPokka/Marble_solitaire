package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Represents a triangle version solitaire model.
 */
public class TriangleSolitaireModel extends EnglishSolitaireModel implements MarbleSolitaireModel {
  private final int row;
  private final int col;
  private final int size;

  /**
   * The default constructor of the class.
   */
  public TriangleSolitaireModel() {
    this.row = 0;
    this.col = 0;
    this.size = 5;
    this.state = new SlotState[5][5];

    assignBoard(0, 0, 5);
  }

  //********************************************//

  /**
   * A constructor that its size can be customized.
   *
   * @param size size of the board
   */
  public TriangleSolitaireModel(int size) {
    if (size < 1) {
      throw new IllegalArgumentException("Board dimension must be positive");
    }

    this.row = 0;
    this.col = 0;
    this.size = size;
    this.state = new SlotState[size][size];

    assignBoard(0, 0, size);
  }

  //********************************************//

  /**
   * A constructor that its empty cell position can be customized.
   *
   * @param row row of the empty cell
   * @param col col of the empty cell
   */
  public TriangleSolitaireModel(int row, int col) {
    if (col > row || row > 4 || col < 0 || row < 0) {
      throw new IllegalArgumentException("Invalid empty cell (" + row + ", " + col + ")");
    }

    this.row = row;
    this.col = col;
    this.size = 5;
    this.state = new SlotState[5][5];

    assignBoard(row, col, 5);
  }

  //********************************************//

  /**
   * A fully-customizable constructor.
   *
   * @param row  row of the empty cell
   * @param col  col of the empty cell
   * @param size size of the board
   */
  public TriangleSolitaireModel(int size, int row, int col) {
    if (col > row || row > size - 1 || col < 0 || row < 0) {
      throw new IllegalArgumentException("Invalid empty cell (" + row + ", " + col + ")");
    } else if (size < 1) {
      throw new IllegalArgumentException("Board dimension must be positive");
    }

    this.row = row;
    this.col = col;
    this.size = size;
    this.state = new SlotState[size][size];

    assignBoard(row, col, size);
  }

  //#####################################################################################//

  /**
   * Assigns marble, empty, and invalid position to the board.
   *
   * @param row  y empty position
   * @param col  x empty position
   * @param size size of the board
   */
  private void assignBoard(int row, int col, int size) {
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (j > i) {
          state[i][j] = SlotState.Invalid;
        } else if (i == row && j == col) {
          state[i][j] = SlotState.Empty;
        } else {
          state[i][j] = SlotState.Marble;
        }
      }
    }
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (outOfBoardCheck(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("The position is out of board");

    } else if (state[fromRow][fromCol] == SlotState.Empty) {
      throw new IllegalArgumentException("There isn't a marble at the from position");

    } else if (!((fromRow == toRow && Math.abs(fromCol - toCol) == 2)
            || (Math.abs(fromRow - toRow) == 2 && fromCol == toCol)
            || (Math.abs(fromRow - toRow) == 2 && Math.abs(toCol - fromCol) == 2))) {
      throw new IllegalArgumentException("Invalid move");

    } else if (state[toRow][toCol] == SlotState.Marble) {
      throw new IllegalArgumentException("The destined slot has a marble");

    } else if (state[toRow][toCol] == SlotState.Invalid) {
      throw new IllegalArgumentException("Cannot move to an invalid position");

    } else if (state[(fromRow + toRow) / 2][(fromCol + toCol) / 2] != SlotState.Marble) {
      throw new IllegalArgumentException("A marble is needed between the from position "
              + "and the to position");
    }

    state[toRow][toCol] = SlotState.Marble;
    state[fromRow][fromCol] = SlotState.Empty;
    state[(fromRow + toRow) / 2][(fromCol + toCol) / 2] = SlotState.Empty;
  }

  @Override
  public boolean isGameOver() {
    boolean flag = true;

    // the for loop to determine right, up-right, and down-right
    for (int i = 1; i < this.size - 1; i++) {
      for (int j = 0; j < this.size - 1; j++) {
        // if the position is a marble, we determine the surrounding states
        if (state[i][j] == SlotState.Marble) {
          if (i == this.size - 2 || j == this.size - 2) {
            if (state[i + 1][j] == SlotState.Marble
                    || state[i + 1][j + 1] == SlotState.Marble) {
              flag = false;
            }
          } else {
            if ((state[i][j + 1] == SlotState.Marble && state[i][j + 2] == SlotState.Empty)
                    || (state[i + 1][j + 1] == SlotState.Marble
                    && state[i + 2][j + 2] == SlotState.Empty)
                    || (state[i - 1][j + 1] == SlotState.Marble
                    && state[i - 2][j + 2] == SlotState.Empty)) {
              flag = false;
            }
          }
        }
      }
    }

    // the for loop to determine left, up-left, and down-left
    for (int i = 1; i < this.size - 2; i++) {
      for (int j = 1; j < this.size; j++) {
        // if the position is a marble, we determine the surrounding states
        if (state[i][j] == SlotState.Marble) {
          if (i == 1 || j == 1) {
            if (state[i - 1][j] == SlotState.Marble
                    || state[i - 1][j - 1] == SlotState.Marble
                    || state[i - 1][j + 1] == SlotState.Marble) {
              flag = false;
            }
          } else if ((state[i][j - 1] == SlotState.Marble && state[i][j - 2] == SlotState.Empty)
                  || (state[i - 1][j - 1] == SlotState.Marble
                  && state[i - 2][j - 2] == SlotState.Empty)
                  || (state[i + 1][j - 1] == SlotState.Marble
                  && state[i + 2][j - 2] == SlotState.Empty)) {
            flag = false;
          }
        }
      }
    }

    if (!lastRowGameOverRight() || !lastRowGameOverLeft()) {
      flag = false;
    }

    return flag;
  }

  @Override
  public int getBoardSize() {
    return this.size;
  }
}
