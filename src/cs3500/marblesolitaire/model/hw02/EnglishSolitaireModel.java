package cs3500.marblesolitaire.model.hw02;

//#####################################################################################//

/**
 * to represent an English version of Solitaire Model.
 */
public class EnglishSolitaireModel implements MarbleSolitaireModel {
  protected final int armLength;
  protected final int row;
  protected final int col;
  protected SlotState[][] state;

  /**
   * the default constructor of the game.
   */
  public EnglishSolitaireModel() {
    this.armLength = 3;
    this.row = 3;
    this.col = 3;
    state = new SlotState[7][7];

    for (int j = 0; j < 7; j++) {
      for (int i = 0; i < 7; i++) {
        if (invalidDeter(j, i, armLength)) {
          state[j][i] = SlotState.Invalid;
        } else if (j == 3 && i == 3) {
          state[j][i] = SlotState.Empty;
        } else {
          state[j][i] = SlotState.Marble;
        }
      }
    }
  }

  /**
   * the constructor that users could modify the empty slot.
   *
   * @param sRow the row of the empty slot
   * @param sCol the column of the empty slot
   */
  public EnglishSolitaireModel(int sRow, int sCol) {
    if (invalidDeter(sRow, sCol, 3)) {
      throw new IllegalArgumentException(
              "Invalid empty cell position (" + sRow + ", " + sCol + ")");
    }

    this.armLength = 3;
    this.row = sRow;
    this.col = sCol;
    state = new SlotState[7][7];

    for (int j = 0; j < (armLength + (armLength - 1) * 2); j++) {
      for (int i = 0; i < (armLength + (armLength - 1) * 2); i++) {
        if (invalidDeter(j, i, armLength)) {
          state[j][i] = SlotState.Invalid;
        } else if (j == sRow && i == sCol) {
          state[j][i] = SlotState.Empty;
        } else {
          state[j][i] = SlotState.Marble;
        }
      }
    }
  }

  /**
   * the constructor that allows user to modify the arm length.
   *
   * @param armLength the arm length
   */
  public EnglishSolitaireModel(int armLength) {
    if (armLength <= 1 || armLength % 2 == 0) {
      throw new IllegalArgumentException("Invalid Arm Length");
    }

    this.armLength = armLength;
    this.row = (armLength - 1) + (armLength / 2);
    this.col = (armLength - 1) + (armLength / 2);
    state = new SlotState[(armLength + (armLength - 1) * 2)][(armLength + (armLength - 1) * 2)];

    for (int j = 0; j < (armLength + (armLength - 1) * 2); j++) {
      for (int i = 0; i < (armLength + (armLength - 1) * 2); i++) {
        if (invalidDeter(j, i, armLength)) {
          state[j][i] = SlotState.Invalid;
        } else if (j == (armLength - 1) + (armLength / 2)
                && i == (armLength - 1) + (armLength / 2)) {
          state[j][i] = SlotState.Empty;
        } else {
          state[j][i] = SlotState.Marble;
        }
      }
    }
  }

  /**
   * a free constructor of the game.
   *
   * @param armLength the arm length
   * @param row       the row of the empty slot
   * @param col       the column of the empty slot
   */
  public EnglishSolitaireModel(int armLength, int row, int col) {
    if (armLength < 0 || armLength % 2 == 0) {
      throw new IllegalArgumentException("Invalid Arm Length");
    } else if (invalidDeter(row, col, armLength)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + row + ", " + col + ")");
    }

    this.armLength = armLength;
    this.row = row;
    this.col = col;
    state = new SlotState[(armLength + (armLength - 1) * 2)][(armLength + (armLength - 1) * 2)];

    for (int j = 0; j < (armLength + (armLength - 1) * 2); j++) {
      for (int i = 0; i < (armLength + (armLength - 1) * 2); i++) {
        if (invalidDeter(j, i, armLength)) {
          state[j][i] = SlotState.Invalid;
        } else if (j == row && i == col) {
          state[j][i] = SlotState.Empty;
        } else {
          state[j][i] = SlotState.Marble;
        }
      }
    }
  }

  //#####################################################################################//

  /**
   * Moves the marble to a valid position.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException any invalid move is not allow
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (outOfBoardCheck(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("The position is out of board");
    } else if ((Math.abs(fromRow - toRow) != 2 && fromCol - toCol == 0)
            || (Math.abs(fromCol - toCol) != 2 && fromRow - toRow == 0)) {
      throw new IllegalArgumentException("Invalid move");

    } else if (state[toRow][toCol] == SlotState.Marble) {
      throw new IllegalArgumentException("The destined slot has a marble");

    } else if (state[toRow][toCol] == SlotState.Invalid) {
      throw new IllegalArgumentException("Cannot move to an invalid position");

    } else if (this.outOfBoardCheck(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("The destined slot is out of board");

    } else if (state[fromRow][fromCol] == SlotState.Empty) {
      throw new IllegalArgumentException("There isn't a marble at the from position");

    } else if (state[(fromRow + toRow) / 2][(fromCol + toCol) / 2] != SlotState.Marble) {
      throw new IllegalArgumentException("A marble is needed between the from position "
              + "and the to position");

    } else if ((fromRow != toRow) && (fromCol != toCol)) {
      throw new IllegalArgumentException("Invalid move");
    }

    state[toRow][toCol] = SlotState.Marble;
    state[fromRow][fromCol] = SlotState.Empty;
    state[(fromRow + toRow) / 2][(fromCol + toCol) / 2] = SlotState.Empty;
  }

  //********************************************//

  @Override
  public boolean isGameOver() {
    boolean flag = true;

    for (int i = 0; i < this.state.length - 1; i++) {
      for (int j = 0; j < this.state[0].length - 1; j++) {
        if (state[i][j] == SlotState.Marble) {
          if (i == this.state.length - 2 || j == this.state.length - 2) {
            if ((state[i + 1][j] == SlotState.Marble)
                    || (state[i][j + 1] == SlotState.Marble)) {
              flag = false;
            }
          } else {
            if ((state[i + 1][j] == SlotState.Marble && state[i + 2][j] == SlotState.Empty)
                    || (state[i][j + 1] == SlotState.Marble
                    && state[i][j + 2] == SlotState.Empty)) {
              flag = false;
            }
          }
        }
      }
    }

    for (int i = 1; i < this.state.length; i++) {
      for (int j = 1; j < this.state.length; j++) {
        if (state[i][j] == SlotState.Marble) {
          if (i == 1 || j == 1) {
            if ((state[i - 1][j] == SlotState.Marble)
                    || (state[i][j - 1] == SlotState.Marble)) {
              flag = false;
            }
          } else {
            if ((state[i - 1][j] == SlotState.Marble && state[i - 2][j] == SlotState.Empty)
                    || (state[i][j - 1] == SlotState.Marble
                    && state[i][j - 2] == SlotState.Empty)) {
              flag = false;
            }
          }
        }
      }
    }

    if (!lastRowGameOverRight() || !lastRowGameOverLeft()) {
      flag = false;
    }

    return flag;
  }

  //********************************************//

  @Override
  public int getBoardSize() {
    return armLength + (armLength - 1) * 2;
  }

  //********************************************//

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row > this.getBoardSize() - 1 || col > this.getBoardSize() - 1) {
      throw new IllegalArgumentException("Invalid slot");
    }

    return state[row][col];
  }

  //********************************************//

  @Override
  public int getScore() {
    int score = 0;

    for (int row = 0; row < this.getBoardSize(); row++) {
      for (int col = 0; col < this.getBoardSize(); col++) {
        if (this.getSlotAt(row, col) == SlotState.Marble) {
          score = score + 1;
        }
      }
    }
    return score;
  }

  //********************************************//

  protected boolean invalidDeter(int j, int i, int armLength) {
    return (j < (armLength - 1) && i < (armLength - 1))
            || (j > ((armLength + (armLength - 1) * 2) - armLength)
            && i > ((armLength + (armLength - 1) * 2) - armLength))
            || (j < (armLength - 1) && i > ((armLength + (armLength - 1) * 2) - armLength))
            || (j > ((armLength + (armLength - 1) * 2) - armLength) && i < (armLength - 1));
  }

  //********************************************//

  /**
   * to tell whether the empty slot is out of board.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @return boolean value of the result.
   */
  protected boolean outOfBoardCheck(int fromRow, int fromCol, int toRow, int toCol) {
    return (fromRow < 0 || fromRow > this.getBoardSize() - 1
            || toRow < 0 || toRow > this.getBoardSize() - 1
            || fromCol < 0 || fromCol > this.getBoardSize() - 1
            || toCol < 0 || toCol > this.getBoardSize() - 1);
  }

  //********************************************//

  protected boolean lastRowGameOverLeft() {
    int lastRow = this.getBoardSize() - 1;
    boolean flag = true;

    // the for loop at the last row to determine left
    for (int j = 2; j < lastRow + 1; j++) {
      if (state[lastRow][j] == SlotState.Marble) {
        if (state[lastRow][j - 1] == SlotState.Marble && state[lastRow][j - 2] == SlotState.Empty) {
          flag = false;
        }
      }
    }
    return flag;
  }

  protected boolean lastRowGameOverRight() {
    int lastRow = this.getBoardSize() - 1;
    boolean flag = true;

    // the for loop at the last row to determine right
    for (int j = 0; j < lastRow - 1; j++) {
      if (state[lastRow][j] == SlotState.Marble) {
        if (state[lastRow][j + 1] == SlotState.Marble && state[lastRow][j + 2] == SlotState.Empty) {
          flag = false;
        }
      }
    }
    return flag;
  }
}


