package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * It represents a European version solitaire marble.
 */
public class EuropeanSolitaireModel extends EnglishSolitaireModel implements MarbleSolitaireModel {

  /**
   * The default constructor for the class.
   */
  public EuropeanSolitaireModel() {
    super();

    assignMarble(armLength, row, col);
  }

  //********************************************//

  /**
   * The constructor that its arm length could be customized.
   *
   * @param armLength arm length
   */
  public EuropeanSolitaireModel(int armLength) {
    super(armLength);

    assignMarble(armLength, row, col);
  }

  //********************************************//

  /**
   * The constructor that its empty cell could be customized.
   *
   * @param row row of the empty cell
   * @param col column of the empty cell
   */
  public EuropeanSolitaireModel(int row, int col) {
    super(row, col);

    assignMarble(armLength, row, col);
  }

  //********************************************//

  /**
   * The constructor that its arm length and empty cell could be customized.
   *
   * @param armLength arm length
   * @param row       row of the empty cell
   * @param col       column of the empty cell
   */
  public EuropeanSolitaireModel(int armLength, int row, int col) {
    super(armLength, row, col);

    assignMarble(armLength, row, col);
  }

  //#####################################################################################//

  private void assignMarble(int armLength, int row, int col) {
    for (int i = 0; i < armLength - 1; i++) {
      upInvalid(i);
    }

    for (int i = armLength - 1; i < armLength * 2 - 1; i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (i == row && j == col) {
          state[i][j] = SlotState.Empty;
        } else {
          state[i][j] = SlotState.Marble;
        }
      }
    }

    for (int i = armLength * 2 - 1; i < this.getBoardSize(); i++) {
      downInvalid(i);
    }
  }

  //********************************************//

  private void upInvalid(int i) {
    int invalidLeftCol = armLength - i;
    int invalidRightCol = this.getBoardSize() - invalidLeftCol;

    for (int j = 0; j < this.getBoardSize(); j++) {
      if (j < invalidLeftCol - 1 || j > invalidRightCol) {
        state[i][j] = SlotState.Invalid;
      } else if (i == row && j == col) {
        state[i][j] = SlotState.Empty;
      } else {
        state[i][j] = SlotState.Marble;
      }
    }
  }

  //********************************************//

  private void downInvalid(int i) {
    int invalidLeftCol = i - armLength - (armLength - 2);
    int invalidRightCol = this.getBoardSize() - invalidLeftCol - 1;

    for (int j = 0; j < this.getBoardSize(); j++) {
      if (j < invalidLeftCol || j > invalidRightCol) {
        state[i][j] = SlotState.Invalid;
      } else if (i == row && j == col) {
        state[i][j] = SlotState.Empty;
      } else {
        state[i][j] = SlotState.Marble;
      }
    }
  }

  //********************************************//

  @Override
  protected boolean invalidDeter(int row, int col, int armLength) {
    int invalidLeftCol = row - armLength - (armLength - 2);
    int invalidRightCol = (armLength + (armLength - 1) * 2) - invalidLeftCol - 1;
    boolean up = col < armLength - row - 1 || col > armLength + (armLength - 2) + row;
    boolean down = col < row - (armLength + (armLength - 2)) || col > invalidRightCol;

    return (row < armLength - 1 && up) || (row > armLength + (armLength - 2) && down);
  }
}

